/**
 * Project Name:HaiHeFinance
 * File Name:UserSecurityInfomationInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-25下午3:38:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-9-25 下午3:38:29
 * @author: XueBo Li
 * @version:
 * @description: 修改个人资料实体
 * @see
 */
public class UserSecurityInfomationEditInfo {

	private String MessageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String signValue;
	private String yhm; // 用户名
	private String zsxm; // 真实姓名
	private String sfzh; // 身份证号
	private String sjh; // 手机号
	private String xb; // 性别
	private String csrq; // 出生日期
	private String zgxl; // 最高学历
	private String byyx; // 毕业院校
	private String hyzk; // 婚姻状况
	private String sshy; // 所属行业
	private String srfw; // 年收入范围
	private String lxmc; // 联系人名称
	private String gx; // 与您关系
	private String lxfs; // 联系方式

	public UserSecurityInfomationEditInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSecurityInfomationEditInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String signValue, String yhm,
			String zsxm, String sfzh, String sjh, String xb, String csrq,
			String zgxl, String byyx, String hyzk, String sshy, String srfw,
			String lxmc, String gx, String lxfs) {
		super();
		MessageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.signValue = signValue;
		this.yhm = yhm;
		this.zsxm = zsxm;
		this.sfzh = sfzh;
		this.sjh = sjh;
		this.xb = xb;
		this.csrq = csrq;
		this.zgxl = zgxl;
		this.byyx = byyx;
		this.hyzk = hyzk;
		this.sshy = sshy;
		this.srfw = srfw;
		this.lxmc = lxmc;
		this.gx = gx;
		this.lxfs = lxfs;
	}

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
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

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
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

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getZgxl() {
		return zgxl;
	}

	public void setZgxl(String zgxl) {
		this.zgxl = zgxl;
	}

	public String getByyx() {
		return byyx;
	}

	public void setByyx(String byyx) {
		this.byyx = byyx;
	}

	public String getHyzk() {
		return hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getSrfw() {
		return srfw;
	}

	public void setSrfw(String srfw) {
		this.srfw = srfw;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	@Override
	public String toString() {
		return "UserSecurityInfomationInfo [MessageType=" + MessageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", signValue=" + signValue + ", yhm="
				+ yhm + ", zsxm=" + zsxm + ", sfzh=" + sfzh + ", sjh=" + sjh
				+ ", xb=" + xb + ", csrq=" + csrq + ", zgxl=" + zgxl
				+ ", byyx=" + byyx + ", hyzk=" + hyzk + ", sshy=" + sshy
				+ ", srfw=" + srfw + ", lxmc=" + lxmc + ", gx=" + gx
				+ ", lxfs=" + lxfs + "]";
	}

}
