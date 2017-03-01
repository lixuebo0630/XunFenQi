package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: LoginInfo 
* @Description: 登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:37:40 
*
 */

public class TransferZrInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String signValue;
	
	private String userId;
	private String fId;
	private String transferSum;
	private String payPassword;
	
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

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getTransferSum() {
		return transferSum;
	}

	public void setTransferSum(String transferSum) {
		this.transferSum = transferSum;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	
}
