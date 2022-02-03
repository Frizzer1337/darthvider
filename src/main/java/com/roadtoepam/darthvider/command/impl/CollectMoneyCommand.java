package com.roadtoepam.darthvider.command.impl;

import static com.roadtoepam.darthvider.command.PageRouting.*;
import static com.roadtoepam.darthvider.command.RequestContent.*;

import java.util.ArrayList;
import java.util.Optional;

import com.roadtoepam.darthvider.command.Command;
import com.roadtoepam.darthvider.command.Router;
import com.roadtoepam.darthvider.entity.Tariff;
import com.roadtoepam.darthvider.entity.User;
import com.roadtoepam.darthvider.entity.UserContract;
import com.roadtoepam.darthvider.entity.ConnectedTariff;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.service.ClientService;
import com.roadtoepam.darthvider.service.TariffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CollectMoneyCommand implements Command {
	
	private ClientService clientService;
	private TariffService tariffService;

    public CollectMoneyCommand(ClientService clientService,TariffService tariffService){
    	
        this.clientService = clientService;
        this.tariffService = tariffService;
        
    }

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {	
		
		
		ArrayList<User> userList;
		try {
			userList = (ArrayList<User>) clientService.getAllUsers();
			for(User user : userList) {
				Optional<UserContract> contract= clientService.getUserContractByEmail(user.getEmail());
				Optional<ConnectedTariff> tariff= clientService.getUserTariffByEmail(user.getEmail());
				if(tariff.isPresent() && contract.isPresent()) {
					ConnectedTariff userTariff = tariff.get();
					UserContract userContract = contract.get();
					float contractDiscount = userContract.getDiscount() * 0.01f;
					ArrayList<Integer> userTariffData = userTariff.getContractInfo()					  
								.get((int)userContract.getIdContract());
					float dailyPayment = 0;
					for(int tariffId : userTariffData) {
						
						Optional<Tariff> tariffOptional = tariffService.getTariffById(tariffId);
						
						if(tariffOptional.isPresent()) {
							Tariff tariffData = tariffOptional.get();
							float tariffPrice = tariffData.getPrice();
							float tariffDiscount = tariffData.getDiscount()* 0.01f;
							switch(tariffData.getDueType()) {
							case 0:{
								dailyPayment+=(1-contractDiscount-tariffDiscount)*tariffPrice;
								break;
								}
							case 1:{
								dailyPayment+=(1-contractDiscount-tariffDiscount)*tariffPrice/30;
								break;
								}
							case 2:{
								dailyPayment+=(1-contractDiscount-tariffDiscount)*tariffPrice/10;
								break;
								}
							}
						}				
					}
					clientService.changeBalance(user.getId(),user.getBalance()-dailyPayment);
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}	
		
		return new Router(ADMIN_PAGE, Router.RouterType.REDIRECT);
	}


}
