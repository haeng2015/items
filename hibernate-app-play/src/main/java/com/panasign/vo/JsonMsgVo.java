package com.panasign.vo;

public class JsonMsgVo {
	private boolean reflag; // 返回boolean值：true成功，false失败
	
	private String infoMsg; // 提示信息
	
	private int dlgType; // 对话框类型
	
	private String confirmUrl; // 确定跳转地址
	
	private String cancelUrl; // 取消跳转地址
	
	private Object returnObj;
	
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
	
	public JsonMsgVo(boolean reflag, String infoMsg, int dlgType, String confirmUrl, String cancelUrl) {
		super();
		this.reflag = reflag;
		this.infoMsg = infoMsg;
		this.dlgType = dlgType;
		this.confirmUrl = confirmUrl;
		this.cancelUrl = cancelUrl;
	}
	
	public JsonMsgVo() {
		super();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	public Object getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	
}
