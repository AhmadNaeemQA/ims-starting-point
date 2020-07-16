package com.qa.ims.persistance.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoMysqTest {

	@Spy
	@InjectMocks
	OrderDaoMysql order = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
			"root");

	@Spy
	@InjectMocks
	OrderDaoMysql order1 = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
			"Wrong Passwrod");

	@Test
	public void constructorTest() {
		OrderDaoMysql order = new OrderDaoMysql("root", "root");
		OrderDaoMysql order1 = new OrderDaoMysql("jdbc:mysql://localhost:3306/ims", "root", "root");
	}

	@Test
	public void readAllTest() {
		ArrayList<Order> orders = new ArrayList<>();
		Mockito.doReturn("all").when(order).getInput();
		orders = (ArrayList<Order>) order.readAll();
		assertNotNull(orders);
	}

	@Test
	public void readOneTest() {
		ArrayList<Order> orders = new ArrayList<>();
		Mockito.doReturn("one", "1").when(order).getInput();
		orders = (ArrayList<Order>) order.readAll();

		assertNotNull(orders);
	}

	@Test
	public void readAllNegativeTest1() {
		ArrayList<Order> orders = new ArrayList<>();
		Mockito.doReturn("false input", "one", "1").when(order).getInput();
		orders = (ArrayList<Order>) order.readAll();
		assertNotNull(orders);
	}

	@Test
	public void readAllNegativeTest2() {
		ArrayList<Order> orders = new ArrayList<>();
		Mockito.doReturn("all").when(order1).getInput();
		orders = (ArrayList<Order>) order1.readAll();
		assertEquals(new ArrayList<>(), orders);
	}

	@Test
	public void readAllNegativeTest3() {
		ArrayList<Order> orders = new ArrayList<>();
		Mockito.doReturn("one", "1").when(order1).getInput();
		orders = (ArrayList<Order>) order1.readAll();
		assertNull(orders);
	}

	@Test
	public void createTest() {
		Order newOrder = new Order();
		newOrder.setCustomer_id(1);
		newOrder.addItem(1);
		Order outcome = order.create(newOrder);
		assertNotNull(outcome);
		Mockito.doReturn("whole").when(order).getInput();
		order.delete(outcome.getOrder_id());
	}

	@Test
	public void createTestNegative() {
		Order newOrder = new Order();
		newOrder.setCustomer_id(1);
		newOrder.addItem(1);
		assertNull(order1.create(newOrder));
	}

	@Test
	public void deleteItemTest() {
		Order newOrder = new Order();
		newOrder.setCustomer_id(1);
		newOrder.addItem(1);
		Order createdOrder = order.create(newOrder);
		Mockito.doReturn("item", "1").when(order).getInput();
		order.delete(createdOrder.getOrder_id());
	}

	@Test
	public void deleteOrderTestNegative() {
		Order order = new Order();
		order.setOrder_id(Long.parseLong("1"));
		Mockito.doReturn("whole", "1").when(order1).getInput();
		order1.delete(order.getOrder_id());
	}

	@Test
	public void deleteItemTestNegative() {
		Order order = new Order();
		order.setOrder_id(Long.parseLong("1"));
		Mockito.doReturn("item", "1").when(order1).getInput();
		order1.delete(order.getOrder_id());
	}

	@Test
	public void deleteTestNegativeOther() {
		Order order = new Order();
		order.setOrder_id(Long.parseLong("1"));
		Mockito.doReturn("invalid input", "whole").when(order1).getInput();
		order1.delete(order.getOrder_id());
	}

	@Test
	public void readLatestTestNegative() {
		assertNull(order1.readLatest());
	}

}
