package com.roadtoepam.darthvider.command;

import com.roadtoepam.darthvider.command.impl.LoginCommand;
import com.roadtoepam.darthvider.command.impl.SignUpCommand;
import com.roadtoepam.darthvider.service.ClientService;

public enum CommandType {

			LOGIN(new LoginCommand(new ClientService())),
			SIGNUP(new SignUpCommand(new ClientService()));
//	        LOGOUT(new LogOutCommand(new DefaultService())),
//	        SUCCESSFULLY_REGISTRATION(new SuccessfullyRegistrationCommand(new DefaultService()));
	
	    	private Command command;

	        CommandType(Command command){
	            this.command = command;
	        }

	        public Command getCommand() {
	            return command;
	        }

}
