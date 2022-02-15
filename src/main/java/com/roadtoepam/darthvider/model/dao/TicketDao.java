package com.roadtoepam.darthvider.model.dao;

import com.roadtoepam.darthvider.exception.DaoException;

public interface TicketDao {
	
	 /**
	   * Add a ticket.
	   *
	   * @param ticket author email,problem,name,last name to be added.
	   * @return true if ticket is successfully added, false if not.
	   * @throws DaoException if any error occurs.
	   */
	  int add(String email, String problem, String name, String lastname) throws DaoException;
}