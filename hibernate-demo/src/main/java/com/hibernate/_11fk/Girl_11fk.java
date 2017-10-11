package com.hibernate._11fk;

public class Girl_11fk {
    
    private int gId;
    private String gName;
    private int gAge;
    private Boy_11fk boy; // 插入外键，boy作为girl的父项，现有boy后才能插入girl
    
    public Girl_11fk() {
	super();
    }
    
    public Girl_11fk(String gName, int gAge) {
	super();
	this.gName = gName;
	this.gAge = gAge;
    }
    
    public Girl_11fk(int gId) {
	super();
	this.gId = gId;
    }
    
    public Girl_11fk(String gName, int gAge, Boy_11fk boy) {
	super();
	this.gName = gName;
	this.gAge = gAge;
	this.boy = boy;
    }
    
    public Girl_11fk(int gId, String gName, int gAge, Boy_11fk boy) {
	super();
	this.gId = gId;
	this.gName = gName;
	this.gAge = gAge;
	this.boy = boy;
    }
    
    public int getgId() {
	return gId;
    }
    
    public void setgId(int gId) {
	this.gId = gId;
    }
    
    public String getgName() {
	return gName;
    }
    
    public void setgName(String gName) {
	this.gName = gName;
    }
    
    public int getgAge() {
	return gAge;
    }
    
    public void setgAge(int gAge) {
	this.gAge = gAge;
    }
    
    public Boy_11fk getBoy() {
	return boy;
    }
    
    public void setBoy(Boy_11fk boy) {
	this.boy = boy;
    }
    
}
