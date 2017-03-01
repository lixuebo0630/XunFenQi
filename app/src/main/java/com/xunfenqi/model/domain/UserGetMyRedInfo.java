/**
 * Project Name:HaiHeFinance
 * File Name:UserGetMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-25下午1:56:00
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

/**
 * @date: 2015-9-25 下午1:56:00
 * @author: XueBo Li
 * @version:
 * @description: 领取红包接口实例
 * @see
 */
public class UserGetMyRedInfo {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String redBagId; // 红包id
	private String lqzt;// 领取状态
	private String signValue;
	private String lqsj;

	public String getLqsj() {
		return lqsj;
	}

	public void setLqsj(String lqsj) {
		this.lqsj = lqsj;
	}

	public UserGetMyRedInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGetMyRedInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String redBagId, String lqzt,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.redBagId = redBagId;
		this.lqzt = lqzt;
		this.signValue = signValue;
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

	public String getRedBagId() {
		return redBagId;
	}

	public void setRedBagId(String redBagId) {
		this.redBagId = redBagId;
	}

	public String getLqzt() {
		return lqzt;
	}

	public void setLqzt(String lqzt) {
		this.lqzt = lqzt;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "UserGetMyRedInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userId="
				+ userId + ", redBagId=" + redBagId + ", lqzt=" + lqzt
				+ ", signValue=" + signValue + "]";
	}

}
