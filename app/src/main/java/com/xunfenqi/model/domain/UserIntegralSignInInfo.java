/**
 * Project Name:HaiHeFinance
 * File Name:RegistInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-15下午4:27:18
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

/**
 * @date: 2015-9-15 下午4:27:18
 * @author: XueBo Li
 * @version:
 * @description: 注册返回数据实体
 * @see
 */
public class UserIntegralSignInInfo {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String qdzt;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQdzt() {
		return qdzt;
	}

	public void setQdzt(String qdzt) {
		this.qdzt = qdzt;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
