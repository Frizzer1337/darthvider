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
	
	private static final String SELECT_ALL_USER = "SELECT id_tariff,name,price,discount,is_active,due_type,info,short_info FROM tariff";

	ConnectionPool connectionPool = ConnectionPool.getInstance();
	  
	@Override
	public List<Tariff> findAll() throws DaoException {
		
		 ArrayList<Tariff> tariffList = new ArrayList<>();
			
			try(Connection connection = connectionPool.getConnection();
			    var statement = connection.prepareStatement(SELECT_ALL_USER);
				var resultSet = statement.executeQuery();){
				 
			      while (resultSet.next()) {
			    	  
			    	  var tariff = Tariff.newBuilder()
			    			  		.setId(resultSet.getInt("id_tariff"))
			    			  		.setName(resultSet.getString("name"))
			    			  		.setPrice(resultSet.getFloat("price"))
			    			  		.setDiscount(resultSet.getInt("discount"))
			    			  		.setIsActive(resultSet.getBoolean("is_active"))
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
	public Optional<Tariff> findById(int idContract) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(int idContract, int idTariff) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idContract, int idTariff) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
