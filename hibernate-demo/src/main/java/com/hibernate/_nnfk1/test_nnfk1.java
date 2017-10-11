package com.hibernate._nnfk1;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.util.HibernateSessionFactory;

public class test_nnfk1 {

	public static void main(String[] args) {
		// test_nnfk1.save();
		// test_nnfk1.search();
		test_nnfk1.update2();
	}

	/*
	 * 根据具有外键的一方添加数据（通过多方向一方添加数据）
	 */
	@Test   //JUnit 不能测试static方法
	public void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			Student_nnfk1 s1 = new Student_nnfk1();
			s1.setStuName("瑞金路");
			s1.setStuAge(22);
			Student_nnfk1 s2 = new Student_nnfk1("人工林",35);
			Student_nnfk1 s3 = new Student_nnfk1("第十九",26);
			
			Teacher_nnfk1 t1 = new Teacher_nnfk1();
			t1.setTchName("Eyou");
			t1.setTchAge(38);
			Teacher_nnfk1 t2 = new Teacher_nnfk1("土豆姐",35);
			Teacher_nnfk1 t3 = new Teacher_nnfk1("王师傅",44);
			
			s1.getTchSet().add(t1);
			
			s2.getTchSet().add(t1);
			s2.getTchSet().add(t2);
			s2.getTchSet().add(t3);

			s3.getTchSet().add(t2);
			s3.getTchSet().add(t3);
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			
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

	//  一:get方式查找,只需找出具有外键的一方，即可根据外键获得另一张表的关联数据
	@Test
	public void search1() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			//查询该student对象对应的所有teacher对象
			Student_nnfk1 stu = (Student_nnfk1) session.get(Student_nnfk1.class, 5);
			Set<Teacher_nnfk1> tchSet = stu.getTchSet();
			for (Teacher_nnfk1 t : tchSet) {
				System.out.println(stu.getStuName()+t.getTchName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	// / 二:hql语句方式查找
	@Test
	public void search2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			//根据student对象查询对应的所有teacher对象
			String hql = "select t from Teacher_nnfk1 t,Student_nnfk1 s where t.tchName in elements(s.tchSet) and s.stuName= ?";
			
			Query query = session.createQuery(hql);
			query.setString(0, "瑞金路");
			
			List<Teacher_nnfk1> list = query.list();
			for (Teacher_nnfk1 t : list) {
				System.out.println(t.getTchName());
			}
			
			//输出所有学生对象
			/*List<Student_nnfk1> list = query.list();
			for (Student_nnfk1 s : list) {
				System.out.println(s.getStuName());
			}*/
			
			/*List list = query.list();
			 for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				for (int j = 0; j < obj.length; j++) {
					System.out.println(obj[j]);
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
