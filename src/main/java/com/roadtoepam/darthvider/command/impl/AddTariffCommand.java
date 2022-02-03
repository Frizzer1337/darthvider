	package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddTariffCommand implements Command {

	private ClientService clientService;

    public AddTariffCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
		
		int tariffId = Integer.parseInt((String) session.getAttribute(TARIFFID));
		int contractId = ((Long)session.getAttribute(CONTRACTID)).intValue();
		
		session.getAttribute(BALANCE);
	
		if(contractId!=0 & tariffId!=0) {
			try {
				clientService.addTariff(contractId, tariffId);
			} catch (ServiceException e) {
				throw new CommandException("Error occcured while adding tariff.",e);
			}
		}
		
		session.setAttribute(CABINET_EXIST, "CABINET_EXISTS");
		
		return new Router(CABINET_PAGE, Router.RouterType.REDIRECT) ;
	}

}
