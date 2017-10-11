package com.hibernate._n11nfk;

/*
 * 一对多双向传递
 */
public class Person_n11nfk {

	private int perId;
	private String name;
	private int age;
	private Address_n11nfk address;

	public Person_n11nfk() {
		super();
	}

	public Person_n11nfk(int perId) {
		super();
		this.perId = perId;
	}

	public Person_n11nfk(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person_n11nfk(String name, int age, Address_n11nfk address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Person_n11nfk(int perId, String name, int age, Address_n11nfk address) {
		super();
		this.perId = perId;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public int getPerId() {
		return perId;
	}

	public void setPerId(int perId) {
		this.perId = perId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address_n11nfk getAddress() {
		return address;
	}

	public void setAddress(Address_n11nfk address) {
		this.address = address;
	}

}
