package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class BlockUserCommand implements Command{

	private ClientService clientService;

    public BlockUserCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter(USERTOBLOCK));
		
		try {
			clientService.blockUser(id);
			clientService.deleteUserTariffs(id);
			clientService.demoteUser(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}

		String currentPath = request.getHeader("referer");

		return new Router(currentPath, Router.RouterType.REDIRECT);
	}
	
}
