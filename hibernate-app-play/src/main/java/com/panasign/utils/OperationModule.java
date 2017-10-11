package com.panasign.utils;

import java.util.HashMap;
import java.util.Map;

public final class OperationModule {
	// 模块类型
	// 贴子回复
	public final static String BBS_COMMENT = "BBS_COMMENT";
	// 发表贴子
	public final static String BBS_POST = "BBS_POST";
	// 黑名单
	public final static String BLACKLIST = "BLACKLIST";
	// DEPARTMENT
	public final static String DEPARTMENT = "DEPARTMENT";
	// 医生表
	public final static String DOCTOR = "DOCTOR";
	// 专家门诊停诊
	public final static String DOCTOR_STOP_DIAGNOSIS = "DOCTOR_STOP_DIAGNOSIS";
	// 医院
	public final static String HOSPITAL = "HOSPITAL";
	// 知识库
	public final static String KNOWLEDGE_BASE = "KNOWLEDGE_BASE";
	// 管理员用户
	public final static String MANAGER_USER = "MANAGER_USER";
	// 后台日志
	// public final static String MANAGE_LOG="MANAGE_LOG";
	// 患者用户表
	public final static String PATIENT_USER = "PATIENT_USER";
	// 患者用户导入
	public final static String PATIENT_USER_IMPORT = "PATIENT_USER_IMPORT";
	// 手机用户
	public final static String PHONE_USER = "PHONE_USER";
	// 推送日志
	public final static String PUSH_INFO = "PUSH_INFO";
	// 用户挂号
	public final static String REGISTER = "REGISTER";
	// 排班计划
	public final static String SCHEDULING = "SCHEDULING";
	// 终端日志表
	// public final static String TERMINAL_LOG="TERMINAL_LOG";
	// 排班统一设置
	public final static String SCHEDULING_SETTING = "SCHEDULING_SETTING";
	// 升级
	public final static String UPGRADE = "UPGRADE";
	// 推送规则
	public final static String PUSHRULE = "PUSHRULE";
	// 反馈信息
	public final static String USEROPINION = "USEROPINION";
	//定制模块信息
	public final static String MODULEINFO = "MODULEINFO";
	
	//满意度调查模板
	public final static String SURVEY_THEME = "SURVEY_THEME";
	
	//素材模板
	public final static String MATERIAL = "MATERIAL";
	
	//文档模板
		public final static String ARCHIVE = "ARCHIVE";
	
	public static final Map<String, String> operationModuleLogMap;
	static {
		operationModuleLogMap = new HashMap<String, String>();
		operationModuleLogMap.put(BBS_COMMENT, "贴子回复");
		operationModuleLogMap.put(BBS_POST, "发表贴子");
		operationModuleLogMap.put(BLACKLIST, "用户黑名单");
		operationModuleLogMap.put(DEPARTMENT, "科室");
		operationModuleLogMap.put(DOCTOR, "医生");
		operationModuleLogMap.put(DOCTOR_STOP_DIAGNOSIS, "专家门诊停诊");
		operationModuleLogMap.put(HOSPITAL, "医院");
		operationModuleLogMap.put(KNOWLEDGE_BASE, "知识库");
		operationModuleLogMap.put(MANAGER_USER, "管理员用户");
		operationModuleLogMap.put(PATIENT_USER, "患者用户");
		operationModuleLogMap.put(PATIENT_USER_IMPORT, "患者用户导入");
		operationModuleLogMap.put(PHONE_USER, "手机用户");
		operationModuleLogMap.put(PUSH_INFO, "推送日志");
		operationModuleLogMap.put(REGISTER, "用户挂号");
		operationModuleLogMap.put(SCHEDULING, "排班计划");
		operationModuleLogMap.put(SCHEDULING_SETTING, "排班统一设置");
		operationModuleLogMap.put(UPGRADE, "升级");
		operationModuleLogMap.put(PUSHRULE, "推送规则");
		operationModuleLogMap.put(USEROPINION, "反馈信息");
		operationModuleLogMap.put(MODULEINFO, "定制模块信息");
		operationModuleLogMap.put(SURVEY_THEME, "满意度调查模板");
		operationModuleLogMap.put(MATERIAL, "素材模板");
		operationModuleLogMap.put(ARCHIVE, "文档模板");
	}
	/*
	 * 操作类型
	 */
	// 操作类型 初次登入
	public final static String LOGIN_FIRST = "LOGIN_FIRST";
	
	// 操作类型 登入
	public final static String LOGIN = "LOGIN";
	
	// 操作类型 登出
	public final static String LOGOUT = "LOGOUT";
	// 操作类型 增加
	public final static String ADD = "ADD";

	// 操作类型 删除
	public final static String DEL = "DEL";

	// 操作类型 更新
	public final static String UPDATE = "UPDATE";

	// 操作类型 查看
	// public final static String VIEW="VIEW";

	// 操作类型 获取（App首次启动链接，获取医院信息）
	public final static String GET = "GET";
	// 操作类型 查看
	public final static String LIST = "LIST";
	// 操作类型 搜索
	public final static String SEARCH = "SEARCH";
	// 操作类型 注册
	public final static String INSERT = "INSERT";
	// 操作类型 管理员添加/更新
	public final static String UPDATEORADD = "UPDATEORADD";
	// 操作类型 管理员添加停诊
	public final static String STOPDOCTOR = "STOPDOCTOR";
	// 操作类型 管理员添加排班
	public final static String PAIBAN = "PAIBAN";
	// 操作类型 反馈答复
	public final static String RESPOND = "RESPOND";
	//操作类型 反馈处理
	public final static String DISPOSE = "DISPOSE";

	public static final Map<String, String> operationTypeLogMap;
	static {
		operationTypeLogMap = new HashMap<String, String>();
		operationTypeLogMap.put(ADD, "增加");
		operationTypeLogMap.put(DEL, "删除");
		operationTypeLogMap.put(UPDATE, "更新");
		// operationTypeLogMap.put(VIEW,"查看");
		operationTypeLogMap.put(LIST, "查看");
		operationTypeLogMap.put(GET, "获取");
		operationTypeLogMap.put(SEARCH, "搜索");
		operationTypeLogMap.put(INSERT, "注册");
		operationTypeLogMap.put(LOGIN_FIRST, "医院管理员初次登陆");
		operationTypeLogMap.put(LOGIN, "医院管理员登陆");
		operationTypeLogMap.put(LOGOUT, "医院管理员注销");
		operationTypeLogMap.put(UPDATEORADD, "添加/更新");
		operationTypeLogMap.put(STOPDOCTOR, "添加停诊");
		operationTypeLogMap.put(PAIBAN, "添加排班");
		operationTypeLogMap.put(RESPOND, "答复");
		operationTypeLogMap.put(DISPOSE, "处理");
	}

}
