package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: UserMyTransferSqzrInfo
 * @Description: 用户进入申请转让实体类
 * @author Xuebo Li
 * @date 2015-12-12 下午2:34:22
 * 
 */
/**
 * @ClassName: UserMyTransferSqzrInfo
 * @Description:
 * @author Xuebo Li
 * @date 2015-12-12 下午2:35:50
 * 
 */
public class UserMyTransferSqzrInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String transferId;
	private String xmmc;
	private String xmqx;
	private String nhll;
	private String cybj;
	private String ycyts;
	private String zrsx;
	private String signValue;
	private String sxMoney;
	private String sxf;

	public UserMyTransferSqzrInfo() {
		super();
	}

	public UserMyTransferSqzrInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String transferId, String xmmc,
			String xmqx, String nhll, String cybj, String ycyts, String zrsx,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.transferId = transferId;
		this.xmmc = xmmc;
		this.xmqx = xmqx;
		this.nhll = nhll;
		this.cybj = cybj;
		this.ycyts = ycyts;
		this.zrsx = zrsx;
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

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmqx() {
		return xmqx;
	}

	public void setXmqx(String xmqx) {
		this.xmqx = xmqx;
	}

	public String getNhll() {
		return nhll;
	}

	public void setNhll(String nhll) {
		this.nhll = nhll;
	}

	public String getCybj() {
		return cybj;
	}

	public void setCybj(String cybj) {
		this.cybj = cybj;
	}

	public String getYcyts() {
		return ycyts;
	}

	public void setYcyts(String ycyts) {
		this.ycyts = ycyts;
	}

	public String getZrsx() {
		return zrsx;
	}

	public void setZrsx(String zrsx) {
		this.zrsx = zrsx;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getSxMoney() {
		return sxMoney;
	}

	public void setSxMoney(String sxMoney) {
		this.sxMoney = sxMoney;
	}

	public String getSxf() {
		return sxf;
	}

	public void setSxf(String sxf) {
		this.sxf = sxf;
	}

	
	
	
}
