package com.klindziuk.flowershop.service;

import java.util.List;

import com.klindziuk.flowershop.model.Bouquet;
import com.klindziuk.flowershop.model.Flower;
import com.klindziuk.flowershop.service.exception.ServiceException;

public interface AdminService {

	boolean addFlower(Flower flower) throws ServiceException;
	boolean deleteFlower(int flowerId) throws ServiceException;
	boolean updateFlowerDescription(int flowerId, String name, float price) throws ServiceException;
	Flower getFlower(int flowerId) throws ServiceException;
	List<Flower> getAllFlowers() throws ServiceException;
	List<Flower> findByName(String name) throws ServiceException;
	List<Flower> findByCountry(String country) throws ServiceException;
}
