package com.roadtoepam.darthvider.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;


import com.roadtoepam.darthvider.utils.security.PasswordEncrypter;
import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.connectionpool.ProxyConnection;
import com.roadtoepam.darthvider.dao.UserDao;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;

public class UserDaoImpl implements UserDao{

	private static final String SELECT_ALL_USER = "SELECT id_user,role,balance,login,email,login,status FROM users";
	private static final String SELECT_USER_BY_ID= "SELECT id_user,role,balance,login,email,login,status FROM users WHERE id_user=?";
	private static final String ADD_USER="INSERT INTO users (role,balance,login,email,status,password) VALUES  (?, ?, ?, ?, ?,?)";
	
	String url = "jdbc:mysql://localhost:3306/provideruserdata";
    String username = "root";
    String pass = "Giraf008";
	
    ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public List<User> findAll() throws DaoException { 
        
        ArrayList<User> userList = new ArrayList<>();
		
		try(ProxyConnection connection = connectionPool.getConnection();
		    var statement = connection.prepareStatement(SELECT_ALL_USER);){
			 
		      var resultSet = statement.executeQuery(); 
		      while (resultSet.next()) {
		    	  
		    	 var user = User.newBuilder()
		    	  	  .setId(resultSet.getInt(ID_USER))
		    	  	  .setRole(resultSet.getInt(ROLE))
		    	  	  .setBalance(resultSet.getFloat(BALANCE))
		    	  	  .setEmail(resultSet.getString(EMAIL))
		    	  	  .setLogin(resultSet.getString(LOGIN))
		    	  	  .setBlockStatus(resultSet.getBoolean(STATUS))
		    	  	  .build();
		    	  	  		
		    	  
		    	 	  userList.add(user);
		    	  
		      }
		      
		      return userList;
		      
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		      
		}

	@Override
	public Optional<User> findById(int id) throws DaoException {
		
		try(var connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(SELECT_USER_BY_ID);) { 
		      
		      statement.setInt(1,id);     
		      
		      var resultSet = statement.executeQuery();
		      
		      if(resultSet.getFetchSize()>0) {
		      
		      resultSet.next();
		    	  
		      var user = User.newBuilder()
		    	  	  .setId(resultSet.getInt(ID_USER))
		    	  	  .setRole(resultSet.getInt(ROLE))
		    	  	  .setBalance(resultSet.getFloat(BALANCE))
		    	  	  .setEmail(resultSet.getString(EMAIL))
		    	  	  .setLogin(resultSet.getString(LOGIN))
		    	  	  .setBlockStatus(resultSet.getBoolean(STATUS))
		    	  	  .build();
		    	  
		      var userOptional = Optional.of(user);
		      
		      connectionPool.returnConnection(connection);
	    	  
		      return userOptional; } else {
		    	 
		    	  return Optional.empty();
		    	 
		      }
		      
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
			
	}

	@Override
	public boolean add(User user,String password) throws DaoException {
		try(var connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(ADD_USER);) { 
		      
		      statement.setLong(1,user.getRole()); 
		      statement.setFloat(2,user.getBalance()); 
		      statement.setString(3,user.getLogin()); 
		      statement.setString(4,user.getEmail()); 
		      statement.setBoolean(5,user.isBlocked());
		      statement.setString(6, PasswordEncrypter.encrypt(password));
		      
		      
		      int status = statement.executeUpdate();
		      
		      connectionPool.returnConnection(connection);
	    	  
		      return status > 0;
		      
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		} 
	}

	@Override
	public boolean update(User customer) throws DaoException {
//		try(var connection = connectionPool.getConnection();
//			var statement = connection.prepareStatement(ADD_USER);){
//			
//			
//			
//			
//			
//			
//		}
		return false;
	}

	@Override
	public boolean delete(User customer) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
