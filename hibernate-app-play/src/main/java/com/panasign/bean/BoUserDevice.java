package com.panasign.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月29日
 * @说明：用户设备实体类
 */
@Entity
@Table(name = "bo_user_device", catalog = "panasign_app_play")
public class BoUserDevice implements java.io.Serializable {
	
	private static final long serialVersionUID = -2582202442464149579L;
	private Integer id;
	private BoUserDevice boUserDevice;
	private String userName;
	private String deviceId;
	private String deviceNum;
	private String deviceName;
	private String devicePlace;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String isDeleted;
	private String remarks;
	private BoAttachmentSource boAttachmentSource;
	private Set<BoUserDevice> boUserDevices = new HashSet<BoUserDevice>(0);
	
	// Constructors
	
	/** default constructor */
	public BoUserDevice() {
	}
	
	/** minimal constructor */
	public BoUserDevice(String userName, String deviceId, String deviceNum, String devicePlace, String deviceName, Timestamp createTime, Timestamp updateTime, String isDeleted) {
		this.userName = userName;
		this.deviceId = deviceId;
		this.deviceNum = deviceNum;
		this.deviceName = deviceName;
		this.devicePlace = devicePlace;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeleted = isDeleted;
	}
	
	/** full constructor */
	public BoUserDevice(Integer id, BoUserDevice boUserDevice, String userName, String deviceId, String deviceNum, String deviceName, String devicePlace, Timestamp createTime, Timestamp updateTime, String isDeleted,
			String remarks, BoAttachmentSource boAttachmentSource, Set<BoUserDevice> boUserDevices) {
		super();
		this.id = id;
		this.boUserDevice = boUserDevice;
		this.userName = userName;
		this.deviceId = deviceId;
		this.deviceNum = deviceNum;
		this.deviceName = deviceName;
		this.devicePlace = devicePlace;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDeleted = isDeleted;
		this.remarks = remarks;
		this.boAttachmentSource = boAttachmentSource;
		this.boUserDevices = boUserDevices;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public BoUserDevice getBoUserDevice() {
		return this.boUserDevice;
	}
	
	public void setBoUserDevice(BoUserDevice boUserDevice) {
		this.boUserDevice = boUserDevice;
	}
	
	@Column(name = "USER_NAME", length = 50)
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "DEVICE_ID", length = 50)
	public String getDeviceId() {
		return this.deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name = "DEVICE_NAME", length = 50)
	public String getDeviceName() {
		return this.deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Column(name = "DEVICE_NUM", length = 50)
	public String getDeviceNum() {
		return deviceNum;
	}
	
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	
	@Column(name = "DEVICE_PLACE", length = 100)
	public String getDevicePlace() {
		return devicePlace;
	}
	
	public void setDevicePlace(String devicePlace) {
		this.devicePlace = devicePlace;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE")
	public BoAttachmentSource getBoAttachmentSource() {
		return boAttachmentSource;
	}
	
	public void setBoAttachmentSource(BoAttachmentSource boAttachmentSource) {
		this.boAttachmentSource = boAttachmentSource;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "boUserDevice")
	public Set<BoUserDevice> getBoUserDevices() {
		return this.boUserDevices;
	}
	
	public void setBoUserDevices(Set<BoUserDevice> boUserDevices) {
		this.boUserDevices = boUserDevices;
	}
	
}
