package com.klindziuk.flowershop.controller.command.impl;

import org.apache.log4j.Logger;

import com.klindziuk.flowershop.controller.Command;
import com.klindziuk.flowershop.controller.util.RequestParser;
import com.klindziuk.flowershop.service.AdminService;
import com.klindziuk.flowershop.service.exception.ServiceException;
import com.klindziuk.flowershop.service.factory.ServiceFactory;

public class DeleteFlowerById implements Command{
	private static final Logger logger = Logger.getLogger(DeleteFlowerById.class);
	private static final String SUCCESSFUL_DELETING_MESSAGE = "Flower deleted successfully.";
	
	@Override
	public String execute(String request) {
		if (null == request || request.isEmpty()) {
			logger.error(INVALID_REQUEST_EXCEPTION_MESSAGE);
			 return INVALID_REQUEST_EXCEPTION_MESSAGE;
		}
		logger.info(PERFORMING_COMMAND_MESSAGE + this.getClass().getSimpleName());
		String response = UNSUCCESSFUL_OPERATION_MESSAGE;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		try {
			int flowerId = RequestParser.getId(request);
			if (adminService.deleteFlower(flowerId)) {
				System.out.println(adminService.deleteFlower(flowerId));
				response = SUCCESSFUL_DELETING_MESSAGE;
				logger.info(response);
			}
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
