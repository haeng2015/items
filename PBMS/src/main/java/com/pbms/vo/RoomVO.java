package com.pbms.vo;

import java.util.List;

import com.pbms.pojo.BoBuilding;
import com.pbms.util.Page;

public class RoomVO extends Page<BoBuilding> {
    /**
     * @版权所有：Hehaipeng
     * @项目名称:PBMS物业后台管理系统
     * @创建者:Hehaipeng
     * @创建日期:2017年4月1日
     * @说明：
     */
    private static final long serialVersionUID = -4016337365861286409L;
    //楼栋
    private Integer buildId;
    
    private List<String> buildIds;
    
    private String buildName;
    
    private String buildStartDate;
    
    private String buildEndDate;
    
    private String buildInfo;
    
    private String buildArea;
    
    private List<BoBuilding> buildList;
    
    //房间
    private Integer roomId;
    
    private List<Integer> roomIds;
    
    private String roomName;
    
    private String roomStartDate;
    
    private String roomEndDate;
    
    private String roomType;
    
    private String roomUse;
    
    private String roomArea;
    
    private String roomProb;
    
    //所属者
    private Integer ownerId;
    
    private Integer userId;
    
    private String ownerName;
    
    //其他
    private String mark;
    
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
    
    public List<BoBuilding> getBuildList() {
	return buildList;
    }
    
    public void setBuildList(List<BoBuilding> buildList) {
	this.buildList = buildList;
    }
    
    public Integer getRoomId() {
	return roomId;
    }
    
    public void setRoomId(Integer roomId) {
	this.roomId = roomId;
    }
    
    public Integer getOwnerId() {
	return ownerId;
    }
    
    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }
    
    public String getRoomName() {
	return roomName;
    }
    
    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }
    
    public String getRoomStartDate() {
	return roomStartDate;
    }
    
    public void setRoomStartDate(String roomStartDate) {
	this.roomStartDate = roomStartDate;
    }
    
    public String getRoomEndDate() {
	return roomEndDate;
    }
    
    public void setRoomEndDate(String roomEndDate) {
	this.roomEndDate = roomEndDate;
    }
    
    public String getRoomType() {
	return roomType;
    }
    
    public void setRoomType(String roomType) {
	this.roomType = roomType;
    }
    
    public String getRoomUse() {
	return roomUse;
    }
    
    public void setRoomUse(String roomUse) {
	this.roomUse = roomUse;
    }
    
    public String getRoomArea() {
	return roomArea;
    }
    
    public void setRoomArea(String roomArea) {
	this.roomArea = roomArea;
    }
    
    public String getRoomProb() {
	return roomProb;
    }
    
    public void setRoomProb(String roomProb) {
	this.roomProb = roomProb;
    }
    
    public List<Integer> getRoomIds() {
	return roomIds;
    }
    
    public void setRoomIds(List<Integer> roomIds) {
	this.roomIds = roomIds;
    }
    
    public Integer getUserId() {
	return userId;
    }
    
    public void setUserId(Integer userId) {
	this.userId = userId;
    }
    
    public String getOwnerName() {
	return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
}
