package com.roadtoepam.darthvider.dao.impl;

import static com.roadtoepam.darthvider.dao.impl.UserColumnName.*;
import static com.roadtoepam.darthvider.dao.impl.TariffColumnName.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.ConnectedTariffDao;
import com.roadtoepam.darthvider.entity.ConnectedTariff;
import com.roadtoepam.darthvider.exception.DaoException;

public class ConnectedTariffDaoImpl extends AbstractDao implements ConnectedTariffDao{
	
	private static final String SELECT_ALL_CONNECTIONS = "SELECT id_contract,id_tariff FROM connect";
	private static final String SELECT_CONNECTION_BY_ID= "SELECT id_contract,id_tariff FROM connect WHERE id_contract=?";
	private static final String ADD_CONNECTION="INSERT INTO connect (id_contract,id_tariff) VALUES  (?, ?)";
	private static final String DELETE_CONNECTION="DELETE FROM connect WHERE id_contract=? AND id_tariff=?";
	private static final String DELETE_CONNECTION_BY_CONTRACT_ID="DELETE FROM connect WHERE id_contract=?";
	private static final String DELETE_CONNECTION_BY_TARIFF_ID="DELETE FROM connect WHERE id_tariff=?";
	
	
	 ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public List<ConnectedTariff> findAll() throws DaoException {
		 ArrayList<ConnectedTariff> connectedList = new ArrayList<>();
			
			try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_ALL_CONNECTIONS);
				var resultSet = statement.executeQuery();){	
				
				  int tariffId = -1;
				  int contractId = -1;
				  int previousContractId = -1;
				  int previousTariffId = -1;
				  var connect = new ConnectedTariff();
				  HashMap<Integer,ArrayList<Integer>> connectMap = new HashMap<>();
				  ArrayList<Integer> list = new ArrayList<>();
				
			    while (true) {
			    	  
			    if (resultSet.next() && contractId == previousContractId) {
			      
			    	  
			    	  
			    	  contractId = resultSet.getInt(ID_CONTRACT);
			    	  tariffId = resultSet.getInt(ID_TARIFF);
			    	  previousContractId = previousContractId < 0 ? contractId:previousContractId;
			    	  
			    		 
			    		 list.add(tariffId);
			    		
			    		 
			    	  
			    	  
			      	} else {
				    	
				    		 connectMap.put(previousContractId, list);
				    		 connect.setContractInfo(connectMap); 	
				    		 previousContractId = contractId;
					    	 connectedList.add(connect);
				    		 connectMap = new HashMap<>();
				    		 list = new ArrayList<>();
				    		 list.add(tariffId);
				    		 if (!resultSet.next()) {break;}
				    		 
			      	}
			     }
			      
			      return connectedList;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			      
	}

	@Override
	public Optional<ConnectedTariff> findById(int idContract) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_CONNECTION_BY_ID);){
				
			    
			    statement.setInt(1, idContract);
				
			    var resultSet = statement.executeQuery();
			    
			      if(resultSet.next()) {
			    	  
			    	  var connect = new ConnectedTariff();
			    	  
			    	  int contractId = resultSet.getInt(ID_CONTRACT);
			    	  int tariffId = resultSet.getInt(ID_TARIFF);
			    	  int previousContractId = contractId;
			    	  ArrayList<Integer> list = new ArrayList<>();
			    	  list.add(tariffId);
			    	  
			    	  while (contractId==previousContractId) {
			    					    		  
			    		  if(resultSet.next()) {
			    			  
			    			  contractId=resultSet.getInt(ID_CONTRACT);
			    			  list.add(resultSet.getInt(ID_TARIFF));
			    			  
			    			  
			    			  
			    		  } else { break; }	    		  
			    		  
			    	  }
			    	  
			    	  HashMap<Integer,ArrayList<Integer>> connectMap = new HashMap<>();
			    	  
			    	  connectMap.put(contractId, list);
			    	  	    	  
			    	  connect.setContractInfo(connectMap); 		
			    
			    	  return Optional.of(connect); } else {
			    		  
			    		  return Optional.empty();
			    	  }
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			      
	}

	@Override
	public boolean add(int idContract, int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(ADD_CONNECTION);){
			
			statement.setInt(1, idContract);
			statement.setInt(2, idTariff);
			
			int status = statement.executeUpdate();
			
			return status>0;
		
			
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	@Override
	public boolean delete(int idContract, int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(DELETE_CONNECTION);){
			
			statement.setInt(1, idContract);
			statement.setInt(2, idTariff);
			
			int status = statement.executeUpdate();
			
			return status>0;
		
			
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean deleteByContract(int idContract) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(DELETE_CONNECTION_BY_CONTRACT_ID);){
			
			statement.setInt(1, idContract);
			
			int status = statement.executeUpdate();
			
			return status>0;
			
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}

	@Override
	public boolean deleteByTariff(int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(DELETE_CONNECTION_BY_TARIFF_ID);){
			
			statement.setInt(1, idTariff);
			
			int status = statement.executeUpdate();
			
			return status>0;
			
		} catch (SQLException e) {
			
			throw new DaoException(e);
			
		}
	}
	
	

}
