package com.klindziuk.flowershop.controller.command;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.command.impl.*;

public enum ClientCommandName {

	GETFLOWER(new GetFlower()),
	GETALLFLOWERS(new GetAllFlowers()),
	GETBOUQUET(new GetBouquet()),
	FINDBYNAME(new FindByName()),
	FINDBYCOUNTRY(new FindByCountry());
	    
	    private static final String UNSUPPORTED_OPERATION_MESSAGE = " - this command unfortunately unsupported.";
	    private Command command;
	    private static HashMap<String, Command> clientCommandMap;

	    static {
	        clientCommandMap = new HashMap<>();
	        for (ClientCommandName s : ClientCommandName.values())
	            clientCommandMap.put(s.name().toUpperCase(), s.command);
	    }

	     ClientCommandName(Command command) {
	        this.command = command;
	    }

	    public static Command getCommandByName(String name) throws OperationNotSupportedException {
	        if(!clientCommandMap.containsKey(name.toUpperCase()) || name.isEmpty()) {
	        	 throw new OperationNotSupportedException(name + UNSUPPORTED_OPERATION_MESSAGE );
	        }
	        return clientCommandMap.get(name.toUpperCase());
	    }
	}
