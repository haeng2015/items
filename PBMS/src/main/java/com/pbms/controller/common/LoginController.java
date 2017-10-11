package com.pbms.controller.common;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.pbms.service.BoUserService;
import com.pbms.service.RoleAuthService;
import com.pbms.service.UserRoleService;
import com.pbms.vo.JsonVO;
import com.pbms.vo.UserVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年4月28日
 * @说明：用户登录控制器（不进入拦截器）
 */
@Controller
@RequestMapping("/")
public class LoginController {
    
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private BoUserService boUserService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleAuthService roleAuthService;
    
    public BoUserService getBoUserService() {
	return boUserService;
    }
    
    @Resource
    public void setBoUserService(BoUserService boUserService) {
	this.boUserService = boUserService;
    }
    
    public static Logger getLogger() {
	return LOGGER;
    }
    
    public UserRoleService getUserRoleService() {
	return userRoleService;
    }
    
    public void setUserRoleService(UserRoleService userRoleService) {
	this.userRoleService = userRoleService;
    }
    
    public RoleAuthService getRoleAuthService() {
	return roleAuthService;
    }
    
    public void setRoleAuthService(RoleAuthService roleAuthService) {
	this.roleAuthService = roleAuthService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 跳转到错误页面
     * @return
     */
    @RequestMapping("error")
    public ModelAndView login() {
	ModelAndView mv = new ModelAndView("/error");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 跳转到登录页面
     * @return
     */
    @RequestMapping("login")
    public ModelAndView toLogin() {
	ModelAndView mv = new ModelAndView("/login");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 注销退出
     * @return
     */
    @RequestMapping("cancelQuite")
    @ResponseBody
    public JsonVO cancelQuite(HttpServletRequest request) {
	JsonVO json = new JsonVO();
	try {
	    // UserVO userVO = (UserVO)
	    // request.getSession().getAttribute("userVO");
	    request.getSession().removeAttribute("userVO");
	    // 销毁session
	    request.getSession().invalidate();
	    // ServletContext application =
	    // request.getSession().getServletContext();
	    json.setReflag(true);
	} catch (Exception e) {
	    json.setReflag(false);
	    LOGGER.error("LoginController -> cancelQuite : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 检查验证码
     * @return
     */
    public boolean checkCodeImage(HttpServletRequest request, UserVO userVO) {
	try {
	    // 设置request编码方式
	    request.setCharacterEncoding("utf-8");
	    // 从session中获取google kaptcha 插件生成的验证码
	    String googlecode = request.getSession()
		    .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
	    // 比较验证码正确性
	    if (googlecode.equalsIgnoreCase(userVO.getSecurityCode())) {
		return true;
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> checkCodeImage : " + e.getMessage());
	}
	return false;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 验证验证码
     * @param userVO
     * @param request
     * @param response
     */
    @RequestMapping("chechKaptchaImage")
    public void chechKaptchaImage(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	PrintWriter pw = null;
	String jsonString = null;
	// JSONObject jsonObj = null;
	try {
	    if (this.checkCodeImage(request, userVO)) {
		json.setReflag(true);
		json.setInfoMsg("验证码输入正确！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("验证码输入错误！");
	    }
	    // jsonObj = OrgJsonUtil.beanToJsonObj(json); //
	    // 转为json对象(先转为map，再转为json格式，不能映射boolean型变量)
	    // jsonObj = new JSONObject(json); //转为json对象
	    response.setContentType("application/json;charset=UTF-8");
	    pw = response.getWriter();
	    jsonString = JSON.toJSONString(json);
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("LoginController -> chechKaptchaImage : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 用户登录信息检查
     * @param userVO
     * @return
     */
    @RequestMapping("checkUserInfo")
    public void checkUserInfo(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	UserVO user = null;
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    if (this.checkCodeImage(request, userVO)) { // 验证码正确
		user = boUserService.findBoUserByNameAndPwd(userVO);
		if (user != null && !"".equals(user)) {
		    // 将用户信息放入session中
		    request.getSession().setAttribute("userVO", user);
		    // 将输入的登录密码(没有经过MD5加密)放入session中，便于修改密码
		    request.getSession().setAttribute("userPWD", userVO.getUserPwd());
		    
		    // 将用户权限路径放入(url)session中
		    request.getSession().setAttribute("authRequestUrl",
			    this.boUserService.findBoAuthUrlByBoUserId(user.getUserId()));
		    json.setReflag(true);
		    json.setInfoMsg("用户登录成功！");
		} else {
		    json.setReflag(false);
		    json.setInfoMsg("用户名或密码错误！");
		}
	    } else {
		json.setReflag(false);
		json.setInfoMsg("验证码输入错误");
		json.setDlgType(1);
	    }
	    response.setContentType("application/json;charset=UTF-8");
	    pw = response.getWriter();
	    jsonString = JSON.toJSONString(json);
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("LoginController -> checkUserInfo : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 登录成功。进入欢迎页
     * @return
     */
    @RequestMapping("intoWelcome")
    public ModelAndView intoWelcome(HttpServletRequest request) {
	ModelAndView mv = null;
	UserVO loginUser = null;
	try {
	    loginUser = (UserVO) request.getSession().getAttribute("userVO");
	    if (loginUser != null && !"".equals(loginUser)) {
		mv = new ModelAndView("/common/welcome");
	    } else {
		mv = new ModelAndView("redirect:/login.do");
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> intoWelcome : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 没有权限，进入指定页面
     * @return
     */
    @RequestMapping("noAuth")
    public ModelAndView noAuth() {
	ModelAndView mv = new ModelAndView("/noAuth");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为超级角色添加所有权限（超级管理员id和超级角色id都指定为adminId）
     * @return
     */
    @RequestMapping("/addRoleAndAuthForAdmin")
    @ResponseBody
    public JsonVO addRoleAndAuthForAdmin(String superType) {
	JsonVO json = new JsonVO();
	UserRole userRole = null;
	Integer result = 0;
	try {
	    if (superType != null && !"".equals(superType)) {
		// 为超级管理员添加并赋予超级角色
		userRole = this.userRoleService.addRoleForAdmin(superType);
		
		if (userRole != null) {
		    // 为超级角色添加所有权限
		    result = this.roleAuthService.addAuthForAdmin(userRole.getRole().getRoleId());
		}
		
		if (result > 0) {
		    json.setReflag(true);
		    json.setInfoMsg("管理员权限刷新成功！");
		} else {
		    json.setReflag(false);
		    json.setInfoMsg("管理员权限刷新失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> addRoleAndAuthForAdmin : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 进入修改信息页面
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public ModelAndView updateUserInfo(HttpServletRequest request) {
	ModelAndView mv = new ModelAndView("/user/updateUserInfo");
	try {
	    UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
	    String userPWD = (String) request.getSession().getAttribute("userPWD");
	    userVO.setUserPwd(userPWD);
	    mv.addObject("userVO", userVO);
	} catch (Exception e) {
	    LOGGER.error("LoginController -> updateUserInfo : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 更新个人(不拦截)
     * @param buildingVO
     * @param response
     * @ResponseBody为 转换json对象的注解
     */
    @RequestMapping("saveUpdateUserInfo")
    @ResponseBody
    public JsonVO saveUpdateUserInfo(UserVO userVO, HttpServletRequest request) {
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
		json.setInfoMsg("信息更新成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("信息更新失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> saveUpdateUserInfo : " + e.getMessage());
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
    @RequestMapping("checkLoginName")
    @ResponseBody
    public JsonVO checkLoginName(UserVO userVO) {
	JsonVO json = new JsonVO();
	try {
	    BoUser boUser = this.boUserService.findBoUserByLoginName(userVO);
	    if (boUser != null) {
		json.setReflag(true);
		json.setInfoMsg("该账号已存在!");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("该账号不存在!");
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> checkLoginName : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月14日
     * @function：TODO 忘记密码，进入验证信息页面
     * @return
     */
    @RequestMapping("toUpdatePwd")
    public ModelAndView toUpdatePwd() {
	ModelAndView mv = new ModelAndView("/user/setUserPWD");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月14日
     * @function：TODO 验证用户信息（忘记密码）
     * @param userVO
     * @return
     */
    @RequestMapping("validateUserInfo")
    @ResponseBody
    public JsonVO validateUserInfo(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	BoUser boUser = null;
	try {
	    // root 14578432132  230125197906277477  5E543256C480AC577D30F76F9120EB74
	    boUser = this.boUserService.validateUserInfo(userVO);
	    if (boUser != null) {
		// 重定向
//		request.setAttribute("userVO", boUser);
//		request.getRequestDispatcher("/user/updateUserPWD.jsp").forward(request, response);
		
//		response.setContentType("text/html; charset=gb2312");
//		response.sendRedirect("/user/updateUserPWD.jsp");
		
		json.setReflag(true);
		json.setReturnObj(boUser);
	    } else {
		json.setReflag(false);
		json.setInfoMsg("输入信息错误!");
	    }
	} catch (Exception e) {
	    LOGGER.error("LoginController -> validateUserInfo : " + e.getMessage());
	}
	return json;
    }

    /**
     * @author：Hehaipeng
     * @date：2017年5月14日
     * @function：TODO 跳转到更新页面
     * @return
     */
    @RequestMapping("tosetSetUserPwd")
    public ModelAndView tosetSetUserPwd(UserVO userVO) {
	ModelAndView mv = new ModelAndView("/user/updateUserPWD");
	mv.addObject("userVO", userVO);
	return mv;
    }
    
    
}
