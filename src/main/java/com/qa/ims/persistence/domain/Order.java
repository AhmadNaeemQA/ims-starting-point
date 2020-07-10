package com.qa.ims.persistence.domain;

public class Order {

	private Long id;
	private Long customer_id;

	public Order(Long customer_id) {
		customer_id = this.customer_id;
	}

	public Order(long id, long customer_id) {
		id = this.id;
		customer_id = this.customer_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String toString() {
		return ("id:" + id + " Order ID: " + id + " Customer Id: " + customer_id);
	}
}
