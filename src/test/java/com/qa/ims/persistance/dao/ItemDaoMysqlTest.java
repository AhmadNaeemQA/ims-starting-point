package com.qa.ims.persistance.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import com.qa.ims.persistence.dao.ItemDaoMysql;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemDaoMysqlTest {

	ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root", "root");

	@Test
	public void constructorTest() {
		ItemDaoMysql item = new ItemDaoMysql("root", "root");
		ItemDaoMysql item1 = new ItemDaoMysql("jdbc:mysql://localhost:3306/ims", "root", "root");
	}

	@Test
	public void readAllTestPositive() {
		ArrayList<Item> items = new ArrayList<>();
		items = (ArrayList<Item>) item.readAll();
		assertNotNull(items);
	}

	@Test
	public void readAllTestNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		ArrayList<Item> items = new ArrayList<>();
		items = (ArrayList<Item>) item.readAll();
		assertEquals(new ArrayList<>(), items);
	}

	@Test
	public void createTest() {
		Item newItem = new Item("Iphone", 800);
		Item createdItem = (item.create(newItem));
		assertEquals(createdItem.getName(), "Iphone");
		assertEquals(Integer.toString(createdItem.getPrice()), "800");
		item.delete(createdItem.getId());
	}

	@Test
	public void createTestNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		assertNull(item.create(new Item("Iphone", 800)));
	}

	@Test
	public void readLatestTestNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		assertNull(item.readLatest());
	}

	@Test
	public void readCustomerNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		assertNull(item.readItem(Long.parseLong("1")));
	}

	@Test
	public void updateTest() {
		Item addedItem = (item.create(new Item("Iphone", 800)));
		addedItem.setName("MacBook Pro");
		addedItem.setPrice(1600);
		Item updatedItem = (item.update(addedItem));
		assertEquals(updatedItem.getName(), "MacBook Pro");
		assertEquals(Integer.toString(updatedItem.getPrice()), "1600");
		item.delete(updatedItem.getId());

	}

	@Test
	public void deleteTestNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		item.delete(Long.parseLong("1"));
	}

	@Test
	public void updateTestNegative() {
		ItemDaoMysql item = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/" + Utils.DATABASE_NAME, "root",
				"wrong password");
		assertNull(item.update(new Item("Iphone", 800)));
	}

}
