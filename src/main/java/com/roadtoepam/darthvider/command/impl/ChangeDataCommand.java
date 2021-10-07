package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;
import java.util.Map;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeDataCommand implements Command {

	private ClientService clientService;

    public ChangeDataCommand(ClientService clientService){
        this.clientService = clientService;
    }
	
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
		
		String password = request.getParameter(PASSWORD);
		String passwordRepeated = request.getParameter(PASSWORD_REPEAT);
		String email = (String)session.getAttribute(EMAIL);
		long id = (long)session.getAttribute(USERID);
		String type = request.getParameter(TYPEOFCHANGE);
		String data = request.getParameter(DATATOCHANGE);
		
		HashMap<String,String> toChangeData = new HashMap<>(); 
		
		toChangeData.put(PASSWORD, password);
		toChangeData.put(PASSWORD_REPEAT, passwordRepeated);
		toChangeData.put(USERID,id+"");
		toChangeData.put(EMAIL,email);
		toChangeData.put(TYPEOFCHANGE, type);
		toChangeData.put(DATATOCHANGE, data);
		
		Map<String, String> validData;
		try {
			
			validData = clientService.validateChange(toChangeData);
			
		} catch (ServiceException e) {
			
			throw new CommandException("Error occured while validating changes",e);
		}		
		
		if (validData.get(CABINET_REQUEST).equals("SUCCESS_CHANGE")) {
			
			switch (type) {
			
			case LOGIN:{
				
				validData.put(LOGIN, data);
				
				try {
					clientService.changeLogin(validData);
				} catch (ServiceException e) {
					
					throw new CommandException("Error while changing login",e);
					
				}
			}
			
			}
			
			session.removeAttribute(DATATOCHANGE);
			
			session.setAttribute(CABINET_REQUEST, "SUCCESS_CHANGE");
			

		} else {		
			
			session.setAttribute(DATATOCHANGE,validData.get(DATATOCHANGE));
			session.setAttribute(CABINET_REQUEST,validData.get(CABINET_REQUEST));
			
			
		}
		
		String optionalPath="";
		
		switch(type) {
		
		case LOGIN: { optionalPath = "#login_change"; }
		
		}
		
		String currentPath = request.getHeader("referer")+optionalPath;
		
		
		
		return new Router(currentPath, Router.RouterType.REDIRECT);
		

	}
	
}
