package com.pbms.vo;

import java.util.List;

import com.pbms.pojo.BoBuilding;
import com.pbms.util.Page;

public class BuildingVO extends Page<BoBuilding> {
    /**
     * @版权所有：Hehaipeng
     * @项目名称:PBMS物业后台管理系统
     * @创建者:Hehaipeng
     * @创建日期:2017年4月1日
     * @说明：
     */
    private static final long serialVersionUID = -4016337365861286409L;
    
    private Integer buildId;
    
    private List<String> buildIds;
    
    private String buildName;
    
    private String buildStartDate;
    
    private String buildEndDate;
    
    private String buildInfo;
    
    private String buildArea;
    
    private String mark; // 标示
    
    public Integer getBuildId() {
	return buildId;
    }
    
    public void setBuildId(Integer buildId) {
	this.buildId = buildId;
    }
    
    public List<String> getBuildIds() {
	return buildIds;
    }
    
    public void setBuildIds(List<String> buildIds) {
	this.buildIds = buildIds;
    }
    
    public static long getSerialversionuid() {
	return serialVersionUID;
    }
    
    public String getBuildName() {
	return buildName;
    }
    
    public void setBuildName(String buildName) {
	this.buildName = buildName == null ? null : buildName.trim();
    }
    
    public String getBuildStartDate() {
	return buildStartDate;
    }
    
    public void setBuildStartDate(String buildStartDate) {
	this.buildStartDate = buildStartDate;
    }
    
    public String getBuildEndDate() {
	return buildEndDate;
    }
    
    public void setBuildEndDate(String buildEndDate) {
	this.buildEndDate = buildEndDate;
    }
    
    public String getBuildInfo() {
	return buildInfo;
    }
    
    public void setBuildInfo(String buildInfo) {
	this.buildInfo = buildInfo;
    }
    
    public String getBuildArea() {
	return buildArea;
    }
    
    public void setBuildArea(String buildArea) {
	this.buildArea = buildArea == null ? null : buildArea.trim();
    }
    
    public String getMark() {
	return mark;
    }
    
    public void setMark(String mark) {
	this.mark = mark;
    }
    
}
