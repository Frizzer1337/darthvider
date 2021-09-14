package com.roadtoepam.darthvider.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.validator.UserValidator;
import com.roadtoepam.darthvider.util.MailSender;

public class ClientService {
	
	public void sendConfirmationEmail(long id, String email) throws ServiceException {
		
		MailSender sender = new MailSender();
		sender.sendMessage(id, email);
		
	}
	
	public int getUserIdByEmail(String email) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		int userId=-1;
		try {
			transaction.start(userDao);
			userId = userDao.findIdByEmail(email);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
		return userId;
		
		
		
	}

	public boolean isLoginOrEmailBusy(Map<String,String> userData) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.isLoginAndEmailFree(userData.get(LOGIN), userData.get(EMAIL));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
	
		return result;
	}
	
	public boolean isPasswordCorrect(Map<String,String> regData) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.login(regData.get(EMAIL), regData.get(PASSWORD));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
	
		return result;
	}
	
	public boolean addUser(Map<String,String> userData) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			User user = User.newBuilder()
							.setEmail(userData.get(EMAIL))
							.setLogin(userData.get(LOGIN))
							.setRole(0)
							.setBalance(0)
							.setBlockStatus(false)
							.build();
			result = userDao.add(user, userData.get(PASSWORD));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding user",e);
			
		}
	
		return result;
	}
	
	public boolean isPasswordRepeatedCorrect(Map<String,String> userData){
		
		boolean result = userData.get(PASSWORD).equals(userData.get(PASSWORD_REPEAT));
		
		return result;	
		
		
	}
	
	public void verify(HashMap<String, String> regData) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		try {
			transaction.start(userDao);
			int unhashedId = (Integer.parseInt(regData.get(USERID))-293)/397;
			userDao.changeRole(unhashedId, 1);
			transaction.end();
		} catch (NumberFormatException | DaoException e) {
			throw new ServiceException("Error occured while verifying email",e);
		}
	}
	
	public boolean validateEmail(Map<String,String> userData) {
		
		return UserValidator.validateEmail(userData.get(EMAIL));
		
	}
	
	public boolean validatePassword(Map<String,String> userData) {
		
		return UserValidator.validatePassword(userData.get(PASSWORD));
		
	}
	
	public boolean validateLogin(Map<String,String> userData) {
		
		return UserValidator.validateLogin(userData.get(LOGIN));
		
	}
	
	/**
	 * Validate login, email and password in this order.
	 *
	 * @param user data map
	 * @return the map of correct data(if not correct String="") and status of the validation
	 * @throws ServiceException 
	 */
	
	public Map<String,String> validationMap(Map<String,String> userData) throws ServiceException {
		
		Map<String,String> validUserData = new HashMap<>();
		
		
		if (!isPasswordRepeatedCorrect(userData))	
			validUserData.put(REQUEST_STATUS, "PASSWORD_NOT_REPEATED");
		
		if(UserValidator.validatePassword(userData.get(PASSWORD))) {
			
			validUserData.put(PASSWORD, userData.get(PASSWORD));
			
		} else { 
			validUserData.put(PASSWORD,"");
			validUserData.put(REQUEST_STATUS,"BAD_PASSWORD");
			}
		
		if(UserValidator.validateEmail(userData.get(EMAIL))) {
			
			validUserData.put(EMAIL, userData.get(EMAIL));
			
		} else { 
			validUserData.put(EMAIL,"");
			validUserData.put(REQUEST_STATUS,"BAD_EMAIL");
			}

		if(UserValidator.validateLogin(userData.get(LOGIN))) {
			
			validUserData.put(LOGIN, userData.get(LOGIN));
			
		} else { 
			validUserData.put(LOGIN,"");
			validUserData.put(REQUEST_STATUS,"BAD_LOGIN");
			}
			
		if(isLoginOrEmailBusy(userData)) {
			validUserData.put(REQUEST_STATUS, "DATA_ALREADY_EXISTS");
		}
		
		if (!validUserData.containsKey(REQUEST_STATUS)) {
			validUserData.put(REQUEST_STATUS,"SUCCESS_REGISTRATION");
		} else {
			validUserData.put(PASSWORD,"");
			validUserData.put(PASSWORD_REPEAT, "");
		}
		
		return validUserData;
		
		
			
		
		
	}
	
	

}
