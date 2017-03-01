/**
 * Project Name:HaiHeFinance
 * File Name:UserIncomeSatementInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午4:02:49
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;


/**
 * 
* @ClassName: UserIntoRechargeInfo 
* @Description: 用户进入充值实体
* @author Xuebo Li
* @date 2015-10-27 下午6:41:53 
*
 */
public class UserIntoRechargeInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String cardlast;//银行卡尾号
	private String yymc;//银行名称
	private String drxe;//单日限额
	private String kyye;//可用余额
	private String cardAll;//银行卡号(全)
	private String sfzh;//身份证号
	private String zsxm;//真实姓名
	private String bkxe;//绑卡限额
	
	private String imgPath;
	
	public String getImgPath() {
		return imgPath;
	}




	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}




	private String signValue;
	
	
	
	
	public UserIntoRechargeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}




	public UserIntoRechargeInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String cardlast, String yymc,
			String drxe, String kyye, String cardAll, String sfzh, String zsxm,
			String bkxe, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.cardlast = cardlast;
		this.yymc = yymc;
		this.drxe = drxe;
		this.kyye = kyye;
		this.cardAll = cardAll;
		this.sfzh = sfzh;
		this.zsxm = zsxm;
		this.bkxe = bkxe;
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




	public String getCardlast() {
		return cardlast;
	}




	public void setCardlast(String cardlast) {
		this.cardlast = cardlast;
	}




	public String getYymc() {
		return yymc;
	}




	public void setYymc(String yymc) {
		this.yymc = yymc;
	}




	public String getDrxe() {
		return drxe;
	}




	public void setDrxe(String drxe) {
		this.drxe = drxe;
	}




	public String getKyye() {
		return kyye;
	}




	public void setKyye(String kyye) {
		this.kyye = kyye;
	}




	public String getCardAll() {
		return cardAll;
	}




	public void setCardAll(String cardAll) {
		this.cardAll = cardAll;
	}




	public String getSfzh() {
		return sfzh;
	}




	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}




	public String getZsxm() {
		return zsxm;
	}




	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}




	public String getBkxe() {
		return bkxe;
	}




	public void setBkxe(String bkxe) {
		this.bkxe = bkxe;
	}




	public String getSignValue() {
		return signValue;
	}




	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}




	@Override
	public String toString() {
		return "UserIntoRechargeInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", cardlast=" + cardlast + ", yymc="
				+ yymc + ", drxe=" + drxe + ", kyye=" + kyye + ", cardAll="
				+ cardAll + ", sfzh=" + sfzh + ", zsxm=" + zsxm + ", bkxe="
				+ bkxe + ", signValue=" + signValue + "]";
	}
	
	
}
