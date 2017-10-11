package com.hibernate._1nfk;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.util.HibernateSessionFactory;

/*
 * 一：临时状态（Transient）：用new创建的对象，它没有持久化，没有处于Session中，处于此状态的对象叫临时对象；
 * 						临时对象在内存孤立存在,它是携带信息的载体,不和数据库的数据有任何关联关系.
 * 二：持久化状态（Persistent）：已经持久化，加入到了Session缓存中。如通过hibernate语句保存的对象。处于此状态的对象叫持久对象；
 * 三：游离状态（Detached）：持久化对象脱离了Session的对象。如Session缓存被清空的对象。特点：已经持久化，但不在Session缓存中。处于此状态的对象叫游离对象；
 */
public class test_1nfk {

	public static void main(String[] args) {
//		 test_1nfk.save();
//		test_1nfk.search1();
		 test_1nfk.update3();
	}

	/*
	 * 一对多，以外键一方添加控制多方的信息
	 */
	public static void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_1nfk add = new Address_1nfk("上海");

			Person_1nfk per1 = new Person_1nfk("师范", 21);
			Person_1nfk per2 = new Person_1nfk("替换", 15);
			Person_1nfk per3 = new Person_1nfk("接口", 31);

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
	 * 将某一个person对应的地址address修改为另一个地址（该地址数据库已存在，只需修改其对应的address外键id）
	 * 方案：先移除掉该地址下对应的某个person对象，然后获得另一个地址后，重新添加该person对象
	 */
	@Test
	public void update1() { // 先查找再更新(两种查找方式)

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
			System.out.println(pp);
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
	
	//修改Address地址，并且将某个人对应修改到该地址
	@Test
	public void update2() {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Address_1nfk address = (Address_1nfk)session.get(Address_1nfk.class, 1);
			
			address.setAddress("湖北襄阳");
			
			Set<Person_1nfk> perSet = address.getPerSet();
			
			for (Person_1nfk p : perSet) {
				
				if(p.getPerId() == 6){
					p.setName("老张");
				}
			}
			
			session.update(address);
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
	public static void update3() {
		
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

	// 一:get方式查找,根据一方信息查找出对应的多方信息
	public static void search1() {

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {

			// 根据一方主键查的对应的信息（ 查询主键为1的地址）
			Address_1nfk add = (Address_1nfk) session.get(Address_1nfk.class, 2);
			// 根据查到的一方信息，通过Set获得对应的外键，查询多方信息
			Set<Person_1nfk> a = add.getPerSet();
			for (Person_1nfk p : a) {
				System.out.println(add.getAddress() + p.getName() + p.getAge());
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Test
	public void search2() { // 二:hql语句方式查找（可查找出所有用户信息）

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session.createQuery("from Address_1nfk where addId=3 order by addId desc");
			List<Address_1nfk> list = query.list();
			for (Address_1nfk add : list) { // 外层循环查找所有的一方信息

				Set<Person_1nfk> per = add.getPerSet(); // 根据一方信息获得对应的多方信息
				for (Person_1nfk p : per) { // 内层循环查找一方信息对应的所有多方信息

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
}
