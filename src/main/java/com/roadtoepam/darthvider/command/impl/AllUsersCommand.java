package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.ArrayList;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AllUsersCommand implements Command {
	
	private ClientService clientService;

    public AllUsersCommand(ClientService clientService){
    	
        this.clientService = clientService;
        
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {	
		
		
		ArrayList<User> userList;
		try {
			userList = (ArrayList<User>) clientService.getAllUsers();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(USERS, userList);
		
		return new Router(ADMIN_PAGE, Router.RouterType.FORWARD);
	}


}
