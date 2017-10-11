package com.pbms.util;

import java.util.HashMap;
import java.util.Map;


public class DataGridPage implements java.io.Serializable {
    /**
     * @版权所有：Hehaipeng
     * @项目名称:PBMS物业后台管理系统
     * @创建者:Hehaipeng
     * @创建日期:2017年4月12日
     * @说明：
     */
    private static final long serialVersionUID = -1065490537962225475L;
    
    /**
     * 每页记录数(rows名不可变)
     */
    private Integer rows;
    
    /**
     * 当前页(page名不可变)
     */
    private Integer page;
    
    /**
     * 总页数
     */
    private Integer pageOffset;
    
    /**
     * 升序(ASC)还是降序(默认倒序)
     */
    private String order = "DESC";
    
    /**
     * 根据那个字段排序
     */
    private Object sort;
    
    /**
     * 传入的分页参数
     */
    private Map<String, Object> params = new HashMap<String, Object>();
    
    public Integer getRows() {
	return rows;
    }
    
    public void setRows(Integer rows) {
	this.rows = rows;
    }
    
    public Integer getPage() {
	return page;
    }
    
    public void setPage(Integer page) {
	this.page = page;
    }
    
    public Integer getPageOffset() {
	return pageOffset;
    }
    
    public void setPageOffset(Integer pageOffset) {
	this.pageOffset = pageOffset;
    }
    
    public String getOrder() {
	return order;
    }
    
    public void setOrder(String order) {
	this.order = order;
    }
    
    public Object getSort() {
	return sort;
    }
    
    public void setSort(Object sort) {
	this.sort = sort;
    }
    
    public Map<String, Object> getParams() {
	return params;
    }
    
    public void setParams(Map<String, Object> params) {
	this.params = params;
    }
    
}
