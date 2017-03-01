package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: LoginInfo 
* @Description: 登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:37:40 
*
 */

public class RemarkInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}


	

	
}
