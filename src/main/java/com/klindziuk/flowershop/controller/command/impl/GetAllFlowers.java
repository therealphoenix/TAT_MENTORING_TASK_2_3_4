package com.klindziuk.flowershop.controller.command.impl;

import org.apache.log4j.Logger;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.util.ResponseBuilder;
import com.klindziuk.flowershop.service.ClientService;
import com.klindziuk.flowershop.service.exception.ServiceException;
import com.klindziuk.flowershop.service.factory.ServiceFactory;

public class GetAllFlowers implements Command {
	private static final Logger logger = Logger.getLogger(GetAllFlowers.class);
		
	@Override
	public String execute(String request) {
		if (null == request || request.isEmpty()) {
			logger.error(INVALID_REQUEST_EXCEPTION_MESSAGE);
			return INVALID_REQUEST_EXCEPTION_MESSAGE;
		}
		logger.info(PERFORMING_COMMAND_MESSAGE + this.getClass().getSimpleName());
		String response = UNSUCCESSFUL_OPERATION_MESSAGE;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			response = ResponseBuilder.build(clientService.getAllFlowers());
			logger.info(response);
		} catch (ServiceException seex) {
			logger.error(seex.getMessage(), seex);
			response = UNSUCCESSFUL_OPERATION_MESSAGE + seex.getMessage();
		}
		return response;
	}
}
