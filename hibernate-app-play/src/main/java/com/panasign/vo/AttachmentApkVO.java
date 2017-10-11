package com.panasign.vo;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月24日
 * @说明：apk文件vo对象
 */
public class AttachmentApkVO extends BaseVO {
	
	private static final long serialVersionUID = 3880347417319463074L;
	private Integer apkId;
	private Integer[] apkIds;
	private Integer versionCode;
	private String versionName;
	private String fileName;
	private String uniqueName;
	private String uri;
	private String createDate;
	private String updateDate;
	private String fileSize;
	private String remarks;
	
	public Integer getApkId() {
		return apkId;
	}
	
	public void setApkId(Integer apkId) {
		this.apkId = apkId;
	}
	
	public Integer[] getApkIds() {
		return apkIds;
	}
	
	public void setApkIds(Integer[] apkIds) {
		this.apkIds = apkIds;
	}
	
	public Integer getVersionCode() {
		return versionCode;
	}
	
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	
	public String getVersionName() {
		return versionName;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
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
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
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
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
