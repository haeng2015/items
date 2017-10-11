package com.panasign.core;

/**
 * treeGrid对象， 转换成json格式之后可直接返回给easyUI前端treeGrid工具接收 自动生成表格
 * 
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-5
 */
public class TreeGrid {
	/**
	 * 总记录数
	 */
	private Long total;
	
	/**
	 * 结果集 一般为List<Object>
	 */
	private Object rows;
	/**
	 * 页脚统计集合 一般为List<Object>
	 */
	private Object footer;
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public Object getRows() {
		return rows;
	}
	
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	public Object getFooter() {
		return footer;
	}
	
	public void setFooter(Object footer) {
		this.footer = footer;
	}

}
