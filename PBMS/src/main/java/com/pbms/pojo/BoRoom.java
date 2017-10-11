package com.pbms.pojo;

import java.sql.Timestamp;

public class BoRoom {
    private Integer roomId;
    
    private Integer buildId;
    
    private String roomName;
    
    private Timestamp roomStartTime;
    
    private Timestamp roomEndTime;
    
    private String roomType;
    
    private String roomUse;
    
    private String roomArea;
    
    private String roomProb;
    
    private String extend1;
    
    private String extend2;
    
    private BoOwner owner;
    
    private BoBuilding building;
    
    public Integer getRoomId() {
	return roomId;
    }
    
    public void setRoomId(Integer roomId) {
	this.roomId = roomId;
    }
    
    public Integer getBuildId() {
	return buildId;
    }
    
    public void setBuildId(Integer buildId) {
	this.buildId = buildId;
    }
    
    public String getRoomName() {
	return roomName;
    }
    
    public void setRoomName(String roomName) {
	this.roomName = roomName == null ? null : roomName.trim();
    }
    
    public Timestamp getRoomStartTime() {
	return roomStartTime;
    }
    
    public void setRoomStartTime(Timestamp roomStartTime) {
	this.roomStartTime = roomStartTime;
    }
    
    public Timestamp getRoomEndTime() {
	return roomEndTime;
    }
    
    public void setRoomEndTime(Timestamp roomEndTime) {
	this.roomEndTime = roomEndTime;
    }
    
    public String getRoomType() {
	return roomType;
    }
    
    public void setRoomType(String roomType) {
	this.roomType = roomType == null ? null : roomType.trim();
    }
    
    public String getRoomUse() {
	return roomUse;
    }
    
    public void setRoomUse(String roomUse) {
	this.roomUse = roomUse == null ? null : roomUse.trim();
    }
    
    public String getRoomArea() {
	return roomArea;
    }
    
    public void setRoomArea(String roomArea) {
	this.roomArea = roomArea == null ? null : roomArea.trim();
    }
    
    public String getRoomProb() {
	return roomProb;
    }
    
    public void setRoomProb(String roomProb) {
	this.roomProb = roomProb == null ? null : roomProb.trim();
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
    
    public BoOwner getOwner() {
	return owner;
    }
    
    public void setOwner(BoOwner owner) {
	this.owner = owner;
    }
    
    public BoBuilding getBuilding() {
	return building;
    }
    
    public void setBuilding(BoBuilding building) {
	this.building = building;
    }
}
