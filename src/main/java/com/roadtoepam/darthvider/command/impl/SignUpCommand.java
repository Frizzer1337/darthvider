package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.service.ClientService;
import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import com.roadtoepam.darthvider.command.Command;

import jakarta.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {

	private ClientService clientService;

    public SignUpCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		
		String login = request.getParameter(LOGIN);
		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASSWORD);
		String passAgain = request.getParameter(PASSWORD_REPEAT);
		
		HashMap<String,String> regData = new HashMap<>(); 
		
		regData.put(LOGIN, login);
		regData.put(EMAIL, email);
		regData.put(PASSWORD, pass);
		regData.put(PASSWORD_REPEAT, passAgain);
		
		System.out.print(regData);
		try {
			System.out.print(clientService.checkForLoginAndEmail(regData));
		} catch (DaoException e) {
			
		}

		
		return new Router(MAIN_PAGE, Router.RouterType.FORWARD);
	}

}

