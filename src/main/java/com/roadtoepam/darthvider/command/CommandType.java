package com.roadtoepam.darthvider.command;

import com.roadtoepam.darthvider.command.impl.ChangeDataCommand;
import com.roadtoepam.darthvider.command.impl.ChangeLocaleCommand;
import com.roadtoepam.darthvider.command.impl.CreateCabinetCommand;
import com.roadtoepam.darthvider.command.impl.LoginCommand;
import com.roadtoepam.darthvider.command.impl.LogoutCommand;
import com.roadtoepam.darthvider.command.impl.PreloadCabinetCommand;
import com.roadtoepam.darthvider.command.impl.PreloadCommand;
import com.roadtoepam.darthvider.command.impl.SendHelpMailCommand;
import com.roadtoepam.darthvider.command.impl.SignUpCommand;
import com.roadtoepam.darthvider.command.impl.VerifyUserCommand;
import com.roadtoepam.darthvider.service.ClientService;
import com.roadtoepam.darthvider.service.TariffService;
import com.roadtoepam.darthvider.service.TicketService;

public enum CommandType {

			LOGIN(new LoginCommand(new ClientService())),
			SIGNUP(new SignUpCommand(new ClientService())),
			VERIFYUSER(new VerifyUserCommand(new ClientService())),
			CHANGELOCALE(new ChangeLocaleCommand(new ClientService())),
	        PRELOAD(new PreloadCommand(new TariffService())),
			SENDHELPMAIL(new SendHelpMailCommand(new TicketService())),
			LOGOUT(new LogoutCommand(new ClientService())),
			CHANGEDATA(new ChangeDataCommand(new ClientService())),
			CREATECABINET(new CreateCabinetCommand(new ClientService())),
			PRELOADCABINET(new PreloadCabinetCommand(new ClientService()));
	
	    	private Command command;

	        CommandType(Command command){
	            this.command = command;
	        }

	        public Command getCommand() {
	            return command;
	        }

}
