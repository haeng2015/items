package com.pbms.controller;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoAuth;
import com.pbms.pojo.BoRole;
import com.pbms.pojo.RoleAuth;
import com.pbms.service.BoAuthService;
import com.pbms.service.BoRoleService;
import com.pbms.service.RoleAuthService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.JsonVO;
import com.pbms.vo.UserVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：角色控制器
 */
@Controller
@RequestMapping("/user")
public class BoRoleController {
    
    private static final Logger LOGGER = Logger.getLogger(BoRoleController.class);
    
    @Autowired
    private BoRoleService boRoleService;
    private BoAuthService boAuthService;
    @Resource
    private RoleAuthService roleAuthService;
    
    public BoAuthService getBoAuthService() {
	return boAuthService;
    }
    
    @Resource
    public void setBoAuthService(BoAuthService boAuthService) {
	this.boAuthService = boAuthService;
    }
    
    public RoleAuthService getRoleAuthService() {
	return roleAuthService;
    }
    
    public void setRoleAuthService(RoleAuthService roleAuthService) {
	this.roleAuthService = roleAuthService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 到角色列表
     * @return
     */
    @RequestMapping("/toRoleList")
    public ModelAndView toRoleList() {
	ModelAndView mv = new ModelAndView("/role/roleList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载角色列表
     * @param params
     * @return
     */
    @RequestMapping("/loadRoleInfo")
    public void loadRoleInfo(PageEntity param, HttpServletResponse response) {
	PagingResult<BoRole> pagingResult = new PagingResult<BoRole>();
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    pagingResult = this.boRoleService.loadRoleList(param);
	    jsonString = JSON.toJSONString(pagingResult);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoRoleController -> loadRoleInfo : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载角色列表
     * @param params
     * @return
     */
    @RequestMapping("/loadRoleList")
    @ResponseBody
    public DataGrid loadRoleList(DataGridPage dataGridPage) {
	DataGrid dataGrid = new DataGrid();
	try {
	    dataGrid = this.boRoleService.loadRoleListInfo(dataGridPage);
	} catch (Exception e) {
	    LOGGER.error("BoRoleController -> loadRoleList : " + e.getMessage());
	}
	return dataGrid;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 编辑角色（添加、修改）
     * @return
     */
    @RequestMapping("/toEditRole")
    public ModelAndView toEditRole(UserVO userVO) {
	ModelAndView mv = new ModelAndView("/role/roleEdit");
	if (userVO != null) {
	    userVO = this.boRoleService.boToVo(this.boRoleService.findRoleById(userVO.getRoleId()));
	    mv.addObject("roleVO", userVO);
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 添加、更新角色信息
     * @param roleVO
     * @param response
     */
    @RequestMapping("/addUpdateRole")
    public void addUpdateRole(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    if (userVO != null && !"".equals(userVO)) {
		UserVO loginUser = (UserVO) request.getSession().getAttribute("userVO");
		if (loginUser == null) {
		    // mav = new
		    // ModelAndView("redirect:/purchaser/userIndex.do"); //重定向
		    response.sendRedirect("/cancelQuite.do"); // 重定向到登录页
		}
		int result = this.boRoleService.updateOrAddRole(userVO, loginUser);
		if (result > 0) {
		    json.setReflag(true);
		    json.setInfoMsg("角色(" + userVO.getRoleName() + ")添加成功");
		} else {
		    json.setReflag(false);
		}
	    } else {
		json.setReflag(false);
	    }
	    response.setContentType("application/json;charset=UTF-8");
	    pw = response.getWriter();
	    jsonString = JSON.toJSONString(json);
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    json.setReflag(false);
	    LOGGER.error("BoRoleController -> addUpdateRole : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 删除角色
     * @param roleVO
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public JsonVO deleteRole(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    int result = this.boRoleService.deleteRoleByIds(userVO.getRoleIds());
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("角色删除成功!");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("角色删除失败!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoleController -> deleteRole : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 到角色列表
     * @return
     */
    @RequestMapping("/toRoleAuth")
    public ModelAndView toRoleAuth() {
	ModelAndView mv = new ModelAndView("/roleAuth/roleList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 为角色添加权限
     * @return
     */
    @RequestMapping("/addAuthForRole")
    public ModelAndView addAuthForRole(UserVO userVO) {
	ModelAndView mv = new ModelAndView("/role/roleAuth");
	List<BoAuth> boAuths = null;
	List<RoleAuth> roleAuths = null;
	try {
	    // 查找所有权限
	    boAuths = this.boAuthService.findAllBoAuth();
	    
	    // 查找该角色下的权限
	    if (userVO != null && !"".equals(userVO)) {
		roleAuths = this.roleAuthService.findBoAuthByRole(userVO.getRoleId());
	    }
	    
	    // 注意：删除元素，如果未改变迭代的下标，这样当迭代到最后一个的时候就会抛异常(for (UserVO auth : auths) )
	    Iterator<BoAuth> authIterator = boAuths.iterator();
	    while (authIterator.hasNext()) {
		BoAuth auth = authIterator.next();
		for (RoleAuth roleAuth : roleAuths) {
		    if (auth.getAuthId() == roleAuth.getAuth().getAuthId()) {
			authIterator.remove();
			break;
		    }
		}
	    }
	    
	    // for (int i = 0, len = auths.size(); i < len; i++) {
	    // for (RoleAuth roleAuth : roleAuths) {
	    // if (auths.get(i).getAuthId() == roleAuth.getAuth().getAuthId()) {
	    // auths.remove(i); // 移除角色存在的权限
	    // -- len; //减少一个
	    // -- i;
	    // break;
	    // }
	    // }
	    // }
	    
	    mv.addObject("roleId", userVO.getRoleId());
	    mv.addObject("auths", boAuths);
	    mv.addObject("roleAuths", roleAuths);
	    
	} catch (Exception e) {
	    LOGGER.error("BoUserController -> addAuthForRole : " + e.getMessage());
	}
	return mv;
    }

    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 为角色添加权限
     * @param roleVO
     * @return
     */
    @RequestMapping("/saveOrUpdateRoleAuth")
    @ResponseBody
    public JsonVO saveOrUpdateRoleAuth(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    int result = this.roleAuthService.addOrUpdateRoleAuth(userVO);
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("角色权限添加成功!");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("角色权限添加失败!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoleController -> saveOrUpdateRoleAuth : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 为角色添加权限
     * @param roleVO
     * @return
     */
    @RequestMapping("/getRoleAuthByRole")
    @ResponseBody
    public JsonVO getRoleAuthByRole(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    List<RoleAuth> roleAuths = this.roleAuthService.findBoAuthByRole(userVO.getRoleId());
	    if (roleAuths.size() > 0) {
		json.setReflag(true);
		json.setReturnObj(roleAuths);
	    } else {
		json.setReflag(false);
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoleController -> getRoleAuthByRole : " + e.getMessage());
	}
	return json;
    }
    
}
