package com.hibernate._11fk;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.util.HibernateSessionFactory;

public class test_11fk {
    
    public static void main(String[] args) {
	// test_11fk.save();
	test_11fk.search2();
	// test_11fk.update1();
    }
    
    /*
     * 根据具有外键的一方添加数据,调用save方法，同时保存二者信息
     */
    public static void save() {
	Session session = HibernateSessionFactory.getSession();
	Transaction transaction = session.beginTransaction();
	try {
	    Boy_11fk boy = new Boy_11fk("福儿宝", 28);
	    Girl_11fk gril = new Girl_11fk("图一", 25, boy);
	    session.save(gril);
	    transaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.rollback();
	} finally {
	    HibernateSessionFactory.closeSession();
	}
    }
    
    /*
     * 一对一情况下只修改一方的信息，通过有外键一方修改 ① 修改到数据库中有的其他对象（二者交换boy对象） 问题：违反唯一约束条件
     */
    @Test
    public void update_fk1() {
	
	Session session = HibernateSessionFactory.getSession();
	Transaction transaction = session.beginTransaction();
	try {
	    // 富士康,沃达丰,沃达丰
	    // 荣提红,王俊凯,王俊凯
	    Girl_11fk girl1 = (Girl_11fk) session.get(Girl_11fk.class, 1);
	    Girl_11fk girl2 = (Girl_11fk) session.get(Girl_11fk.class, 3);
	    String b1 = girl1.getBoy().getbName();
	    String b2 = girl2.getBoy().getbName();
	    Boy_11fk boy1 = (Boy_11fk) session.get(Boy_11fk.class, 1);
	    Boy_11fk boy2 = (Boy_11fk) session.get(Boy_11fk.class, 3);
	    System.out.println(girl1.getgName() + "," + boy1.getbName() + "," + b1);
	    System.out.println(girl2.getgName() + "," + boy2.getbName() + "," + b2);
	    girl1.setBoy(boy2);
	    // girl2.setBoy(boy1);
	    session.update(girl1);
	    // session.update(girl2);
	    
	    transaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.rollback();
	} finally {
	    HibernateSessionFactory.closeSession();
	}
    }
    
    /*
     * 一对一情况下只修改一方的信息，通过有外键一方修改 ② 修改girl的对象boy，数据库中没有，修改为新添加的boy,移除以前的boy对象
     * 问题：(删除后会失去该主键)
     */
    @Test
    public void update_fk2() {
	
	Session session = HibernateSessionFactory.getSession();
	Transaction transaction = session.beginTransaction();
	try {
	    
	    Girl_11fk girl = (Girl_11fk) session.get(Girl_11fk.class, 2); // 查的该具有外键的girl对象
	    Boy_11fk b = girl.getBoy(); // 由girl获得对应的boy对象
	    session.delete(b); // 移除该boy对象
	    Boy_11fk boy2 = new Boy_11fk("木子易", 24);
	    girl.setBoy(boy2); // 重新为该girl对象添加一个新的boy对象外键（实例化所得）
	    session.update(girl);
	    
	    transaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.rollback();
	} finally {
	    HibernateSessionFactory.closeSession();
	}
    }
    
    /*
     * many-to-one 的外键方式一对一更新,需要重新建立一张表（带有外键） 一对一情况下只修改一方的信息，通过有外键一方修改 ②
     * 修改girl的对象boy，数据库中没有，修改为新添加的boy,移除以前的boy对象
     */
    
    // 一:get方式查找,只需找出具有外键的一方，即可根据外键获得另一张表的关联数据
    public static void search1() {
	
	Session session = HibernateSessionFactory.getSession();
	Transaction transaction = session.beginTransaction();
	try {
	    
	    Girl_11fk girl = (Girl_11fk) session.get(Girl_11fk.class, 2);
	    System.out.print(girl.getgName() + "\t");
	    System.out.print(girl.getgAge() + "\t");
	    System.out.print(girl.getBoy().getbName() + "\t");
	    System.out.println(girl.getBoy().getbAge());
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.rollback();
	} finally {
	    HibernateSessionFactory.closeSession();
	}
    }
    
    // 二:hql语句方式查找,可查询出所有
    public static void search2() {
	
	Session session = HibernateSessionFactory.getSession();
	Transaction transaction = session.beginTransaction();
	try {
	    
	    String hql = "from Girl_11fk"; // 此为类名和类属性名
	    Query query = session.createQuery(hql);
	    List<Girl_11fk> girlList = query.list();
	    // 第一种遍历方式for
	    for (int i = 0; i < girlList.size(); i++) {
		System.out.print(girlList.get(i).getgName() + "\t");
		System.out.print(girlList.get(i).getgAge() + "\t");
		System.out.print(girlList.get(i).getBoy().getbName() + "\t");
		System.out.println(girlList.get(i).getBoy().getbAge());
	    }
	    
	    // 第二种遍历方式foreach
	    for (Girl_11fk g : girlList) {
		System.out.print(g.getgName() + "\t");
		System.out.println(g.getBoy().getbName());
	    }
	    
	    // 第三种遍历方式 迭代器Iterable
	    Iterator<Girl_11fk> girl = girlList.iterator();
	    while (girl.hasNext()) {
		Girl_11fk g = girl.next();
		System.out.println("女:" + g.getgName() + ",男:" + g.getBoy().getbName() + g.getBoy().getbAge());
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.rollback();
	} finally {
	    HibernateSessionFactory.closeSession();
	}
    }
}
