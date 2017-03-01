package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: LoginInfo 
* @Description: 登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:37:40 
*
 */

public class HetongInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String signValue;

	private String dzht;
 
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

	
	
	public HetongInfo(String messageType, String respCode, String respCodeDesc,
			String signValue, String dzht) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.signValue = signValue;
		this.dzht = dzht;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getDzht() {
		return dzht;
	}

	public void setDzht(String dzht) {
		this.dzht = dzht;
	}

	public HetongInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
