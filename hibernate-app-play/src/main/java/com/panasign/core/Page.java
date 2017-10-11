package com.panasign.core;

/**
 * 页对象
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-12-10
 */
public class Page {
	/**
	 * 总记录数
	 */
	private Long total;
	private int beginIndex = 0;	//起始位置，默认0
	private int pageIndex = 1;	//当前页，默认第1页
	private int pageSize = 10; //每页记录数
	private int totalPage = 1;	//总页数
	private boolean status = false;
	private String code = "";//错误编码
	private String msg = "";
	private String action = "";
	/**
	 * 需要返回给页面的json对象，存放了返回的数据
	 */
	private Object data;
	
	public void setPageIndex(int pageIndex){
		this.pageIndex = pageIndex;
	}
	
	public int getPageIndex(){
		return this.pageIndex;
	}
	
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	public int getPageSize(){
		return this.pageSize;
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}
	public boolean getStatus(){
		return this.status;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	public String getMsg(){
		return this.msg;
	}
	
	public void setData(Object data){
		this.data = data;
	}
	public Object getData(){
		return this.data;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	public String getAction(){
		return this.action;
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
