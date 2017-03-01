package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: QueryProductDetailInfo
 * @Description: 查询债权转让产品详细实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:38:41
 * 
 */
public class UserTransferDetailInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String nhll;
	private String tzxe;
	private String qx;
	private String xmje;
	private String zrzt;
	private String zrzh;
	private String pId;
	private String sysj;
	private String xmjd;
	private String syje;
	private String xmmc;
	private String fbsj;
	private String hzfs;
	private String cm;
	private String sfkzr;
	private String qtje;
	private String productId;

	private String kyed;
	private String diyu;
	private String proName;

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getKyed() {
		return kyed;
	}

	public void setKyed(String kyed) {
		this.kyed = kyed;
	}

	public String getDiyu() {
		return diyu;
	}

	public void setDiyu(String diyu) {
		this.diyu = diyu;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	private String signValue;

	public UserTransferDetailInfo() {
		super();
	}

	public UserTransferDetailInfo(String messageType, String respCode,
			String respCodeDesc, String nhll, String tzsx, String qx,
			String xmje, String zrzt, String zrzh, String pId, String sysj,
			String xmjd, String syje, String xmmc, String fbsj, String hzfs,
			String cm, String sfkzr, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.nhll = nhll;
		this.qx = qx;
		this.xmje = xmje;
		this.zrzt = zrzt;
		this.zrzh = zrzh;
		this.pId = pId;
		this.sysj = sysj;
		this.xmjd = xmjd;
		this.syje = syje;
		this.xmmc = xmmc;
		this.fbsj = fbsj;
		this.hzfs = hzfs;
		this.cm = cm;
		this.sfkzr = sfkzr;
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

	public String getNhll() {
		return nhll;
	}

	public void setNhll(String nhll) {
		this.nhll = nhll;
	}

	public String getTzxe() {
		return tzxe;
	}

	public void setTzxe(String tzxe) {
		this.tzxe = tzxe;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getZrzt() {
		return zrzt;
	}

	public void setZrzt(String zrzt) {
		this.zrzt = zrzt;
	}

	public String getZrzh() {
		return zrzh;
	}

	public void setZrzh(String zrzh) {
		this.zrzh = zrzh;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public String getSysj() {
		return sysj;
	}

	public void setSysj(String sysj) {
		this.sysj = sysj;
	}

	public String getXmjd() {
		return xmjd;
	}

	public void setXmjd(String xmjd) {
		this.xmjd = xmjd;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	public String getHzfs() {
		return hzfs;
	}

	public void setHzfs(String hzfs) {
		this.hzfs = hzfs;
	}

	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}

	public String getSfkzr() {
		return sfkzr;
	}

	public void setSfkzr(String sfkzr) {
		this.sfkzr = sfkzr;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getQtje() {
		return qtje;
	}

	public void setQtje(String qtje) {
		this.qtje = qtje;
	}

}
