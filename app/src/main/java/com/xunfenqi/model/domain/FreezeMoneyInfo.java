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

/**
 * @date: 2015-8-31 下午5:41:26
 * @author: XueBo Li
 * @version:
 * @description: 冻结金额列表信息
 * @see
 */
public class FreezeMoneyInfo {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;

	private String totalCount;

	private String pageCount;

	private String currentPage;

	private List<FreezeMoney> dataList;

	private String signValue;

	public class FreezeMoney {
		
		private String cjDay;
		private String cjTime;
		private String djType;
		private String djje;
		private String back1;
		private String back2;
		private String back3;
		public String getCjDay() {
			return cjDay;
		}
		public void setCjDay(String cjDay) {
			this.cjDay = cjDay;
		}
		public String getCjTime() {
			return cjTime;
		}
		public void setCjTime(String cjTime) {
			this.cjTime = cjTime;
		}
		public String getDjType() {
			return djType;
		}
		public void setDjType(String djType) {
			this.djType = djType;
		}
		public String getDjje() {
			return djje;
		}
		public void setDjje(String djje) {
			this.djje = djje;
		}
		public String getBack1() {
			return back1;
		}
		public void setBack1(String back1) {
			this.back1 = back1;
		}
		public String getBack2() {
			return back2;
		}
		public void setBack2(String back2) {
			this.back2 = back2;
		}
		public String getBack3() {
			return back3;
		}
		public void setBack3(String back3) {
			this.back3 = back3;
		}
		public FreezeMoney(String cjDay, String cjTime, String djType,
				String djje, String back1, String back2, String back3) {
			super();
			this.cjDay = cjDay;
			this.cjTime = cjTime;
			this.djType = djType;
			this.djje = djje;
			this.back1 = back1;
			this.back2 = back2;
			this.back3 = back3;
		}
		public FreezeMoney() {
			super();
		}
	
		

	}

	public FreezeMoneyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreezeMoneyInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String totalCount,
			String pageCount, String currentPage, List<FreezeMoney> dataList,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
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

	public List<FreezeMoney> getDataList() {
		return dataList;
	}

	public void setDataList(List<FreezeMoney> dataList) {
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
		return "UserMyRedInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", userId="
				+ userId + ", totalCount=" + totalCount
				+ ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", dataList=" + dataList + ", signValue=" + signValue + "]";
	}

}
