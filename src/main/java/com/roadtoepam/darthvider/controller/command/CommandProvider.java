package com.roadtoepam.darthvider.controller.command;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommandProvider {
		
		private static final Logger logger = LogManager.getLogger();
	    private static CommandProvider instance;

	    public static CommandProvider getInstance() {
	    	
	        if (instance == null) {
	        	logger.info("Command provider was created");
	            instance = new CommandProvider();
	        }
	        
	        return instance;
	    }

	    public Optional<Command> getCommand(String commandName) {
	    	
	    	Optional<Command> command = Arrays.stream(CommandType.values())
                    .filter(currentType -> currentType.name().equalsIgnoreCase(commandName))
                    .map(CommandType::getCommand)
                    .findAny();
	    	
	        return command;
	    }
}
	
	


