package com.roadtoepam.darthvider.controller.command.impl.admin;

import static com.roadtoepam.darthvider.controller.command.PageRouting.*;
import static com.roadtoepam.darthvider.controller.command.RequestContent.*;

import java.util.ArrayList;
import java.util.Optional;

import com.roadtoepam.darthvider.controller.command.Command;
import com.roadtoepam.darthvider.controller.command.Router;
import com.roadtoepam.darthvider.exception.CommandException;
import com.roadtoepam.darthvider.exception.ServiceException;
import com.roadtoepam.darthvider.model.entity.ConnectedTariff;
import com.roadtoepam.darthvider.model.entity.Tariff;
import com.roadtoepam.darthvider.model.entity.User;
import com.roadtoepam.darthvider.model.entity.UserContract;
import com.roadtoepam.darthvider.model.service.ClientService;
import com.roadtoepam.darthvider.model.service.TariffService;

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
		
		final float percent = 0.01f;
		final int hundredPercent = 1;
		final int decade = 10;
		final int month = 30;
		ArrayList<User> userList;
		try {
			userList = (ArrayList<User>) clientService.getAllUsers();
			for(User user : userList) {
				Optional<UserContract> contract= clientService.getUserContractByEmail(user.getEmail());
				Optional<ConnectedTariff> tariff= clientService.getUserTariffByEmail(user.getEmail());
				if(tariff.isPresent() && contract.isPresent()) {
					ConnectedTariff userTariff = tariff.get();
					UserContract userContract = contract.get();
					float contractDiscount = userContract.getDiscount() * percent;
					ArrayList<Integer> userTariffData = userTariff.getContractInfo()					  
								.get((int)userContract.getIdContract());
					float dailyPayment = 0;
					for(int tariffId : userTariffData) {
						
						Optional<Tariff> tariffOptional = tariffService.getTariffById(tariffId);
						
						if(tariffOptional.isPresent()) {
							Tariff tariffData = tariffOptional.get();
							float tariffPrice = tariffData.getPrice();
							float tariffDiscount = tariffData.getDiscount()* percent;
							switch(tariffData.getDueType()) {
							case 0:{
								dailyPayment+=(hundredPercent-contractDiscount-tariffDiscount)*tariffPrice;
								break;
								}
							case 1:{
								dailyPayment+=(hundredPercent-contractDiscount-tariffDiscount)*tariffPrice/month;
								break;
								}
							case 2:{
								dailyPayment+=(hundredPercent-contractDiscount-tariffDiscount)*tariffPrice/decade;
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
