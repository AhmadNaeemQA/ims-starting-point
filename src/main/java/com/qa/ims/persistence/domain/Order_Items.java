package com.qa.ims.persistence.domain;

public class Order_Items extends Item {
	private int customer_id;

	public Order_Items(int customer_id, Long id, String name, int price) {
		super(id, name, price);
		this.customer_id = customer_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String toString() {
		return ("customer ID: " + customer_id + "item ID: " + this.getId() + "Item Name: " + this.getName()
				+ "Item Price: " + this.getPrice());
	}

}
