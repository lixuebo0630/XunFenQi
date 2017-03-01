package com.xunfenqi.model.domain;

public class UserIntoEnchashment {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String cardlast;
	private String yymc;
	private String kyye;
	private String branchBankName;
	private String userTel;
	private String signValue;
	private String hqdj;
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getHqdj() {
		return hqdj;
	}

	public void setHqdj(String hqdj) {
		this.hqdj = hqdj;
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

	public String getKyye() {
		return kyye;
	}

	public void setKyye(String kyye) {
		this.kyye = kyye;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public UserIntoEnchashment() {
		super();
	}

	public UserIntoEnchashment(String messageType, String respCode,
			String respCodeDesc, String userId, String cardlast, String yymc,
			String kyye, String userTel, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.cardlast = cardlast;
		this.yymc = yymc;
		this.kyye = kyye;
		this.userTel = userTel;
		this.signValue = signValue;
	}

}
