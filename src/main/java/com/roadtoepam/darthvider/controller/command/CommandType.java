package com.roadtoepam.darthvider.controller.command;

import com.roadtoepam.darthvider.controller.command.impl.admin.AllTariffsCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.AllUsersCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.BlockTariffCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.BlockUserCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.ChangePriceCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.CollectMoneyCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.DeleteTariffCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.NewAdminCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.NewTariffCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.UnlockTariffCommand;
import com.roadtoepam.darthvider.controller.command.impl.admin.UnlockUserCommand;
import com.roadtoepam.darthvider.controller.command.impl.auth.LoginCommand;
import com.roadtoepam.darthvider.controller.command.impl.auth.LogoutCommand;
import com.roadtoepam.darthvider.controller.command.impl.auth.SignUpCommand;
import com.roadtoepam.darthvider.controller.command.impl.auth.VerifyUserCommand;
import com.roadtoepam.darthvider.controller.command.impl.cabinet.AddTariffCommand;
import com.roadtoepam.darthvider.controller.command.impl.cabinet.ChangeDataCommand;
import com.roadtoepam.darthvider.controller.command.impl.cabinet.CreateCabinetCommand;
import com.roadtoepam.darthvider.controller.command.impl.cabinet.PreloadCabinetCommand;
import com.roadtoepam.darthvider.controller.command.impl.guest.ChangeLocaleCommand;
import com.roadtoepam.darthvider.controller.command.impl.guest.PreloadCommand;
import com.roadtoepam.darthvider.controller.command.impl.guest.SendHelpMailCommand;
import com.roadtoepam.darthvider.model.service.ClientService;
import com.roadtoepam.darthvider.model.service.TariffService;
import com.roadtoepam.darthvider.model.service.TicketService;

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
