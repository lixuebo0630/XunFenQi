/**
 * Project Name:HaiHeFinance
 * File Name:UserMessageInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-25下午2:18:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: UserSecuritySafeInfo
 * @Description: 查看安全信息实体
 * @author Xuebo Li
 * @date 2015-9-25 下午5:10:37
 * 
 */
/**
 * @ClassName: UserSecuritySafeInfo
 * @Description:
 * @author Xuebo Li
 * @date 2016-4-26 上午10:34:12
 * 
 */
public class ApplyLoanInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String userId;

	private String orderId;
	private String applyStatus;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
