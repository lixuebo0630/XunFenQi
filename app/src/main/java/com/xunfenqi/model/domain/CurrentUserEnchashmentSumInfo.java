package com.xunfenqi.model.domain;


public class CurrentUserEnchashmentSumInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String signValue;
	@Override
	public String toString() {
		return "CurrentUserEnchashmentSumInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", signValue=" + signValue + "]";
	}
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSignValue() {
		return signValue;
	}
	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
	
	
}
