package com.panasign.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月29日
 * @说明：apk文件实体类
 */
@Entity
@Table(name = "bo_attachment_apk", catalog = "panasign_app_play")
public class BoAttachmentApk implements java.io.Serializable {
	
	/**
	 * apk附件上传路径
	 */
	public final static String APK_DIR = "\\attachment\\apk\\";
	
	private static final long serialVersionUID = -2056393374444369181L;
	private Integer id;
	private Integer versionCode;
	private String versionName;
	private String fileName;
	private String uniqueName;
	private String uri;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Long size;
	private String isDeleted;
	private String remarks;
	
	// Constructors
	
	/** default constructor */
	public BoAttachmentApk() {
	}
	
	/** minimal constructor */
	public BoAttachmentApk(Integer versionCode, String versionName, String fileName, String uniqueName, String uri, Timestamp createTime, Timestamp updateTime, Long size, String isDeleted) {
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.fileName = fileName;
		this.uniqueName = uniqueName;
		this.uri = uri;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.size = size;
		this.isDeleted = isDeleted;
	}
	
	/** full constructor */
	public BoAttachmentApk(Integer versionCode, String versionName, String fileName, String uniqueName, String uri, Timestamp createTime, Timestamp updateTime, Long size, String isDeleted, String remarks) {
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.uniqueName = uniqueName;
		this.uri = uri;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.size = size;
		this.isDeleted = isDeleted;
		this.remarks = remarks;
	}
	
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "VERSION_CODE", nullable = false, length = 20)
	public Integer getVersionCode() {
		return versionCode;
	}
	
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	
	@Column(name = "VERSION_NAME", nullable = false, length = 20)
	public String getVersionName() {
		return versionName;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	@Column(name = "FILE_NAME", nullable = false, length = 50)
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "UNIQUE_NAME", nullable = false, length = 50)
	public String getUniqueName() {
		return this.uniqueName;
	}
	
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	@Column(name = "URI", nullable = false, length = 200)
	public String getUri() {
		return this.uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Column(name = "CREATE_TIME", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "UPDATE_TIME", nullable = false, length = 19)
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "SIZE", nullable = false)
	public Long getSize() {
		return this.size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	@Column(name = "IS_DELETED", nullable = false, length = 2)
	public String getIsDeleted() {
		return this.isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "REMARKS", length = 500)
	public String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
