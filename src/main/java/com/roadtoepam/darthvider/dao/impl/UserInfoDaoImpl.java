package com.roadtoepam.darthvider.dao.impl;

import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.UserInfoDao;
import com.roadtoepam.darthvider.entity.UserInfo;
import com.roadtoepam.darthvider.exception.DaoException;

public class UserInfoDaoImpl extends AbstractDao implements UserInfoDao{
	

	private static final String SELECT_ALL_USER_INFO = "SELECT id_user,name,surname,city,phone FROM user_info";
	private static final String SELECT_USER_INFO_BY_ID= "SELECT id_user,name,surname,city,phone FROM user_info WHERE id_user=?";
	private static final String ADD_USER_INFO="INSERT INTO user_info (name,surname,city,phone) VALUES  (?, ?, ?, ?)";
	private static final String ADD_USER_MAIN_INFO="INSERT INTO user_info (name,surname) VALUES  (?, ?)";
	private static final String UPDATE_USER_INFO="UPDATE users SET name=?,surname=?,city=?,phone = ? WHERE id_user=?";
	private static final String CHECK_USER_INFO_BY_ID="SELECT 1 FROM user_info WHERE id_user = ? LIMIT 1";
	
	ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public List<UserInfo> findAll() throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_ALL_USER_INFO);
				var resultSet = statement.executeQuery();){
			
			 ArrayList<UserInfo> userInfoList = new ArrayList<>();
			
			      while (resultSet.next()) {
			    	  
			    	 var userInfo = UserInfo.newBuilder()
			    		  .setId(resultSet.getInt(ID_USER))
			    	  	  .setName(resultSet.getString(NAME))
			    	  	  .setSurname(resultSet.getString(SURNAME))
			    	  	  .setCity(resultSet.getString(CITY))
			    	  	  .setPhone(resultSet.getString(PHONE))
			    	  	  .build();
			    	  	  		
			    	  
			    	 	  userInfoList.add(userInfo);
			    	  
			      }
			      
			      return userInfoList;
			} catch (SQLException e) {
			
				throw new DaoException(e);
			}
	}

	@Override
	public Optional<UserInfo> findById(int id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_USER_INFO_BY_ID);){
			
			 statement.setLong(1,id);     
		      
		     var resultSet = statement.executeQuery();
			
			 if (resultSet.next()) {
			    	  
			    	 var userInfo = UserInfo.newBuilder()
			    		  .setId(resultSet.getInt(ID_USER))
			    	  	  .setName(resultSet.getString(NAME))
			    	  	  .setSurname(resultSet.getString(SURNAME))
			    	  	  .setCity(resultSet.getString(CITY))
			    	  	  .setPhone(resultSet.getString(PHONE))
			    	  	  .build();
			    	  	  		
			    	  
			    	var userInfoOptional = Optional.of(userInfo);
			      
			    	return userInfoOptional; 
			 
			 } else {
			    	  
			    	  return Optional.empty();
			    	  
			      }
			    
			} catch (SQLException e) {
			
				throw new DaoException(e);
			}
	}

	@Override
	public boolean add(UserInfo userInfo, String city, String phone) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(ADD_USER_INFO);){
			
			 statement.setString(1,userInfo.getName()); 
		     statement.setString(2,userInfo.getSurname()); 
		     statement.setString(3,city); 
		     statement.setString(4,phone); 
		      
		      int status = statement.executeUpdate();
	    	  
		      return status > 0;
			
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}

	@Override
	public boolean addMainInfo(UserInfo userInfo, String city, String phone) throws DaoException {
			try(Connection connection = connectionPool.getConnection();
				    var statement = connection.prepareStatement(ADD_USER_MAIN_INFO);){
				
				 statement.setString(1,userInfo.getName()); 
			     statement.setString(2,userInfo.getSurname()); 
			      
			      int status = statement.executeUpdate();
		    	  
			      return status > 0;
				
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			}
		}

	@Override
	public boolean update(UserInfo userInfo, int id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
		 var statement = connection.prepareStatement(UPDATE_USER_INFO);){
			
			 statement.setString(1,userInfo.getName()); 
		     statement.setString(2,userInfo.getSurname()); 
		     statement.setString(3,userInfo.getCity()); 
		     statement.setString(4,userInfo.getPhone()); 
		     statement.setInt(5, id);
		      
		      int status = statement.executeUpdate();
	    	  
		      return status > 0;
			
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}

	@Override
	public boolean checkById(long id) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				 var statement = connection.prepareStatement(CHECK_USER_INFO_BY_ID);){
					
					 statement.setLong(1,id);
				      
				      ResultSet resultSet = statement.executeQuery();
			    	  
				      return resultSet.next();
					
				} catch (SQLException e) {
					
					throw new DaoException(e);
					
				}
	}

}
