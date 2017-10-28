package com.klindziuk.flowershop.controller.command;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.command.impl.*;
import com.klindziuk.flowershop.controller.command.impl.GetBouquet;

public enum AdminCommandName {	

		ADDFLOWER(new AddFlower()),
		DELETEFLOWERBYID(new DeleteFlowerById()),
		UPDATEFLOWERDESCRIPTION(new UpdateFlowerDescription()),
		GETFLOWER(new GetFlower()),
	    GETALLFLOWERS(new GetAllFlowers()),
	    GETBOUQUET(new GetBouquet()),
	    FINDBYNAME(new FindByName()),
	    FINDBYCOUNTRY(new FindByCountry());

		private static final String UNSUPPORTED_OPERATION_MESSAGE = " - this command unfortunately unsupported.";
		private Command command;
	    private static HashMap<String, Command> adminCommandMap;

	    static {
	        adminCommandMap = new HashMap<>();
	        for (AdminCommandName s : AdminCommandName.values())
	            adminCommandMap.put(s.name().toUpperCase(), s.command);
	    }

	    AdminCommandName(Command command) {
	        this.command = command;
	    }

	    public static Command getCommandByName(String name) throws OperationNotSupportedException {
	    	if(!adminCommandMap.containsKey(name.toUpperCase()) || name.isEmpty())  {
	        	 throw new OperationNotSupportedException(name + UNSUPPORTED_OPERATION_MESSAGE );
	        }
	        return adminCommandMap.get(name.toUpperCase());
	    }
	}


