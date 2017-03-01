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
public class QueryUserRateInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String userId;
	private String fqje;
	private String yjje;

	private String sxf;
	private String yj;
	private String yq;
	private String fq;
	private String fwf;
	private String signValue;


	public String getFqje() {
		return fqje;
	}

	public void setFqje(String fqje) {
		this.fqje = fqje;
	}

	public String getYjje() {
		return yjje;
	}

	public void setYjje(String yjje) {
		this.yjje = yjje;
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

	public String getSxf() {
		return sxf;
	}

	public void setSxf(String sxf) {
		this.sxf = sxf;
	}

	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}

	public String getYq() {
		return yq;
	}

	public void setYq(String yq) {
		this.yq = yq;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
	}

	public String getFwf() {
		return fwf;
	}

	public void setFwf(String fwf) {
		this.fwf = fwf;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
