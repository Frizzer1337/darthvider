package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.HashMap;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;
import com.roadtoepam.darthvider.service.TariffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
 
public class UnlockTariffCommand implements Command{

	private TariffService tariffService;

    public UnlockTariffCommand(TariffService tariffService){
        this.tariffService = tariffService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {		
		
		int id = Integer.parseInt(request.getParameter(TARIFFTOUNLOCK));
		
		try {
			tariffService.unlockTariff(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}

		String currentPath = request.getHeader("referer");

		return new Router(currentPath, Router.RouterType.REDIRECT);
	}
	
}
