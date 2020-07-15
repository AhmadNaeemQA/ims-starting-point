package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order order2;
	private Order order3;
	private Order order4;

	@Before
	public void setUp() {
		order = new Order(Long.valueOf("1"), 1, Long.valueOf("1"), "Iphone", 800);
		order2 = new Order(Long.valueOf(2), 2, Long.valueOf(2));
		order3 = new Order();
	}

	@Test
	public void setterTest() {
		order3.setOrder_id(Long.valueOf(1));
		assertNotNull(order3.getOrder_id());
		order3.setCustomer_id(1);
		assertNotNull(order3.getCustomer_id());
	}

	@Test
	public void toStringTest() {
		assertEquals(order.toString(), "Order ID:" + order.getOrder_id() + " Customer ID: " + order.getCustomer_id()
				+ " Item ID: " + order.getId() + " Name: " + order.getName() + " Price: £" + order.getPrice());
	}

	@Test
	public void basketArrayTest() {
		order.addItem(1);
		order.addItem(2);
		order.addItem(3);
		assertNotNull(order.getItem(0));
		assertNotNull(order.getItem(1));
		assertNotNull(order.getItem(2));
		assertNotNull(order.getSize());
		order.getAllItems();
	}

}
