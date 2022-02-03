package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.ArrayList;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;
import com.roadtoepam.darthvider.service.TariffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AllTariffsCommand implements Command {
	
	private TariffService tariffService;

    public AllTariffsCommand(TariffService tariffService){
    	
        this.tariffService = tariffService;
        
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {		
		
		ArrayList<Tariff> tariffList;
		try {
			tariffList = (ArrayList<Tariff>) tariffService.getAllTariff();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(ADMIN_TARIFFS, tariffList);
		
		return new Router(ADMIN_PAGE, Router.RouterType.FORWARD);
	}


}
