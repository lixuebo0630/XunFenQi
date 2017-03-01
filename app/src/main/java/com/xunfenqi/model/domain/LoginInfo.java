package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: LoginInfo
 * @Description: 登录信息实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:37:40
 * 
 */

public class LoginInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String signValue;

	private String trueName;

	private String userId;

	private String userName;

	private String dengLuMing;

	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getDengLuMing() {
		return dengLuMing;
	}

	public void setDengLuMing(String dengLuMing) {
		this.dengLuMing = dengLuMing;
	}

	private String userTel;

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

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public LoginInfo(String messageType, String respCode, String respCodeDesc,
			String signValue, String trueName, String userId, String userName) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.signValue = signValue;
		this.trueName = trueName;
		this.userId = userId;
		this.userName = userName;
	}

	public LoginInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Login [messageType=" + messageType + ", respCode=" + respCode
				+ ", respCodeDesc=" + respCodeDesc + ", signValue=" + signValue
				+ ", trueName=" + trueName + ", userId=" + userId
				+ ", userName=" + userName + "]";
	}

}
