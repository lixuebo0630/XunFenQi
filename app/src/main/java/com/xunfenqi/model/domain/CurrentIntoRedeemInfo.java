package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: LoginInfo
 * @Description: 登录信息实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:37:40
 * 
 */

public class CurrentIntoRedeemInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String signValue;

	private String userId;
	private String shjs;
	private String shje;
	private String shdz;

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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShjs() {
		return shjs;
	}

	public void setShjs(String shjs) {
		this.shjs = shjs;
	}

	public String getShje() {
		return shje;
	}

	public void setShje(String shje) {
		this.shje = shje;
	}

	public String getShdz() {
		return shdz;
	}

	public void setShdz(String shdz) {
		this.shdz = shdz;
	}

	@Override
	public String toString() {
		return "CurrentIntoRedeemInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", signValue=" + signValue + ", userId=" + userId + ", shjs="
				+ shjs + ", shje=" + shje + ", shdz=" + shdz + "]";
	}

}
