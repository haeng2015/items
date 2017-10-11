package com.pbms.vo;

import java.util.List;

public class OwnerVO {
    private Integer ownerId;
    private List<Integer> ownerIds;
    
    private Integer userId;
    
    private String ownerName;
    
    private String ownerSex;
    
    private String ownerAddr;
    private String ownerAddrId;
    
    private String ownerPhone;
    
    private String ownerCard;
    
    private String ownerWork;
    
    private Integer buildId;
    private String buildName;
    
    private Integer roomId;
    private String roomName;
    
    private String mark;
    
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String regionId;
    private String regionName;
    
    public Integer getOwnerId() {
	return ownerId;
    }
    
    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }
    
    public Integer getUserId() {
	return userId;
    }
    
    public List<Integer> getOwnerIds() {
	return ownerIds;
    }
    
    public void setOwnerIds(List<Integer> ownerIds) {
	this.ownerIds = ownerIds;
    }
    
    public void setUserId(Integer userId) {
	this.userId = userId;
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
    
    public Integer getRoomId() {
	return roomId;
    }
    
    public void setRoomId(Integer roomId) {
	this.roomId = roomId;
    }
    
    public String getRoomName() {
	return roomName;
    }
    
    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }
    
    public String getMark() {
	return mark;
    }
    
    public void setMark(String mark) {
	this.mark = mark;
    }
    
    public String getProvinceId() {
	return provinceId;
    }
    
    public void setProvinceId(String provinceId) {
	this.provinceId = provinceId;
    }
    
    public String getCityId() {
	return cityId;
    }
    
    public void setCityId(String cityId) {
	this.cityId = cityId;
    }
    
    public String getRegionId() {
	return regionId;
    }
    
    public void setRegionId(String regionId) {
	this.regionId = regionId;
    }
    
    public String getProvinceName() {
	return provinceName;
    }
    
    public void setProvinceName(String provinceName) {
	this.provinceName = provinceName;
    }
    
    public String getCityName() {
	return cityName;
    }
    
    public void setCityName(String cityName) {
	this.cityName = cityName;
    }
    
    public String getRegionName() {
	return regionName;
    }
    
    public void setRegionName(String regionName) {
	this.regionName = regionName;
    }
    
}
