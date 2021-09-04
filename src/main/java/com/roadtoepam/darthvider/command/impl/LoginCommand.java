package com.roadtoepam.darthvider.command.impl;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.service.ClientService;

import static com.roadtoepam.darthvider.command.PageRouting.*;

import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

	private ClientService clientService;

    public LoginCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		
		
		
		return new Router(MAIN_PAGE, Router.RouterType.FORWARD);
	}

}
