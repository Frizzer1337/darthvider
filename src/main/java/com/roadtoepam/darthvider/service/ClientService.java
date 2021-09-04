package com.roadtoepam.darthvider.service;

import java.util.Map;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.service.validator.UserValidator;

public class ClientService {
	
	private final UserDaoImpl userDao = new UserDaoImpl();
	private final DaoTransaction transaction = new DaoTransaction();

	public boolean checkForLoginAndEmail(Map<String,String> userData) throws DaoException {
		
		transaction.init(userDao);
		boolean result = userDao.checkUser(userData.get(LOGIN), userData.get(EMAIL));
		transaction.end();
	
		return result;
	}
	
	public boolean checkPasswordRepeat(Map<String,String> userData){
		
		boolean result = userData.get(PASSWORD).equals(PASSWORD_REPEAT);
		
		return result;	
		
		
	}
	
	public boolean validateEmail(Map<String,String> userData) {
		
		return UserValidator.validateEmail(userData.get(EMAIL));
		
	}
	
	public boolean validatePassword(Map<String,String> userData) {
		
		return UserValidator.validateEmail(userData.get(PASSWORD));
		
	}
	
	public boolean validateLogin(Map<String,String> userData) {
		
		return UserValidator.validateEmail(userData.get(LOGIN));
		
	}
	
	

}
