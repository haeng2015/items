package com.panasign.core;

/**
 * dataGrid对象，
 * 转换成json格式之后可直接返回给easyUI前端datagrid工具接收
 * 自动生成表格
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-5
 */
public class DataGrid{
	/**
	 * 总记录数
	 */
	private long total;
	
	/**
	 * （分页之后的）结果集
	 * 一般为List<Object>
	 */
	private Object rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
}
