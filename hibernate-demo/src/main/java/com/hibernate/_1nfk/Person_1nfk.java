package com.hibernate._1nfk;

public class Person_1nfk {

	private int perId;
	private String name;
	private int age;

	public Person_1nfk() {
		super();
	}

	public Person_1nfk(int perId) {
		super();
		this.perId = perId;
	}

	public Person_1nfk(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person_1nfk(int perId, String name, int age) {
		super();
		this.perId = perId;
		this.name = name;
		this.age = age;
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

}
