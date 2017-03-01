/**
 * Project Name:HaiHeFinance
 * File Name:CurrentInitOrdinaryToCurrentInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2016-4-18下午2:42:44
 * Copyright (c) 2016, haihejinrong.com All Rights Reserved.
 *
*/

package com.xunfenqi.model.domain;
/**
 * @date:    2016-4-18 下午2:42:44 
 * @author:  XueBo Li
 * @version:  
 * @description:
 * @see 	 
 */
public class CurrentInitOrdinaryToCurrentInfo {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String ptkyye;
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
	public String getPtkyye() {
		return ptkyye;
	}
	public void setPtkyye(String ptkyye) {
		this.ptkyye = ptkyye;
	}
	public String getSignValue() {
		return signValue;
	}
	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
	@Override
	public String toString() {
		return "CurrentInitOrdinaryToCurrentInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", ptkyye=" + ptkyye + ", signValue="
				+ signValue + "]";
	}
	
	
}
