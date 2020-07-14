package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {

	private Long id;
	private int customer_id;
	private int item_id;

	// Array List storing all items they want in that order
	private ArrayList<Integer> basket = new ArrayList<Integer>();

	public Order(int customer_id) {
		customer_id = this.customer_id;
	}

	public Order(Long id, int customer_id) {
		id = this.id;
		customer_id = this.customer_id;
	}

	public Order() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public String toString() {
		return ("id:" + this.getId() + " Customer ID: " + this.getCustomer_id() + " Item: " + this.getItem_id());
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
	}

	public int getSize() {
		return basket.size();
	}

}
