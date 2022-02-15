package com.roadtoepam.darthvider.controller.command.impl.admin;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.HashMap;
import java.util.Map;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.service.ClientService;
import com.roadtoepam.darthvider.model.service.TariffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class NewTariffCommand implements Command{

	private TariffService tariffService;

    public NewTariffCommand(TariffService tariffService){
        this.tariffService = tariffService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {		
		
		String name = request.getParameter(TARIFFNAME);
		String price = request.getParameter(TARIFFPRICE);
		String discount = request.getParameter(TARIFFDISCOUNT);
		String due = request.getParameter(TARIFFDUE);
		String shortInfo = request.getParameter(TARIFFSHORTINFO);
		String info = request.getParameter(TARIFFINFO);
		
		Map<String, String> validData = new HashMap<>();
		
		validData.put(TARIFFNAME, name);
		validData.put(TARIFFPRICE, price);
		validData.put(TARIFFDISCOUNT, discount);
		validData.put(TARIFFDUE, due);
		validData.put(TARIFFSHORTINFO, shortInfo);
		validData.put(TARIFFINFO, info);
		
		try {
			tariffService.addTariff(validData);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}

		String currentPath = request.getHeader("referer");

		return new Router(currentPath, Router.RouterType.REDIRECT);
	}
	
}
