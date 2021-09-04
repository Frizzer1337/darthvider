package com.roadtoepam.darthvider.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.roadtoepam.darthvider.exception.DaoException;

public abstract class AbstractDao{

    private static final Logger logger = LogManager.getLogger();

    protected Connection connection;
    
    public void closeStatement(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            } else {
                logger.warn("Statement is null.");
            }

        } catch (SQLException e) {
            logger.error("Statement can't be closed.", e);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
           logger.error("Connection can't be moved back in pool.", e);

        }
    }

}