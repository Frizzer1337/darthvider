package com.roadtoepam.darthvider.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.ConnectedTariffDaoImpl;
import com.roadtoepam.darthvider.dao.impl.TicketDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserContractDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.dao.impl.UserInfoDaoImpl;
import com.roadtoepam.darthvider.entity.ConnectedTariff;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.entity.UserContract;
import com.roadtoepam.darthvider.entity.UserInfo;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.validator.UserValidator;
import com.roadtoepam.darthvider.util.MailSender;
import com.roadtoepam.darthvider.util.security.EmailHasher;

public class ClientService {
	
	public void sendConfirmationEmail(long id, String email) throws ServiceException {
		
		MailSender sender = new MailSender();
		sender.sendMessage(id, email);
		
	}
	
	public Optional<User> getUserByEmail(String email) throws ServiceException{
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		Optional<User> user = Optional.empty();
		try {
			transaction.start(userDao);
			int userId = getUserIdByEmail(email);
			user = userDao.findById(userId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while getting user info by id",e);
			
		}
		return user;
		
	}
	public Optional<UserInfo> getUserInfoByEmail(String email) throws ServiceException{
		
		final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		Optional<UserInfo> userInfo = Optional.empty();
		try {
			transaction.start(userInfoDao);
			int userId = getUserIdByEmail(email);
			userInfo = userInfoDao.findById(userId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while getting user info by id",e);
			
		}
		return userInfo;
		
	}
	
	public Optional<UserContract> getUserContractByEmail(String email) throws ServiceException{
		
		final UserContractDaoImpl userContractDao = new UserContractDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		Optional<UserContract> userContract = Optional.empty();
		try {
			transaction.start(userContractDao);
			int userId = getUserIdByEmail(email);
			userContract = userContractDao.findContractByUserId(userId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while getting user info by id",e);
			
		}
		return userContract;
		
	}
	
	public Optional<ConnectedTariff> getUserTariffByEmail(String email) throws ServiceException{
		
		final UserContractDaoImpl userContractDao = new UserContractDaoImpl();
		final ConnectedTariffDaoImpl connectedTariffDao = new ConnectedTariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		int userContractId;
		Optional<ConnectedTariff> userTariff = Optional.empty();

		try {
			transaction.start(userContractDao,connectedTariffDao);
			int userId = getUserIdByEmail(email);
			userContractId = userContractDao.findContractIdByUserId(userId);
			userTariff = connectedTariffDao.findById(userContractId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while getting user info by id",e);
			
		}
		return userTariff;
		
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
	
	public boolean isLoginBusy(Map<String,String> userData) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.isLoginFree(userData.get(LOGIN));
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
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	public boolean changeLogin(Map<String, String> validData) throws ServiceException {
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changeLogin(validData.get(LOGIN),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
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
	
	public boolean checkCabinet(long id) throws ServiceException {
		
		final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		
		try {
			transaction.start(userInfoDao);
			result = userInfoDao.checkById(id);
			transaction.end();
		}  catch (DaoException e) {
			throw new ServiceException("Error occured while checking if cabinet exist",e);
		}
		
		return result;
	}
	
	public boolean addCabinet(Map<String, String> validData, long id) throws ServiceException {
		
		final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		
		try {
			transaction.start(userInfoDao);
			UserInfo userInfo = UserInfo.newBuilder()
										.setName(validData.get(FIRSTNAME))
										.setCity(validData.get(CITY))
										.setSurname(validData.get(LASTNAME))
										.setPhone(validData.get(PHONE))
										.build();
			result = userInfoDao.add(userInfo,id);
			transaction.end();
		}  catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error occured while adding user info",e);
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
			var hasher = new EmailHasher();
			long unhashedId = hasher.decodeId(Integer.parseInt(regData.get(USERID)));
			userDao.changeRole(unhashedId, 1);
			transaction.end();
		} catch (NumberFormatException | DaoException e) {
			throw new ServiceException("Error occured while verifying email",e);
		}
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
		
		var userValidator = new UserValidator();
		
		if (!isPasswordRepeatedCorrect(userData))	
			validUserData.put(REQUEST_STATUS, "PASSWORD_NOT_REPEATED");
		
		if(userValidator.validatePassword(userData.get(PASSWORD))) {
			
			validUserData.put(PASSWORD, userData.get(PASSWORD));
			
		} else { 
			validUserData.put(PASSWORD,"");
			validUserData.put(REQUEST_STATUS,"BAD_PASSWORD");
			}
		
		if(userValidator.validateEmail(userData.get(EMAIL))) {
			
			validUserData.put(EMAIL, userData.get(EMAIL));
			
		} else { 
			validUserData.put(EMAIL,"");
			validUserData.put(REQUEST_STATUS,"BAD_EMAIL");
			}

		if(userValidator.validateLogin(userData.get(LOGIN))) {
			
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
	
	public Map<String,String> validateCabinet(Map<String,String> userData) {
		
		Map<String,String> validUserData = new HashMap<>();
		
		var userValidator = new UserValidator();
		
		validUserData.put(PHONE, userData.get(PHONE));
		validUserData.put(CITY, userData.get(CITY));
		validUserData.put(FIRSTNAME, userData.get(FIRSTNAME));
		validUserData.put(LASTNAME, userData.get(LASTNAME));
		
		
		if (!userValidator.validatePhone(userData.get(PHONE))) {
			
			validUserData.put(PHONE, "");
			validUserData.put(CABINET_EXIST, "WRONG_PHONE");
			
		}
		
		if (!userValidator.validateCity(userData.get(CITY))) {
					
					validUserData.put(CITY, "");
					validUserData.put(CABINET_EXIST, "WRONG_CITY");
					
				}
		
		if (!userValidator.validateName(userData.get(FIRSTNAME))) {
			
			validUserData.put(FIRSTNAME, "");
			validUserData.put(CABINET_EXIST, "WRONG_NAME");
			
		}
		
		if (!userValidator.validateSurname(userData.get(LASTNAME))) {
			
			validUserData.put(LASTNAME, "");
			validUserData.put(CABINET_EXIST, "WRONG_SURNAME");
			
		}
		
		if (!validUserData.containsKey(CABINET_EXIST)) {
			
			validUserData.put(CABINET_EXIST, "GOOD_DATA");
			
		} 
		
		return validUserData;
	}
	
	public Map<String,String> validateChange(Map<String,String> userData) throws ServiceException {
		
		Map<String,String> validUserData = new HashMap<>();
		
		var userValidator = new UserValidator();
		
		validUserData.put(USERID,userData.get(USERID));
		validUserData.put(TYPEOFCHANGE, userData.get(TYPEOFCHANGE));
		validUserData.put(EMAIL, userData.get(EMAIL));
		
		switch(userData.get(TYPEOFCHANGE)) {

			case LOGIN:{
				
				userData.put(LOGIN,userData.get(DATATOCHANGE));
			
				if(userValidator.validateLogin(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_LOGIN");
					}
					
				if(isLoginBusy(userData)) {
					validUserData.put(CABINET_REQUEST, "DATA_ALREADY_EXISTS");
				}
			}
		
		}
		
		if (!isPasswordRepeatedCorrect(userData))	
			validUserData.put(CABINET_REQUEST, "PASSWORD_NOT_REPEATED");
		
		
		if(!isPasswordCorrect(userData)) {
			validUserData.put(CABINET_REQUEST, "WRONG_PASSWORD");
		}
		
		
		if (!validUserData.containsKey(CABINET_REQUEST)) {
			validUserData.put(CABINET_REQUEST,"SUCCESS_CHANGE");
		} else {
			validUserData.put(PASSWORD,"");
			validUserData.put(PASSWORD_REPEAT, "");
		}
		
		return validUserData;	
		
		
	}
		
	
}
