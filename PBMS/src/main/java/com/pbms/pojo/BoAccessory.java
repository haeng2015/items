package com.pbms.pojo;

import java.sql.Timestamp;

public class BoAccessory {
    
    /**
     * 用户附件标示
     */
    public static final String USER_SIGN = "1"; // 用户附件标示
    /**
     * 楼栋附件标示
     */
    public static final String BUILDING_SIGN = "2"; // 楼栋附件标示
    /**
     * 房间附件标示
     */
    public static final String ROOM_SIGN = "3"; // 房间附件标示
    /**
     * 业主附件标示
     */
    public static final String OWNER_SIGN = "4"; // 业主附件标示
    
    private Integer id;
    
    private String name;
    
    private String sign;
    
    private String url;
    
    private Long size;
    
    private String type;
    
    private Timestamp date;
    
    private Integer creator;
    
    private BoUser user;
    
    private BoBuilding building;
    
    private BoRoom room;
    
    private BoOwner owner;
    
    public Integer getId() {
	return id;
    }
    
    public void setId(Integer id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name == null ? null : name.trim();
    }
    
    public String getSign() {
	return sign;
    }
    
    public void setSign(String sign) {
	this.sign = sign == null ? null : sign.trim();
    }
    
    public String getUrl() {
	return url;
    }
    
    public void setUrl(String url) {
	this.url = url == null ? null : url.trim();
    }
    
    public Long getSize() {
	return size;
    }
    
    public void setSize(Long size) {
	this.size = size;
    }
    
    public String getType() {
	return type;
    }
    
    public void setType(String type) {
	this.type = type == null ? null : type.trim();
    }
    
    public Timestamp getDate() {
	return date;
    }
    
    public void setDate(Timestamp date) {
	this.date = date;
    }
    
    public Integer getCreator() {
	return creator;
    }
    
    public void setCreator(Integer creator) {
	this.creator = creator;
    }
    
    public BoUser getUser() {
	return user;
    }
    
    public void setUser(BoUser user) {
	this.user = user;
    }
    
    public BoBuilding getBuilding() {
	return building;
    }
    
    public void setBuilding(BoBuilding building) {
	this.building = building;
    }
    
    public BoRoom getRoom() {
	return room;
    }
    
    public void setRoom(BoRoom room) {
	this.room = room;
    }
    
    public BoOwner getOwner() {
	return owner;
    }
    
    public void setOwner(BoOwner owner) {
	this.owner = owner;
    }
    
}
