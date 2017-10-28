package com.klindziuk.flowershop.controller.command.impl;

import com.klindziuk.flowershop.model.Bouquet;
import com.klindziuk.flowershop.model.Flower;
import com.klindziuk.flowershop.service.ClientService;
import org.apache.log4j.Logger;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.util.RequestParser;
import com.klindziuk.flowershop.service.exception.ServiceException;
import com.klindziuk.flowershop.service.factory.ServiceFactory;

import java.util.List;
import java.util.Map;

public class GetBouquet implements Command {
	private static final Logger logger = Logger.getLogger(GetBouquet.class);
	
	@Override
	public String execute(String request) {
		if(null == request || request.isEmpty()){
			logger.error(INVALID_REQUEST_EXCEPTION_MESSAGE);
			return INVALID_REQUEST_EXCEPTION_MESSAGE;	
			}
		logger.info(PERFORMING_COMMAND_MESSAGE + this.getClass().getSimpleName());
		String response = "";
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			Map<Integer, Integer> orderMap = RequestParser.getBouquetMap(request);
			List<Flower> flowerList = clientService.getAllFlowers();
			response = new Bouquet(orderMap,flowerList).toString();
			logger.info(response);
		} catch (ServiceException seex) {
			logger.error(seex.getMessage(), seex);
			response = UNSUCCESSFUL_OPERATION_MESSAGE + seex.getMessage();
		} catch (IllegalArgumentException ieax) {
			logger.error(ieax.getMessage(), ieax);
			response = UNSUCCESSFUL_OPERATION_MESSAGE + ieax.getMessage();
		}
		return response;
	}
}
