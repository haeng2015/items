package com.hibernate.bbs_hql;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.util.HibernateSessionFactory;

public class test_bbshql {

	public static void main(String[] args) {
		// test_n1fk.save();
		// test_n1fk.search();
//		test_bbshql.update2();
		test_bbshql.search4(10,20);
	}

	/*
	 * 根据具有外键的一方添加数据（通过多方向一方添加数据）
	 */
	@Test   //JUnit 不能测试static方法
	public void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			Bbstopic topic = new Bbstopic();
			topic.setTtopic("topic");
//			topic.setTclickcount(10005);

			Bbsuser u = new Bbsuser();
			u.setUname("user");
			
			Bbssection s = new Bbssection();
			s.setSname("section");
			
			Bbsreply r = new Bbsreply();
			r.setRtopic("reply");
			
			r.setBbssection(s);
			r.setBbstopic(topic);
			r.setBbsuser(u);
			
			s.setBbsuser(u);
			
			topic.setBbsuser(u);
			topic.setBbssection(s);
			
			session.save(u);
			session.save(topic);
			session.save(s);
			session.save(r);
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
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	//  一:get方式查找用户名、密码
	@Test
	public void search1() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			BigDecimal bg = new BigDecimal(1);
			
			Bbsuser user = (Bbsuser) session.get(Bbsuser.class, bg);
			user.getUname();
			user.getUpassword();
			System.out.println(user.getUname()+user.getUpassword());
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	// hql查找用户名及密码 user表
	@Test
	public void search2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			String hql = "select u.uname,u.upassword from Bbsuser u ";
			Query query = session.createQuery(hql);
			
			List user = query.list();
			for (int i = 0; i < user.size(); i++) {
				Object[] obj = (Object[]) user.get(i);  //获得对象数组
				for (int j = 0; j < obj.length; j++) {
					System.out.println(obj[j]);
				}
			}
			
			//  不能通过这种方式遍历获得对象属性值
			/*List<Bbsuser> user = query.list();
			for (Bbsuser u : user) {
				System.out.println(u.getUname()+u.getUpassword());
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	//  like模糊查询
	@Test
	public void search3() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			String hql = "from Bbsuser u where u.uname like ?";
			
			Query query = session.createQuery(hql);
			query.setString(0, "%f%");  //下标从0开始，查询名字中带有f的人
			
			List<Bbsuser> user = query.list();
			for (Bbsuser u : user) {
				System.out.println(u.getUname()+u.getUpassword());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	//  范围查找
	public static void search4(int p1,int p2) {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
//			String hql = "from Bbsuser u where u.upoint between ? and ? ";   //u.upoint >= ? and u.upoint <= ?
			String hql = "from Bbsuser u where u.upoint > ? and u.upoint <= ? ";  
			
			Query query = session.createQuery(hql);
			query.setInteger(0, p1);  //下标从0开始，查询名字中带有f的人
			query.setInteger(1, p2);
			
			List<Bbsuser> user = query.list();
			for (Bbsuser u : user) {
				System.out.println(u.getUname()+u.getUpassword());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	// 联合查询某个人的信息
	public static void search5() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			String hql = "select from Bbstopic t where u.upoint > ? and u.upoint <= ? ";  
			
			Query query = session.createQuery(hql);
			
			List<Bbsuser> user = query.list();
			for (Bbsuser u : user) {
				System.out.println(u.getUname()+u.getUpassword());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}



}
