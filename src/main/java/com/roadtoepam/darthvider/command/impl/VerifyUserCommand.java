package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.MAIN_PAGE;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class VerifyUserCommand implements Command{

	private ClientService clientService;

    public VerifyUserCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String id = request.getParameter(USERID);
		
		HashMap<String,String> regData = new HashMap<>(); 
		
		HttpSession session = request.getSession();
		
		regData.put(USERID, id);
		
		try {
			clientService.verify(regData);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
		
		session.removeAttribute(REQUEST_STATUS);

		
		return new Router(MAIN_PAGE, Router.RouterType.REDIRECT);
	}
	
}
