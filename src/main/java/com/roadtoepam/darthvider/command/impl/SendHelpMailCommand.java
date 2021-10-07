package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.TicketService;
import com.roadtoepam.darthvider.util.HelpMailSender;

import jakarta.servlet.http.HttpServletRequest;

public class SendHelpMailCommand implements Command{

	private TicketService ticketService;

    public SendHelpMailCommand( TicketService ticketService){
        this.ticketService = ticketService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String firstname= request.getParameter(FIRSTNAME);
		String lastname = request.getParameter(LASTNAME);
		String problem = request.getParameter(PROBLEM);
		String email = request.getParameter(EMAIL);
		
		
		try {
			long ticketId = ticketService.addTicket(email,problem,firstname,lastname);
			ticketService.sendHelpEmail(email, ticketId);
		} catch (ServiceException e) {
			throw new CommandException("Error occured while sending help mail",e);
		}
		
		return new Router(MAIN_PAGE, Router.RouterType.REDIRECT);
	}

}
