package com.xunfenqi.model.domain;
/**
 * 
* @ClassName: VerifyUsernameInfo 
* @Description: 验证用户名信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:42:15 
*
 */
public class VerifyUsernameInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userName;
	private String isExist;
	private String signValue;
	public VerifyUsernameInfo(String messageType, String respCode,
			String respCodeDesc, String userName, String isExist,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userName = userName;
		this.isExist = isExist;
		this.signValue = signValue;
	}
	public VerifyUsernameInfo() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return "VerifyUsernameInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userName="
				+ userName + ", isExist=" + isExist + ", signValue="
				+ signValue + "]";
	}

}
