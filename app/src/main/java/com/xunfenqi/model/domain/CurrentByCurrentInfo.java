package com.xunfenqi.model.domain;

public class CurrentByCurrentInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String tzje;
	private String yqsy;
	private String qxDate;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTzje() {
		return tzje;
	}

	public void setTzje(String tzje) {
		this.tzje = tzje;
	}

	public String getYqsy() {
		return yqsy;
	}

	public void setYqsy(String yqsy) {
		this.yqsy = yqsy;
	}

	public String getQxDate() {
		return qxDate;
	}

	public void setQxDate(String qxDate) {
		this.qxDate = qxDate;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "CurrentByCurrentInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", tzje=" + tzje + ", yqsy=" + yqsy
				+ ", qxDate=" + qxDate + ", signValue=" + signValue + "]";
	}

}
