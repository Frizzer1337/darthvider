package com.roadtoepam.darthvider.controller.command.impl.guest;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.Locale;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.model.service.ClientService;
import com.roadtoepam.darthvider.model.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
	
	private ClientService clientService;

    public ChangeLocaleCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        String currentPath = request.getHeader("referer");
        locale = LanguageSwitcher.change(locale);
        session.setAttribute(LOCALE, locale);
        return new Router(currentPath,Router.RouterType.REDIRECT);
	}

}
