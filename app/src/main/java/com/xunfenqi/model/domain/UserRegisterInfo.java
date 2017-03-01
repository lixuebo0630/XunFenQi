package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: UserRegisterInfo 
* @Description: 用户注册信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:41:55 
*
 */
public class UserRegisterInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userName;
	private String userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public UserRegisterInfo(String messageType, String respCode,
			String respCodeDesc, String userName, String userId,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userName = userName;
		this.userId = userId;
		this.signValue = signValue;
	}
	public UserRegisterInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserRegisterInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userName="
				+ userName + ", userId=" + userId + ", signValue=" + signValue
				+ "]";
	}
	
	
	
	

}
