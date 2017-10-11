package com.hibernate._nnfk2;

import java.util.HashSet;
import java.util.Set;

public class Student_nnfk2 {

	private int stuId;
	private String stuName;
	private int stuAge;
	private Set<Teacher_nnfk2> tchSet = new HashSet<Teacher_nnfk2>(); // 老师作为学生父项

	public Student_nnfk2() {
		super();
	}

	public Student_nnfk2(String stuName, int stuAge) {
		super();
		this.stuName = stuName;
		this.stuAge = stuAge;
	}

	public Student_nnfk2(String stuName, int stuAge, Set<Teacher_nnfk2> tchSet) {
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

	public Set<Teacher_nnfk2> getTchSet() {
		return tchSet;
	}

	public void setTchSet(Set<Teacher_nnfk2> tchSet) {
		this.tchSet = tchSet;
	}

}
