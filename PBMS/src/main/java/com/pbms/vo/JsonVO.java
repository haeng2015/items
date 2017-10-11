package com.pbms.vo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class JsonVO {
    private boolean reflag; // 返回boolean值：true成功，false失败
    
    private String infoMsg; // 提示信息
    
    private int dlgType; // 对话框类型
    
    private String confirmUrl; // 确定跳转地址
    
    private String cancelUrl; // 取消跳转地址
    
    private Object returnObj;
    
    // 先将对象JsonVO进行json转换
     static {
	 
     }
    
    public JsonVO() {
	super();
    }
    
    public JsonVO(HttpServletResponse response) {
	super();
	PrintWriter pw = null;
	try {
	    pw = response.getWriter();
	    pw.write(JSON.toJSONString(new JsonVO()));
	    pw.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public boolean isReflag() {
	return reflag;
    }
    
    public void setReflag(boolean reflag) {
	this.reflag = reflag;
    }
    
    public String getInfoMsg() {
	return infoMsg;
    }
    
    public void setInfoMsg(String infoMsg) {
	this.infoMsg = infoMsg;
    }
    
    public int getDlgType() {
	return dlgType;
    }
    
    public void setDlgType(int dlgType) {
	this.dlgType = dlgType;
    }
    
    public String getConfirmUrl() {
	return confirmUrl;
    }
    
    public void setConfirmUrl(String confirmUrl) {
	this.confirmUrl = confirmUrl;
    }
    
    public String getCancelUrl() {
	return cancelUrl;
    }
    
    public void setCancelUrl(String cancelUrl) {
	this.cancelUrl = cancelUrl;
    }
    
    public Object getReturnObj() {
	return returnObj;
    }
    
    public void setReturnObj(Object returnObj) {
	this.returnObj = returnObj;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
    }
}
