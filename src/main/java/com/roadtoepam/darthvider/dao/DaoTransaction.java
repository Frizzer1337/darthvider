package com.roadtoepam.darthvider.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.connectionpool.*;

public class DaoTransaction {

	    private static Logger logger = LogManager.getLogger();
	    private Connection connection;

	    public void initTransaction(AbstractDao dao, AbstractDao... daos) throws DaoException {
	        if (connection == null) {
	            connection = ConnectionPool.getInstance().getConnection();
	        }
	        try {
	            connection.setAutoCommit(false);
	        } catch (SQLException e) {
	            logger.error("Unable to setup autocommit: ", e);
	            throw new DaoException("Unable to setup autocommit: ", e);
	        }
	        dao.setConnection(connection);
	        for (AbstractDao daoElement : daos) {
	            daoElement.setConnection(connection);
	        }
	    }

	    public void init(AbstractDao dao) {
	        if (connection == null) {
	            connection = ConnectionPool.getInstance().getConnection();
	        }
	        dao.setConnection(connection);
	    }

	    public void endTransaction() throws DaoException {
	        if (connection == null) {
	            throw new DaoException("Connection is null.");
	        }
	        try {
	            connection.setAutoCommit(true);
	        } catch (SQLException e) {
	            logger.error("Unable to setup autocommit: ", e);
	            throw new DaoException("Unable to setup autocommit: ", e);
	        }
	        ConnectionPool.getInstance().returnConnection(connection);
	        connection = null;
	    }

	    public void end() throws DaoException {
	        if (connection == null) {
	            throw new DaoException("Connection is null.");
	        }
	      ConnectionPool.getInstance().returnConnection(connection);
	        connection = null;
	    }

	    public void commit() throws DaoException {
	        try {
	            connection.commit();
	        } catch (SQLException e) {
	            logger.error("Failed to commit: ", e);
	            throw new DaoException("Failed to commit: ", e);
	        }
	    }

	    public void rollback() throws DaoException {
	        try {
	            connection.rollback();
	        } catch (SQLException e) {
	            logger.error("Failed to rollback: ", e);
	            throw new DaoException("Failed to rollback: ", e);
	        }
	    }
}

