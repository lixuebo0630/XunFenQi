/**
 * Project Name:HaiHeFinance
 * File Name:UserMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:41:26
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2015-8-31 下午5:41:26
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class UserEditZhmcInfo implements Serializable {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
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

	public UserEditZhmcInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEditZhmcInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.signValue = signValue;
	}

}
