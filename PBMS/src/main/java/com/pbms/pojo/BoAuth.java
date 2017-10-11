package com.pbms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoAuth implements Serializable {
    /**
     * @版权所有：Hehaipeng
     * @项目名称:PBMS物业后台管理系统
     * @创建者:Hehaipeng
     * @创建日期:2017年5月2日
     * @说明：
     */
    private static final long serialVersionUID = 2810878135379683466L;
    
    private Integer authId;
    
    private Integer pId; // 父节点id
    
    private String authName;
    
    private String authUrl;
    
    private String authType;
    
    private String isLeafNode; // 是否叶子节点（0否，1是）
    
    private String extend1;
    
    private String extend2;
    
    private List<BoAuth> childrens = new ArrayList<BoAuth>(0); // 子节点集合
    
    public Integer getAuthId() {
	return authId;
    }
    
    public void setAuthId(Integer authId) {
	this.authId = authId;
    }
    
    public String getAuthName() {
	return authName;
    }
    
    public void setAuthName(String authName) {
	this.authName = authName == null ? null : authName.trim();
    }
    
    public String getAuthUrl() {
	return authUrl;
    }
    
    public void setAuthUrl(String authUrl) {
	this.authUrl = authUrl == null ? null : authUrl.trim();
    }
    
    public String getAuthType() {
	return authType;
    }
    
    public void setAuthType(String authType) {
	this.authType = authType == null ? null : authType.trim();
    }
    
    public String getIsLeafNode() {
	return isLeafNode;
    }
    
    public void setIsLeafNode(String isLeafNode) {
	this.isLeafNode = isLeafNode;
    }
    
    public Integer getpId() {
	return pId;
    }
    
    public void setpId(Integer pId) {
	this.pId = pId;
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
    
    public List<BoAuth> getChildrens() {
	return childrens;
    }
    
    public void setChildrens(List<BoAuth> childrens) {
	this.childrens = childrens;
    }
}
