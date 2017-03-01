/**
 * Project Name:HaiHeFinance
 * File Name:UserPresentRecordInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午4:48:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午4:48:51
 * @author: XueBo Li
 * @version:
 * @description: 提现明细Bean
 * @see
 */
public class CurrentUserPresentRecordInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String userId;
	private String totalCount;
	private String pageCount;
	private String currentPage;
	// 提现状态 a 成功 b 失败 c 处理中传值用-分割 例：a-b-c
	private String presentStatus;// (选填)

	private String signValue;

	private List<UserPresentRecord> dataList;

	public class UserPresentRecord {
		// 提现金额
		private String txje;
		// 提现状态
		private String txzt;
		//银行卡号
		private String bankNo;
		//银行名称
		private String bankName;
		//提现日期
		private String txDay;
		//提现时间
		private String txTime;
		// 备注
		private String beizhu;

		public UserPresentRecord() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getTxje() {
			return txje;
		}

		public void setTxje(String txje) {
			this.txje = txje;
		}

		public String getTxzt() {
			return txzt;
		}

		public void setTxzt(String txzt) {
			this.txzt = txzt;
		}

		public String getBankNo() {
			return bankNo;
		}

		public void setBankNo(String bankNo) {
			this.bankNo = bankNo;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getTxDay() {
			return txDay;
		}

		public void setTxDay(String txDay) {
			this.txDay = txDay;
		}

		public String getTxTime() {
			return txTime;
		}

		public void setTxTime(String txTime) {
			this.txTime = txTime;
		}

		public String getBeizhu() {
			return beizhu;
		}

		public void setBeizhu(String beizhu) {
			this.beizhu = beizhu;
		}

		@Override
		public String toString() {
			return "UserPresentRecord [txje=" + txje + ", txzt=" + txzt
					+ ", bankNo=" + bankNo + ", bankName=" + bankName
					+ ", txDay=" + txDay + ", txTime=" + txTime + ", beizhu="
					+ beizhu + "]";
		}

		

	}

	public CurrentUserPresentRecordInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentUserPresentRecordInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String totalCount,
			String pageCount, String currentPage, String presentStatus,
			String signValue, List<UserPresentRecord> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.presentStatus = presentStatus;
		this.signValue = signValue;
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "UserPresentRecordInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", totalCount=" + totalCount
				+ ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", presentStatus=" + presentStatus + ", signValue="
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

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<UserPresentRecord> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserPresentRecord> dataList) {
		this.dataList = dataList;
	}

}
