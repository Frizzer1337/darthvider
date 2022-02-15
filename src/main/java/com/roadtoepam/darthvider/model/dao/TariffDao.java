package com.roadtoepam.darthvider.model.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.model.entity.Tariff;

public interface TariffDao {
		
		/**
		   * Get all connected tariffs .
		   *
		   * @return all tariffs as a list.
		   * @throws DaoException if any error occurs.
		   */
		  List<Tariff> findAll() throws DaoException;

		  /**
		   * Get tariff as Optional by id.
		   *
		   * @param id unique identifier of the tariff.
		   * @return an optional with tariff if a tariff with unique identifier exists,
		   *     empty optional otherwise.
		   * @throws DaoException if any error occurs.
		   */
		  Optional<Tariff> findById(int idContract) throws DaoException;

		  /**
		   * Add a tariff.
		   *
		   * @param tariff id to be added.
		   * @return true if tariff is successfully connected, false if not.
		   * @throws DaoException if any error occurs.
		   */
		  boolean add(Tariff tariff) throws DaoException;
		  
		  /**
		   * Delete a tariff.
		   *
		   * @param tariff id to be deleted.
		   * @return true if tariff exists and successfully deleted, false otherwise.
		   * @throws DaoException if any error occurs.
		   */
		  boolean delete(int idTariff) throws DaoException;
		  
		  /**
		   * Unlocks a tariff.
		   *
		   * @param tariff id to be unlocked.
		   * @return true if tariff exists and successfully deleted, false otherwise.
		   * @throws DaoException if any error occurs.
		   */
		 boolean unlock(int idTariff) throws DaoException;
		 
		 /**
		   * Change a tariff price.
		   *
		   * @param tariff id to be changed and new price.
		   * @return true if tariff exists and price successfully changed, false otherwise.
		   * @throws DaoException if any error occurs.
		   */
		 boolean changePrice(int idTariff,float price) throws DaoException;
		
		 /**
		   * Check a due type.
		   *
		   * @param tariff id which due type you want to check.
		   * @return due type of a tariff.
		   * @throws DaoException if any error occurs.
		   */
		int checkDueType(int IdTariff) throws DaoException;
		

}
