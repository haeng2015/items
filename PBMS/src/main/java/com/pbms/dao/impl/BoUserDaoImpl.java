package com.pbms.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pbms.dao.BoUserDao;
import com.pbms.pojo.BoUser;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月26日
 * @说明：sqlSession继承了SqlSessionDaoSupport 
 *                                       如果使用@Repository(""),则需要在spring-mybatis.xml配置文件中配置该类的实际路径
 *                                       <bean id="boUserDao"
 *                                       class="com.pbms.dao.BoUserDaoImpl">
 *                                       <property name="sqlSessionFactory"
 *                                       ref="sqlSessionFactory"></property>
 *                                       </bean>
 */

@Repository("boUserDao")
// spring-mybatis.xml中配置的单个bean标签
public class BoUserDaoImpl extends SqlSessionDaoSupport implements BoUserDao {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 根据用户名、密码查找用户信息
     * @param userVO
     * @return
     */
    @Override
    public BoUser findBoUserByNameAndPwd(BoUser boUser) throws Exception {
	try {
	    return this.getSqlSession().selectOne("boUserMapper.findBoUserByNameAndPwd", boUser);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月24日
     * @function：TODO 根据id查找用户
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public BoUser findBoUserById(Integer userId) throws Exception {
	if (userId != null && userId != 0) {
	    SqlSession sqlSession = this.getSqlSession();
	    BoUser boUser = sqlSession.selectOne("boUserMapper.findBoUserById", userId);
	    return boUser;
	}
	return null;
    }
    
}
