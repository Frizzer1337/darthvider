package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;
import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.mysql.cj.Session;
import com.roadtoepam.darthvider.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignUpCommand implements Command {

	private ClientService clientService;

    public SignUpCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String data = request.getParameter(DATATOCHANGE);
		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASSWORD);
		String passAgain = request.getParameter(PASSWORD_REPEAT);
		
		HashMap<String,String> regData = new HashMap<>(); 
		
		HttpSession session = request.getSession();
		
		regData.put(DATATOCHANGE, data);
		regData.put(EMAIL, email);
		regData.put(PASSWORD, pass);
		regData.put(PASSWORD_REPEAT, passAgain);
		
		Map<String, String> validData;
		
		try {
			validData = clientService.validationMap(regData);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
		
		
		if (validData.get(REQUEST_STATUS).equals("SUCCESS_CHANGE")) {
				
			session.removeAttribute(DATATOCHANGE);
			session.setAttribute(CABINET_REQUEST, "SUCCESS_CHANGE");

		} else {		
			
			session.setAttribute(DATATOCHANGE,validData.get(DATATOCHANGE));
			session.setAttribute(CABINET_REQUEST,validData.get(CABINET_REQUEST));
			
			
		}

		
		return new Router(MAIN_PAGE_LOGIN, Router.RouterType.REDIRECT);
	}

}

