package com.hibernate.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.Bean.Student;

public class test {

	public static void main(String[] args) {
		// 加载 hibernate.cfg.xml配置文件
		Configuration conf = new Configuration().configure();
		// 得到session工厂
		SessionFactory sessionFactory = conf.buildSessionFactory();
		// 打开session得到session对象
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		try {
			Student S = new Student("123", "123456");
			session.save(S);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();

		} finally {
			session.close();
		}

	}
}
