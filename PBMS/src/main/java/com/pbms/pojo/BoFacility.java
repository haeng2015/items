package com.pbms.pojo;

import java.util.Date;

public class BoFacility {
    private Integer facId;

    private Integer cateId;

    private String facName;

    private String facCount;

    private String facPrice;

    private String facType;

    private Date facInTime;

    private Date facOutTime;

    private String facState;

    private String authType;

    private String extend1;

    private String extend2;

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName == null ? null : facName.trim();
    }

    public String getFacCount() {
        return facCount;
    }

    public void setFacCount(String facCount) {
        this.facCount = facCount == null ? null : facCount.trim();
    }

    public String getFacPrice() {
        return facPrice;
    }

    public void setFacPrice(String facPrice) {
        this.facPrice = facPrice == null ? null : facPrice.trim();
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType == null ? null : facType.trim();
    }

    public Date getFacInTime() {
        return facInTime;
    }

    public void setFacInTime(Date facInTime) {
        this.facInTime = facInTime;
    }

    public Date getFacOutTime() {
        return facOutTime;
    }

    public void setFacOutTime(Date facOutTime) {
        this.facOutTime = facOutTime;
    }

    public String getFacState() {
        return facState;
    }

    public void setFacState(String facState) {
        this.facState = facState == null ? null : facState.trim();
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
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