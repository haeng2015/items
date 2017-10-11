package com.pbms.vo;

import java.util.List;

public class UserVO {
    // 用户
    private Integer userId;
    
    private List<String> userIds;
    
    private String userLogin;
    
    private String userPwd;
    
    private String userName;
    
    private String userSex;
    
    private String userType;
    
    private String userPhone;
    private String userCard;
    
    private String userQq;
    
    private String userMsn;
    
    private String userPhoto;
    
    private String securityCode; // 验证码
    
    private Integer userRoleId;
    // 角色
    private Integer roleId;
    
    private List<String> roleIds; // 多个角色id
    
    private String roleName;
    
    private List<String> roleNames; // 多个角色roleName
    
    private String roleType;
    
    private List<String> roleTypes; // 多个角色roleType
    
    private Integer roleAuthId; // 角色权限id
    
    private List<Integer> roleAuths; // 角色权限id集合
    
    private String roleAuthIds; // 角色权限id字符串连接
    // 权限
    private Integer authId;
    
    private List<String> authIds; // 多个权限id
    
    private Integer pId; // 权限父主键id
    
    private String authName;
    
    private String authUrl;
    
    private String authType;
    
    private String isLeafNode; // 是否叶子节点（0否，1是）
    
    // 其他
    private String mark;
    
    public Integer getUserId() {
	return userId;
    }
    
    public void setUserId(Integer userId) {
	this.userId = userId;
    }
    
    public List<String> getUserIds() {
	return userIds;
    }
    
    public void setUserIds(List<String> userIds) {
	this.userIds = userIds;
    }
    
    public String getUserLogin() {
	return userLogin;
    }
    
    public void setUserLogin(String userLogin) {
	this.userLogin = userLogin;
    }
    
    public String getUserPwd() {
	return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
    }
    
    public String getUserName() {
	return userName;
    }
    
    public void setUserName(String userName) {
	this.userName = userName;
    }
    
    public String getUserSex() {
	return userSex;
    }
    
    public void setUserSex(String userSex) {
	this.userSex = userSex;
    }
    
    public List<String> getRoleNames() {
	return roleNames;
    }
    
    public void setRoleNames(List<String> roleNames) {
	this.roleNames = roleNames;
    }
    
    public List<String> getRoleTypes() {
	return roleTypes;
    }
    
    public void setRoleTypes(List<String> roleTypes) {
	this.roleTypes = roleTypes;
    }
    
    public String getUserType() {
	return userType;
    }
    
    public void setUserType(String userType) {
	this.userType = userType;
    }
    
    public String getUserPhone() {
	return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
	this.userPhone = userPhone;
    }
    
    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getUserQq() {
	return userQq;
    }
    
    public void setUserQq(String userQq) {
	this.userQq = userQq;
    }
    
    public String getUserMsn() {
	return userMsn;
    }
    
    public void setUserMsn(String userMsn) {
	this.userMsn = userMsn;
    }
    
    public String getUserPhoto() {
	return userPhoto;
    }
    
    public void setUserPhoto(String userPhoto) {
	this.userPhoto = userPhoto;
    }
    
    public String getSecurityCode() {
	return securityCode;
    }
    
    public void setSecurityCode(String securityCode) {
	this.securityCode = securityCode;
    }
    
    public Integer getUserRoleId() {
	return userRoleId;
    }
    
    public void setUserRoleId(Integer userRoleId) {
	this.userRoleId = userRoleId;
    }
    
    public List<String> getRoleIds() {
	return roleIds;
    }
    
    public void setRoleIds(List<String> roleIds) {
	this.roleIds = roleIds;
    }
    
    public Integer getRoleAuthId() {
	return roleAuthId;
    }
    
    public void setRoleAuthId(Integer roleAuthId) {
	this.roleAuthId = roleAuthId;
    }
    
    public List<Integer> getRoleAuths() {
	return roleAuths;
    }
    
    public void setRoleAuths(List<Integer> roleAuths) {
	this.roleAuths = roleAuths;
    }
    
    public String getRoleAuthIds() {
	return roleAuthIds;
    }
    
    public void setRoleAuthIds(String roleAuthIds) {
	this.roleAuthIds = roleAuthIds;
    }
    
    public List<String> getAuthIds() {
	return authIds;
    }
    
    public void setAuthIds(List<String> authIds) {
	this.authIds = authIds;
    }
    
    public Integer getpId() {
	return pId;
    }
    
    public void setpId(Integer pId) {
	this.pId = pId;
    }
    
    public Integer getRoleId() {
	return roleId;
    }
    
    public void setRoleId(Integer roleId) {
	this.roleId = roleId;
    }
    
    public String getRoleName() {
	return roleName;
    }
    
    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }
    
    public String getRoleType() {
	return roleType;
    }
    
    public void setRoleType(String roleType) {
	this.roleType = roleType;
    }
    
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
	this.authName = authName;
    }
    
    public String getAuthUrl() {
	return authUrl;
    }
    
    public void setAuthUrl(String authUrl) {
	this.authUrl = authUrl;
    }
    
    public String getAuthType() {
	return authType;
    }
    
    public void setAuthType(String authType) {
	this.authType = authType;
    }
    
    public String getIsLeafNode() {
	return isLeafNode;
    }
    
    public void setIsLeafNode(String isLeafNode) {
	this.isLeafNode = isLeafNode;
    }
    
    public String getMark() {
	return mark;
    }
    
    public void setMark(String mark) {
	this.mark = mark;
    }
    
}
