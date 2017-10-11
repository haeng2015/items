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
 * @说明：资源文件实体类
 */
@Entity
@Table(name = "bo_attachment_source", catalog = "panasign_app_play")
public class BoAttachmentSource implements java.io.Serializable {
	
	private static final long serialVersionUID = -4640103271552716632L;
	/**
	 * 资源附件上传路径
	 */
	public final static String RESOURCE_DIR = "\\attachment\\resource\\";
	
	private Integer id;
	private String fileName;
	private String uniqueName;
	private String uri;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Long size;
	private String isDeleted;
	private String type;
	private String remarks;
	
	// Constructors
	
	/** default constructor */
	public BoAttachmentSource() {
	}
	
	/** minimal constructor */
	public BoAttachmentSource(String fileName, String uniqueName, String uri, Timestamp createTime, Timestamp updateTime, Long size, String isDeleted, String type) {
		this.fileName = fileName;
		this.uniqueName = uniqueName;
		this.uri = uri;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.size = size;
		this.isDeleted = isDeleted;
		this.type = type;
	}
	
	/** full constructor */
	public BoAttachmentSource(String fileName, String uniqueName, String uri, Timestamp createTime, Timestamp updateTime, Long size, String isDeleted, String type, String remarks) {
		this.fileName = fileName;
		this.uniqueName = uniqueName;
		this.uri = uri;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.size = size;
		this.isDeleted = isDeleted;
		this.type = type;
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
	
	@Column(name = "URI", nullable = false)
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
	
	@Column(name = "TYPE", nullable = false, length = 2)
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "REMARKS", length = 500)
	public String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
