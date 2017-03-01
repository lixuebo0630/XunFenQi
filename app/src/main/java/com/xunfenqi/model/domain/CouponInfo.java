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
import java.util.List;

/**
 * @date: 2015-8-31 下午5:41:26
 * @author: XueBo Li
 * @version:
 * @description: 查询我的红包列表信息
 * @see
 */
public class CouponInfo implements Serializable {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String yhqStatus;
	private List<Coupon> dataList;

	private String totalCount;

	private String pageCount;

	private String currentPage;

	private String signValue;

	public class Coupon implements Serializable {
		private String yhqId;
		private String mz;
		private String content;
		private String content2;
		private String yxq;
		private String condition;
		private String qlx;
		private String yhqStatus;
		private String shortTerm;
		private String minInvestment;

		public Coupon() {
			super();
		}

		public String getShortTerm() {
			return shortTerm;
		}

		public void setShortTerm(String shortTerm) {
			this.shortTerm = shortTerm;
		}

		public String getMinInvestment() {
			return minInvestment;
		}

		public void setMinInvestment(String minInvestment) {
			this.minInvestment = minInvestment;
		}

		public String getYhqId() {
			return yhqId;
		}

		public void setYhqId(String yhqId) {
			this.yhqId = yhqId;
		}

		public String getMz() {
			return mz;
		}

		public void setMz(String mz) {
			this.mz = mz;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent2() {
			return content2;
		}

		public void setContent2(String content2) {
			this.content2 = content2;
		}

		public String getYxq() {
			return yxq;
		}

		public void setYxq(String yxq) {
			this.yxq = yxq;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public String getQlx() {
			return qlx;
		}

		public void setQlx(String qlx) {
			this.qlx = qlx;
		}

		public String getYhqStatus() {
			return yhqStatus;
		}

		public void setYhqStatus(String yhqStatus) {
			this.yhqStatus = yhqStatus;
		}

	}

	public CouponInfo() {
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

	public String getYhqStatus() {
		return yhqStatus;
	}

	public void setYhqStatus(String yhqStatus) {
		this.yhqStatus = yhqStatus;
	}

	public List<Coupon> getDataList() {
		return dataList;
	}

	public void setDataList(List<Coupon> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
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

}
