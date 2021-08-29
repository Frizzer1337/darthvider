package com.roadtoepam.darthvider.dao;

import java.util.List;

import com.roadtoepam.darthvider.entity.RoleInfo;
import com.roadtoepam.darthvider.exception.DaoException;

public interface UserRoleDao {
	
	/**
	   * Get list of user roles.
	   *
	   * @return all the customers as a list.
	   * @throws DaoException if any error occurs.
	   */
	  List<RoleInfo> findAll() throws DaoException;

	  /**
	   * Add a new role.
	   *
	   * @param info to be added and it's id.
	   * @return true if user is successfully added, false if role already exists.
	   * @throws DaoException if any error occurs.
	   */
	  boolean add(RoleInfo role,int id) throws DaoException;

	  /**
	   * Update a role info.
	   *
	   * @param roleInfo and id to be updated.
	   * @return true if role exists and is successfully updated, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean update(RoleInfo role,int id) throws DaoException;
	  
	  
	  /**
	   * Delete a role.
	   *
	   * @param role to be deleted.
	   * @return true if user exists and is successfully deleted, false otherwise.
	   * @throws DaoException if any error occurs.
	   */
	  boolean delete(int id) throws DaoException;
}


