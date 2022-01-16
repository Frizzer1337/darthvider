package com.roadtoepam.darthvider.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.entity.UserInfo;
import com.roadtoepam.darthvider.exception.DaoException;

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

	}