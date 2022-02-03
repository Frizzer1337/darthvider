package com.roadtoepam.darthvider.dao.impl;

import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.UserContractDao;
import com.roadtoepam.darthvider.entity.UserContract;
import com.roadtoepam.darthvider.exception.DaoException;

public class UserContractDaoImpl extends AbstractDao implements UserContractDao{
	
	private static final String SELECT_ALL_CONTRACT = "SELECT id_user,id_contract,start_date,end_date,is_active,discount FROM user_contract";
	private static final String SELECT_CONTRACT_BY_ID= "SELECT id_user,id_contract,start_date,end_date,is_active,discount FROM user_contract WHERE id_user=?";
	private static final String SELECT_CONTRACT_ID_BY_ID= "SELECT id_contract FROM user_contract WHERE id_user=?";
	private static final String ADD_CONTRACT="INSERT INTO user_contract (id_user,id_contract,start_date,end_date,is_active,discount) VALUES  (?, ?, ?, ?, ?,?)";
	private static final String UPDATE_CONTRACT="UPDATE user_contract SET id_contract = ?,start_date = ?,end_date = ?,is_active = ?,discount = ? WHERE id_user = ?";
	private static final String ACTIVATE_CONTRACT="UPDATE user_contract SET is_active = 1 WHERE id_user=?";
	private static final String DEACTIVATE_CONTRACT="UPDATE user_contract SET is_active = 0 WHERE id_user=?";
	
	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public List<UserContract> findAll() throws DaoException {
		
		ArrayList<UserContract> userContractList = new ArrayList<>();
		
		try(Connection connection = connectionPool.getConnection();
		    var statement = connection.prepareStatement(SELECT_ALL_CONTRACT);
			var resultSet = statement.executeQuery();){
			 
		      while (resultSet.next()) {
		    	  
		    	 var contract = UserContract.newBuilder()
		    	  	  .setId(resultSet.getInt(ID_USER))
		    	  	  .setIdContract(resultSet.getInt(ID_CONTRACT))
		    	  	  .setStartDate(resultSet.getDate(START_DATE))
		    	  	  .setEndDate(resultSet.getDate(END_DATE))
		    	  	  .setIsActive(resultSet.getBoolean(IS_ACTIVE))
		    	  	  .setDiscount(resultSet.getInt(DISCOUNT))
		    	  	  .build();
		    	 
		    	  	  		
		    	  
		    	 	  userContractList.add(contract);
		    	  
		      }
		      
		      return userContractList;
		      
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		      
		}
	
	@Override
	public Optional<UserContract> findContractByUserId(int id) throws DaoException {


		try(Connection connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(SELECT_CONTRACT_BY_ID);) { 
		      
		      statement.setLong(1,id);     
		      
		      var resultSet = statement.executeQuery();
		     	      
		      if(resultSet.next()) {
		    	  
		      var contract = UserContract.newBuilder()
		    	  	  .setId(resultSet.getInt(ID_USER))
		    	  	  .setIdContract(resultSet.getInt(ID_CONTRACT))
		    	  	  .setStartDate(resultSet.getDate(START_DATE))
		    	  	  .setEndDate(resultSet.getDate(END_DATE))
		    	  	  .setIsActive(resultSet.getBoolean(IS_ACTIVE))
		    	  	  .setDiscount(resultSet.getInt(DISCOUNT))
		    	  	  .build();
		    	  
		      var contractOptional = Optional.of(contract);
	    	  
		      return contractOptional; } else {
		    	 
		    	  return Optional.empty();
		    	 
		      }
		      
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
			
	}
	
	@Override
	public int findContractIdByUserId(int id) throws DaoException {


		try(Connection connection = connectionPool.getConnection();
			var statement = connection.prepareStatement(SELECT_CONTRACT_ID_BY_ID);) { 
		    
			 statement.setLong(1,id); 
			
			 var resultSet = statement.executeQuery();
			 
    	      
		      if(resultSet.next()) {
		    	  
		      
		    	 int contractId = resultSet.getInt(ID_CONTRACT);
		    	 return contractId;
		    	  
		      } else { return -1;}
		      
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
			
	}

	@Override
	public boolean add(UserContract contract) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(ADD_CONTRACT);) { 
			      
			      statement.setLong(1,contract.getId()); 
			      statement.setLong(2,contract.getIdContract()); 
			      statement.setDate(3,contract.getStartDate()); 
			      statement.setDate(4,contract.getEndDate()); 
			      statement.setBoolean(5,contract.isActive());
			      statement.setInt(6, contract.getDiscount());
			      
			      
			      int status = statement.executeUpdate();
		    	  
			      return status > 0;
			      
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			} 
	}

	@Override
	public boolean update(UserContract contract, int id) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(UPDATE_CONTRACT);) { 
			      
			      statement.setLong(1,contract.getId()); 
			      statement.setLong(2,contract.getIdContract()); 
			      statement.setDate(3,contract.getEndDate()); 
			      statement.setDate(4,contract.getStartDate()); 
			      statement.setBoolean(5,contract.isActive());
			      statement.setInt(6, contract.getDiscount());
			      
			      
			      int status = statement.executeUpdate();
		    	  
			      return status > 0;
			      
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			}
		
	}

	@Override
	public boolean activateContract(int id) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(ACTIVATE_CONTRACT);) { 
			      
			      statement.setInt(1,id); 
			      
			      int status = statement.executeUpdate();
		    	  
			      return status > 0;
			      
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			}
		
	}
	
	@Override
	public boolean deactivateContract(int id) throws DaoException {
		
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(DEACTIVATE_CONTRACT);) { 
			      
			      statement.setInt(1,id); 
			      
			      int status = statement.executeUpdate();
		    	  
			      return status > 0;
			      
			} catch (SQLException e) {
				
				throw new DaoException(e);
				
			}
		
	}

}
