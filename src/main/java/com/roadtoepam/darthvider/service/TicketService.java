package com.roadtoepam.darthvider.service;

import com.roadtoepam.darthvider.dao.DaoTransaction;
import com.roadtoepam.darthvider.dao.impl.TicketDaoImpl;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.util.HelpMailSender;
import com.roadtoepam.darthvider.util.MailSender;

public class TicketService {
	
	/**
	 * Adds a ticket and return it's id.
	 *
	 * @param email,problem,first name,last name
	 * @return the id of added ticket
	 * @throws ServiceException the service exception
	 */
	public long addTicket(String email,String problem,String firstname,String lastname) throws ServiceException {
		
		final TicketDaoImpl ticketDao = new TicketDaoImpl();
		final DaoTransaction transaction = new DaoTransaction();
		
		
		try {
			
			transaction.start(ticketDao);
			
			long ticketId = ticketDao.add(email, problem, firstname, lastname);
			
			transaction.end();
			
			return ticketId;
			
			
		} catch (DaoException e) {
			
			throw new ServiceException("Error while adding ticket or getting ticket id",e);
			
		}
		
	}
	
	public void sendHelpEmail( String email, long id) throws ServiceException {
		
		HelpMailSender sender = new HelpMailSender();
		sender.sendMessage(email, id);
		
	}

}
