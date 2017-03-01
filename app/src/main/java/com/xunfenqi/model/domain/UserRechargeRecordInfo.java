/**
 * Project Name:HaiHeFinance
 * File Name:UserRechargeRecord.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午4:19:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午4:19:29
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class UserRechargeRecordInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;

	private String totalCount;
	private String pageCount;
	private String currentPage;
	private String userId;
	private String rechargeStatus;//
	private String kyye;// 可用余额

	private List<UserRechargeRecord> dataList;
	private String signValue;

	public class UserRechargeRecord {
		// 订单号
		private String ddh1;
		private String ddh2;
		// 充值日期
		private String czrq;
		// 充值金额
		private String czje;
		// 充值结果
		private String czjg;
		// 充值日期
		private String czDay;
		// 充值时间
		private String czTime;
		// 备注
		private String beizhu;

		public UserRechargeRecord() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getDdh1() {
			return ddh1;
		}

		public void setDdh1(String ddh1) {
			this.ddh1 = ddh1;
		}

		public String getDdh2() {
			return ddh2;
		}

		public void setDdh2(String ddh2) {
			this.ddh2 = ddh2;
		}

		public String getCzrq() {
			return czrq;
		}

		public void setCzrq(String czrq) {
			this.czrq = czrq;
		}

		public String getCzje() {
			return czje;
		}

		public void setCzje(String czje) {
			this.czje = czje;
		}

		public String getCzjg() {
			return czjg;
		}

		public void setCzjg(String czjg) {
			this.czjg = czjg;
		}

		public String getCzDay() {
			return czDay;
		}

		public void setCzDay(String czDay) {
			this.czDay = czDay;
		}

		public String getCzTime() {
			return czTime;
		}

		public void setCzTime(String czTime) {
			this.czTime = czTime;
		}

		public String getBeizhu() {
			return beizhu;
		}

		public void setBeizhu(String beizhu) {
			this.beizhu = beizhu;
		}

		@Override
		public String toString() {
			return "UserRechargeRecord [ddh1=" + ddh1 + ", ddh2=" + ddh2
					+ ", czrq=" + czrq + ", czje=" + czje + ", czjg=" + czjg
					+ ", czDay=" + czDay + ", czTime=" + czTime + ", beizhu="
					+ beizhu + "]";
		}

	}

	public UserRechargeRecordInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRechargeRecordInfo(String messageType, String respCode,
			String respCodeDesc, String totalCount, String pageCount,
			String currentPage, String userId, String rechargeStatus,
			String kyye, List<UserRechargeRecord> dataList, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.userId = userId;
		this.rechargeStatus = rechargeStatus;
		this.kyye = kyye;
		this.dataList = dataList;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(String rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	public String getKyye() {
		return kyye;
	}

	public void setKyye(String kyye) {
		this.kyye = kyye;
	}

	public List<UserRechargeRecord> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserRechargeRecord> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "UserRechargeRecordInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", totalCount=" + totalCount + ", pageCount=" + pageCount
				+ ", currentPage=" + currentPage + ", userId=" + userId
				+ ", rechargeStatus=" + rechargeStatus + ", kyye=" + kyye
				+ ", dataList=" + dataList + ", signValue=" + signValue + "]";
	}

}
