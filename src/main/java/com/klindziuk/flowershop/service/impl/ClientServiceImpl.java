package com.klindziuk.flowershop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.klindziuk.flowershop.dao.FlowerDAO;
import com.klindziuk.flowershop.dao.exception.DAOException;
import com.klindziuk.flowershop.dao.factory.DAOFactory;
import com.klindziuk.flowershop.model.Bouquet;
import com.klindziuk.flowershop.model.Flower;
import com.klindziuk.flowershop.service.ClientService;
import com.klindziuk.flowershop.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {
    protected static final String NAME_NULL_MESSAGE_EXCEPTION = "There is no flower with this empty name.";
    protected static final String COUNTRY_NULL_MESSAGE_EXCEPTION = "There is no flower with empty country.";
    protected static final String FLOWER_NULL_MESSAGE_EXCEPTION = "There is no flower with this id.";
    private final FlowerDAO flowerDao = DAOFactory.getInstance().getFlowerDAO();

    public Flower getFlower(int flowerId) throws ServiceException {
        Flower flower = null;
        if (0 == flowerId) {
            throw new ServiceException(FLOWER_NULL_MESSAGE_EXCEPTION);
        }
        try {
            flower = flowerDao.getFlower(flowerId);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return flower;
    }

    public List<Flower> getAllFlowers() throws ServiceException {
        List<Flower> flowers = new ArrayList<>();
        try {
            flowers = flowerDao.getAllFlowers();
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return flowers;
    }

    public List<Flower> findByName(String name) throws ServiceException {
        if (null == name || name.isEmpty()) {
            throw new ServiceException(NAME_NULL_MESSAGE_EXCEPTION);
        }
        List<Flower> flowers = new ArrayList<>();
        try {
            flowers = flowerDao.findByName(name);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return flowers;
    }

    public List<Flower> findByCountry(String country) throws ServiceException {
        if (null == country || country.isEmpty()) {
            throw new ServiceException(COUNTRY_NULL_MESSAGE_EXCEPTION);
        }
        List<Flower> flowers = new ArrayList<>();
        try {
            flowers = flowerDao.findByCountry(country);
        } catch (DAOException daoex) {
            throw new ServiceException(daoex, daoex.getMessage());
        }
        return flowers;
    }
}
