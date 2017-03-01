/**
 * Project Name:HaiHeFinance
 * File Name:UserIntoPaymentByYMInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:14:52
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午5:14:52
 * @author: XueBo Li
 * @version: 查询回款计划年月列表信息Bean
 * @description:
 * @see
 */
public class UserIntoPaymentByYMInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String signValue;
	// 投资年月 (选填)
	private String tzyy;

	private List<UserIntoPaymentByYM> dataList;

	public class UserIntoPaymentByYM {
		// 回款日期
		private String hkrq;
		// 项目名称
		private String cmmc;
		// 年化收益
		private String nhsy;
		// 附加利率
		private String fjlv;
		// 产品期限
		private String cpqx;
		// 收益方式
		private String syfs;
		// 实收本息
		private String ssbx;
		// 待收本金
		private String dsbj;
		// 待收利息
		private String dslx;
		// 期数
		private String qs;
		// 产品状态
		private String cczt;
		// 投资理财id
		private String tzlcid;
		
		private String state;

		public UserIntoPaymentByYM() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserIntoPaymentByYM(String hkrq, String cmmc, String nhsy,
				String fjlv, String cpqx, String syfs, String ssbx,
				String dsbj, String dslx, String qs, String cczt, String tzlcid) {
			super();
			this.hkrq = hkrq;
			this.cmmc = cmmc;
			this.nhsy = nhsy;
			this.fjlv = fjlv;
			this.cpqx = cpqx;
			this.syfs = syfs;
			this.ssbx = ssbx;
			this.dsbj = dsbj;
			this.dslx = dslx;
			this.qs = qs;
			this.cczt = cczt;
			this.tzlcid = tzlcid;
		}

		public String getHkrq() {
			return hkrq;
		}

		public void setHkrq(String hkrq) {
			this.hkrq = hkrq;
		}

		public String getCmmc() {
			return cmmc;
		}

		public void setCmmc(String cmmc) {
			this.cmmc = cmmc;
		}

		public String getNhsy() {
			return nhsy;
		}

		public void setNhsy(String nhsy) {
			this.nhsy = nhsy;
		}

		public String getFjlv() {
			return fjlv;
		}

		public void setFjlv(String fjlv) {
			this.fjlv = fjlv;
		}

		public String getCpqx() {
			return cpqx;
		}

		public void setCpqx(String cpqx) {
			this.cpqx = cpqx;
		}

		public String getSyfs() {
			return syfs;
		}

		public void setSyfs(String syfs) {
			this.syfs = syfs;
		}

		public String getSsbx() {
			return ssbx;
		}

		public void setSsbx(String ssbx) {
			this.ssbx = ssbx;
		}

		public String getDsbj() {
			return dsbj;
		}

		public void setDsbj(String dsbj) {
			this.dsbj = dsbj;
		}

		public String getDslx() {
			return dslx;
		}

		public void setDslx(String dslx) {
			this.dslx = dslx;
		}

		public String getQs() {
			return qs;
		}

		public void setQs(String qs) {
			this.qs = qs;
		}

		public String getCczt() {
			return cczt;
		}

		public void setCczt(String cczt) {
			this.cczt = cczt;
		}

		public String getTzlcid() {
			return tzlcid;
		}

		public void setTzlcid(String tzlcid) {
			this.tzlcid = tzlcid;
		}
		
		

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "UserIntoPaymentByYM [hkrq=" + hkrq + ", cmmc=" + cmmc
					+ ", nhsy=" + nhsy + ", fjlv=" + fjlv + ", cpqx=" + cpqx
					+ ", syfs=" + syfs + ", ssbx=" + ssbx + ", dsbj=" + dsbj
					+ ", dslx=" + dslx + ", qs=" + qs + ", cczt=" + cczt
					+ ", tzlcid=" + tzlcid + "]";
		}

	}

	public UserIntoPaymentByYMInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserIntoPaymentByYMInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String signValue, String tzyy,
			List<UserIntoPaymentByYM> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.signValue = signValue;
		this.tzyy = tzyy;
		this.dataList = dataList;
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

	public String getTzyy() {
		return tzyy;
	}

	public void setTzyy(String tzyy) {
		this.tzyy = tzyy;
	}

	public List<UserIntoPaymentByYM> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserIntoPaymentByYM> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "UserIntoPaymentByYMInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", signValue=" + signValue + ", tzyy="
				+ tzyy + ", dataList=" + dataList + "]";
	}
	
	

}
