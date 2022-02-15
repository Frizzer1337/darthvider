package com.roadtoepam.darthvider.controller.command.impl.cabinet;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Optional;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.entity.ConnectedTariff;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserContract;
import com.roadtoepam.darthvider.model.entity.UserInfo;
import com.roadtoepam.darthvider.model.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class PreloadCabinetCommand implements Command{
	
	private ClientService clientService;

    public PreloadCabinetCommand(ClientService clientService){
        this.clientService = clientService;
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute(EMAIL);
		Optional<User> user = Optional.empty();
		Optional<UserInfo> userInfo = Optional.empty();
		Optional<UserContract> userContract = Optional.empty();
		Optional<ConnectedTariff> userTariff = Optional.empty();
		try {
			user = clientService.getUserByEmail(email);
		
			userInfo = clientService.getUserInfoByEmail(email);
			
			userContract = clientService.getUserContractByEmail(email);
			
			userTariff = clientService.getUserTariffByEmail(email);

		} catch (ServiceException e) { 
			throw new CommandException("Error occured while getting user info",e);
		}
		if(user.isPresent() && userInfo.isPresent() && userContract.isPresent()) {
			User userData = user.get();
			UserInfo userInfoData = userInfo.get();
			UserContract userContractData = userContract.get();
			ArrayList<Integer> userTariffData = userTariff.isPresent() ?userTariff.get()
														  .getContractInfo()
														  .get((int)userContractData.getIdContract()):new ArrayList<>();
			session.setAttribute(LOGIN,userData.getLogin());
			session.setAttribute(BALANCE, userData.getBalance());
			session.setAttribute(NAME, userInfoData.getName());
			session.setAttribute(SURNAME, userInfoData.getSurname());
			session.setAttribute(PHONE, userInfoData.getPhone());
			session.setAttribute(CITY, userInfoData.getCity());
			session.setAttribute(PASSWORD, "****");
			session.setAttribute(CONTRACTID, userContractData.getIdContract());
			session.setAttribute(CONTRACTSTART, userContractData.getStartDate());
			session.setAttribute(CONTRACTEND, userContractData.getEndDate());
			session.setAttribute(CONTRACTDISCOUNT, userContractData.getDiscount());
			session.setAttribute(TARIFFS, userTariffData);
		}	

		session.setAttribute(CABINET_EXIST, "CABINET_LOADED");
		return new Router(CABINET_PAGE, Router.RouterType.REDIRECT);
	}

}
