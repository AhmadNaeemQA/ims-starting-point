package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order extends Item {

	private Long order_id;
	private int customer_id;

	// Array List storing all items they want in that order
	private ArrayList<Integer> basket = new ArrayList<Integer>();

	public Order(Long order_id, int customer_id, Long id, String name, int price) {
		super(id, name, price);
		this.order_id = order_id;
		this.customer_id = customer_id;
	}

	public Order(Long order_id, int customer_id, Long id) {
		super(id);
		order_id = this.order_id;
		customer_id = this.customer_id;
	}

	public Order() {
		super();
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long id) {
		this.order_id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String toString() {
		return ("Order ID:" + this.getOrder_id() + " Customer ID: " + this.getCustomer_id() + " Item ID: "
				+ this.getId() + " Name: " + this.getName() + " Price: £" + this.getPrice());
	}

	public void addItem(int item_id) {
		basket.add(item_id);
	}

	public int getItem(int index) {
		return basket.get(index);
	}

	public void getAllItems() {
		for (int i = 0; i < basket.size(); i++) {
			System.out.println(basket.get(i));
		}
		return;
	}

	public int getSize() {
		return basket.size();
	}

}
