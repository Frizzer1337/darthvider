package com.roadtoepam.darthvider.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.connectionpool.ConnectionPool;
import com.roadtoepam.darthvider.dao.AbstractDao;
import com.roadtoepam.darthvider.dao.TariffDao;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.exception.DaoException;

public class TariffDaoImpl extends AbstractDao implements TariffDao {
	
	private static final String SELECT_ALL_TARIFFS = "SELECT id_tariff,name,price,discount,is_active,due_type,info,short_info FROM tariff";
	private static final String SELECT_TARIFF_BY_ID = "SELECT id_tariff,name,price,discount,is_active,due_type,info,short_info FROM tariff WHERE id_tariff=?";
	private static final String ADD_TARIFF="INSERT INTO tariff (name,price,discount,is_active,due_type,info,short_info) VALUES  (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_DUE = "SELECT due_type FROM tariff WHERE id_tariff=?";
	private static final String DELETE_TARIFF="UPDATE tariff SET is_active = 0 WHERE id_tariff=?";
	private static final String UNLOCK_TARIFF="UPDATE tariff SET is_active = 1 WHERE id_tariff=?";
	private static final String CHANGE_PRICE="UPDATE tariff SET price = ? WHERE id_tariff=?";

	ConnectionPool connectionPool = ConnectionPool.getInstance();
	  
	@Override
	public List<Tariff> findAll() throws DaoException {
		
		 ArrayList<Tariff> tariffList = new ArrayList<>();
			
			try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_ALL_TARIFFS);
				var resultSet = statement.executeQuery();){
				 
			      while (resultSet.next()) {
			    	  
			    	  var tariff = Tariff.newBuilder()
			    			  		.setId(resultSet.getInt("id_tariff"))
			    			  		.setName(resultSet.getString("name"))
			    			  		.setPrice(resultSet.getFloat("price"))
			    			  		.setDiscount(resultSet.getInt("discount"))
			    			  		.setStatus(resultSet.getInt("is_active"))
			    			  		.setDueType(resultSet.getInt("due_type"))
			    			  		.setInfo(resultSet.getString("info"))
			    			  		.setShortInfo(resultSet.getString("short_info")) 
			    			  		.build();
			    	  
			    	  tariffList.add(tariff);
			    			  	   
			    	  
			      }
			      
			      return tariffList;
			      
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			      
		
	}

	@Override
	public Optional<Tariff> findById(int id) throws DaoException {
				
				try(Connection connection = connectionPool.getConnection();
				    var statement = connection.prepareStatement(SELECT_TARIFF_BY_ID);){
					
					statement.setInt(1, id);
					
					var resultSet = statement.executeQuery();
					
				    if (resultSet.next()) {
				    	  
				    	  var tariff = Tariff.newBuilder()
				    			  		.setId(resultSet.getInt("id_tariff"))
				    			  		.setName(resultSet.getString("name"))
				    			  		.setPrice(resultSet.getFloat("price"))
				    			  		.setDiscount(resultSet.getInt("discount"))
				    			  		.setStatus(resultSet.getInt("is_active"))
				    			  		.setDueType(resultSet.getInt("due_type"))
				    			  		.setInfo(resultSet.getString("info"))
				    			  		.setShortInfo(resultSet.getString("short_info")) 
				    			  		.build();
				    	  
				    	  return Optional.of(tariff); 	   
				    	  
				      }	else {
				    	  return Optional.empty();
				      }
				      
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				      
			
		}

	@Override
	public boolean add(Tariff tariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(ADD_TARIFF);) { 
		
		statement.setString(1, tariff.getName());
		statement.setFloat(2, tariff.getPrice());
		statement.setInt(3, tariff.getDiscount());
		statement.setInt(4, tariff.getStatus());
		statement.setInt(5, tariff.getDueType());
		statement.setString(6, tariff.getInfo());
		statement.setString(7, tariff.getShortInfo());

		 int status = statement.executeUpdate();

	     return status > 0;
	      
	} catch (SQLException e) {
		
		throw new DaoException(e);
		
		} 
	}
	
	@Override
	public int checkDueType(int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(SELECT_DUE);){
			
			statement.setInt(1,idTariff);
			var resultSet = statement.executeQuery();
			
			int dueType = -1;
			
			while (resultSet.next()) {
					dueType = resultSet.getInt("due_type");
			}
			
		    return dueType;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public boolean changePrice(int idTariff,float price) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(CHANGE_PRICE);){
			
			statement.setFloat(1,price);
			statement.setInt(2,idTariff);
			
			int status = statement.executeUpdate();
			
		    return status>0;
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}
	
	@Override
	public boolean unlock(int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(UNLOCK_TARIFF);){
			
			statement.setInt(1,idTariff);
			
			int status = statement.executeUpdate();
			
		    return status>0;
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}

	@Override
	public boolean delete(int idTariff) throws DaoException {
		try(Connection connection = connectionPool.getConnection();
				var statement = connection.prepareStatement(DELETE_TARIFF);){
			
			statement.setInt(1,idTariff);
			
			int status = statement.executeUpdate();
			
		    return status>0;
			
		} catch (SQLException e) {
		
		throw new DaoException(e);
		
		}
	}

}
