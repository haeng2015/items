package com.pbms.vo;

import java.util.List;

public class ChargeVO {
    private Integer chargeId;
    
    private List<Integer> chargeIds;
    
    private String chargeName;
    
    private String chargePrice;
    
    public Integer getChargeId() {
	return chargeId;
    }
    
    public void setChargeId(Integer chargeId) {
	this.chargeId = chargeId;
    }
    
    public List<Integer> getChargeIds() {
	return chargeIds;
    }
    
    public void setChargeIds(List<Integer> chargeIds) {
	this.chargeIds = chargeIds;
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
    
}
