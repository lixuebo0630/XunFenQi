package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: VerifyUserTelInfo
 * @Description: 验证用户手机号信息实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:42:44
 * 
 */
public class UserEditPasswordInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String signValue;
	private String userId;
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
	public UserEditPasswordInfo(String messageType, String respCode,
			String respCodeDesc, String signValue, String userId) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.signValue = signValue;
		this.userId = userId;
	}
	public UserEditPasswordInfo() {
		super();
	}
	
	

}
