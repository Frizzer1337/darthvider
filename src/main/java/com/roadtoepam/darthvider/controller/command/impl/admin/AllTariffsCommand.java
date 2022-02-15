package com.roadtoepam.darthvider.controller.command.impl.admin;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.ArrayList;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.entity.Tariff;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.service.ClientService;
import com.roadtoepam.darthvider.model.service.TariffService;

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
