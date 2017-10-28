package com.klindziuk.flowershop.dao;

import java.util.List;

import com.klindziuk.flowershop.dao.exception.DAOException;
import com.klindziuk.flowershop.model.Flower;

public interface FlowerDAO {
	boolean addFlower(Flower flower) throws DAOException;
	boolean updateFlowerDescription(int flowerId, String name, float price) throws DAOException;
	boolean setFlowerAvailable(int flowerId) throws DAOException;
	boolean setFlowerUnavailable(int flowerId) throws DAOException;
	boolean deleteFlower(int flowerId) throws DAOException;
	Flower getFlower(int id) throws DAOException;
	List<Flower> getAllFlowers() throws DAOException;
	List<Flower> findByName(String name) throws DAOException;
	List<Flower> findByCountry(String country) throws DAOException;
}
