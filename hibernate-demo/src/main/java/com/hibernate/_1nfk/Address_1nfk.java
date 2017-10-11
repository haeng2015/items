package com.hibernate._1nfk;

import java.util.HashSet;
import java.util.Set;

public class Address_1nfk {

	private int addId;
	private String address;
	private Set<Person_1nfk> perSet = new HashSet<Person_1nfk>(); // 一对多在一方设置外键

	public Address_1nfk() {
		super();
	}

	public Address_1nfk(int addId) {
		super();
		this.addId = addId;
	}

	public Address_1nfk(String address) {
		super();
		this.address = address;
	}

	public Address_1nfk(String address, Set<Person_1nfk> perSet) {
		super();
		this.address = address;
		this.perSet = perSet;
	}

	public Address_1nfk(int addId, String address, Set<Person_1nfk> perSet) {
		super();
		this.addId = addId;
		this.address = address;
		this.perSet = perSet;
	}

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Person_1nfk> getPerSet() {
		return perSet;
	}

	public void setPerSet(Set<Person_1nfk> perSet) {
		this.perSet = perSet;
	}

}
