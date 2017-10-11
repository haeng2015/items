package com.hibernate._nnfk1;

import java.util.HashSet;
import java.util.Set;

public class Student_nnfk1 {

	private int stuId;
	private String stuName;
	private int stuAge;
	private Set<Teacher_nnfk1> tchSet = new HashSet<Teacher_nnfk1>(); // 老师作为学生父项

	public Student_nnfk1() {
		super();
	}

	public Student_nnfk1(String stuName, int stuAge) {
		super();
		this.stuName = stuName;
		this.stuAge = stuAge;
	}

	public Student_nnfk1(String stuName, int stuAge, Set<Teacher_nnfk1> tchSet) {
		super();
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.tchSet = tchSet;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	public Set<Teacher_nnfk1> getTchSet() {
		return tchSet;
	}

	public void setTchSet(Set<Teacher_nnfk1> tchSet) {
		this.tchSet = tchSet;
	}

}
