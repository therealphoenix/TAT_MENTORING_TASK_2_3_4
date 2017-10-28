package com.klindziuk.flowershop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klindziuk.flowershop.dao.FlowerDAO;
import com.klindziuk.flowershop.dao.exception.DAOException;
import com.klindziuk.flowershop.dao.util.DBconnector;
import com.klindziuk.flowershop.model.Flower;

public class SQLFlowerDAO implements FlowerDAO {

	private static final boolean SET_IS_AVAILABLE = true;
	private static final boolean SET_IS_UNAVAILABLE = false;
	private static final String FLOWER_ID_COLUMN_LABEL = "id";
	private static final String FLOWER_NAME_COLUMN_LABEL = "name";
	private static final String FLOWER_PRICE_COLUMN_LABEL = "price";
	private static final String FLOWER_COUNTRY_COLUMN_LABEL = "country";
	private static final String FLOWER_IS_AVAILABLE_COLUMN_LABEL = "isAvailable";
	private static final String SQL_EXCEPTION_MESSAGE = "Cannot perform SQL command";
	private static final String EMPTY_EXCEPTION_MESSAGE = "Unfortunatelly we don't have this flowers.";
	private static final String FINDBY_NAME_EXCEPTION_MESSAGE = "There are no flowers with this name";
	private static final String FINDBY_AUTHOR_EXCEPTION_MESSAGE = "There are no flowers with this country";
	private static final String FLOWER_UPDATE_QUERY = "UPDATE flower SET isAvailable = ?  WHERE id = ?";
	private static final String FLOWER_NAME_QUERY = "SELECT id, name from flower";
	private static final String FLOWER_AUTHOR_QUERY = "SELECT id, country from flower";
	private static final String ADD_FLOWER_QUERY = "INSERT INTO flower (name, price, country, isAvailable) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_FLOWER_QUERY = "UPDATE flower SET name = ?, price = ?  WHERE id = ?";
	private static final String DELETE_FLOWER_QUERY = "DELETE FROM flower where id = ?";
	private static final String GET_FLOWER_QUERY = "SELECT flower.id, flower.name, flower.price, flower.country,"
			+ " flower.isAvailable FROM flower WHERE id = ?";
	private static final String SELECT_ALL_FLOWER_QUERY = "SELECT flower.id, flower.name, flower.price, flower.country,"
			+ " flower.isAvailable FROM flower ";

	DBconnector connector;
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;

	public SQLFlowerDAO() {
		connector = DBconnector.getInstance();
	}

	@Override
	public boolean addFlower(Flower flower) throws DAOException {
		boolean rowInserted = false;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(ADD_FLOWER_QUERY);
			preparedStatement.setString(1, flower.getName());
			preparedStatement.setFloat(2, flower.getPrice());
			preparedStatement.setString(3, flower.getCountry());
			preparedStatement.setBoolean(4, flower.isAvailable());
			rowInserted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return rowInserted;
	}

	@Override
	public boolean updateFlowerDescription(int flowerId, String name, float price) throws DAOException {
		boolean rowUpdated = false;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(UPDATE_FLOWER_QUERY);
			preparedStatement.setString(1, name);
			preparedStatement.setFloat(2, price);
			preparedStatement.setInt(3, flowerId);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteFlower(int flowerId) throws DAOException {
		boolean rowDeleted = false;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(DELETE_FLOWER_QUERY);
			preparedStatement.setInt(1, flowerId);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return rowDeleted;
	}

	@Override
	public Flower getFlower(int id) throws DAOException {
		Flower flower = null;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(GET_FLOWER_QUERY);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(FLOWER_NAME_COLUMN_LABEL);
				float price = resultSet.getFloat(FLOWER_PRICE_COLUMN_LABEL);
				String country = resultSet.getString(FLOWER_COUNTRY_COLUMN_LABEL);
				boolean isAvailable = resultSet.getBoolean(FLOWER_IS_AVAILABLE_COLUMN_LABEL);
				flower = new Flower(id, name, price, country, isAvailable);
			} else {
				throw new DAOException(EMPTY_EXCEPTION_MESSAGE);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return flower;
	}

	@Override
	public List<Flower> getAllFlowers() throws DAOException {
		List<Flower> flowers = new ArrayList<>();
		try {
			connector.connect();
			statement = connector.getJdbcConnection().createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_FLOWER_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt(FLOWER_ID_COLUMN_LABEL);
				String name = resultSet.getString(FLOWER_NAME_COLUMN_LABEL);
				float price = resultSet.getFloat(FLOWER_PRICE_COLUMN_LABEL);
				String country = resultSet.getString(FLOWER_COUNTRY_COLUMN_LABEL);
				boolean isAvailable = resultSet.getBoolean(FLOWER_IS_AVAILABLE_COLUMN_LABEL);
				Flower flower = new Flower(id, name, price, country, isAvailable);
				flowers.add(flower);
			}
			if (flowers.isEmpty()) {
				throw new DAOException(EMPTY_EXCEPTION_MESSAGE);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				statement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return flowers;
	}

	@Override
	public List<Flower> findByName(String name) throws DAOException {
		List<Flower> list = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		int flowerId = 0;
		String searchedFlowerName = "";
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(FLOWER_NAME_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				flowerId = resultSet.getInt(FLOWER_ID_COLUMN_LABEL);
				searchedFlowerName = resultSet.getString(FLOWER_NAME_COLUMN_LABEL);
				map.put(flowerId, searchedFlowerName);
			}
			for (Map.Entry<Integer, String> entry : map.entrySet()) {
				if (name.contains(entry.getValue())) {
					list.add(getFlower(entry.getKey()));
				}
			}
			if (list.isEmpty()) {
				throw new DAOException(EMPTY_EXCEPTION_MESSAGE);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, FINDBY_NAME_EXCEPTION_MESSAGE);
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return list;
	}

	@Override
	public List<Flower> findByCountry(String country) throws DAOException {
		List<Flower> list = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		int flowerId = 0;
		String searchedCountryName = "";
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(FLOWER_AUTHOR_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				flowerId = resultSet.getInt(FLOWER_ID_COLUMN_LABEL);
				searchedCountryName = resultSet.getString(FLOWER_PRICE_COLUMN_LABEL);
				map.put(flowerId, searchedCountryName);
			}
			for (Map.Entry<Integer, String> entry : map.entrySet()) {
				if (country.contains(entry.getValue())) {
					list.add(getFlower(entry.getKey()));
				}
			}
			if (list.isEmpty()) {
				throw new DAOException(EMPTY_EXCEPTION_MESSAGE);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, FINDBY_AUTHOR_EXCEPTION_MESSAGE);
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return list;
	}

	@Override
	public boolean setFlowerUnavailable(int flowerId) throws DAOException {
		boolean rowUpdated = false;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(FLOWER_UPDATE_QUERY);
			preparedStatement.setBoolean(1, SET_IS_UNAVAILABLE);
			preparedStatement.setInt(2, flowerId);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return rowUpdated;
	}

	@Override
	public boolean setFlowerAvailable(int flowerId) throws DAOException {
		boolean rowUpdated = false;
		try {
			connector.connect();
			preparedStatement = connector.getJdbcConnection().prepareStatement(FLOWER_UPDATE_QUERY);
			preparedStatement.setBoolean(1, SET_IS_AVAILABLE);
			preparedStatement.setInt(2, flowerId);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new DAOException(sqlex, SQL_EXCEPTION_MESSAGE);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connector.disconnect();
			} catch (Exception e) {
				/* ignored */ }
		}
		return rowUpdated;
	}
}
