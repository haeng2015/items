package service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoUser;
import com.pbms.service.BoUserService;

//@RunWith(SpringJUnit4ClassRunner.class)   //表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:conf/spring.xml"})
public class ServiceTest {
    
    private static final Logger LOGGER = Logger.getLogger(ServiceTest.class);
    
    @Resource
    private BoUserService boUserService = null;
    
    private ApplicationContext applicationContext;
    
    @Before
    public void setUpBeforeClass() throws Exception {
	
//	ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/spring.xml");
	applicationContext = new ClassPathXmlApplicationContext("classpath:conf/spring.xml");
	
    }
    
    @Test
    public void TestBoUser() throws Exception {
//	BoUser user = boUserService.findBoUserById(1);
//	System.out.println(user);
//	LOGGER.info(JSON.toJSONString(user));
	
	applicationContext.getBean("");
	
    }
    
    
}
