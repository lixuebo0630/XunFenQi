package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: LoginInfo 
* @Description: 登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:37:40 
*
 */

public class AudioSmsInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String signValue;

	private String userTel;
	private String verifyType ;
	private String verifyCode;
 
	public String getmessageType() {
		return messageType;
	}

	public void setmessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespCodeDesc() {
		return respCodeDesc;
	}

	public void setRespCodeDesc(String respCodeDesc) {
		this.respCodeDesc = respCodeDesc;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	
	
	
	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public AudioSmsInfo(String messageType, String respCode, String respCodeDesc,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.signValue = signValue;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	

	public AudioSmsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
