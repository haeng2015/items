package com.panasign.utils;

/**
 * Image 上传异常类
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-12-8
 */
public class PanasignUploadImageException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = -7271158825896731647L;

	/** 错误码 */
	private String errorCode;

	/** 错误描述 */
	private String errorMsg;

	/**
	 * 构造器
	 * 
	 * @param errorCode 错误码
	 * @param errorMsg  错误码描述
	 */
	public PanasignUploadImageException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * 构造器
	 * 
	 * @param errorCode  错误码
	 * @param errorMsg   错误码描述
	 * @param cause      异常
	 */
	public PanasignUploadImageException(String errorCode, String errorMsg,
			Throwable cause) {
		super(errorCode, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * Getter method for property <tt>errorCode</tt>.
	 * 
	 * @return property value of errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Setter method for property <tt>errorCode</tt>.
	 * 
	 * @param errorCode value to be assigned to property errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Getter method for property <tt>errorMsg</tt>.
	 * 
	 * @return property value of errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Setter method for property <tt>errorMsg</tt>.
	 * 
	 * @param errorMsg value to be assigned to property errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
