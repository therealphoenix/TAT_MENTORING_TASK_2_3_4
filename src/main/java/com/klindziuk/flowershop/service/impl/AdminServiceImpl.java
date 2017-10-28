package com.klindziuk.flowershop.service.impl;

import com.klindziuk.flowershop.dao.FlowerDAO;
import com.klindziuk.flowershop.dao.exception.DAOException;
import com.klindziuk.flowershop.dao.factory.DAOFactory;
import com.klindziuk.flowershop.model.Flower;
import com.klindziuk.flowershop.service.AdminService;
import com.klindziuk.flowershop.service.exception.ServiceException;

public class AdminServiceImpl extends ClientServiceImpl implements AdminService {
    private static final String NULL_FLOWER_EXCEPTION_MESSAGE = "Cannot add null.";
    private static final String NULL_FLOWER_DESCPRIPTION_MESSAGE = "Cannot update description with null.";
    private static final String NULL_FLOWER_UPDATE_MESSAGE = "Cannot update with zero parameters.";
    private static final String NULL_FLOWER_DELETE_EXCEPTION_MESSAGE = "Cannot remove null.";
    private final FlowerDAO flowerDAO = DAOFactory.getInstance().getFlowerDAO();

    @Override
    public boolean addFlower(Flower flower) throws ServiceException {
        if (null == flower) {
            throw new ServiceException(NULL_FLOWER_EXCEPTION_MESSAGE);
        }
        boolean result = false;
        try {
            result = flowerDAO.addFlower(flower);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteFlower(int flowerId) throws ServiceException {
        if (0 == flowerId) {
            throw new ServiceException(NULL_FLOWER_DELETE_EXCEPTION_MESSAGE);
        }
        boolean result = false;
        try {
            result = flowerDAO.deleteFlower(flowerId);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateFlowerDescription(int flowerId, String name, float price) throws ServiceException {
        if (0 == flowerId) {
            throw new ServiceException(NULL_FLOWER_UPDATE_MESSAGE);
        }
        if (null == name) {
            throw new ServiceException(NULL_FLOWER_DESCPRIPTION_MESSAGE);
        }
        boolean result = false;
        try {
            result = flowerDAO.updateFlowerDescription(flowerId, name, price);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return result;
    }
}
