package com.pbms.vo;

public class AccessoryVO {
    
    public static final String USER_SIGN = "1"; // 用户附件标示
    public static final String BUILDING_SIGN = "2"; // 楼栋附件标示
    public static final String ROOM_SIGN = "3"; // 房间附件标示
    
    private Integer id;
    
    private String name;
    
    private String sign;
    
    private String url;
    
    private Long size;
    
    private String type;
    
    private String time;
    
    private Object user;
    
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
    
    public String getTime() {
	return time;
    }
    
    public void setTime(String time) {
	this.time = time;
    }
    
    public Object getUser() {
	return user;
    }
    
    public void setUser(Object user) {
	this.user = user;
    }
    
}
