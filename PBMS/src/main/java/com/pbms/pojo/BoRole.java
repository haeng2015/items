package com.pbms.pojo;

import java.util.List;

public class BoRole {
    private Integer roleId;
    
    private Integer userId;  //添加角色的用户
    
    private String roleName;
    
    private String roleType;
    
    private String extend1;
    
    private String extend2;
    
    private List<RoleAuth> roleAuthList;
    
    public Integer getRoleId() {
	return roleId;
    }
    
    public void setRoleId(Integer roleId) {
	this.roleId = roleId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
	return roleName;
    }
    
    public void setRoleName(String roleName) {
	this.roleName = roleName == null ? null : roleName.trim();
    }
    
    public String getRoleType() {
	return roleType;
    }
    
    public void setRoleType(String roleType) {
	this.roleType = roleType;
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
    
    public List<RoleAuth> getRoleAuthList() {
	return roleAuthList;
    }
    
    public void setRoleAuthList(List<RoleAuth> roleAuthList) {
	this.roleAuthList = roleAuthList;
    }
}
