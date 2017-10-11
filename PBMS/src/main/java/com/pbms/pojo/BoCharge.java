package com.pbms.pojo;

public class BoCharge {
    private Integer chargeId;
    
    private String chargeName;
    
    private String chargePrice;
    
    private String extend1;
    
    private String extend2;
    
    public Integer getChargeId() {
	return chargeId;
    }
    
    public void setChargeId(Integer chargeId) {
	this.chargeId = chargeId;
    }
    
    public String getChargeName() {
	return chargeName;
    }
    
    public void setChargeName(String chargeName) {
	this.chargeName = chargeName == null ? null : chargeName.trim();
    }
    
    public String getChargePrice() {
	return chargePrice;
    }
    
    public void setChargePrice(String chargePrice) {
	this.chargePrice = chargePrice == null ? null : chargePrice.trim();
    }
    
    public String getExtend1() {
	return extend1;
    }
    
    public void setExtend1(String extend1) {
	this.extend1 = extend1 == null ? null : extend1.trim();
    }
    
    public String getExtend2() {
	return extend2;
    }
    
    public void setExtend2(String extend2) {
	this.extend2 = extend2 == null ? null : extend2.trim();
    }
}
