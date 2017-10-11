package com.pbms.pojo;

public class BoAddressCity {
    
    private String cId;
    
    private String cName;
    
    private String cCode;
    
    private String cType;
    
    private BoAddressProvince province;
    
    public String getcId() {
	return cId;
    }
    
    public void setcId(String cId) {
	this.cId = cId;
    }
    
    public String getcName() {
	return cName;
    }
    
    public void setcName(String cName) {
	this.cName = cName;
    }
    
    public String getcCode() {
	return cCode;
    }
    
    public void setcCode(String cCode) {
	this.cCode = cCode;
    }
    
    public String getcType() {
	return cType;
    }
    
    public void setcType(String cType) {
	this.cType = cType;
    }
    
    public BoAddressProvince getProvince() {
	return province;
    }
    
    public void setProvince(BoAddressProvince province) {
	this.province = province;
    }
    
}
