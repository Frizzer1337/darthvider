package com.roadtoepam.darthvider.connectionpool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.DaoException;

public class ConnectionPool {

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> activeConnections;

    private static AtomicBoolean doesExist = new AtomicBoolean(false);
    
    private static final int DEFAULT_AMOUNT = 20; 
    
    private static ConnectionPool pool;

    static final Logger logger = LogManager.getLogger();

    private int amountOfConnections;

    private ConnectionPool() {
            
    		amountOfConnections = DEFAULT_AMOUNT;
    try {
    	
            freeConnections = new LinkedBlockingDeque<>(amountOfConnections);
            activeConnections = new LinkedBlockingDeque<>();
            
            ConnectionFactory factory = ConnectionFactory.getInstance();
            
            for (int i = 0; i < amountOfConnections; i++) {
                
            	ProxyConnection connection = new ProxyConnection(factory.getConnection());
                freeConnections.add(connection);
            }
            
            logger.info("Connection pool size of {} elements was created ",amountOfConnections);
           
    		} catch (SQLException e) {
        	
        	logger.fatal("Error happend during connection pool creation", e);
            throw new RuntimeException(e);
            
        }

    }

    public static ConnectionPool getInstance(){
    	
    	while (pool == null) {
    		
    		if (doesExist.compareAndSet(false,true)) {
    			
					pool = new ConnectionPool();
    			
    		}
    	}
    		
        return pool;
    }

    public Connection getConnection() {
    	
        ProxyConnection connection = null;
        
        try {
         	
            connection = freeConnections.take();
            activeConnections.put(connection);
            
        } catch (InterruptedException e) {
        	
            logger.error("Connection is interrupted",e);
            Thread.currentThread().interrupt();
        }
        
        return connection;
    }

   public void returnConnection(Connection connection) {
  
        try {
        	
            if(activeConnections.remove(connection)) {
            	
            	freeConnections.put((ProxyConnection) connection);
            	
            } else {
            	
            	logger.warn("Connection wasn't removed from free connections");
            			
            }
            
            } catch (InterruptedException e) {
            	
            	logger.error("Thread " + Thread.currentThread().getName()+" was interrupted",e);
                Thread.currentThread().interrupt();
                
            }
    }

    public void closeAllConnections() {
        for (int i = 0; i < amountOfConnections; i++) {
            try {
                ProxyConnection connection = freeConnections.take();
                connection.reallyClose();
            } catch (InterruptedException e) {
            	logger.error("Thread " + Thread.currentThread().getName()+" was interrupted",e);
               	Thread.currentThread().interrupt();
            } catch (SQLException e) {
				
			}
        }
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Error while closing driver", e);
            }
        });
    }

}

