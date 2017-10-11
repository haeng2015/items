package com.hibernate._n1fk;

public class Address_n1fk {

	private int addId;
	private String name;

	public Address_n1fk() {
		super();
	}

	public Address_n1fk(int addId) {
		super();
		this.addId = addId;
	}

	public Address_n1fk(String name) {
		super();
		this.name = name;
	}

	public Address_n1fk(int addId, String name) {
		super();
		this.addId = addId;
		this.name = name;
	}

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
