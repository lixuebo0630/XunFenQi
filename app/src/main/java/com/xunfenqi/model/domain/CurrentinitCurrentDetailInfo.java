package com.xunfenqi.model.domain;

import java.io.Serializable;

public class CurrentinitCurrentDetailInfo implements Serializable {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String signValue;
	private String pfRate;
	private String btRate;
	private String least;
	private String multiple;
	private String income;
	private String qxNum;
	private String beizhu;
	private String drTop;
	private String account;
	private String zll;
	private String qxDate;
	private String kgmje;
	private String riqiString;
	private String rateString;
	private int tzje;

	public int getTzje() {
		return tzje;
	}

	public void setTzje(int tzje) {
		this.tzje = tzje;
	}

	public String getRiqiString() {
		return riqiString;
	}

	public void setRiqiString(String riqiString) {
		this.riqiString = riqiString;
	}

	public String getRateString() {
		return rateString;
	}

	public void setRateString(String rateString) {
		this.rateString = rateString;
	}

	public String getKgmje() {
		return kgmje;
	}

	public void setKgmje(String kgmje) {
		this.kgmje = kgmje;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getPfRate() {
		return pfRate;
	}

	public void setPfRate(String pfRate) {
		this.pfRate = pfRate;
	}

	public String getBtRate() {
		return btRate;
	}

	public void setBtRate(String btRate) {
		this.btRate = btRate;
	}

	public String getLeast() {
		return least;
	}

	public void setLeast(String least) {
		this.least = least;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getQxNum() {
		return qxNum;
	}

	public void setQxNum(String qxNum) {
		this.qxNum = qxNum;
	}

	public String getDrTop() {
		return drTop;
	}

	public void setDrTop(String drTop) {
		this.drTop = drTop;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getZll() {
		return zll;
	}

	public void setZll(String zll) {
		this.zll = zll;
	}

	public String getQxDate() {
		return qxDate;
	}

	public void setQxDate(String qxDate) {
		this.qxDate = qxDate;
	}

	@Override
	public String toString() {
		return "CurrentinitCurrentDetailInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", signValue=" + signValue
				+ ", pfRate=" + pfRate + ", btRate=" + btRate + ", least="
				+ least + ", multiple=" + multiple + ", income=" + income
				+ ", qxNum=" + qxNum + ", drTop=" + drTop + ", account="
				+ account + ", zll=" + zll + ", qxDate=" + qxDate + "]";
	}

}
