package com.pbms.util;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年5月17日
 * @说明：对list排序的工具类
 */
public class ListSort implements Comparable<ListSort> {
    
    // 排序字段
    private Integer id;
    
    private Integer name;
    
    public Integer getId() {
	return id;
    }
    
    public void setId(Integer id) {
	this.id = id;
    }
    
    public Integer getName() {
	return name;
    }
    
    public void setName(Integer name) {
	this.name = name;
    }
    
    @Override
    public int compareTo(ListSort o) {
	Integer result = 0;
	if (o != null && !"".equals(o)) {
	    result = this.getId() - o.getId(); // 先按照id排序
	    
	    if (result > 0) {
		return this.getName() - o.getName(); // 再继续按名称排序
	    }
	}
	return result;
    }
}
