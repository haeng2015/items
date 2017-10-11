package com.hibernate._nnfk2;

import java.util.HashSet;
import java.util.Set;

public class Teacher_nnfk2 {

	private int tchId;
	private String tchName;
	private int tchAge;
	private Set<Student_nnfk2> stuSet = new HashSet<Student_nnfk2>();

	public Teacher_nnfk2() {
		super();
	}

	public Teacher_nnfk2(String tchName, int tchAge) {
		super();
		this.tchName = tchName;
		this.tchAge = tchAge;
	}

	public Teacher_nnfk2(int tchId, String tchName, int tchAge,
			Set<Student_nnfk2> stuSet) {
		super();
		this.tchId = tchId;
		this.tchName = tchName;
		this.tchAge = tchAge;
		this.stuSet = stuSet;
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

	public Set<Student_nnfk2> getStuSet() {
		return stuSet;
	}

	public void setStuSet(Set<Student_nnfk2> stuSet) {
		this.stuSet = stuSet;
	}

}
