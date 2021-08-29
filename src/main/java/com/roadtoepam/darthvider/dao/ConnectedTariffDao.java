package com.roadtoepam.darthvider.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.entity.UserContract;
import com.roadtoepam.darthvider.exception.DaoException;

public interface ConnectedTariffDao {
	
	/**
	   * Get all .
	   *
	   * @return all the users as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<UserContract> findAll() throws DaoException;

	  /**
	   * Get user as Optional by id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with user if a user with unique identifier exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  Optional<UserContract> findById(int id) throws DaoException;

	  /**
	   * Add a user.
	   *
	   * @param user to be added.
	   * @return true if user is successfully added, false if customer already exists.
	   * @throws DaoException if any error occurs.
	   */
	  boolean add(User user, String password) throws DaoException;

	  /**
	   * Update a user without changing password.
	   *
	   * @param user to be updated.
	   * @return true if user exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean update(User user,int id) throws DaoException;
	  
	  
	  /**
	   * Update a user.
	   *
	   * @param user to be updated.
	   * @return true if user exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean update(User user,String password,int id) throws DaoException;

	  /**
	   * Delete a user.
	   *
	   * @param user to be deleted.
	   * @return true if user exists and is successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean delete(int id) throws DaoException;

}
