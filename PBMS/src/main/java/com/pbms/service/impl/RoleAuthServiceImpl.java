package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoAuth;
import com.pbms.pojo.RoleAuth;
import com.pbms.service.BoAuthService;
import com.pbms.service.BoRoleService;
import com.pbms.service.RoleAuthService;
import com.pbms.vo.UserVO;

@Service("roleAuthService")
public class RoleAuthServiceImpl implements RoleAuthService {
    
    // xml文件中的namespac值
    public static final String NAMESPACE = "roleAuthMapper";
    // UserVO loginUser = (UserVO) request.getSession().getAttribute("userVO");
    private BaseDao<RoleAuth> baseDao;
    @Resource
    private BoRoleService boRoleService;
    private BoAuthService boAuthService;
    
    public BaseDao<RoleAuth> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<RoleAuth> baseDao) {
	this.baseDao = baseDao;
    }
    
    public BoRoleService getBoRoleService() {
	return boRoleService;
    }
    
    public void setBoRoleService(BoRoleService boRoleService) {
	this.boRoleService = boRoleService;
    }
    
    public BoAuthService getBoAuthService() {
	return boAuthService;
    }
    
    @Resource
    public void setBoAuthService(BoAuthService boAuthService) {
	this.boAuthService = boAuthService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据角色查找权限
     * @param userVO
     * @return
     */
    @Override
    public List<RoleAuth> findBoAuthByRole(Integer roleId) {
	if (roleId != null && !"".equals(roleId)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.selectFks(roleId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据角色删除权限
     * @param userVO
     * @return
     */
    @Override
    public Integer deleteRoleAuthByRole(Integer roleId) {
	Integer result = 0;
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (roleId != null && !"".equals(roleId)) {
	    result = this.baseDao.delete(roleId);
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 
     *                为角色添加权限（先删除该角色下的所有权限，再添加权限）[自动事务处理，spring-mybatis.xml中事务配置]
     * @param userVO
     * @return
     */
    @Override
    public Integer addOrUpdateRoleAuth(UserVO userVO) {
	Integer result = 0;
	BoAuth boAuth = null;
	if (userVO != null && !"".equals(userVO)) {
	    // 先根据角色删除权限
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    List<RoleAuth> roleAuths = this.findBoAuthByRole(userVO.getRoleId());
	    if (roleAuths != null && roleAuths.size() > 0) {
		result = this.deleteRoleAuthByRole(userVO.getRoleId());
	    } else {
		result = 1;
	    }
	    
	    if (result > 0) {
		// 给角色添加权限
		RoleAuth roleAuth = new RoleAuth();
		List<String> authIdList = userVO.getAuthIds();
		roleAuth.setRole(this.boRoleService.findRoleById(userVO.getRoleId()));
		for (String authId : authIdList) {
		    if (authId != null && !"".equals(authId)) { // 移除空值
			boAuth = this.boAuthService.findBoAuthById(Integer.parseInt(authId));
			
			// 只添加叶子节点
			if ("1".equals(boAuth.getIsLeafNode())) {
			    roleAuth.setAuth(boAuth);
			    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
			    result = this.baseDao.insertSelective(roleAuth);
			}
		    }
		}
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为超级角色添加所有权限（超级管理员id和超级角色id都指定为adminId）
     * @return
     */
    @Override
    public Integer addAuthForAdmin(Integer roleId) {
	List<Integer> isEexistAuth = new ArrayList<Integer>();
	List<Integer> notEexistAuth = new ArrayList<Integer>();
	List<RoleAuth> roleAuths = null;
	Integer result = 1;
	if (roleId != null && !"".equals(roleId)) {
	    // 先根据角色id查找已经存在的权限
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    roleAuths = this.findBoAuthByRole(roleId);
	    for (RoleAuth roleAuth : roleAuths) {
		// 将RoleAuth对象转换为BoAuth的id放入list中
		isEexistAuth.add(roleAuth.getAuth().getAuthId());
	    }
	    
	    // 再查找所有权限
	    List<BoAuth> boAuths = this.boAuthService.findAllBoAuth();
	    for (BoAuth boAuth : boAuths) {
		notEexistAuth.add(boAuth.getAuthId());
	    }
	    
	    // 从所有权限中移除已经存在的权限
	    Iterator<Integer> it = notEexistAuth.iterator();
	    while (it.hasNext()) {
		Integer authId = it.next();
		for (Integer boAuth : isEexistAuth) {
		    if (authId.equals(boAuth)) {
			it.remove();
		    }
		}
	    }
	    
	    // 如果移除后还有值，再添加
	    if (notEexistAuth != null && !"".equals(notEexistAuth) && notEexistAuth.size() > 0) {
		RoleAuth roleAuth = new RoleAuth();
		roleAuth.setRole(this.boRoleService.findRoleById(roleId));
		for (Integer boAuthId : notEexistAuth) {
		    BoAuth boAuth = this.boAuthService.findBoAuthById(boAuthId);
		    // 只添加叶子节点
		    if ("1".equals(boAuth.getIsLeafNode())) {
			roleAuth.setAuth(boAuth);
			this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
			result = this.baseDao.insertSelective(roleAuth);
		    }
		}
	    }
	}
	return result;
    }
    
}
