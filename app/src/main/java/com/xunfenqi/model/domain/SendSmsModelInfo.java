package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: SendSmsModelInfo 
* @Description: 发送短信实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:39:14 
*
 */
public class SendSmsModelInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userTel;
	private String verifyType;
	private String verifyCode;
	private String signValue;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public SendSmsModelInfo(String messageType, String respCode,
			String respCodeDesc, String userTel, String verifyType,
			String verifyCode, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userTel = userTel;
		this.verifyType = verifyType;
		this.verifyCode = verifyCode;
		this.signValue = signValue;
	}

	public SendSmsModelInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SendSmsModelInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userTel="
				+ userTel + ", verifyType=" + verifyType + ", verifyCode="
				+ verifyCode + ", signValue=" + signValue + "]";
	}

}
