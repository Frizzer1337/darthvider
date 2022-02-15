package com.roadtoepam.darthvider.model.service;

import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.dao.DaoTransaction;
import com.roadtoepam.darthvider.model.dao.impl.ConnectedTariffDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.TariffDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.TicketDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.UserContractDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.UserDaoImpl;
import com.roadtoepam.darthvider.model.dao.impl.UserInfoDaoImpl;
import com.roadtoepam.darthvider.model.entity.ConnectedTariff;
import com.roadtoepam.darthvider.model.entity.Tariff;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserContract;
import com.roadtoepam.darthvider.model.entity.UserInfo;
import com.roadtoepam.darthvider.model.service.validator.UserValidator;
import com.roadtoepam.darthvider.model.util.MailSender;
import com.roadtoepam.darthvider.model.util.security.EmailHasher;

/**
 * The class that collects method connected with User.
 */
public class ClientService {
	
	/**
	 * Send confirmation email.
	 *
	 * @param id 
	 * @param email 
	 * @throws ServiceException if any happens
	 */
	public void sendConfirmationEmail(long id, String email) throws ServiceException {
		
		MailSender sender = new MailSender();
		sender.sendMessage(id, email);
		
	}
	
	/**
	 * Gets the user by email.
	 *
	 * @param email 
	 * @return optional of User
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Gets the user info by email.
	 *
	 * @param email 
	 * @return optional of User
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Gets the user contract by email.
	 *
	 * @param email
	 * @return optional of user contract
	 * @throws ServiceException if any happens
	 */
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

	/**
	 * Gets all users.
	 *
	 * @return list of users
	 * @throws ServiceException if any happens
	 */
	public List<User> getAllUsers() throws ServiceException{
		
		ArrayList<User> userList = new ArrayList<>();
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		try {
			transaction.start(userDao);
			userList = (ArrayList<User>) userDao.findAll();
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding user",e);
			
		}
		
		return userList;
		
	}
	
	/**
	 * Gets the user tariff by email.
	 *
	 * @param email
	 * @return optional of connected tariff
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Gets the user id by email.
	 *
	 * @param email 
	 * @return user id
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Gets the user role.
	 *
	 * @param user id
	 * @return the user role
	 * @throws ServiceException if any happens
	 */
	public int getUserRole(long id) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		int userRole=-1;
		try {
			transaction.start(userDao);
			userRole = userDao.findRole(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
		return userRole;
		
		
		
	}
	
	/**
	 * Promote user to admin.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean promoteUser(long id) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result;
		try {
			transaction.start(userDao);
			result = userDao.changeRole(id, 2);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
		return result;
		
		
		
	}
	
	/**
	 * Demote user to default user.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean demoteUser(long id) throws ServiceException {
		
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result;
		try {
			transaction.start(userDao);
			result = userDao.changeRole(id, 1);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for login and email",e);
			
		}
		return result;
		
		
		
	}

	/**
	 * Checks if is login or email busy.
	 *
	 * @param map of user data
	 * @return true, if is login or email busy
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Checks if login is busy.
	 *
	 * @param map of user data
	 * @return true, if login is busy
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Checks if phone is busy.
	 *
	 * @param map of user data
	 * @return true, if is phone busy
	 * @throws ServiceException if any happens
	 */
	public boolean isPhoneBusy(Map<String,String> userData) throws ServiceException {
		
		final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userInfoDao);
			result = userInfoDao.isPhoneFree(userData.get(PHONE));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking is phone number free",e);
			
		}
	
		return result;
	}
	
	/**
	 * Checks if password is correct.
	 *
	 * @param map of user data
	 * @return true, if is password correct
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Change login.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Change name.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changeName(Map<String, String> validData) throws ServiceException {
		final UserInfoDaoImpl userDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changeName(validData.get(NAME),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change surname.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changeSurname(Map<String, String> validData) throws ServiceException {
		final UserInfoDaoImpl userDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changeSurname(validData.get(SURNAME),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change phone.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changePhone(Map<String, String> validData) throws ServiceException {
		final UserInfoDaoImpl userDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changePhone(validData.get(PHONE),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change password.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changePassword(Map<String, String> validData) throws ServiceException {
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changePassword(validData.get(NEWPASSWORD),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change city.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changeCity(Map<String, String> validData) throws ServiceException {
		final UserInfoDaoImpl userDao = new UserInfoDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.changeCity(validData.get(CITY),Long.parseLong(validData.get(USERID)));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while checking for password correctness",e);
			
		}
	
		return result;
	}
	
	/**
	 * Change balance.
	 *
	 * @param id
	 * @param balance 
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean changeBalance(long id, float balance) throws ServiceException {
	
			final UserDaoImpl userDao = new UserDaoImpl();
			final DaoTransaction transaction = new DaoTransaction();
			
			boolean result = false;
			try {
				transaction.start(userDao);
				result = userDao.changeBalance(id,balance);
				transaction.end(); 
			} catch (DaoException e) {
				
				throw new ServiceException("Error while checking for password correctness",e);
				
			}
			
			return result;
	}
		
	
	/**
	 * Adds the user.
	 *
	 * @param map of user data
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
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
							.setStatus(0)
							.build();
			result = userDao.add(user, userData.get(PASSWORD));
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Block user.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean blockUser(int id) throws ServiceException {
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.delete(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Unlock user.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean unlockUser(int id) throws ServiceException {
		final UserDaoImpl userDao = new UserDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		try {
			transaction.start(userDao);
			result = userDao.unlock(id);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while blocking user",e);
			
		}
	
		return result;
	}
	
	/**
	 * Adds the tariff to contract.
	 *
	 * @param contract id 
	 * @param tariff id 
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean addTariff(int contractId,int tariffId) throws ServiceException {
		
		final ConnectedTariffDaoImpl connectedTariffDao = new ConnectedTariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		
		try {
			transaction.start(connectedTariffDao);

			result=connectedTariffDao.add(contractId, tariffId);
			transaction.end();

			
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding tariff to user",e);
			
		}
		
		
		return result;
		
	}
	
	/**
	 * Delete tariff from contract.
	 *
	 * @param contract id
	 * @param tariff id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean deleteTariff(int contractId,int tariffId) throws ServiceException {
		
		final ConnectedTariffDaoImpl connectedTariffDao = new ConnectedTariffDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		
		try {
			transaction.start(connectedTariffDao);
			result=connectedTariffDao.delete(contractId, tariffId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while deleting tariff from user",e);
			
		}
		
		
		return result;
		
	}
	
	/**
	 * Delete user tariffs.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
	public boolean deleteUserTariffs(int id) throws ServiceException {
		
		final ConnectedTariffDaoImpl connectedTariffDao = new ConnectedTariffDaoImpl();
		final UserContractDaoImpl userContractDao = new UserContractDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		boolean result = false;
		
		try {
			transaction.start(connectedTariffDao);
			int contractId = userContractDao.findContractIdByUserId(id);
			result=connectedTariffDao.deleteByContract(contractId);
			transaction.end();
		} catch (DaoException e) {
			
			throw new ServiceException("Error while deleting tariff from user",e);
			
		}
		
		
		return result;
		
	}
	
	/**
	 * Checks is cabinet exists.
	 *
	 * @param id 
	 * @return true, if successful
	 * @throws ServiceException if any happens
	 */
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
	
	/**
	 * Adds the cabinet.
	 *
	 * @param map of user data
	 * @param id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addCabinet(Map<String, String> validData, long id) throws ServiceException {
		
		final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
		final UserContractDaoImpl userContractDao = new UserContractDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		boolean result = false;
		boolean resultContract = false;
		
		try {
			transaction.start(userInfoDao,userContractDao);
			UserInfo userInfo = UserInfo.newBuilder()
										.setName(validData.get(FIRSTNAME))
										.setCity(validData.get(CITY))
										.setSurname(validData.get(LASTNAME))
										.setPhone(validData.get(PHONE))
										.build();
			result = userInfoDao.add(userInfo,id);
			LocalDate today = LocalDate.now();
			UserContract userContract = UserContract.newBuilder()
					                                .setId(id)
												    .setIdContract((int) (id+10032))
												    .setStartDate(Date.valueOf(today))
												    .setEndDate(Date.valueOf(today.plusYears(1)))
												    .setDiscount(5)
												    .setIsActive(true)
												    .build();
			resultContract = userContractDao.add(userContract);
			transaction.end();
		}  catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error occured while adding user info",e);
		}
		
		return result && resultContract;
	}
	
	
	/**
	 * Checks if is password repeated correct.
	 *
	 * @param map of user data
	 * @return true, if is password repeated correct
	 */
	public boolean isPasswordRepeatedCorrect(Map<String,String> userData){
		
		boolean result = userData.get(PASSWORD).equals(userData.get(PASSWORD_REPEAT));
		
		return result;	
		
		
	}
	
	/**
	 * Verify.
	 *
	 * @param regData the reg data
	 * @throws ServiceException the service exception
	 */
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
	 * @param map of user data
	 * @return the map of correct data(if not correct String="") and status of the validation
	 * @throws ServiceException the service exception
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
	
	/**
	 * Validate user data before adding personal cabinet.
	 *
	 * @param map of the user data
	 * @return the map of correct data(if not correct String="") and status of the validation
	 */
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
	
	/**
	 * Validate change of data in user cabinet.
	 *
	 * @param map of user data
	 * @return the map of correct data(if not correct String="") and status of the validation
	 * @throws ServiceException the service exception
	 */
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
				break;
			}
			
			case NAME:{
				
				userData.put(NAME,userData.get(DATATOCHANGE));
			
				if(userValidator.validateName(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_NAME");
					}
				break;
			}
			
			case SURNAME:{
				
				userData.put(SURNAME,userData.get(DATATOCHANGE));
			
				if(userValidator.validateSurname(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_SURNAME");
					}
				break;
			}
			
			case PHONE:{
				
				userData.put(PHONE,userData.get(DATATOCHANGE));
			
				if(userValidator.validatePhone(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_PHONE");
					}
				
				if(isPhoneBusy(userData)) {
					validUserData.put(CABINET_REQUEST, "DATA_ALREADY_EXISTS");
				}
				break;	
			}
			
			case CITY:{
				
				userData.put(CITY,userData.get(DATATOCHANGE));
			
				if(userValidator.validateCity(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_CITY");
					}
				break;
			}
			case NEWPASSWORD:{

				userData.put(NEWPASSWORD,userData.get(DATATOCHANGE));
			
				if(userValidator.validatePassword(userData.get(DATATOCHANGE))) {
				
					validUserData.put(DATATOCHANGE, userData.get(DATATOCHANGE));
					
				} else { 
					validUserData.put(DATATOCHANGE,"");
					validUserData.put(CABINET_REQUEST,"BAD_NEW_PASSWORD");
					}
				break;
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
