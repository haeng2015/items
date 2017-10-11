package com.pbms.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoUser;
import com.pbms.pojo.UserRole;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoRoleService;
import com.pbms.service.BoUserService;
import com.pbms.service.UserRoleService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.JsonVO;
import com.pbms.vo.UserVO;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月23日
 * @说明：用户控制器
 */

@Controller
@RequestMapping("/user")
public class BoUserController {
    
    private static final Logger LOGGER = Logger.getLogger(BoUserController.class);
    @Autowired
    private BoUserService boUserService;
    private BoRoleService boRoleService;
    @Resource
    private UserRoleService userRoleService;
    @Autowired
    private BoAccessoryService boAccessoryService;
    
    public BoRoleService getBoRoleService() {
	return boRoleService;
    }
    
    @Resource
    public void setBoRoleService(BoRoleService boRoleService) {
	this.boRoleService = boRoleService;
    }
    
    public UserRoleService getUserRoleService() {
	return userRoleService;
    }
    
    public void setUserRoleService(UserRoleService userRoleService) {
	this.userRoleService = userRoleService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 进入用户列表
     * @return
     */
    @RequestMapping("/toUserList")
    public ModelAndView toUserList() {
	ModelAndView mv = new ModelAndView("/user/userList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 加载user列表
     * @param param
     * @param response
     */
    @RequestMapping("/loadBoUserList")
    public void loadBoUserList(PageEntity param, HttpServletResponse response) {
	PagingResult<UserVO> pagingResult = new PagingResult<UserVO>();
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    pagingResult = this.boUserService.findAllBoUser(param);
	    if (pagingResult != null) {
		jsonString = JSON.toJSONString(pagingResult);
	    }
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> loadBoUserList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载用户列表信息（datagrid数据格式）、
     * @param pageContext
     * @return
     */
    @RequestMapping("/loadUserList")
    public void loadUserList(DataGridPage dataGridPage, HttpServletResponse response) {
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    DataGrid dataGrid = this.boUserService.loadUserListInfo(dataGridPage);
	    jsonString = JSON.toJSONString(dataGrid);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController -> loadUserList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 跳转到编辑(添加/修改）
     * @param userVO
     * @return
     */
    @RequestMapping("/editUser")
    public ModelAndView editUser(UserVO userVO) {
	ModelAndView mv = null;
	if ("1".equals(userVO.getMark())) { // 详细信息
	    mv = new ModelAndView("/user/userDetailed");
	} else {
	    mv = new ModelAndView("/user/editUser");
	}
	try {
	    if (userVO.getUserId() != null && !"".equals(userVO.getUserId())) {
		userVO = boUserService.boToVo(boUserService.findBoUserById(userVO.getUserId()),
			this.userRoleService.findUserRolesByUserId(userVO.getUserId()));
		mv.addObject("userVO", userVO);
		mv.addObject("mark", userVO.getMark());
	    }
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> editUser : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存、更新对象
     * @param buildingVO
     * @param response
     * @ResponseBody为 转换json对象的注解
     * 
     *                // ？？?::思考：：mybatis保存后对象后如何返回该对象（或者去查找一遍）， //
     *                解决方法：保存后返回主键id（mysql）keyProperty="userId"
     */
    @RequestMapping("/saveOrUpdateUser")
    @ResponseBody
    public JsonVO saveOrUpdateUser(UserVO userVO, HttpServletRequest request) {
	JsonVO json = new JsonVO();
	try {
	    // 文件上传处理
	    Map<String, MultipartFile> fileMap = null;
	    // 创建一个通用的多部分解析器
	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession()
		    .getServletContext());
	    // 判断 request 是否有文件上传,即多部分请求...
	    if (commonsMultipartResolver.isMultipart(request)) {
		// 普通request请求对象转换成可以处理文件的multiRequest对象
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 获取参数名对应的文件列表
		fileMap = multiRequest.getFileMap();
	    }
	    // 此处返回的BoBuilding对象是保存前的，所以不会有保存到数据库后的属性（如，自增的主键等）
	    BoUser boUser = this.boUserService.addOrUpdateUser(userVO, fileMap, request);
	    
	    if (boUser != null && !"".equals(boUser)) {
		json.setReflag(true);
		if (userVO.getUserId() != null && !"".equals(userVO.getUserId())) {
		    json.setInfoMsg("用户更新成功！");
		} else {
		    json.setInfoMsg("用户添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (userVO.getUserId() != null && !"".equals(userVO.getUserId())) {
		    json.setInfoMsg("用户更新失败！");
		} else {
		    json.setInfoMsg("用户添加失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> saveOrUpdateUser : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 检查登录名是否已经存在
     * @param userVO
     * @return
     */
    @RequestMapping("/checkUserLoginName")
    @ResponseBody
    public JsonVO checkUserLoginName(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    // 更新用户，不需要检测
	    if (userVO.getUserId() != null && !"".equals(userVO)) {
		json.setReflag(false);
		return json;
	    } else {
		BoUser boUser = this.boUserService.findBoUserByLoginName(userVO);
		if (boUser != null) {
		    json.setReflag(true);
		    json.setInfoMsg("该账号已存在!");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> checkUserLoginName : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 删除对象
     * @param buildingVO
     * @param response
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public JsonVO deleteUser(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    Integer result = this.boUserService.deleteBoUserById(userVO.getUserIds());
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("用户删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("用户删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> deleteUser : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 进入编辑角色页面（携带用户id及所有角色）
     * @return
     */
    @RequestMapping("/editUserRole")
    public ModelAndView editUserRole(UserVO userVO) {
	ModelAndView mv = new ModelAndView("/user/userRole");
	UserRole userRole = null;
	try {
	    if (userVO.getUserId() != null && !"".equals(userVO.getUserId())) {
		// 查找该用户所拥有的所有角色
		userRole = this.userRoleService.findUserRolesByUserId(userVO.getUserId());
		if (userRole != null) {
		    userVO.setUserRoleId(userRole.getId());
		    userVO.setRoleId(userRole.getRole().getRoleId());
		    userVO.setRoleName(userRole.getRole().getRoleName());
		}
		mv.addObject("userVO", userVO);
	    }
	    // 查找所有角色
	    mv.addObject("roleList", this.boRoleService.findAllRoles());
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> editUserRole : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存用户角色
     * @param buildingVO
     * @param response
     */
    @RequestMapping("/saveUserRole")
    public void saveUserRole(UserVO userVO, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    result = this.userRoleService.updateOrAddUserRole(userVO);
	    if (result > 0) {
		json.setReflag(true);
		if (userVO.getUserRoleId() != null && !"".equals(userVO.getUserRoleId())) { // 更新
		    json.setInfoMsg("角色更新成功！");
		} else {
		    json.setInfoMsg("角色添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (userVO.getUserRoleId() != null && !"".equals(userVO.getUserRoleId())) { // 更新
		    json.setInfoMsg("角色更新失败！");
		} else {
		    json.setInfoMsg("角色添加失败！");
		}
	    }
	    jsonString = JSON.toJSONString(json);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> saveUserRole : " + e.getMessage());
	}
    }
    
}
