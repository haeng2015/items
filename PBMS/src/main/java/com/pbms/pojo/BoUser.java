package com.pbms.pojo;

import java.util.List;

public class BoUser {
    private Integer userId;
    
    private String userLogin;
    
    private String userPwd;
    
    private String userName;
    
    private String userSex;
    
    private String userType;
    
    private String userPhone;
    
    private String userQq;
    
    private String userMsn;
    
    private String userCard;
    
    private String userPhoto;
    
    private String isDeleted;
    
    private String extend1;
    
    private String extend2;
    
    private List<UserRole> userRoleList; // 一个用户对应多个角色
    
    public Integer getUserId() {
	return userId;
    }
    
    public void setUserId(Integer userId) {
	this.userId = userId;
    }
    
    public String getUserLogin() {
	return userLogin;
    }
    
    public void setUserLogin(String userLogin) {
	this.userLogin = userLogin == null ? null : userLogin.trim();
    }
    
    public String getUserPwd() {
	return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
	this.userPwd = userPwd == null ? null : userPwd.trim();
    }
    
    public String getUserName() {
	return userName;
    }
    
    public void setUserName(String userName) {
	this.userName = userName == null ? null : userName.trim();
    }
    
    public String getUserSex() {
	return userSex;
    }
    
    public void setUserSex(String userSex) {
	this.userSex = userSex == null ? null : userSex.trim();
    }
    
    public String getUserType() {
	return userType;
    }
    
    public void setUserType(String userType) {
	this.userType = userType == null ? null : userType.trim();
    }
    
    public String getUserPhone() {
	return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
	this.userPhone = userPhone == null ? null : userPhone.trim();
    }
    
    public String getUserQq() {
	return userQq;
    }
    
    public void setUserQq(String userQq) {
	this.userQq = userQq == null ? null : userQq.trim();
    }
    
    public String getUserMsn() {
	return userMsn;
    }
    
    public String getUserCard() {
	return userCard;
    }
    
    public void setUserCard(String userCard) {
	this.userCard = userCard;
    }
    
    public void setUserMsn(String userMsn) {
	this.userMsn = userMsn == null ? null : userMsn.trim();
    }
    
    public String getUserPhoto() {
	return userPhoto;
    }
    
    public void setUserPhoto(String userPhoto) {
	this.userPhoto = userPhoto;
    }
    
    public String getIsDeleted() {
	return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
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
    
    public List<UserRole> getUserRoleList() {
	return userRoleList;
    }
    
    public void setUserRoleList(List<UserRole> userRoleList) {
	this.userRoleList = userRoleList;
    }
    
    @Override
    public String toString() {
	return "BoUser [userId=" + userId + ", userLogin=" + userLogin + ", userPwd=" + userPwd + ", userName="
		+ userName + ", userSex=" + userSex + ", userType=" + userType + ", userPhone=" + userPhone
		+ ", userQq=" + userQq + ", userMsn=" + userMsn + ", isDeleted=" + isDeleted + ", extend1=" + extend1
		+ ", extend2=" + extend2 + ", userRoleList=" + userRoleList + "]";
    }
    
}
