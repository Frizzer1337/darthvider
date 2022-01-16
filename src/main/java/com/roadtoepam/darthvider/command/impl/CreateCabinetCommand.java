package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;
import java.util.Map;

import static com.roadtoepam.darthvider.command.PageRouting.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CreateCabinetCommand implements Command {
	
	private ClientService clientService;

    public CreateCabinetCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		String name = request.getParameter(FIRSTNAME);
		String surname = request.getParameter(LASTNAME);
		String city = request.getParameter(CITY);
		String phone = request.getParameter(PHONE);
		
		HashMap<String,String> regData = new HashMap<>();
		
		regData.put(FIRSTNAME,name);
		regData.put(LASTNAME, surname);
		regData.put(CITY, city);
		regData.put(PHONE,phone);
		
		HttpSession session = request.getSession();
		
		long id = (long) session.getAttribute(USERID);
		
		Map<String,String> validData  = clientService.validateCabinet(regData);
		
		if (validData.get(CABINET_EXIST).equals("GOOD_DATA")) {
			
			session.removeAttribute(PHONE);
			session.removeAttribute(CITY);
			session.removeAttribute(LASTNAME);
			session.removeAttribute(FIRSTNAME);
			
			try {
				clientService.addCabinet(validData,id);
			} catch (ServiceException e) {
				throw new CommandException("Error occured while adding user info",e);
			}
			
			session.setAttribute(CABINET_EXIST, "CABINET_EXISTS");

		} else {		
			
			session.setAttribute(CABINET_EXIST,validData.get(CABINET_EXIST));
			session.setAttribute(PHONE, validData.get(PHONE));
			session.setAttribute(CITY, validData.get(CITY));
			session.setAttribute(LASTNAME, validData.get(LASTNAME));
			session.setAttribute(FIRSTNAME, validData.get(FIRSTNAME));
			
		}
		
		return new Router(CABINET_PAGE,Router.RouterType.REDIRECT);
	}

}
