package mapper;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.pbms.dao.BoUserDao;
import com.pbms.pojo.BoUser;


public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	// 此方法是在执行testFindUserById之前执行
	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory

		// mybatis配置文件
//		String resource = "com/pbms/mapper/BoUserMapper.xml";
		String resource = "conf/spring.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	//用户信息的综合 查询
	@Test
	public void testFindUserList() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		BoUserDao userMapper = sqlSession.getMapper(BoUserDao.class);
		
		//创建包装对象，设置查询条件
		BoUser userVO = new BoUser();
		//由于这里使用动态sql，如果不设置某个值，条件不会拼接在sql中
//		userCustom.setSex("1");
		userVO.setUserName("张三");
		userVO.setUserPwd("123");
//		//传入多个id
//		List<String> ids = new ArrayList<String>();
//		ids.add("1");
//		ids.add("2");
//		ids.add("3");
//		//将ids通过userQueryVo传入statement中
//		UserVO.setUserIds(ids);
//		userQueryVo.setUserCustom(userCustom);
//		//调用userMapper的方法
//		
//		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		
//		System.out.println(list);
		
		BoUser user = userMapper.findBoUserByNameAndPwd(userVO);
		
		System.out.println(user);
		
		
	}
	

}
