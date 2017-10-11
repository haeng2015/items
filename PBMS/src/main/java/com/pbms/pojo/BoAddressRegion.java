package com.pbms.pojo;

public class BoAddressRegion {
    
    private String rId;
    
    private String rName;
    
    private String rCode;
    
    private String rType;
    
    private BoAddressCity city;
    
    public String getrId() {
	return rId;
    }
    
    public void setrId(String rId) {
	this.rId = rId;
    }
    
    public String getrName() {
	return rName;
    }
    
    public void setrName(String rName) {
	this.rName = rName;
    }
    
    public String getrCode() {
	return rCode;
    }
    
    public void setrCode(String rCode) {
	this.rCode = rCode;
    }
    
    public String getrType() {
	return rType;
    }
    
    public void setrType(String rType) {
	this.rType = rType;
    }
    
    public BoAddressCity getCity() {
	return city;
    }
    
    public void setCity(BoAddressCity city) {
	this.city = city;
    }
    
}
