package com.roadtoepam.darthvider.model.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserContract;

public interface UserContractDao {
	
	/**
	   * Get all contracts.
	   *
	   * @return all the contracts as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<UserContract> findAll() throws DaoException;

	  /**
	   * Get contract as Optional by user id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with contract if a user with unique identifier exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  Optional<UserContract> findContractByUserId(int id) throws DaoException;
	  
	  /**
	   * Get contract id as int by user id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with contract if a user with unique identifier exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  int findContractIdByUserId(int id) throws DaoException;


	  /**
	   * Add a user contract.
	   *
	   * @param contract to be added.
	   * @return true if user contract is successfully added, false if customer already exists.
	   * @throws DaoException if any error occurs.
	   */
	  boolean add(UserContract contract) throws DaoException;

	  /**
	   * Update a user contract without changing password.
	   *
	   * @param user contract to be updated.
	   * @return true if user contract exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean update(UserContract contract,int id) throws DaoException;
	  
	  
	  /**
	   * Activate a user contract.
	   *
	   * @param user contract id.
	   * @return true if user contract exists and is successfully activated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean activateContract(int id) throws DaoException;

	  /**
	   * Deactivates a contract.
	   *
	   * @param user contract id to be deactivated.
	   * @return true if user exists and is successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean deactivateContract(int id) throws DaoException;

}
