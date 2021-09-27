package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.EMAIL;
import static com.roadtoepam.darthvider.command.RequestContent.LOGIN;
import static com.roadtoepam.darthvider.command.RequestContent.PASSWORD;
import static com.roadtoepam.darthvider.command.RequestContent.PASSWORD_REPEAT;
import static com.roadtoepam.darthvider.command.RequestContent.REQUEST_STATUS;

import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

	private ClientService clientService;

    public LoginCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASSWORD);
		
		HashMap<String,String> regData = new HashMap<>(); 
		
		HttpSession session = request.getSession();
		
		regData.put(EMAIL, email);
		regData.put(PASSWORD, pass);
		
		boolean passwordCorrect=false;
		
		try {
			passwordCorrect = clientService.isPasswordCorrect(regData);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		if (passwordCorrect) {
				
			session.setAttribute(REQUEST_STATUS, "SUCCESS_LOGIN");
			session.setAttribute(EMAIL,regData.get(EMAIL));

		} else {		
			
			session.setAttribute(REQUEST_STATUS, "WRONG_LOGIN");
			
			
		}
		
		
		
		return new Router(MAIN_PAGE_LOGIN, Router.RouterType.REDIRECT);
	}

}
