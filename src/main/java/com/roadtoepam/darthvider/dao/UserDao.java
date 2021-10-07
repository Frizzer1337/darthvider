package com.roadtoepam.darthvider.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.DaoException;

public interface UserDao {

	  /**
	   * Get all users.
	   *
	   * @return all the users as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<User> findAll() throws DaoException;

	  /**
	   * Get user as Optional by id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with user if a user with unique identifier exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  Optional<User> findById(int id) throws DaoException;
	  
	  /**
	   * Get user id by email.
	   *
	   * @param email of the user.
	   * @return user id if exists, -1 otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  
	  int findIdByEmail(String email) throws DaoException;

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
	 
	  /**
	   * Check if user with same login or email exists.
	   *
	   * @param login and email to be checked.
	   * @return true if user exists and false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean isLoginAndEmailFree(String login,String email) throws DaoException;
	  
	  /**
	   * Check if user with same login and password exists.
	   *
	   * @param login and password to be checked.
	   * @return true if user exists and false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean isLoginFree(String login) throws DaoException;
	  
	  /**
	   * Check if user with same login exists.
	   *
	   * @param login to be checked.
	   * @return true if user exists and false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  
	  boolean login(String login,String email) throws DaoException;
	  
	  /**
	   * Change role of user by id.
	   *
	   * @param userd id and role to change.
	   * @return true if change successfully and false otherwise.
	   * @throws DaoException if any error occurs.
	   */

	  boolean changeRole(long id, int roleId) throws DaoException;
	  
	  /**
	   * Change login of user by id.
	   *
	   * @param id and login to change.
	   * @return true if change successfully and false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean changeLogin(String login, long id) throws DaoException;

}