package com.hibernate._n11nfk;

import java.util.HashSet;
import java.util.Set;

public class Address_n11nfk {

	private int addId;
	private String address;
	private Set<Person_n11nfk> perSet = new HashSet<Person_n11nfk>(); // 一对多在一方设置外键

	public Address_n11nfk() {
		super();
	}

	public Address_n11nfk(int addId) {
		super();
		this.addId = addId;
	}

	public Address_n11nfk(String address) {
		super();
		this.address = address;
	}

	public Address_n11nfk(String address, Set<Person_n11nfk> perSet) {
		super();
		this.address = address;
		this.perSet = perSet;
	}

	public Address_n11nfk(int addId, String address, Set<Person_n11nfk> perSet) {
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

	public Set<Person_n11nfk> getPerSet() {
		return perSet;
	}

	public void setPerSet(Set<Person_n11nfk> perSet) {
		this.perSet = perSet;
	}

}
