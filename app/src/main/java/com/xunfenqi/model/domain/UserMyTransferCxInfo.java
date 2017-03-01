package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: userMyTransferCxInfo
 * @Description: 撤销债权转让实体类
 * @author Xuebo Li
 * @date 2015-12-12 下午12:15:42
 * 
 */
public class UserMyTransferCxInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String transferId;
	private String signValue;
	public UserMyTransferCxInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String transferId,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.transferId = transferId;
		this.signValue = signValue;
	}
	public UserMyTransferCxInfo() {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public String getSignValue() {
		return signValue;
	}
	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}


}
