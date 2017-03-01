/**
 * Project Name:HaiHeFinance
 * File Name:UserMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:41:26
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @date: 2015-8-31 下午5:41:26
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class ConfirmInfo implements Serializable {

	private String xmmc;
	private String qianxian;
	private String rate;
	private String syed;
	private String kyed;
	private String oldUser;
	private Double oldNh;
	private String qtje;// 起投金额
	private String tzxe;// 单人投资限额
	private String isXsb;// 新手标
	private String productId;
	private String plusRate; // 活动利率
	private int tzje;
	private String diyu;
	
	public String getDiyu() {
		return diyu;
	}

	public void setDiyu(String diyu) {
		this.diyu = diyu;
	}

	public int getTzje() {
		return tzje;
	}

	public void setTzje(int tzje) {
		this.tzje = tzje;
	}

	public String getPlusRate() {
		return plusRate;
	}

	public void setPlusRate(String plusRate) {
		this.plusRate = plusRate;
	}

	private ArrayList<CouponInfo.Coupon> couponList;

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getQianxian() {
		return qianxian;
	}

	public void setQianxian(String qianxian) {
		this.qianxian = qianxian;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSyed() {
		return syed;
	}

	public void setSyed(String syed) {
		this.syed = syed;
	}

	public String getKyed() {
		return kyed;
	}

	public void setKyed(String kyed) {
		this.kyed = kyed;
	}

	public ArrayList<CouponInfo.Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<CouponInfo.Coupon> couponList) {
		this.couponList = couponList;
	}

	public String getOldUser() {
		return oldUser;
	}

	public void setOldUser(String oldUser) {
		this.oldUser = oldUser;
	}

	public Double getOldNh() {
		return oldNh;
	}

	public void setOldNh(Double oldNh) {
		this.oldNh = oldNh;
	}

	public String getQtje() {
		return qtje;
	}

	public void setQtje(String qtje) {
		this.qtje = qtje;
	}

	public String getTzxe() {
		return tzxe;
	}

	public void setTzxe(String tzxe) {
		this.tzxe = tzxe;
	}

	public String getIsXsb() {
		return isXsb;
	}

	public void setIsXsb(String isXsb) {
		this.isXsb = isXsb;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
