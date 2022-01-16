package com.roadtoepam.darthvider.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.connectionpool.ProxyConnection;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.UserDao;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.util.security.PasswordEncrypter;

public class UserDaoImpl extends AbstractDao implements UserDao{

	private static final String SELECT_ALL_USER = "SELECT id_user,role,balance,login,email,status FROM users";
	private static final String SELECT_USER_BY_ID= "SELECT id_user,role,balance,login,email,status FROM users WHERE id_user=?";
	private static final String SELECT_USER_BY_EMAIL= "SELECT id_user,role,balance,login,email,status FROM users WHERE email=?";
	private static final String ADD_USER="INSERT INTO users (role,balance,login,email,status,password) VALUES  (?, ?, ?, ?, ?,?)";
	private static final String UPDATE_USER="UPDATE users SET role = ?,balance = ?,login = ?,email = ?,status = ?,password = ? WHERE id_user=?";
	private static final String UPDATE_USER_WITHOUT_PASSWORD="UPDATE users SET role = ?,balance = ?,login = ?,email = ?,status = ? WHERE id_user=?";
	private static final String CHANGE_LOGIN="UPDATE users SET login = ? WHERE id_user=?";
	private static final String DELETE_USER="UPDATE users SET status = 1 WHERE id_user=?";
	private static final String CHECK_LOGIN_OR_EMAIL = "SELECT id_user FROM users WHERE login=? OR email=?";
	private static final String CHECK_LOGIN = "SELECT id_user FROM users WHERE login=?";
	private static final String CHECK_EMAIL = "SELECT id_user FROM users WHERE email=?";
	private static final String CHECK_FOR_USER = "SELECT id_user FROM users WHERE (email=? AND password=?)";
	private static final String UPDATE_ROLE="UPDATE users SET role = ? WHERE id_user=?";
	
	
    ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public List<User> findAll() throws DaoException { 
        
        ArrayList<User> userList = new ArrayList<>();
		
		try(Connection connection = connectionPool.getConnection();
		    var statement = connection.prepareStatement(SELECT_ALL_USER);
			var resultSet = statement.executeQuery();){
			 
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
		
		try(Connection connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(SELECT_USER_BY_ID);) { 
		      
		      statement.setInt(1,id);     
		      
		      var resultSet = statement.executeQuery();
		     	      
		      if(resultSet.next()) {
		    	  
		      var user = User.newBuilder()
		    	  	  .setId(resultSet.getInt(ID_USER))
		    	  	  .setRole(resultSet.getInt(ROLE))
		    	  	  .setBalance(resultSet.getFloat(BALANCE))
		    	  	  .setEmail(resultSet.getString(EMAIL))
		    	  	  .setLogin(resultSet.getString(LOGIN))
		    	  	  .setBlockStatus(resultSet.getBoolean(STATUS))
		    	  	  .build();
		    	  
		      var userOptional = Optional.of(user);
	    	  
		      return userOptional; } else {
		    	 
		    	  return Optional.empty();
		    	 
		      }
		      
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
			
	}
	
	@Override
	public int findIdByEmail(String email) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);) { 
		      
		      statement.setString(1,email);     
		      
		      var resultSet = statement.executeQuery();
		     	      
		      if(resultSet.next()) {
		    	  
		      
		    	 int id = resultSet.getInt(ID_USER);
		    	 return id;
		    	  
		      } else { return -1;}
		      
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
			
	}
	
	@Override
	public boolean isLoginFree(String login) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(CHECK_LOGIN);){
			
			statement.setString(1,login);
			
			ResultSet resultSet = statement.executeQuery();
			
		    return resultSet.next();
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}
	
	@Override
	public boolean isLoginAndEmailFree(String login, String email) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(CHECK_LOGIN_OR_EMAIL);){
			
			statement.setString(1,login);
			statement.setString(2, email);
			
			ResultSet resultSet = statement.executeQuery();
			
		    return resultSet.next();
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}
	
	@Override
	public boolean changeLogin(String login, long id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(CHANGE_LOGIN);){
			
			statement.setString(1,login);
			statement.setLong(2, id);
			
			int status = statement.executeUpdate();
			
		    return status>0;
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}
	
	@Override
	public boolean changeRole(long unhashedId, int roleId) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(UPDATE_ROLE);){
			
		statement.setInt(1, roleId);	
		statement.setLong(2, unhashedId);	
		
		 int status = statement.executeUpdate();
			
	     return status>0;
	     
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
		
	}
	
	@Override
	public boolean login(String email, String password) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(CHECK_FOR_USER);){
			
			statement.setString(1,email);
			statement.setString(2, PasswordEncrypter.encrypt(password));
			
			ResultSet resultSet = statement.executeQuery();
			
		    return resultSet.next();
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}

	@Override
	public boolean add(User user,String password) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(ADD_USER);) { 
		      
		      statement.setLong(1,user.getRole()); 
		      statement.setFloat(2,user.getBalance()); 
		      statement.setString(3,user.getLogin()); 
		      statement.setString(4,user.getEmail()); 
		      statement.setBoolean(5,user.isBlocked());
		      statement.setString(6, PasswordEncrypter.encrypt(password));
		      
		      
		      int status = statement.executeUpdate();
	    	  
		      return status > 0;
		      
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		} 
	}
	
	@Override
	public boolean update(User user,int id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(UPDATE_USER_WITHOUT_PASSWORD);){
			
			  statement.setLong(1,user.getRole()); 
		      statement.setFloat(2,user.getBalance()); 
		      statement.setString(3,user.getLogin()); 
		      statement.setString(4,user.getEmail()); 
		      statement.setBoolean(5,user.isBlocked());
		      statement.setLong(6,id);
		      
		      int status = statement.executeUpdate();
			
		  	  return status>0;
		      
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}

	@Override
	public boolean update(User user,String password,int id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(UPDATE_USER);){
			
			  statement.setLong(1,user.getRole()); 
		      statement.setFloat(2,user.getBalance()); 
		      statement.setString(3,user.getLogin()); 
		      statement.setString(4,user.getEmail()); 
		      statement.setBoolean(5,user.isBlocked());
		      statement.setString(6, PasswordEncrypter.encrypt(password));
		      statement.setLong(7,id);
		      
		      int status = statement.executeUpdate();
			
		      return status>0;
		      
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}

	@Override
	public boolean delete(int id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(DELETE_USER);){
			
			statement.setInt(1,id);
			
			int status = statement.executeUpdate();
			
		    return status>0;
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}

}
