package com.klindziuk.flowershop.dao.factory;

import com.klindziuk.flowershop.dao.FlowerDAO;
import com.klindziuk.flowershop.dao.impl.SQLFlowerDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final FlowerDAO sqlFlowerImpl = new SQLFlowerDAO();

	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	public FlowerDAO getFlowerDAO() {
		return sqlFlowerImpl;
	}
	
	}
