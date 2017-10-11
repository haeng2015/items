package com.pbms.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BoPayment {
    private Integer payId;

    private Integer chargeId;

    private Integer ownerId;

    private BigDecimal payOught;

    private BigDecimal payActual;

    private BigDecimal payArrea;

    private Date payTime;

    private String payState;

    private Date paylDate;

    private String extend1;

    private String extend2;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public BigDecimal getPayOught() {
        return payOught;
    }

    public void setPayOught(BigDecimal payOught) {
        this.payOught = payOught;
    }

    public BigDecimal getPayActual() {
        return payActual;
    }

    public void setPayActual(BigDecimal payActual) {
        this.payActual = payActual;
    }

    public BigDecimal getPayArrea() {
        return payArrea;
    }

    public void setPayArrea(BigDecimal payArrea) {
        this.payArrea = payArrea;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    public Date getPaylDate() {
        return paylDate;
    }

    public void setPaylDate(Date paylDate) {
        this.paylDate = paylDate;
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