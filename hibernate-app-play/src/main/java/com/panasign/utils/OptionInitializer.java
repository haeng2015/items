package com.panasign.utils;

import java.util.HashMap;
import java.util.Map;


public class OptionInitializer {
	
    public static final Map<String, String> MANAGERUSER_IS_BLOCK_MAP;
    
    static{
    	MANAGERUSER_IS_BLOCK_MAP = new HashMap<String, String>();
    	MANAGERUSER_IS_BLOCK_MAP.put("0", "活动");
    	MANAGERUSER_IS_BLOCK_MAP.put("1", "锁定");
    }
    
    public static final Map<Integer, String> UPGRADE_IS_UP_MAP;
    
    static{
    	UPGRADE_IS_UP_MAP = new HashMap<Integer, String>();
    	UPGRADE_IS_UP_MAP.put(0, "否");
    	UPGRADE_IS_UP_MAP.put(1, "是");
    }
    
    public static final Map<Integer, String> UPGRADE_VERSION_TYPE_MAP;
    
    static{
    	UPGRADE_VERSION_TYPE_MAP = new HashMap<Integer, String>();
    	UPGRADE_VERSION_TYPE_MAP.put(0, "用户版");
    	UPGRADE_VERSION_TYPE_MAP.put(1, "医生版");
    }
    
    public static final Map<String, String> UPGRADE_TYPE_MAP;
    
    static{
    	UPGRADE_TYPE_MAP = new HashMap<String, String>();
    	UPGRADE_TYPE_MAP.put("native", "全量");
    	UPGRADE_TYPE_MAP.put("material", "增量");
    }
    
    public static final Map<String, String> UPGRADE_OS_MAP;
    
    static{
    	UPGRADE_OS_MAP = new HashMap<String, String>();
    	UPGRADE_OS_MAP.put("Android", "安卓系统");
    	UPGRADE_OS_MAP.put("IOS", "苹果系统");
    }
    
    public static final Map<String, String> UPGRADE_OS_VERSION_MAP;
    
    static{
    	UPGRADE_OS_VERSION_MAP = new HashMap<String, String>();
    	UPGRADE_OS_VERSION_MAP.put("Android", "4.1");
    	UPGRADE_OS_VERSION_MAP.put("IOS", "6.3.2");
    }
    
    public static final Map<String, String> PUSH_INFO_ISPUSHED_MAP;
    
    static{
    	PUSH_INFO_ISPUSHED_MAP = new HashMap<String, String>();
    	PUSH_INFO_ISPUSHED_MAP.put("true", "已接收");
    	PUSH_INFO_ISPUSHED_MAP.put("false", "未接收");
    }
    
    public static final Map<String, String> PUSH_INFO_SEX_MAP;
    
    static{
    	PUSH_INFO_SEX_MAP = new HashMap<String, String>();
    	PUSH_INFO_SEX_MAP.put("0", "男");
    	PUSH_INFO_SEX_MAP.put("1", "女");
    }
    
    public static final Map<Integer, String> PUSH_TASK_TYPE_MAP;
    
    static{
    	PUSH_TASK_TYPE_MAP = new HashMap<Integer, String>();
    	PUSH_TASK_TYPE_MAP.put(0, "单条定时推送");
    	PUSH_TASK_TYPE_MAP.put(1, "周期定时推送");
    }
    
    public static final Map<Integer, String> USER_OPINION_RESPONDED_MAP;
    
    static{
    	USER_OPINION_RESPONDED_MAP = new HashMap<Integer, String>();
    	USER_OPINION_RESPONDED_MAP.put(0, "未答复");
    	USER_OPINION_RESPONDED_MAP.put(1, "已答复");
    }
    
    public static final Map<Integer, String> USER_OPINION_OS_MAP;
    
    static{
    	USER_OPINION_OS_MAP = new HashMap<Integer, String>();
    	USER_OPINION_OS_MAP.put(1, "安卓");
    	USER_OPINION_OS_MAP.put(2, "苹果");
    }
    
    public static final Map<Integer, String> COUNT_MONTH_MAP;
    
    static{
    	COUNT_MONTH_MAP = new HashMap<Integer, String>();
    	COUNT_MONTH_MAP.put(1, "一月");
    	COUNT_MONTH_MAP.put(2, "二月");
    	COUNT_MONTH_MAP.put(3, "三月");
    	COUNT_MONTH_MAP.put(4, "四月");
    	COUNT_MONTH_MAP.put(5, "五月");
    	COUNT_MONTH_MAP.put(6, "六月");
    	COUNT_MONTH_MAP.put(7, "七月");
    	COUNT_MONTH_MAP.put(8, "八月");
    	COUNT_MONTH_MAP.put(9, "九月");
    	COUNT_MONTH_MAP.put(10, "十月");
    	COUNT_MONTH_MAP.put(11, "十一月");
    	COUNT_MONTH_MAP.put(12, "十二月");
    }
    
    public static final Map<Integer, String> METADATA_PARENT_TYPE_MAP;
    
    static{
    	METADATA_PARENT_TYPE_MAP = new HashMap<Integer, String>();
    	METADATA_PARENT_TYPE_MAP.put(0, "text");
    	METADATA_PARENT_TYPE_MAP.put(1, "json");
    	METADATA_PARENT_TYPE_MAP.put(2, "html");
    }
    
    public static final Map<Integer, String> SURVEY_TYPE_MAP;
    
    static{
    	SURVEY_TYPE_MAP = new HashMap<Integer, String>();
    	SURVEY_TYPE_MAP.put(1, "门诊类型");
    	SURVEY_TYPE_MAP.put(2, "住院类型");
    }
    
    public static final Map<String, String> GET_MONTH_MAP;
    
    static{
    	GET_MONTH_MAP = new HashMap<String, String>();
    	GET_MONTH_MAP.put("00", "全年");
    	GET_MONTH_MAP.put("01", "一月");
    	GET_MONTH_MAP.put("02", "二月");
    	GET_MONTH_MAP.put("03", "三月");
    	GET_MONTH_MAP.put("04", "四月");
    	GET_MONTH_MAP.put("05", "五月");
    	GET_MONTH_MAP.put("06", "六月");
    	GET_MONTH_MAP.put("07", "七月");
    	GET_MONTH_MAP.put("08", "八月");
    	GET_MONTH_MAP.put("09", "九月");
    	GET_MONTH_MAP.put("10", "十月");
    	GET_MONTH_MAP.put("11", "十一月");
    	GET_MONTH_MAP.put("12", "十二月");
    }
    
    public static final Map<Integer, String> TERMINAL_TYPE_MAP;
    
    static{
    	TERMINAL_TYPE_MAP = new HashMap<Integer, String>();
    	TERMINAL_TYPE_MAP.put(8, "QQ");
    	TERMINAL_TYPE_MAP.put(-9, "微信");
    }

    public static final Map<Integer, String> MATERIAL_MAP;
    
    static{
    	MATERIAL_MAP = new HashMap<Integer, String>();
    	MATERIAL_MAP.put(1, "图片");
    	MATERIAL_MAP.put(2, "音频");
    	MATERIAL_MAP.put(3, "视频");
    }
    
    public static final Map<Integer, String> SATIS_GET_EVALUATE_MAP;
    
    static{
    	SATIS_GET_EVALUATE_MAP = new HashMap<Integer, String>();
    	SATIS_GET_EVALUATE_MAP.put(5, "非常满意");
    	SATIS_GET_EVALUATE_MAP.put(4, "满意");
    	SATIS_GET_EVALUATE_MAP.put(3, "一般");
    	SATIS_GET_EVALUATE_MAP.put(2, "差");
    	SATIS_GET_EVALUATE_MAP.put(1, "十分不满意");
    }

}
