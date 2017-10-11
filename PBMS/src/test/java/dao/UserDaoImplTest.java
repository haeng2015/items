package dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pbms.dao.BoUserDao;
import com.pbms.pojo.BoUser;

public class UserDaoImplTest {
    
    private ApplicationContext applicationContext;
    
    
    // 在setUp这个方法得到spring容器
    @Before
    public void setUp() throws Exception {
	applicationContext = new ClassPathXmlApplicationContext("classpath:conf/spring.xml");
    }
    
    @Test
    public void testFindUserById() throws Exception {
	//取得spring-mybatis.xml文件中id为boUserDao的bean节点
	BoUserDao boUserDao = (BoUserDao) applicationContext.getBean("baseDao");
	
	// 调用userDao的方法
	BoUser boUser = boUserDao.findBoUserById(1);
	
	System.out.println(boUser);
    }
    
    @Test
    public void testFindUserByLogin() throws Exception {
	//取得spring-mybatis.xml文件中id为boUserDao的bean节点
	BoUserDao boUserDao = (BoUserDao) applicationContext.getBean("boUserDao");
	BoUser userVO = new BoUser();
	userVO.setUserLogin("111");
	userVO.setUserPwd("123");
	
	// 调用userDao的方法
	BoUser boUser = boUserDao.findBoUserByNameAndPwd(userVO);
	
	System.out.println(boUser);
    }
    
    
}
