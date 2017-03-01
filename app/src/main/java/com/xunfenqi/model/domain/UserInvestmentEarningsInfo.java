/**
 * Project Name:HaiHeFinance
 * File Name:UserInvestmentEarningsInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午3:31:14
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午3:31:14
 * @author: XueBo Li
 * @version:
 * @description: 投资收益信息/回款计划明细Bean
 * @see
 */
public class UserInvestmentEarningsInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;
	// 产品名称
	private String ccmc;
	// 产品利率
	private String cplv;
	// 预期收益
	private String yqsy;
	// 收益方式
	private String syfs;
	// 增加利率
	private String zjlv;
	// 投标状态
	private String tbzt;
	// 投资金额
	private String Tzje;
	// 已回款
	private String Yhk;

	private String investmentId;

	private String userId;

	private String signValue;

	private List<UserInvestmentEarnings> dataList;

	public class UserInvestmentEarnings {
		// 期数
		private String xmqs;
		// 回款日
		private String hkr;
		// 应收本息
		private String ysbx;
		// 实收本金
		private String ysbj;
		// 实收收益
		private String sssy;
		// 结算状态
		private String jszt;

		public UserInvestmentEarnings() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserInvestmentEarnings(String xmqs, String hkr, String ysbx,
				String ysbj, String sssy, String jszt) {
			super();
			this.xmqs = xmqs;
			this.hkr = hkr;
			this.ysbx = ysbx;
			this.ysbj = ysbj;
			this.sssy = sssy;
			this.jszt = jszt;
		}

		public String getXmqs() {
			return xmqs;
		}

		public void setXmqs(String xmqs) {
			this.xmqs = xmqs;
		}

		public String getHkr() {
			return hkr;
		}

		public void setHkr(String hkr) {
			this.hkr = hkr;
		}

		public String getYsbx() {
			return ysbx;
		}

		public void setYsbx(String ysbx) {
			this.ysbx = ysbx;
		}

		public String getYsbj() {
			return ysbj;
		}

		public void setYsbj(String ysbj) {
			this.ysbj = ysbj;
		}

		public String getSssy() {
			return sssy;
		}

		public void setSssy(String sssy) {
			this.sssy = sssy;
		}

		public String getJszt() {
			return jszt;
		}

		public void setJszt(String jszt) {
			this.jszt = jszt;
		}

		@Override
		public String toString() {
			return "UserInvestmentEarnings [xmqs=" + xmqs + ", hkr=" + hkr
					+ ", ysbx=" + ysbx + ", ysbj=" + ysbj + ", sssy=" + sssy
					+ ", jszt=" + jszt + "]";
		}

	}

	public UserInvestmentEarningsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInvestmentEarningsInfo(String messageType, String respCode,
			String respCodeDesc, String ccmc, String cplv, String yqsy,
			String syfs, String zjlv, String tbzt, String tzje, String yhk,
			String investmentId, String userId, String signValue,
			List<UserInvestmentEarnings> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.ccmc = ccmc;
		this.cplv = cplv;
		this.yqsy = yqsy;
		this.syfs = syfs;
		this.zjlv = zjlv;
		this.tbzt = tbzt;
		Tzje = tzje;
		Yhk = yhk;
		this.investmentId = investmentId;
		this.userId = userId;
		this.signValue = signValue;
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "UserInvestmentEarningsInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", ccmc=" + ccmc + ", cplv=" + cplv + ", yqsy=" + yqsy
				+ ", syfs=" + syfs + ", zjlv=" + zjlv + ", tbzt=" + tbzt
				+ ", Tzje=" + Tzje + ", Yhk=" + Yhk + ", investmentId="
				+ investmentId + ", userId=" + userId + ", signValue="
				+ signValue + ", dataList=" + dataList + "]";
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

	public String getCcmc() {
		return ccmc;
	}

	public void setCcmc(String ccmc) {
		this.ccmc = ccmc;
	}

	public String getCplv() {
		return cplv;
	}

	public void setCplv(String cplv) {
		this.cplv = cplv;
	}

	public String getYqsy() {
		return yqsy;
	}

	public void setYqsy(String yqsy) {
		this.yqsy = yqsy;
	}

	public String getSyfs() {
		return syfs;
	}

	public void setSyfs(String syfs) {
		this.syfs = syfs;
	}

	public String getZjlv() {
		return zjlv;
	}

	public void setZjlv(String zjlv) {
		this.zjlv = zjlv;
	}

	public String getTbzt() {
		return tbzt;
	}

	public void setTbzt(String tbzt) {
		this.tbzt = tbzt;
	}

	public String getTzje() {
		return Tzje;
	}

	public void setTzje(String tzje) {
		Tzje = tzje;
	}

	public String getYhk() {
		return Yhk;
	}

	public void setYhk(String yhk) {
		Yhk = yhk;
	}

	public String getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(String investmentId) {
		this.investmentId = investmentId;
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

	public List<UserInvestmentEarnings> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserInvestmentEarnings> dataList) {
		this.dataList = dataList;
	}

}
