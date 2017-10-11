package com.hibernate._n1fk;

public class Person_n1fk {

	private int pid;
	private String name;
	private Address_n1fk addId; // 多对一，在多的一方加外键，配置

	public Person_n1fk() {
		super();
	}

	public Person_n1fk(int pid) {
		super();
		this.pid = pid;
	}

	public Person_n1fk(String name, Address_n1fk addId) {
		super();
		this.name = name;
		this.addId = addId;
	}

	public Person_n1fk(int pid, String name, Address_n1fk addId) {
		super();
		this.pid = pid;
		this.name = name;
		this.addId = addId;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address_n1fk getAddId() {
		return addId;
	}

	public void setAddId(Address_n1fk addId) {
		this.addId = addId;
	}

}
