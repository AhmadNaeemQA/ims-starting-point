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
		return ("id:" + id + " name:" + name + " Price: £" + price);
	}

	// WHAT IS THIS DOING?
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((price == 0) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == 0) {
			if (other.price != 0)
				return false;
//		} else if (!price == (other.price))
//			return false;
		}
		return true;
	}
}
