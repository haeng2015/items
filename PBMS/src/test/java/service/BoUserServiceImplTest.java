package service;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pbms.service.BoUserService;

public class BoUserServiceImplTest {
    
    private ApplicationContext applicationContext;
    private BoUserService boUserService;
    
    // 在setUp这个方法得到spring容器
    @Before
    public void setUp() throws Exception {
	applicationContext = new ClassPathXmlApplicationContext("classpath:conf/spring.xml");
    }
    
    @Test
    public void testFindBoAuthUrlByBoUserId() {
	applicationContext.getBean("boUserDao");
	
	// fail("Not yet implemented");
	Set<String> urlSet = this.boUserService.findBoAuthUrlByBoUserId(4);
	
	for (String urlStr : urlSet) {
	    System.out.println(urlStr);
	}
    }
    
    public BoUserService getBoUserService() {
	return boUserService;
    }
    
    @Resource
    public void setBoUserService(BoUserService boUserService) {
	this.boUserService = boUserService;
    }
    
}
