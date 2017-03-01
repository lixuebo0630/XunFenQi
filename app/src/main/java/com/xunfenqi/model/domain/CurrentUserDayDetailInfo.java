/**
 * Project Name:HaiHeFinance
 * File Name:UserIncomeSatementInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午4:02:49
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午4:02:49
 * @author: XueBo Li
 * @version:
 * @description: 收支明细Bean
 * @see
 */
public class CurrentUserDayDetailInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String totalCount;
	private String pageCount;
	private String currentPage;
	private String incomeType;// 收支类型

	private String signValue;

	private List<UserIncomeStatement> dataList;

	public class UserIncomeStatement {
		private String jsbj;
		private String jsll;
		private String status;
		private String jxsj;
		// 资金
		private String jssj;
		// 账户余额
		private String jslx;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getJxsj() {
			return jxsj;
		}
		public void setJxsj(String jxsj) {
			this.jxsj = jxsj;
		}
		public String getJsbj() {
			return jsbj;
		}
		public void setJsbj(String jsbj) {
			this.jsbj = jsbj;
		}
		public String getJsll() {
			return jsll;
		}
		public void setJsll(String jsll) {
			this.jsll = jsll;
		}
		public String getJssj() {
			return jssj;
		}
		public void setJssj(String jssj) {
			this.jssj = jssj;
		}
		public String getJslx() {
			return jslx;
		}
		public void setJslx(String jslx) {
			this.jslx = jslx;
		}
		

	}

	public CurrentUserDayDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentUserDayDetailInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String totalCount,
			String pageCount, String currentPage, String incomeType,
			String signValue, List<UserIncomeStatement> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.incomeType = incomeType;
		this.signValue = signValue;
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

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<UserIncomeStatement> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserIncomeStatement> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "UserIncomeSatementInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", totalCount=" + totalCount
				+ ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", incomeType=" + incomeType + ", signValue=" + signValue
				+ ", dataList=" + dataList + "]";
	}

}
