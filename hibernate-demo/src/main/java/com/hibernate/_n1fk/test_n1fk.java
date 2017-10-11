package com.hibernate._n1fk;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.util.HibernateSessionFactory;

public class test_n1fk {

	public static void main(String[] args) {
		// test_n1fk.save();
		// test_n1fk.search();
		test_n1fk.update2();
	}

	/*
	 * 根据具有外键的一方添加数据（通过多方向一方添加数据）
	 */
	@Test   //JUnit 不能测试static方法
	public void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_n1fk add = new Address_n1fk("北京市");

			Person_n1fk per1 = new Person_n1fk("南关", add);
			Person_n1fk per2 = new Person_n1fk("独白", add);
			Person_n1fk per3 = new Person_n1fk("家乐福", add);

			session.save(per1);
			session.save(per2);
			session.save(per3);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/*
	 * 多对一情况下只修改一方的信息，而不牵涉到该多信息下其他对象的信息。
	 * 如：某一地址(Address)下有多个人(Person)，若修改某一个人的地址信息，其他人地址不变。
	 * [① 将某个person对象的地址修改到数据库中存在的其他地址信息]
	 */
	public static void update1() { // 先查找再更新(两种查找方式)

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			//通过主键查询获得要修改的一方信息（某person对象）
			Person_n1fk per = (Person_n1fk) session.get(Person_n1fk.class, 5);
			//通过主键查询获得要修改为的多方信息
			Address_n1fk add = (Address_n1fk) session.get(Address_n1fk.class, 3);
			//将该一方信息添加到多方信息的某person对象中，则修改成功地址
			per.setAddId(add);
 //把主键5对应的person的地址外键修改为3（主键3对应的地址）
			session.saveOrUpdate(per);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	/*
	 * 多对一情况下只修改一方的信息，而不牵涉到该多信息下其他信息。
	 * 如：某一地址(Address)下有多个人(Person)，若修改某一个人的地址信息，其他人地址不变。
	 * [② 将某个person对象的地址修改到数据库中不存在的其他地址信息，需要向数据库中添加新地址，同时修改]
	 */
	public static void update2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			//通过主键查询获得要修改的一方信息（某person对象）
			Person_n1fk per = (Person_n1fk) session.get(Person_n1fk.class, 3);
			//通过实例化一方信息，新添加地址address对象
			Address_n1fk add = new Address_n1fk("广州");
			//将该实例化的一方信息添加到多方信息的某person对象中，则修改成功地址
			per.setAddId(add);
			
			session.saveOrUpdate(per);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	//  一:get方式查找,只需找出具有外键的一方，即可根据外键获得另一张表的关联数据
	public static void search1() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			 Person_n1fk per = (Person_n1fk) session.get(Person_n1fk.class,1);// 查询主键为1的用户
			 System.out.println(per.getName() + per.getAddId().getName());

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	// / 二:hql语句方式查找
	public static void search2() { // 两种查找方式
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("from Person_n1fk order by pid desc");  //加where条件查询
			List<Person_n1fk> list = query.list();
			for (Person_n1fk p : list) {
				System.out.println(p.getName() + p.getAddId().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
