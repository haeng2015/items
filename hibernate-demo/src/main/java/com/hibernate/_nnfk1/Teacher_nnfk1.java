package com.hibernate._nnfk1;

public class Teacher_nnfk1 {

	private int tchId;
	private String tchName;
	private int tchAge;

	public Teacher_nnfk1() {
		super();
	}

	public Teacher_nnfk1(String tchName, int tchAge) {
		super();
		this.tchName = tchName;
		this.tchAge = tchAge;
	}

	public Teacher_nnfk1(int tchId, String tchName, int tchAge) {
		super();
		this.tchId = tchId;
		this.tchName = tchName;
		this.tchAge = tchAge;
	}

	public int getTchId() {
		return tchId;
	}

	public void setTchId(int tchId) {
		this.tchId = tchId;
	}

	public String getTchName() {
		return tchName;
	}

	public void setTchName(String tchName) {
		this.tchName = tchName;
	}

	public int getTchAge() {
		return tchAge;
	}

	public void setTchAge(int tchAge) {
		this.tchAge = tchAge;
	}

}
