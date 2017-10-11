package com.pbms.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.pbms.pojo.BoUser;

@MapperScan
public interface BoUserDao {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 根据用户名、密码查找用户信息
     * @param userVO
     * @return
     */
    public BoUser findBoUserByNameAndPwd(BoUser boUser) throws Exception;
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月24日
     * @function：TODO 根据id查找用户
     * @param userId
     * @return
     * @throws Exception
     */
    public BoUser findBoUserById(Integer userId) throws Exception;
    
}
