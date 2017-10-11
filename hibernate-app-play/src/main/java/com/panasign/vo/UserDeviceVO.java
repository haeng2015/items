package com.panasign.vo;

import java.util.List;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：用户设备vo对象
 */
public class UserDeviceVO extends BaseVO {
	
	private static final long serialVersionUID = 4198413868423084856L;
	
	private Integer userDeviceId;
	private Integer pId;
	private Integer[] userDeviceIds; // 用户设备id集合
	private String userDeviceIdStrs; // 用户设备id字符串拼接集合
	private Integer resourceId; // 资源id
	private String userName;
	private String deviceId;
	private String deviceNum; // 设备编号
	private String deviceName;
	private String devicePlace;
	private String createDate;
	private String updateDate;
	private String remarks;
	
	// 资源
	private String fileName;
	private String uniqueName;
	private String fileSize;
	private String type;
	
	private String mark;
	
	// 树结构参数
	private List<UserDeviceVO> children;
	
	public Integer getResourceId() {
		return resourceId;
	}
	
	public Integer getUserDeviceId() {
		return userDeviceId;
	}
	
	public Integer getpId() {
		return pId;
	}
	
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	public void setUserDeviceId(Integer userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
	
	public Integer[] getUserDeviceIds() {
		return userDeviceIds;
	}
	
	public void setUserDeviceIds(Integer[] userDeviceIds) {
		this.userDeviceIds = userDeviceIds;
	}
	
	public String getUserDeviceIdStrs() {
		return userDeviceIdStrs;
	}
	
	public String getDeviceNum() {
		return deviceNum;
	}
	
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	
	public String getDevicePlace() {
		return devicePlace;
	}
	
	public void setDevicePlace(String devicePlace) {
		this.devicePlace = devicePlace;
	}
	
	public void setUserDeviceIdStrs(String userDeviceIdStrs) {
		this.userDeviceIdStrs = userDeviceIdStrs;
	}
	
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getUniqueName() {
		return uniqueName;
	}
	
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public String getMark() {
		return mark;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public List<UserDeviceVO> getChildren() {
		return children;
	}
	
	public void setChildren(List<UserDeviceVO> children) {
		this.children = children;
	}
	
}
