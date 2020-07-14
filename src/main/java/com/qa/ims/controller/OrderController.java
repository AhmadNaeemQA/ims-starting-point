package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	// Logger used for output
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	//
	private CrudServices<Order> orderService;

	// Constructor -
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	// Used to get user input
	String getInput() {
		return Utils.getInput();
	}

	// 4 methods overiding interface CrudController
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		int total = 0;
		for (Order order : orders) {
			LOGGER.info(order.toString());
			total = total + order.getPrice();
		}
		LOGGER.info("The total cost of the items shown above are: £" + total);

		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer id");
		int customer_id = Integer.parseInt(getInput());
		Order order = new Order();
		order.setCustomer_id(customer_id);
		boolean moreItems = true;
		while (moreItems = true) {
			// make sure to only input numbers which are id's of items stored currently on
			// the DB
			LOGGER.info("What is the ID of the items you would like to add to this order (enter '0' to end)");
			int item_id = Integer.parseInt(getInput());
			if (item_id == 0) {
				break;
			} else {
				order.addItem(item_id);
			}
		}
		order = orderService.create(order);
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Do you want to add or delete an item from your order? ");
		String action = getInput();
		if (action.equalsIgnoreCase("add")) {
			create();
		} else if (action.equalsIgnoreCase("delete")) {
			delete();
		} else {
			LOGGER.info("Input not valid");
		}

		LOGGER.info("Order Updated");
		return null;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter your customer ID ");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Deleted ");
	}

}