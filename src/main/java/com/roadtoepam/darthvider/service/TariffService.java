package com.roadtoepam.darthvider.service;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.ConnectedTariffDaoImpl;
import com.roadtoepam.darthvider.dao.impl.TariffDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;

public class TariffService {
	
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
