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
public class UserSecuritySafeInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String userId;

	private String zsxm;// 真实姓名
	private String sfzh;// 身份证号
	private String sjh;// 手机号
	private String cardlast;// 银行卡尾号
	private String jjmm;// 交易密码
	private String ssyh;// 银行名称
	private String aqdj;// 安全等级
	private String aqdjjd;// 安全等级进度
	private String zhmc;
	private String zsxmAndSfzh;
	private String signValue;

	private String proName;

	private String cityName;

	private String proCode;
	private String cityCode;

	private String imgPath;
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public UserSecuritySafeInfo() {
		super();
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public UserSecuritySafeInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String zsxm, String sfzh,
			String sjh, String cardlast, String jjmm, String ssyh, String aqdj,
			String aqdjjd, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.zsxm = zsxm;
		this.sfzh = sfzh;
		this.sjh = sjh;
		this.cardlast = cardlast;
		this.jjmm = jjmm;
		this.ssyh = ssyh;
		this.aqdj = aqdj;
		this.aqdjjd = aqdjjd;
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

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getCardlast() {
		return cardlast;
	}

	public void setCardlast(String cardlast) {
		this.cardlast = cardlast;
	}

	public String getJjmm() {
		return jjmm;
	}

	public String getZhmc() {
		return zhmc;
	}

	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}

	public void setJjmm(String jjmm) {
		this.jjmm = jjmm;
	}

	public String getSsyh() {
		return ssyh;
	}

	public void setSsyh(String ssyh) {
		this.ssyh = ssyh;
	}

	public String getAqdj() {
		return aqdj;
	}

	public void setAqdj(String aqdj) {
		this.aqdj = aqdj;
	}

	public String getAqdjjd() {
		return aqdjjd;
	}

	public void setAqdjjd(String aqdjjd) {
		this.aqdjjd = aqdjjd;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getZsxmAndSfzh() {
		return zsxmAndSfzh;
	}

	public void setZsxmAndSfzh(String zsxmAndSfzh) {
		this.zsxmAndSfzh = zsxmAndSfzh;
	}

	@Override
	public String toString() {
		return "UserSecuritySafeInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", zsxm=" + zsxm + ", sfzh=" + sfzh
				+ ", sjh=" + sjh + ", cardlast=" + cardlast + ", jjmm=" + jjmm
				+ ", ssyh=" + ssyh + ", aqdj=" + aqdj + ", aqdjjd=" + aqdjjd
				+ ", signValue=" + signValue + "]";
	}

}
