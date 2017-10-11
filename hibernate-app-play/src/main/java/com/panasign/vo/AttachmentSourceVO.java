package com.panasign.vo;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：资源文件vo对象
 */
public class AttachmentSourceVO extends BaseVO {
	
	private static final long serialVersionUID = 6062361488769719251L;
	private Integer resourceId;
	private Integer[] resourceIds;
	private Integer[] userDeviceIds; // 用户设备id集合
	private String deviceId;
	private String fileName;
	private String uniqueName;
	private String createDate;
	private String updateDate;
	private String fileSize;
	private String type;
	private String remarks;
	
	public Integer getResourceId() {
		return resourceId;
	}
	
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public Integer[] getResourceIds() {
		return resourceIds;
	}
	
	public void setResourceIds(Integer[] resourceIds) {
		this.resourceIds = resourceIds;
	}
	
	public Integer[] getUserDeviceIds() {
		return userDeviceIds;
	}
	
	public void setUserDeviceIds(Integer[] userDeviceIds) {
		this.userDeviceIds = userDeviceIds;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
