package com.hibernate.Dao;

import com.hibernate.Bean.Student;
import com.hibernate.util.SuperDao;

public class StudentDao extends SuperDao {

	public static void main(String[] args) {
		System.out.println(Student.class.getName());
		System.out.println(maxPage(Student.class));
	}
}
