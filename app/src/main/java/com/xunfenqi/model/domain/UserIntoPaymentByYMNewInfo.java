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
public class UserIntoPaymentByYMNewInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String days;
	private String signValue;

	private String byYihk;
	private String byYinghk;

	// 投资年月 (选填)
	private String tzyy;

	private List<UserIntoPaymentByYMNew> dataList;

	public class UserIntoPaymentByYMNew {

		private String drYinghk; // 单日待回款
		private String drYihk; // 单日已回款
		private String hkrq; // 回款日期
		private List<ContentEntity> content;

		public class ContentEntity {
			private String cczt;
			private String cmmc;
			private String cpqx;
			private String dsbj;
			private String dslx;
			private String hkrq;
			private String nhsy;
			private String qs;
			private String ssbx;
			private String state;
			private String syfs;
			private String tzlcid;
			private String fjlv;

			public ContentEntity(String cczt, String cmmc, String cpqx,
					String dsbj, String dslx, String hkrq, String nhsy,
					String qs, String ssbx, String state, String syfs,
					String tzlcid, String fjlv) {
				super();
				this.cczt = cczt;
				this.cmmc = cmmc;
				this.cpqx = cpqx;
				this.dsbj = dsbj;
				this.dslx = dslx;
				this.hkrq = hkrq;
				this.nhsy = nhsy;
				this.qs = qs;
				this.ssbx = ssbx;
				this.state = state;
				this.syfs = syfs;
				this.tzlcid = tzlcid;
				this.fjlv = fjlv;
			}

			public ContentEntity() {
				super();
				// TODO Auto-generated constructor stub
			}

			public String getCczt() {
				return cczt;
			}

			public void setCczt(String cczt) {
				this.cczt = cczt;
			}

			public String getCmmc() {
				return cmmc;
			}

			public void setCmmc(String cmmc) {
				this.cmmc = cmmc;
			}

			public String getCpqx() {
				return cpqx;
			}

			public void setCpqx(String cpqx) {
				this.cpqx = cpqx;
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

			public String getHkrq() {
				return hkrq;
			}

			public void setHkrq(String hkrq) {
				this.hkrq = hkrq;
			}

			public String getNhsy() {
				return nhsy;
			}

			public void setNhsy(String nhsy) {
				this.nhsy = nhsy;
			}

			public String getQs() {
				return qs;
			}

			public void setQs(String qs) {
				this.qs = qs;
			}

			public String getSsbx() {
				return ssbx;
			}

			public void setSsbx(String ssbx) {
				this.ssbx = ssbx;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public String getSyfs() {
				return syfs;
			}

			public void setSyfs(String syfs) {
				this.syfs = syfs;
			}

			public String getTzlcid() {
				return tzlcid;
			}

			public void setTzlcid(String tzlcid) {
				this.tzlcid = tzlcid;
			}

			public String getFjlv() {
				return fjlv;
			}

			public void setFjlv(String fjlv) {
				this.fjlv = fjlv;
			}

		}

		public UserIntoPaymentByYMNew() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserIntoPaymentByYMNew(String drYinghk, String drYihk,
				String hkrq, List<ContentEntity> content) {
			super();
			this.drYinghk = drYinghk;
			this.drYihk = drYihk;
			this.hkrq = hkrq;
			this.content = content;
		}

		public String getDrYinghk() {
			return drYinghk;
		}

		public void setDrYinghk(String drYinghk) {
			this.drYinghk = drYinghk;
		}

		public String getDrYihk() {
			return drYihk;
		}

		public void setDrYihk(String drYihk) {
			this.drYihk = drYihk;
		}

		public String getHkrq() {
			return hkrq;
		}

		public void setHkrq(String hkrq) {
			this.hkrq = hkrq;
		}

		public List<ContentEntity> getContent() {
			return content;
		}

		public void setContent(List<ContentEntity> content) {
			this.content = content;
		}

	}

	public UserIntoPaymentByYMNewInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String days, String signValue,
			String tzyy, List<UserIntoPaymentByYMNew> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.days = days;
		this.signValue = signValue;
		this.tzyy = tzyy;
		this.dataList = dataList;
	}

	public UserIntoPaymentByYMNewInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String days, String signValue,
			String byYihk, String byYinghk, String tzyy,
			List<UserIntoPaymentByYMNew> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.days = days;
		this.signValue = signValue;
		this.byYihk = byYihk;
		this.byYinghk = byYinghk;
		this.tzyy = tzyy;
		this.dataList = dataList;
	}

	public UserIntoPaymentByYMNewInfo() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getByYihk() {
		return byYihk;
	}

	public void setByYihk(String byYihk) {
		this.byYihk = byYihk;
	}

	public String getByYinghk() {
		return byYinghk;
	}

	public void setByYinghk(String byYinghk) {
		this.byYinghk = byYinghk;
	}

	public String getTzyy() {
		return tzyy;
	}

	public void setTzyy(String tzyy) {
		this.tzyy = tzyy;
	}

	public List<UserIntoPaymentByYMNew> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserIntoPaymentByYMNew> dataList) {
		this.dataList = dataList;
	}

}
