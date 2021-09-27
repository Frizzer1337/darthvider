package com.roadtoepam.darthvider.service;

import static com.roadtoepam.darthvider.command.RequestContent.EMAIL;
import static com.roadtoepam.darthvider.command.RequestContent.LOGIN;
import static com.roadtoepam.darthvider.command.RequestContent.PASSWORD;

import java.util.ArrayList;
import java.util.List;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.TariffDaoImpl;
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

}
