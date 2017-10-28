package com.klindziuk.flowershop.controller.command.impl;

import com.klindziuk.flowershop.service.ClientService;
import org.apache.log4j.Logger;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.util.RequestParser;
import com.klindziuk.flowershop.controller.util.ResponseBuilder;
import com.klindziuk.flowershop.service.exception.ServiceException;
import com.klindziuk.flowershop.service.factory.ServiceFactory;

public class FindByCountry implements Command {
	private static final Logger logger = Logger.getLogger(FindByCountry.class);
	private static final String UNSUCCESSFUL_OPERATION_MESSAGE = "Cannot perform this operation.";
	
	@Override
	public String execute(String request)  {
		if (null == request || request.isEmpty()) {
			logger.error(INVALID_REQUEST_EXCEPTION_MESSAGE);
			return INVALID_REQUEST_EXCEPTION_MESSAGE;
		}
		logger.info(PERFORMING_COMMAND_MESSAGE + this.getClass().getSimpleName());
		String response = UNSUCCESSFUL_OPERATION_MESSAGE;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			String country = RequestParser.getName(request);
			response = ResponseBuilder.build(clientService.findByCountry(country));
			logger.info(response);
		} catch (ServiceException seex) {
			logger.error(seex.getMessage(), seex);
		} catch (IllegalArgumentException ieax) {
			logger.error(ieax.getMessage(), ieax);
		}
		return response;
	}
}
