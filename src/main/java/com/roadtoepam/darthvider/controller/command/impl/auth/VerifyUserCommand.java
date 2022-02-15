package com.roadtoepam.darthvider.controller.command.impl.auth;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.HashMap;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.service.ClientService;

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

		
		return new Router(PRELOAD_PAGE, Router.RouterType.REDIRECT);
	}
	
}
