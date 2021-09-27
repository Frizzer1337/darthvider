package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.MAIN_PAGE;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.ArrayList;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.TariffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class PreloadCommand implements Command {
	
	private TariffService tariffService;

    public PreloadCommand(TariffService tariffService){
    	
        this.tariffService = tariffService;
        
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
		
		ArrayList<Tariff> tariffList;
		try {
			tariffList = (ArrayList<Tariff>) tariffService.getAllTariff();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		session.setAttribute(PRELOAD_MAP, tariffList);
		
		return new Router(MAIN_PAGE, Router.RouterType.REDIRECT);
	}


}
