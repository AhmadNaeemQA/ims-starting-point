package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderDaoMysql implements Dao<Order> {

	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME;
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("id");
		int customer_id = resultSet.getInt("customer_id");
		Long item_id = resultSet.getLong("item_id");
		String name = resultSet.getString("name");
		int price = resultSet.getInt("price");
		return new Order(order_id, customer_id, item_id, name, price);
	}

	public String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all customers from the database
	 *
	 * @return A list of customers
	 */
	@Override
	public List<Order> readAll() {
		LOGGER.info("Do you want to read all [all] or specific customer [one]? ");
		String option = getInput();
		if (option.equalsIgnoreCase("one")) {
			LOGGER.info("Enter your Customer ID ");
			int customer_id = Integer.parseInt(getInput());
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(
							"SELECT orders.id, orders.customer_id, orders.item_id, items.name, items.price FROM orders\n"
									+ "INNER JOIN items ON orders.item_id=items.id WHERE orders.customer_id ="
									+ customer_id);) {
				ArrayList<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(orderFromResultSet(resultSet));
				}
				return orders;
			} catch (SQLException e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
			return null;
		} else if (option.equalsIgnoreCase("all")) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(
							"SELECT orders.id, orders.customer_id, orders.item_id, items.name, items.price FROM orders\n"
									+ "INNER JOIN items ON orders.item_id=items.id");) {
				ArrayList<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(orderFromResultSet(resultSet));
				}
				return orders;
			} catch (SQLException e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		} else {
			LOGGER.info("Invalid input, try again");
			readAll();
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT orders.id, orders.customer_id, orders.item_id, items.name, items.price FROM orders INNER JOIN items ON orders.item_id=items.id ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 *
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		for (int i = 0; i < order.getSize(); i++) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("insert into orders(customer_id, item_id) values('" + order.getCustomer_id()
						+ "','" + order.getItem(i) + "')");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
				return null;
			}
		}
		return readLatest();
	}

	@Override
	public Order update(Order order) {

		return null;
	}

	/**
	 * Deletes a customer in the database
	 *
	 * @param id - id of the customer
	 */
	@Override
	public void delete(long id) {
		LOGGER.info("Do you want to delete whole order [whole] or an item from this order [item]");
		String action = getInput();
		if (action.equalsIgnoreCase("whole")) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("delete from orders where customer_id = " + id);
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		} else if (action.equalsIgnoreCase("item")) {
			LOGGER.info("Which item do you want to delete from your order");
			int item_number = Integer.parseInt(getInput());
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate(
						"delete from orders where customer_id = " + id + " and item_id = " + item_number);
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		} else {
			LOGGER.info("invalid input");
			delete(id);
		}
	}

}
