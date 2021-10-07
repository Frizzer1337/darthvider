package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import static com.roadtoepam.darthvider.command.PageRouting.*;

import jakarta.servlet.http.HttpServletRequest;

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
		
		clientService.validateCabinet();
		
		return new Router(CABINET_PAGE,Router.RouterType.REDIRECT);
	}

}
