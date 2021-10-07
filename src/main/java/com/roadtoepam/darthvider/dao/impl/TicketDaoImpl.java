package com.roadtoepam.darthvider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.TicketDao;
import com.roadtoepam.darthvider.exception.DaoException;

import static com.roadtoepam.darthvider.dao.impl.TicketColumnName.*;


public class TicketDaoImpl extends AbstractDao implements TicketDao {

	private static final String ADD_TICKET="INSERT INTO tickets(authorEmail,authorProblem,authorLastname,authorFirstname) VALUES (?,?,?,?)";
	private static final String SELECT_LAST_ID="SELECT LAST_INSERT_ID()";
	
	
    ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public int add(String email, String problem, String lastname, String name) throws DaoException {
		
			try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(ADD_TICKET, PreparedStatement.RETURN_GENERATED_KEYS);) { 
			      
			     statement.setString(1,email);
			     statement.setString(2, problem);
			     statement.setString(3, name);
			     statement.setString(4, lastname);
		    	 
			     statement.executeUpdate();
			     
			     ResultSet resultSet = statement.getGeneratedKeys();
			     
			     int result = -1;
			     
			     if (resultSet.next()) { result = resultSet.getInt(1); }
			     
			     return result;
			      
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			} 
		}	
		
}
	
	

