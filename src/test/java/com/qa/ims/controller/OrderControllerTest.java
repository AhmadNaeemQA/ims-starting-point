package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private OrderServices orderServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(Long.getLong("1"), 1, Long.getLong("1"), "Iphone", 800));
		orders.add(new Order(Long.getLong("2"), 2, Long.getLong("2"), "Iphone2", 900));
		orders.add(new Order(Long.getLong("3"), 3, Long.getLong("3"), "Iphone3", 1000));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

//	@Test
//	public void createTest() {
//		String firstName = "Chris";
//		String surname = "Perrins";
//		Mockito.doReturn(firstName, surname).when(customerController).getInput();
//		Customer customer = new Customer(firstName, surname);
//		Customer savedCustomer = new Customer(1L, "Chris", "Perrins");
//		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
//		assertEquals(savedCustomer, customerController.create());
//	}
//
//	/**
//	 *
//	 */
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String firstName = "Rhys";
//		String surname = "Thompson";
//		Mockito.doReturn(id, firstName, surname).when(customerController).getInput();
//		Customer customer = new Customer(1L, firstName, surname);
//		Mockito.when(customerServices.update(customer)).thenReturn(customer);
//		assertEquals(customer, customerController.update());
//	}
//
//	/**
//	 * Delete doesn't return anything, so we can just verify that it calls the
//	 * delete method
//	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}

}
