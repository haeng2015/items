package com.hibernate.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SuperDao<T> {

	public static int count; // 最大页
	public static final int pageCount = 5; // 每页显示最大行数

	public T obj;
	public List<T> list;

	public void save(T t) { // 实例化得打session对象

		// 加载 hibernate.cfg.xml配置文件
		Configuration conf = new Configuration().configure();
		// 得到session工厂
		SessionFactory sessionFactory = conf.buildSessionFactory();
		// 打开session得到session对象
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		try {
			session.save(t);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();

		} finally {
			session.close();
		}

	}

	public void Update(T t) { // 通过HibernateSessionFactory得到session对象

		Session session = HibernateSessionFactory.getSession();

		Transaction transaction = session.beginTransaction();
		try {
			session.update(t);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public T Delete(T t) { // 传参数：对象

		Session session = HibernateSessionFactory.getSession();

		Transaction transaction = session.beginTransaction();
		try {
			session.delete(t);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return obj;
	}

	public List<T> FindAll(Class claxx) { // 传参数：类对象

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			// 通过类对象getName获得路径（包+对象）getSimpleName获得类对象名，查询对象对应属性
			Query query = session.createQuery("from " + claxx.getSimpleName());
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	public T FindById(Class claxx, java.io.Serializable id) {
		/*
		 * 类通过实现 java.io.Serializable 接口以启用其序列化功能。 未实现此接口的类将无法使其任何状态序列化或反序列化。
		 * 可序列化类的所有子类型本身都是可序列化的。 序列化接口没有方法或字段，仅用于标识可序列化的语义。
		 */
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			obj = (T) session.get(claxx, id);
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return obj;
	}

	public List<T> Paging(Class claxx, int pageNumber) {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("from " + claxx.getSimpleName());
			query.setFirstResult((pageNumber - 1) * pageCount);
			query.setMaxResults(pageCount);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	public static int maxPage(Class claxx) {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session.createQuery("select count(*) from Student");
			// + claxx.getSimpleName());
			count = (Integer) query.uniqueResult(); // (Long)
													// query.uniqueResult().int
			if (count % pageCount == 0) {
				count /= pageCount;
			} else {
				count /= pageCount + 1;
			}
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return count;
	}
}
