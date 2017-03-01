/**
 * Project Name:HaiHeFinance
 * File Name:UserMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:41:26
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;


public class UserMyJiekuanInfo {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;

	private String totalCount;

	private String pageCount;

	private String currentPage;

	private List<UserMyJiekuan> dataList;

	private String signValue;

	public class UserMyJiekuan {
		// 金额
		private String yh;
		private String ls;
		private String bz;
		private String zt;
		private String ze;
		private String qs;
		private String sj;
		private String lx;
		private String sxf;

		private String jkid;

		public String getJkid() {
			return jkid;
		}

		public void setJkid(String jkid) {
			this.jkid = jkid;
		}

		public String getSxf() {
			return sxf;
		}

		public void setSxf(String sxf) {
			this.sxf = sxf;
		}

		public String getYh() {
			return yh;
		}

		public void setYh(String yh) {
			this.yh = yh;
		}

		public String getLs() {
			return ls;
		}

		public void setLs(String ls) {
			this.ls = ls;
		}

		public String getBz() {
			return bz;
		}

		public void setBz(String bz) {
			this.bz = bz;
		}

		public String getZt() {
			return zt;
		}

		public void setZt(String zt) {
			this.zt = zt;
		}

		public String getZe() {
			return ze;
		}

		public void setZe(String ze) {
			this.ze = ze;
		}

		public String getQs() {
			return qs;
		}

		public void setQs(String qs) {
			this.qs = qs;
		}

		public String getSj() {
			return sj;
		}

		public void setSj(String sj) {
			this.sj = sj;
		}

		public String getLx() {
			return lx;
		}

		public void setLx(String lx) {
			this.lx = lx;
		}
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

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public List<UserMyJiekuan> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserMyJiekuan> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
