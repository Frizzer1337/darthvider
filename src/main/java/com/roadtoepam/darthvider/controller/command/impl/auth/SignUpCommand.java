package com.roadtoepam.darthvider.controller.command.impl.auth;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.DaoException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.service.ClientService;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.mysql.cj.Session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignUpCommand implements Command {

	private ClientService clientService;

    public SignUpCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String data = request.getParameter(LOGIN);
		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASSWORD);
		String passAgain = request.getParameter(PASSWORD_REPEAT);
		
		HashMap<String,String> regData = new HashMap<>(); 
		
		HttpSession session = request.getSession();
		
		regData.put(LOGIN, data);
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
		
		
		if (validData.get(REQUEST_STATUS).equals("SUCCESS_REGISTRATION")) {
			
			session.removeAttribute(LOGIN);
			session.removeAttribute(EMAIL);
			try {
				clientService.addUser(validData);
				int userId = clientService.getUserIdByEmail(validData.get(EMAIL));
				clientService.sendConfirmationEmail(userId,validData.get(EMAIL));
			} catch (ServiceException e) {
				throw new CommandException("Error occured while adding user or sending confirmation email",e);
			}
			session.setAttribute(REQUEST_STATUS, "SUCCESS_REGISTRATION");

		} else {		
			
			session.setAttribute(LOGIN,validData.get(LOGIN));
			session.setAttribute(EMAIL,validData.get(EMAIL));
			session.setAttribute(REQUEST_STATUS,validData.get(REQUEST_STATUS));
			
			
		}

		
		return new Router(MAIN_PAGE_LOGIN, Router.RouterType.REDIRECT);
	}

}

