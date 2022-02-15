package com.roadtoepam.darthvider.model.connectionpool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.DaoException;

public class ConnectionFactory {
    private static final Logger logger= LogManager.getLogger();
    private static String url;
    private static final String resource = "app.properties";
    private static final Properties appProperties = new Properties();
    private String driver;
    private static ConnectionFactory factory;
    private static AtomicBoolean doesExist = new AtomicBoolean(false);

    public static ConnectionFactory getInstance() {
    	
    
    	while (factory == null) {
		
    		if (doesExist.compareAndSet(false,true)) {
    			
    			factory = new ConnectionFactory();
			
		}
	}
		
    return factory;
    
    }

   private ConnectionFactory ()  {
        try(InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(resource)){
        	
            appProperties.load(inputStream);
            
            driver = appProperties.getProperty("driverName");
            Class.forName(driver);
            
            url = appProperties.getProperty("url");
            
        } catch (IOException e){
        	
            logger.fatal("Error occured while getting:{}",resource);
            throw new RuntimeException("Error occured while getting database properties", e);
            
        } catch (ClassNotFoundException e){
        	
            logger.fatal("Error occured while loading driver class:{}",driver);
            throw new RuntimeException("Error occured while loading driver class", e);
        }
    }
    

    public Connection getConnection() throws SQLException {
    	
            return DriverManager.getConnection(url,appProperties);
            
    }
}