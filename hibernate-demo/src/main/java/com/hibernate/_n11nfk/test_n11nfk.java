package com.hibernate._n11nfk;

/*
 * 一：临时状态（Transient）：用new创建的对象，它没有持久化，没有处于Session中，处于此状态的对象叫临时对象；
 * 二：持久化状态（Persistent）：已经持久化，加入到了Session缓存中。如通过hibernate语句保存的对象。处于此状态的对象叫持久对象；
 * 三：游离状态（Detached）：持久化对象脱离了Session的对象。如Session缓存被清空的对象。特点：已经持久化，但不在Session缓存中。处于此状态的对象叫游离对象；
 */
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate._1nfk.Address_1nfk;
import com.hibernate._1nfk.Person_1nfk;
import com.hibernate._n1fk.Address_n1fk;
import com.hibernate._n1fk.Person_n1fk;
import com.hibernate.util.HibernateSessionFactory;

/*
 * 一对多外键双向，尽量选择多对一方式，以多控制一
 */
public class test_n11nfk {

	public static void main(String[] args) {
//		test_n11nfk.save_1nfk();
		 test_n11nfk.search_n1fk2();
		// test_n11nfk.update();
	}

	/*
	 * 根据具有外键的一方添加多方数据信息（一对多添加1nfk） 
	 * 先将多方数据放入一方外键集合中，通过保存一方数据来添加多方数据信息
	 */
	public static void save_1nfk() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_n11nfk add = new Address_n11nfk("湖北荆州");

			Person_n11nfk per1 = new Person_n11nfk("王五", 23);
			Person_n11nfk per2 = new Person_n11nfk("老五", 45);
			Person_n11nfk per3 = new Person_n11nfk("小三", 15);

			add.getPerSet().add(per1);
			add.getPerSet().add(per2);
			add.getPerSet().add(per3);
			session.save(add);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/*
	 * 根据多方信息添加一方数据数据（多对一添加n1fk）
	 * 将一方信息添加到多方中，分别保存多方数据信息
	 */
	public static void save_n1fk() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_n11nfk add = new Address_n11nfk("湖北襄樊");

			Person_n11nfk per1 = new Person_n11nfk("王五", 28, add);
			Person_n11nfk per2 = new Person_n11nfk("接法", 23, add);
			Person_n11nfk per3 = new Person_n11nfk("微软五", 13, add);

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
	 * 一:get方式查找,只需找出具有外键的一方，即可根据外键获得另一张表的关联数据
	 * 查出多方信息，由多方信息获得一方信息  per.getAddress().getAddress()
	 */
	public static void search_n1fk1() {
	
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			Person_n11nfk per = (Person_n11nfk) session.get(Person_n11nfk.class,2);// 查询主键为1的用户
			 System.out.println(per.getName() + per.getAge()+per.getAddress().getAddress());
	
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * hql语句方式查找,根据查多方信息，由外键获得一方信息p.getAddress().getAddress()
	 */
	public static void search_n1fk2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("from Person_n11nfk order by perId desc");  //加where条件查询
			List<Person_n11nfk> list = query.list();
			for (Person_n11nfk p : list) {
				System.out.println(p.getName() + p.getAddress().getAddress()+p.getAge());
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * get方式查找,根据一方信息查找出对应的多方信息
	 */
	public static void search_1nfk1() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {

			// 根据一方主键查的对应的信息（ 查询主键为1的地址）
			Address_n11nfk add = (Address_n11nfk) session.get(Address_n11nfk.class, 2);
			// 根据查到的一方信息，通过Set获得对应的外键，查询多方信息
			Set<Person_n11nfk> a = add.getPerSet();
			for (Person_n11nfk p : a) {
				System.out.println(add.getAddress() + p.getName() + p.getAge());
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * hql语句方式查找,根据查一方信息，由外键获得多方信息add.getPerSet()
	 */
	public static void search_1nfk2() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session.createQuery("from Address_n11nfk order by addId desc");  //有条件查询where addId=3
			List<Address_n11nfk> list = query.list();
			for (Address_n11nfk add : list) { // 外层循环查找所有的一方信息

				Set<Person_n11nfk> per = add.getPerSet(); // 根据一方信息获得对应的多方信息
				for (Person_n11nfk p : per) { // 内层循环查找一方信息对应的所有多方信息

					System.out.println(add.getAddress() + p.getName()+ p.getAge());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
		
	/*
	 * 将某一个person对应的地址address修改为另一个地址（该地址数据库已存在，只需修改其对应的address外键id）
	 * 方案：先移除掉该地址下对应的某个person对象，然后获得另一个地址后，重新添加该person对象
	 */
	@Test
	public void update_1nfk1() { // 先查找再更新(两种查找方式)

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			// 更改多方（person）对应的地址信息（address）,通过获得主键更改
			Address_1nfk add1 = (Address_1nfk) session.get(Address_1nfk.class,3);
			Set<Person_1nfk> per = add1.getPerSet();

			Person_1nfk pp = null;
			for (Person_1nfk p : per) {
				System.out.print(p.getPerId()+"\t");
				System.out.print(p.getName()+"\t");
				System.out.print(p.getAge()+"\t");
				System.out.print(add1.getAddress()+"\t");
				System.out.println(add1.getAddId()+"\t");
				if (p.getPerId() == 7) {   //通过主键获得该地址下某一个person对象
					pp = p;
				}
			}
			per.remove(pp);
			Address_1nfk add2 = (Address_1nfk) session.get(Address_1nfk.class,1);
			add2.getPerSet().add(pp);

			session.update(add1);
			session.update(add2);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * 将某一个person对应的地址address修改为另一个地址（该地址数据库不存在，需要重新添加）
	 * 方案：先移除掉该地址下对应的某个person对象，然后重新添加另一个地址后，在该地址下添加重新添加该person对象
	 */
	public static void update_1nfk2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_1nfk address1 = (Address_1nfk)session.get(Address_1nfk.class, 8);  //1、通过主键2查找到该地址
			
			Set<Person_1nfk> per = address1.getPerSet();  //2、通过一对多外键遍历查找多方person对象
			
			Person_1nfk pp = null;
			for (Person_1nfk p : per) {
				System.out.print(p.getPerId()+"\t");
				System.out.print(p.getName()+"\t");
				System.out.print(p.getAge()+"\t");
				System.out.print(address1.getAddress()+"\t");
				System.out.println(address1.getAddId()+"\t");
				if(p.getPerId() == 11){   //3、通过person对象的主键2条件查询到某一个需要修改的person对象
					pp = p;
				}
			}
			per.remove(pp);   //4、将其从person对象中移除
			Address_1nfk address2 = new Address_1nfk("浙江杭州");  //5、重新添加一个新的地址
			address2.getPerSet().add(pp);  //6、为该地址添加该person对象
			
			session.saveOrUpdate(address1);
			session.saveOrUpdate(address2);
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
	public static void update_n1fk1() { // 先查找再更新(两种查找方式)

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			//通过主键查询获得要修改的一方信息（某person对象）
			Person_n1fk per = (Person_n1fk) session.get(Person_n1fk.class, 5);
			//通过主键查询获得要修改为的多方信息
			Address_n1fk add = (Address_n1fk) session.get(Address_n1fk.class, 3);
			//将该一方信息通过set方法添加到多方信息的某person对象中，则修改成功地址
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
	public static void update_n1fk2() {
		
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
}
