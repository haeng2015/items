package com.panasign.vo;

import java.util.Date;

import com.panasign.utils.DateTimeUtils;

/**
 * view 视图的基本对象 包含视图中常用到的参数比如分页参数，排序参数
 * 
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-5
 */
public class BaseVO implements java.io.Serializable {
	private static final long serialVersionUID = -250278154586917388L;
	private static final String SEARCHBEGINTIME = "1990-01-01";
	private static final String SEARCHENDTIME = "2990-01-01";
	/**
	 * 排序字段名称
	 */
	private String sort;
	
	/**
	 * 顺序
	 */
	private String order;
	
	/**
	 * 当前页码
	 */
	private int page;
	
	/**
	 * 每页记录条数
	 */
	private int rows;
	
	/**
	 * 搜索开始时间
	 */
	private String searchBeginTime;
	
	/**
	 * 搜索截止时间
	 */
	private String searchEndTime;
	
	/**
	 * 时间标记，-6：前6个月，-3：前3个月，-12：前12个月，0：今天
	 */
	private int timeTag;
	
	// 树结构参数
	private String state = "open"; // node state, 'open' or 'closed',default
	private boolean checked = false; // : Indicate whether the node is
	private Object attributes; // custom attributes can be added to a node
	private String iconCls; // ="icon-typeParent";
	
	/**
	 * 根据timeTag设置时间范围取前几个月
	 * 
	 * @author Wu.Liang
	 */
	public void generateTimeRange() {
		// 如果timeTag标记为1，时间范围为“全部”
		if (1 == this.timeTag) {
			if (null == this.searchBeginTime || "".equals(this.searchBeginTime)) {
				searchBeginTime = SEARCHBEGINTIME;
			}
			if (null == this.searchEndTime || "".equals(this.searchEndTime)) {
				searchEndTime = SEARCHENDTIME;
			}
		} else {
			Date endDate = DateTimeUtils.getDateByRollMonths(timeTag);
			searchBeginTime = DateTimeUtils.dateToStringByFormat(endDate, null);
			searchEndTime = DateTimeUtils.dateToStringByFormat(new Date(), null);
		}
	}
	
	public String getSearchBeginTime() {
		return searchBeginTime;
	}
	
	public void setSearchBeginTime(String searchBeginTime) {
		this.searchBeginTime = searchBeginTime;
	}
	
	public String getSearchEndTime() {
		return searchEndTime;
	}
	
	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public int getTimeTag() {
		return timeTag;
	}
	
	public void setTimeTag(int timeTag) {
		this.timeTag = timeTag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

}
