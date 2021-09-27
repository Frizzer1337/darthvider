package com.roadtoepam.darthvider.command;

import com.roadtoepam.darthvider.command.impl.ChangeLocaleCommand;
import com.roadtoepam.darthvider.command.impl.LoginCommand;
import com.roadtoepam.darthvider.command.impl.PreloadCommand;
import com.roadtoepam.darthvider.command.impl.SignUpCommand;
import com.roadtoepam.darthvider.command.impl.VerifyUserCommand;
import com.roadtoepam.darthvider.service.ClientService;
import com.roadtoepam.darthvider.service.TariffService;

public enum CommandType {

			LOGIN(new LoginCommand(new ClientService())),
			SIGNUP(new SignUpCommand(new ClientService())),
			VERIFYUSER(new VerifyUserCommand(new ClientService())),
			CHANGELOCALE(new ChangeLocaleCommand(new ClientService())),
	        PRELOAD(new PreloadCommand(new TariffService()));
//	        SUCCESSFULLY_REGISTRATION(new SuccessfullyRegistrationCommand(new DefaultService()));
	
	    	private Command command;

	        CommandType(Command command){
	            this.command = command;
	        }

	        public Command getCommand() {
	            return command;
	        }

}
