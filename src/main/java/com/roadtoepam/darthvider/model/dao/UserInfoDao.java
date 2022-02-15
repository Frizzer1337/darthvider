package com.roadtoepam.darthvider.model.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserInfo;

public interface UserInfoDao {

	  /**
	   * Get all users info.
	   *
	   * @return all the user as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<UserInfo> findAll() throws DaoException;

	  /**
	   * Get user info as Optional by id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with user if a user with unique identifier id exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  Optional<UserInfo> findById(int id) throws DaoException;

	  /**
	   * Check if user info exists.
	   *
	   * @param id of the user.
	   * @return true if user info exists , false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean checkById(long id) throws DaoException;

	  /**
	   * Add a user info.
	   *
	   * @param user info to be added and user id.
	   * @return true if user info is successfully added, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  
	  boolean add(UserInfo userInfo,long id) throws DaoException;
	  
	  /**
	   * Update a user info.
	   *
	   * @param user to be updated.
	   * @return true if user info exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  
	  boolean update(UserInfo userInfo,int id) throws DaoException;

	  /**
	   * Change a user name.
	   *
	   * @param user name to be changed.
	   * @return true if user info exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean changeName(String name, long id) throws DaoException;

	  /**
	   * Change a user surname.
	   *
	   * @param user surname to be changed.
	   * @return true if user info exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  
	  boolean changeSurname(String surname, long id) throws DaoException;
	  
	  /**
	   * Change a user phone.
	   *
	   * @param user phone to be changed.
	   * @return true if user info exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean changePhone(String phone, long id) throws DaoException;
	  
	  /**
	   * Change a user city.
	   *
	   * @param user city to be changed.
	   * @return true if user info exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */

	  boolean changeCity(String city, long id) throws DaoException;

	  /**
	   * Checks is phone doesn't exist in database.
	   *
	   * @param user phone to be checked.
	   * @return true if user phone not exist, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean isPhoneFree(String phone) throws DaoException;

	}