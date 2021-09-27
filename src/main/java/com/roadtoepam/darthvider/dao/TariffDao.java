package com.roadtoepam.darthvider.dao;

import java.util.List;
import java.util.Optional;

import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.exception.DaoException;

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
		  boolean add(int idContract, int idTariff) throws DaoException;
		  
		  /**
		   * Delete a tariff.
		   *
		   * @param tariff id to be deleted.
		   * @return true if tariff exists and successfully deleted, false otherwise.
		   * @throws DaoException if any error occurs.
		   */
		  boolean delete(int idContract,int idTariff) throws DaoException;
		

}
