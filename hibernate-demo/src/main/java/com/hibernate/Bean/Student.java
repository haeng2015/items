package com.hibernate.Bean;

public class Student {

	private int id;
	private String name;
	private String pass;

	public Student() {
		super();
	}

	public Student(int id) {
		super();
		this.id = id;
	}

	public Student(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}

	public Student(int id, String name, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
