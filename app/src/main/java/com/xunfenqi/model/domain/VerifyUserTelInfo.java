package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: VerifyUserTelInfo
 * @Description: 验证用户手机号信息实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:42:44
 * 
 */
public class VerifyUserTelInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userTel;
	private String isExist;
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

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "VerifyUserTelInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userTel="
				+ userTel + ", isExist=" + isExist + ", signValue=" + signValue
				+ "]";
	}

	public VerifyUserTelInfo(String messageType, String respCode,
			String respCodeDesc, String userTel, String isExist,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userTel = userTel;
		this.isExist = isExist;
		this.signValue = signValue;
	}

	public VerifyUserTelInfo() {
		super();
	}

}
