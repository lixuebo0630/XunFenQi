/**
 * Project Name:HaiHeFinance
 * File Name:CurrentOrdinaryToCurrentInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2016-4-18下午3:03:28
 * Copyright (c) 2016, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

/**
 * @date: 2016-4-18 下午3:03:28
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class CurrentOrdinaryToCurrentInfo {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String puzcje;
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

	public String getPuzcje() {
		return puzcje;
	}

	public void setPuzcje(String puzcje) {
		this.puzcje = puzcje;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "CurrentOrdinaryToCurrentInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", puzcje=" + puzcje + ", signValue="
				+ signValue + "]";
	}

}
