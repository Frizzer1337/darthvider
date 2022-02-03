package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;
import java.util.Optional;

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
		
		boolean passwordCorrect = false;
		boolean cabinetExists = false;
		long userId = -1;
		long userRole = 0;
		long userStatus = 1;
		try {
			passwordCorrect = clientService.isPasswordCorrect(regData);
			Optional<User> optionalUser = clientService.getUserByEmail(email);
			if(optionalUser.isPresent()) {
				User user = optionalUser.get();
				userId = user.getId();
				userRole = user.getRole();
				userStatus = user.getStatus();
			}
			cabinetExists = clientService.checkCabinet(userId);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
		
		if (passwordCorrect) {
			
			if (cabinetExists) {
				session.setAttribute(CABINET_EXIST, "CABINET_EXISTS");
			} else {
				session.setAttribute(CABINET_EXIST, "CABINET_NOT_EXISTS");
			}
			session.setAttribute(REQUEST_STATUS, "SUCCESS_LOGIN");
			session.setAttribute(USERID,userId);
			session.setAttribute(EMAIL,regData.get(EMAIL));
			session.setAttribute(ROLE,userRole);
			session.setAttribute(USERSTATUS,userStatus);

		} else {		
			
			session.setAttribute(REQUEST_STATUS, "WRONG_LOGIN");
			
			
		}
		
		
		
		return new Router(MAIN_PAGE_LOGIN, Router.RouterType.REDIRECT);
	}

}
