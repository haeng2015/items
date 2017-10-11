package com.pbms.util;

import java.util.Map;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月23日
 * @说明：分页的参数类
 */
public class PageEntity {
    private Integer pageNo; // 目前是第几页
    private Integer pageSize; // 每页大小
    private Map<String, Object> params; // 传入的参数
    private String orderColumn;  //要排序的列
    private String orderTurn = "ASC";  // "ASC"  正序排列   "DESC"  逆序排列
    
    public String getOrderColumn() {
	return orderColumn;
    }
    
    public void setOrderColumn(String orderColumn) {
	this.orderColumn = orderColumn;
    }
    
    public String getOrderTurn() {
	return orderTurn;
    }
    
    public void setOrderTurn(String orderTurn) {
	this.orderTurn = orderTurn;
    }
    
    public Integer getPageNo() {
	return pageNo;
    }
    
    public void setPageNo(Integer pageNo) {
	this.pageNo = pageNo;
    }
    
    public Integer getPageSize() {
	return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
    }
    
    public Map<String, Object> getParams() {
	return params;
    }
    
    public void setParams(Map<String, Object> params) {
	this.params = params;
    }
}
