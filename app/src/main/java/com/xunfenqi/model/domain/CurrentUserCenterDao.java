/**
 * Project Name:HaiHeFinance
 * File Name:CurrentUserCenterDao.java
 * Package Name:com.haihefinance.model.domain
 * Date:2016-4-18上午11:34:20
 * Copyright (c) 2016, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.io.Serializable;

/**
 * @date: 2016-4-18 上午11:34:20
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class CurrentUserCenterDao implements Serializable {
	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String zzc;
	private String ljsy;
	private String zrsy;
	private String cybj;
	private String djje;
	private String dssy;
	private String hqye;
	private String txzt;
	private String zdj;
	private String zpzt;
	private String riqiString;
	private String shoyyiString;
	private String signValue;

	public String getShoyyiString() {
		return shoyyiString;
	}

	public void setShoyyiString(String shoyyiString) {
		this.shoyyiString = shoyyiString;
	}

	public String getZrsy() {
		return zrsy;
	}

	public void setZrsy(String zrsy) {
		this.zrsy = zrsy;
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

	public String getZzc() {
		return zzc;
	}

	public void setZzc(String zzc) {
		this.zzc = zzc;
	}

	public String getLjsy() {
		return ljsy;
	}

	public void setLjsy(String ljsy) {
		this.ljsy = ljsy;
	}

	public String getCybj() {
		return cybj;
	}

	public void setCybj(String cybj) {
		this.cybj = cybj;
	}

	public String getDjje() {
		return djje;
	}

	public void setDjje(String djje) {
		this.djje = djje;
	}

	public String getDssy() {
		return dssy;
	}

	public void setDssy(String dssy) {
		this.dssy = dssy;
	}

	public String getHqye() {
		return hqye;
	}

	public void setHqye(String hqye) {
		this.hqye = hqye;
	}

	public String getTxzt() {
		return txzt;
	}

	public void setTxzt(String txzt) {
		this.txzt = txzt;
	}

	public String getZpzt() {
		return zpzt;
	}

	public void setZpzt(String zpzt) {
		this.zpzt = zpzt;
	}

	public String getRiqiString() {
		return riqiString;
	}

	public void setRiqiString(String riqiString) {
		this.riqiString = riqiString;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getZdj() {
		return zdj;
	}

	public void setZdj(String zdj) {
		this.zdj = zdj;
	}

	@Override
	public String toString() {
		return "CurrentUserCenterDao [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", zzc=" + zzc + ", ljsy=" + ljsy
				+ ", zrsy=" + zrsy + ", cybj=" + cybj + ", djje=" + djje
				+ ", dssy=" + dssy + ", hqye=" + hqye + ", txzt=" + txzt
				+ ", zdj=" + zdj + ", zpzt=" + zpzt + ", riqiString="
				+ riqiString + ", shoyyiString=" + shoyyiString
				+ ", signValue=" + signValue + "]";
	}

	


	
}
