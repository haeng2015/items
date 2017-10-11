package com.pbms.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoBuilding implements Serializable{
    /**@版权所有：Hehaipeng
     * @项目名称:PBMS物业后台管理系统
     * @创建者:Hehaipeng
     * @创建日期:2017年4月12日
     * @说明：
     */
    private static final long serialVersionUID = 6121464317131270484L;

    private Integer buildId;
    
    private String buildName;
    
    private Timestamp buildStartTime;
    
    private Timestamp buildEndTime;
    
    private String buildInfo;
    
    private String buildArea;
    
    private String isDeleted;
    
    private String extend1;
    
    private String extend2;
    
//    private List<BoAccessory> boAccessoryList; // 附件一对多
    
    public Integer getBuildId() {
	return buildId;
    }
    
    public void setBuildId(Integer buildId) {
	this.buildId = buildId;
    }
    
    public String getBuildName() {
	return buildName;
    }
    
    public void setBuildName(String buildName) {
	this.buildName = buildName;
    }
    
    public Timestamp getBuildStartTime() {
	return buildStartTime;
    }
    
    public void setBuildStartTime(Timestamp buildStartTime) {
	this.buildStartTime = buildStartTime;
    }
    
    public Timestamp getBuildEndTime() {
	return buildEndTime;
    }
    
    public void setBuildEndTime(Timestamp buildEndTime) {
	this.buildEndTime = buildEndTime;
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
    
    public String getIsDeleted() {
	return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
    }
    
    public String getExtend1() {
	return extend1;
    }
    
    public void setExtend1(String extend1) {
	this.extend1 = extend1 == null ? null : extend1.trim();
    }
    
    public String getExtend2() {
	return extend2;
    }
    
    public void setExtend2(String extend2) {
	this.extend2 = extend2 == null ? null : extend2.trim();
    }
    
}
