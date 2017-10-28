package com.klindziuk.flowershop.controller;

import javax.naming.OperationNotSupportedException;

import org.apache.log4j.Logger;

import com.klindziuk.flowershop.controller.command.AdminCommandName;
import com.klindziuk.flowershop.controller.command.ClientCommandName;
import com.klindziuk.flowershop.controller.util.RequestParser;

public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);
    private static Controller instance = null;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public String executeClientTask(String request) {
        String commandName = RequestParser.getCommand(request);
        Command executionCommand;
        try {
            executionCommand = ClientCommandName.getCommandByName(commandName);
        } catch (OperationNotSupportedException onsex) {
            logger.error(onsex.getMessage(), onsex);
            return onsex.getMessage();
        }
        return executionCommand.execute(request);
    }

    public String executeAdminTask(String request) {
        String commandName = RequestParser.getCommand(request);
        Command executionCommand;
        try {
            executionCommand = AdminCommandName.getCommandByName(commandName);
        } catch (OperationNotSupportedException onsex) {
            logger.error(onsex.getMessage(), onsex);
            return onsex.getMessage();
        }
        return executionCommand.execute(request);
    }

}
