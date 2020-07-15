package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	private Item item2;
	private Item item3;
	private Item item4;

	@Before
	public void setUp() {
		item = new Item(Long.valueOf(1), "Iphone", 800);
		item2 = new Item("Samsung", 500);
		item3 = new Item(Long.valueOf(2));
		item4 = new Item();
	}

	@Test
	public void setterTest() {
		item.setId(Long.valueOf(1));
		assertNotNull(item.getId());
		item.setName("Iphone");
		assertNotNull(item.getName());
		item.setPrice(800);
		assertNotNull(item.getPrice());
	}

	@Test
	public void toStringTest() {
		assertEquals(item.toString(),
				"id: " + item.getId() + " name: " + item.getName() + " Price: £" + item.getPrice());
	}

}
