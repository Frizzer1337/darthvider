package com.roadtoepam.darthvider.command;

import com.roadtoepam.darthvider.command.impl.ChangeDataCommand;
import com.roadtoepam.darthvider.command.impl.ChangeLocaleCommand;
import com.roadtoepam.darthvider.command.impl.CreateCabinetCommand;
import com.roadtoepam.darthvider.command.impl.LoginCommand;
import com.roadtoepam.darthvider.command.impl.LogoutCommand;
import com.roadtoepam.darthvider.command.impl.PreloadCabinetCommand;
import com.roadtoepam.darthvider.command.impl.DeleteTariffCommand;
import com.roadtoepam.darthvider.command.impl.PreloadCommand;
import com.roadtoepam.darthvider.command.impl.SendHelpMailCommand;
import com.roadtoepam.darthvider.command.impl.SignUpCommand;
import com.roadtoepam.darthvider.command.impl.VerifyUserCommand;
import com.roadtoepam.darthvider.command.impl.AddTariffCommand;
import com.roadtoepam.darthvider.command.impl.AllUsersCommand;
import com.roadtoepam.darthvider.command.impl.AllTariffsCommand;
import com.roadtoepam.darthvider.command.impl.BlockUserCommand;
import com.roadtoepam.darthvider.command.impl.UnlockUserCommand;
import com.roadtoepam.darthvider.command.impl.BlockTariffCommand;
import com.roadtoepam.darthvider.command.impl.UnlockTariffCommand;
import com.roadtoepam.darthvider.command.impl.NewTariffCommand;
import com.roadtoepam.darthvider.command.impl.NewAdminCommand;
import com.roadtoepam.darthvider.command.impl.ChangePriceCommand;
import com.roadtoepam.darthvider.command.impl.CollectMoneyCommand;
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
			PRELOADCABINET(new PreloadCabinetCommand(new ClientService())),
			DELETETARIFF(new DeleteTariffCommand(new ClientService())),
			ADDTARIFF(new AddTariffCommand(new ClientService())),
			ALLUSERS(new AllUsersCommand(new ClientService())),
			ALLTARIFFS(new AllTariffsCommand(new TariffService())),
			BLOCKUSER(new BlockUserCommand(new ClientService())),
			UNLOCKUSER(new UnlockUserCommand(new ClientService())),
			BLOCKTARIFF(new BlockTariffCommand(new TariffService())),
			UNLOCKTARIFF(new UnlockTariffCommand(new TariffService())),
			NEWTARIFF(new NewTariffCommand(new TariffService())),
			NEWADMIN(new NewAdminCommand(new ClientService())),
			CHANGEPRICE(new ChangePriceCommand(new TariffService())),
			COLLECTMONEY(new CollectMoneyCommand(new ClientService(),new TariffService()));
	
	    	private Command command;

	        CommandType(Command command){
	            this.command = command;
	        }

	        public Command getCommand() {
	            return command;
	        }

}
