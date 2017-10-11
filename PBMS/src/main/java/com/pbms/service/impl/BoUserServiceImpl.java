package com.pbms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pbms.dao.BaseDao;
import com.pbms.dao.BoUserDao;
import com.pbms.pojo.BoUser;
import com.pbms.pojo.RoleAuth;
import com.pbms.pojo.UserRole;
import com.pbms.service.BoRoleService;
import com.pbms.service.BoUserService;
import com.pbms.service.RoleAuthService;
import com.pbms.service.UserRoleService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.MD5Util;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.UserVO;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:He.hp
 * @创建日期:2017年3月22日
 * @说明：用户service接口实现类
 */
@Service("boUserService")
public class BoUserServiceImpl implements BoUserService {
    
    /**
     * building的mapper文件的namespace常量值：boBuildingMapper
     */
    public static final String NAMESPACE = "boUserMapper"; // xml文件中的namespac值
    public static final String SOURCE_DIR = "/source/"; // 资源文件存放目录
    
    private BoUserDao boUserDao;
    @Resource
    private BaseDao<BoUser> baseDao;
    private UserRoleService userRoleService;
    @Resource
    private BoRoleService boRoleService;
    private RoleAuthService roleAuthService;
    
    public BoUserDao getBoUserDao() {
	return boUserDao;
    }
    
    @Resource
    public void setBoUserDao(BoUserDao boUserDao) {
	this.boUserDao = boUserDao;
    }
    
    public BaseDao<BoUser> getBaseDao() {
	return baseDao;
    }
    
    public void setBaseDao(BaseDao<BoUser> baseDao) {
	this.baseDao = baseDao;
    }
    
    public UserRoleService getUserRoleService() {
	return userRoleService;
    }
    
    @Resource
    public void setUserRoleService(UserRoleService userRoleService) {
	this.userRoleService = userRoleService;
    }
    
    public RoleAuthService getRoleAuthService() {
	return roleAuthService;
    }
    
    @Resource
    public void setRoleAuthService(RoleAuthService roleAuthService) {
	this.roleAuthService = roleAuthService;
    }
    
    public BoRoleService getBoRoleService() {
	return boRoleService;
    }
    
    public void setBoRoleService(BoRoleService boRoleService) {
	this.boRoleService = boRoleService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO bo转vo
     * @param userRole
     * @return
     */
    @Override
    public UserVO boToVo(BoUser boUser, UserRole userRole) {
	UserVO userVO = new UserVO();
	if (boUser != null && !"".equals(boUser)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(boUser, userVO);
	    userVO.setUserId(boUser.getUserId());
	    // MD5解密
	    // userVO.setUserPwd(userPwd);
	    userVO.setUserName(boUser.getUserName());
	    userVO.setUserSex(boUser.getUserSex());
	    userVO.setUserPhone(boUser.getUserPhone());
	}
	// 角色转换
	if (userRole != null && !"".equals(userRole)) {
	    userVO.setRoleId(userRole.getRole().getRoleId());
	    userVO.setRoleName(userRole.getRole().getRoleName());
	    userVO.setRoleType(userRole.getRole().getRoleType());
	}
	return userVO;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO vo转bo
     * @param userVO
     * @return
     */
    @Override
    public BoUser voToBo(UserVO userVO) {
	BoUser boUser = new BoUser();
	if (userVO != null && !"".equals(userVO)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(userVO, boUser);
	}
	return boUser;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月21日
     * @function：TODO 根据id查找user信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public BoUser findBoUserById(Integer userId) throws Exception {
	if (userId != null && !"".equals(userId)) {
	    return this.boUserDao.findBoUserById(userId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 根据用户名和密码查找用户信息
     * @param userVO
     * @return
     */
    @Override
    public UserVO findBoUserByNameAndPwd(UserVO userVO) {
	BoUser boUser = new BoUser();
	BoUser user = null;
	UserRole userRole = null;
	if (userVO != null && !"".equals(userVO)) {
	    try {
		// MD5密码加密
		boUser.setUserPwd(MD5Util.generatePassword(userVO.getUserPwd()));
		// 登录名大写转小写
		boUser.setUserLogin(userVO.getUserLogin().toLowerCase().trim());
		
		// 查找用户
		user = this.boUserDao.findBoUserByNameAndPwd(boUser);
		
		// 查找用户角色
		if (user != null && !"".equals(user)) {
		    userRole = this.userRoleService.findUserRolesByUserId(user.getUserId());
		    return this.boToVo(user, userRole);
		} else {
		    return null;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 查找所有用户（带分页）
     * @return
     */
    @Override
    public PagingResult<UserVO> findAllBoUser(PageEntity param) {
	PagingResult<BoUser> boUserPage = null;
	PagingResult<UserVO> userVOpage = new PagingResult<UserVO>();
	List<UserVO> userVOList = new ArrayList<UserVO>();
	try {
	    if (param != null && !"".equals(param)) {
		boUserPage = baseDao.selectPagination(param);
		
		// 转为vo对象
		for (BoUser boUser : boUserPage.getResultList()) {
		    userVOList.add(this.boToVo(boUser, this.userRoleService.findUserRolesByUserId(boUser.getUserId())));
		}
		userVOpage.setResultList(userVOList);
		userVOpage.setTotalSize(boUserPage.getTotalSize());
		userVOpage.setCurrentPage(boUserPage.getCurrentPage());
		return userVOpage;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 添加或更新用户
     * @param userVo
     * @return
     */
    @Override
    public BoUser addOrUpdateUser(UserVO userVO, Map<String, MultipartFile> fileMap, HttpServletRequest request) {
	BoUser boUser = new BoUser();
	try {
	    if (userVO != null && !"".equals(userVO)) {
		if (fileMap != null && fileMap.size() > 0) {
		    Iterator<Entry<String, MultipartFile>> fileIterator = fileMap.entrySet().iterator();
		    while (fileIterator.hasNext()) {
			Entry<String, MultipartFile> entry = (Entry<String, MultipartFile>) fileIterator.next();
			
			MultipartFile fileValue = (MultipartFile) entry.getValue();
			if (fileValue.getSize() > 0 && !fileValue.isEmpty()) { // 移除空文件的字段
			    String fileName = fileValue.getOriginalFilename();
			    String fileSign = UUID.randomUUID().toString().replace("-", "")
				    + fileName.substring(fileName.lastIndexOf("."), fileName.length());
			    
			    String url = SOURCE_DIR + fileSign;
			    
			    userVO.setUserPhoto(url);
			    
			    // 将图片资源文件上传保存到服务器，并指定保存位置
			    String path = new String(request.getSession().getServletContext().getRealPath("/")
				    + SOURCE_DIR + fileSign);
			    // 保存上传的文件
			    fileValue.transferTo(new File(path));
			}
		    }
		}
		
		// 密码MD5加密
		if (userVO.getUserPwd() != null && !"".equals(userVO.getUserPwd())) {
		    String encrypt = MD5Util.generatePassword(userVO.getUserPwd());
		    userVO.setUserPwd(encrypt);
		}
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		if (userVO.getUserId() != null && !"".equals(userVO.getUserId())) { // 更新
		    this.baseDao.updateSelective(this.voToBo(userVO));
		} else {
		    boUser = this.voToBo(userVO);
		    boUser.setIsDeleted("0");
		    boUser.setUserType("2"); // 普通管理员
		    this.baseDao.insertSelective(boUser);
		}
		return boUser;
	    }
	} catch (IllegalStateException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 加载用户列表信息（datagrid数据格式）
     * @param dataGridPage
     * @return
     */
    @Override
    public DataGrid loadUserListInfo(DataGridPage dataGridPage) {
	if (dataGridPage != null && !"".equals(dataGridPage)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(dataGridPage);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoUser> boUserList = (List<BoUser>) dataGrid.getRows();
		List<UserVO> userVos = new ArrayList<UserVO>();
		// bo转vo
		for (BoUser boUser : boUserList) {
		    userVos.add(this.boToVo(boUser, this.userRoleService.findUserRolesByUserId(boUser.getUserId())));
		}
		dataGrid.setRows(userVos);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 检查用户名是否已经存在
     * @param userVO
     * @return
     */
    @Override
    public BoUser findBoUserByLoginName(UserVO userVO) {
	BoUser boUser = null;
	if (userVO != null && !"".equals(userVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    boUser = this.baseDao.get(userVO.getUserLogin());
	}
	return boUser;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月20日
     * @function：TODO 根据id删除用户(批量)【先删除权限、再删除角色、最后删除用户】
     * @param userId
     * @return
     */
    @Override
    public Integer deleteBoUserById(List<String> userIds) {
	Integer result = 0;
	UserRole userRole = null;
	List<RoleAuth> roleAuth = null;
	if (userIds != null && !userIds.isEmpty() && userIds.size() > 0) {
	    for (String userId : userIds) {
		// 查找是否有角色
		userRole = this.userRoleService.findUserRolesByUserId(Integer.parseInt(userId));
		
		if (userRole != null) {
		    // 查找角色是否有权限
		    roleAuth = this.roleAuthService.findBoAuthByRole(userRole.getRole().getRoleId());
		    
		    if (roleAuth != null && roleAuth.size() > 0) {
			
			// 删除权限
			result = this.roleAuthService.deleteRoleAuthByRole(userRole.getRole().getRoleId());
			
		    } else {
			result = 1;
		    }
		    if (result > 0) {
			// 删除角色
			result = this.userRoleService.deleteUserRoleByUser(Integer.parseInt(userId));
		    }
		} else { // 没有角色
		    result = 1;
		}
		
		if (result > 0) {
		    // 删除用户
		    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		    result = this.baseDao.delete(Integer.parseInt(userId));
		}
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月27日
     * @function：TODO 根据用户id查找其权限url请求路径(set去除重复路径)
     * @param userId
     * @return
     */
    @Override
    public Set<String> findBoAuthUrlByBoUserId(Integer userId) {
	UserRole userRole = null;
	List<RoleAuth> roleAuths = null;
	Set<String> authUrlSet = new HashSet<String>();
	if (userId != null && !"".equals(userId)) {
	    // 根据用户查找角色
	    userRole = this.userRoleService.findUserRolesByUserId(userId);
	    
	    // 根据角色查找权限
	    roleAuths = this.roleAuthService.findBoAuthByRole(userRole.getRole().getRoleId());
	    
	    // 查找其所有请求路径
	    for (RoleAuth roleAuth : roleAuths) {
		authUrlSet.add(roleAuth.getAuth().getAuthUrl());
	    }
	    
	}
	return authUrlSet;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 根据类型查找超级管理员
     * @param type
     * @return
     */
    @Override
    public BoUser findAdminByType(String type) {
	if (type != null && !"".equals(type)) {
	    UserVO userVO = new UserVO();
	    userVO.setUserType(type);
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.selectFk(userVO);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月14日
     * @function：TODO 验证用户信息（修改密码用）
     * @param userVO
     * @return
     */
    @Override
    public BoUser validateUserInfo(UserVO userVO) {
	if (userVO != null && !"".equals(userVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.selectFk(userVO);
	}
	return null;
    }
}
