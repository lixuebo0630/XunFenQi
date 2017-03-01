/**
 * Project Name:HaiHeFinance
 * File Name:RechargeRzAndCzInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-10-28下午2:04:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

/**
 * @date: 2015-10-28 下午2:04:51
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class RechargeRzAndCzInfo {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String ddFlag;
	private String signValue;
	private String req_data;
	private String paySign;
	private String infoOrder;
	private String dtOrder;
	private String sfzhYrz;
	private String yhkYrz;
	private String zsxmYrz;
	private String ddh;
	private String yhmc;
	private String no_agree;

	public RechargeRzAndCzInfo(String messageType, String respCode,
			String respCodeDesc, String ddFlag, String signValue,
			String req_data, String paySign, String infoOrder, String dtOrder,
			String sfzhYrz, String yhkYrz, String zsxmYrz, String ddh,
			String yhmc, String no_agree) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.ddFlag = ddFlag;
		this.signValue = signValue;
		this.req_data = req_data;
		this.paySign = paySign;
		this.infoOrder = infoOrder;
		this.dtOrder = dtOrder;
		this.sfzhYrz = sfzhYrz;
		this.yhkYrz = yhkYrz;
		this.zsxmYrz = zsxmYrz;
		this.ddh = ddh;
		this.yhmc = yhmc;
		this.no_agree = no_agree;
	}

	public RechargeRzAndCzInfo() {
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

	public String getDdFlag() {
		return ddFlag;
	}

	public void setDdFlag(String ddFlag) {
		this.ddFlag = ddFlag;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getReq_data() {
		return req_data;
	}

	public void setReq_data(String req_data) {
		this.req_data = req_data;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getInfoOrder() {
		return infoOrder;
	}

	public void setInfoOrder(String infoOrder) {
		this.infoOrder = infoOrder;
	}

	public String getDtOrder() {
		return dtOrder;
	}

	public void setDtOrder(String dtOrder) {
		this.dtOrder = dtOrder;
	}

	public String getSfzhYrz() {
		return sfzhYrz;
	}

	public void setSfzhYrz(String sfzhYrz) {
		this.sfzhYrz = sfzhYrz;
	}

	public String getYhkYrz() {
		return yhkYrz;
	}

	public void setYhkYrz(String yhkYrz) {
		this.yhkYrz = yhkYrz;
	}

	public String getZsxmYrz() {
		return zsxmYrz;
	}

	public void setZsxmYrz(String zsxmYrz) {
		this.zsxmYrz = zsxmYrz;
	}

	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getNo_agree() {
		return no_agree;
	}

	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}

}
