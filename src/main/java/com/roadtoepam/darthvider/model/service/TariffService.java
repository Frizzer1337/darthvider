package com.roadtoepam.darthvider.model.service;

import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.dao.DaoTransaction;
import com.roadtoepam.darthvider.model.dao.impl.ConnectedTariffDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.TariffDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.model.entity.Tariff;
import com.roadtoepam.darthvider.model.entity.User;


public class TariffService {
	
	/**
	 * Gets all tariffs.
	 *
	 * @return the list of tariffs
	 * @throws ServiceException if any happens
	 */
	public List<Tariff> getAllTariff() throws ServiceException{
		
		ArrayList<Tariff> tariffList = new ArrayList<>();
		
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		try {
			transaction.start(tariffDao);
			tariffList = (ArrayList<Tariff>) tariffDao.findAll();
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding user",e);
			
		}
		
		return tariffList;
		
	}
	
	/**
	 * Adds the tariff to database.
	 *
	 * @param map of tariff data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean addTariff(Map<String,String> dataMap) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(tariffDao);
			Tariff tariff = Tariff.newBuilder()
								  .setName(dataMap.get(TARIFFNAME))
								  .setPrice(Float.parseFloat(dataMap.get(TARIFFPRICE)))
								  .setDiscount(Integer.parseInt(dataMap.get(TARIFFDISCOUNT)))
								  .setStatus(1)
								  .setDueType(Integer.parseInt(dataMap.get(TARIFFDUE)))
								  .setInfo(dataMap.get(TARIFFINFO))
								  .setShortInfo(dataMap.get(TARIFFSHORTINFO))
								  .build();
			
			result = tariffDao.add(tariff);
			transaction.end();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change price of tariff.
	 *
	 * @param id
	 * @param price
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changePrice(int id,float price) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(tariffDao);
			result = tariffDao.changePrice(id,price);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}

	/**
	 * Block tariff.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean blockTariff(int id) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(tariffDao);
			result = tariffDao.delete(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Checks due type of tariff in database.
	 *
	 * @param id
	 * @return due type
	 * @throws ServiceException if any happens
	 */
	public int checkDueType(int id) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		int result = -1;
		try {
			transaction.start(tariffDao);
			result = tariffDao.checkDueType(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Unlock tariff in database.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean unlockTariff(int id) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(tariffDao);
			result = tariffDao.unlock(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Deletes connected with this tariff contracts.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean deleteConnectedContracts(int id) throws ServiceException {
		final ConnectedTariffDaoImpl connectedDao = new ConnectedTariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(connectedDao);
			result = connectedDao.deleteByTariff(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}

	/**
	 * Gets the tariff optional by id.
	 *
	 * @param id
	 * @return optional of tariff
	 * @throws ServiceException if any happens
	 */
	public Optional<Tariff> getTariffById(int id) throws ServiceException {
		final TariffDaoImpl tariffDao = new TariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		Optional<Tariff> tariff = Optional.empty();
		try {
			transaction.start(tariffDao);
			tariff = tariffDao.findById(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return tariff;
	}


}
