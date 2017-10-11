package com.pbms.pojo;

public class BoOwner {
    private Integer ownerId;
    private Integer userId;
    private Integer buildId;
    private Integer roomId;
    
    private String ownerName;
    
    private String ownerSex;
    
    private String ownerAddr;
    private String ownerAddrId;
    
    private String ownerPhone;
    
    private String ownerCard;
    
    private String ownerWork;
    
    private String isDeleted;
    
    private String extend1;
    
    private String extend2;
    
    // private BoUser user;
    //
    // private BoBuilding building;
    //
    // private BoRoom room;
    
    public Integer getOwnerId() {
	return ownerId;
    }
    
    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }
    
    public Integer getUserId() {
	return userId;
    }
    
    public void setUserId(Integer userId) {
	this.userId = userId;
    }
    
    public Integer getBuildId() {
	return buildId;
    }
    
    public void setBuildId(Integer buildId) {
	this.buildId = buildId;
    }
    
    public Integer getRoomId() {
	return roomId;
    }
    
    public void setRoomId(Integer roomId) {
	this.roomId = roomId;
    }
    
    public String getOwnerName() {
	return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName == null ? null : ownerName.trim();
    }
    
    public String getOwnerSex() {
	return ownerSex;
    }
    
    public void setOwnerSex(String ownerSex) {
	this.ownerSex = ownerSex == null ? null : ownerSex.trim();
    }
    
    public String getOwnerAddr() {
	return ownerAddr;
    }
    
    public void setOwnerAddr(String ownerAddr) {
	this.ownerAddr = ownerAddr == null ? null : ownerAddr.trim();
    }
    
    public String getOwnerAddrId() {
	return ownerAddrId;
    }
    
    public void setOwnerAddrId(String ownerAddrId) {
	this.ownerAddrId = ownerAddrId;
    }
    
    public String getOwnerPhone() {
	return ownerPhone;
    }
    
    public void setOwnerPhone(String ownerPhone) {
	this.ownerPhone = ownerPhone == null ? null : ownerPhone.trim();
    }
    
    public String getOwnerCard() {
	return ownerCard;
    }
    
    public void setOwnerCard(String ownerCard) {
	this.ownerCard = ownerCard == null ? null : ownerCard.trim();
    }
    
    public String getOwnerWork() {
	return ownerWork;
    }
    
    public void setOwnerWork(String ownerWork) {
	this.ownerWork = ownerWork == null ? null : ownerWork.trim();
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
