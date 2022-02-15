package com.roadtoepam.darthvider.model.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.model.entity.ConnectedTariff;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserContract;

public interface ConnectedTariffDao{
	
	/**
	   * Get all connected tariffs .
	   *
	   * @return all the contracts with connected to them tariffs as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<ConnectedTariff> findAll() throws DaoException;

	  /**
	   * Get contract with connected tariff as Optional by id.
	   *
	   * @param id unique identifier of the user.
	   * @return an optional with connected tariff if a contract with unique identifier exists,
	   *     empty optional otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  Optional<ConnectedTariff> findById(int idContract) throws DaoException;

	  /**
	   * Add a contract-tariff pair.
	   *
	   * @param contract id and tariff id to be added.
	   * @return true if contract and tariff is successfully connected, false if not.
	   * @throws DaoException if any error occurs.
	   */
	  boolean add(int idContract, int idTariff) throws DaoException;
	  
	  /**
	   * Delete a contract-tariff pair.
	   *
	   * @param contract id and tariff id to be deleted.
	   * @return true if contract and tariff exists and successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean delete(int idContract,int idTariff) throws DaoException;
	  
	  /**
	   * Delete a contract-tariff pair using contract id.
	   *
	   * @param contract id and tariff id to be deleted.
	   * @return true if contract and tariff exists and successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean deleteByContract(int idContract) throws DaoException;
	  
	  /**
	   * Delete a contract-tariff pair using tariff id.
	   *
	   * @param contract id and tariff id to be deleted.
	   * @return true if contract and tariff exists and successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean deleteByTariff(int idTariff) throws DaoException;

}
