package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String name;
	private Integer price;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public Item(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Item(Long id) {
		this.id = id;
	}

	public Item() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return ("id: " + id + " name: " + name + " Price: £" + price);
	}
}
