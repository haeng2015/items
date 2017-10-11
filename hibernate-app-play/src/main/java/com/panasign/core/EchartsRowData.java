package com.panasign.core;

import java.util.List;

/**
 * Echart图表服务类<p>
 * <ul>name		：坐标轴的横向值,饼图对应扇形名称</ul>
 * <ul>value	：图片的数据</ul>
 * </p>
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-12-21
 */
public class EchartsRowData {

	private String value;
	
	private String name;
	
	private List<EchartsRowData> series;
	
	public EchartsRowData() {

	}

	public EchartsRowData(String name, String value,List<EchartsRowData> series) {
		this.name = name;
		this.value = value;
		this.series=series;
	}

	/**
	 * Getter method for property <tt>value</tt>.
	 * 
	 * @return property value of value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setter method for property <tt>value</tt>.
	 * 
	 * @param value value to be assigned to property value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Getter method for property <tt>name</tt>.
	 * 
	 * @return property value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for property <tt>name</tt>.
	 * 
	 * @param name value to be assigned to property name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the series
	 */
	public List<EchartsRowData> getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(List<EchartsRowData> series) {
		this.series = series;
	}

}
