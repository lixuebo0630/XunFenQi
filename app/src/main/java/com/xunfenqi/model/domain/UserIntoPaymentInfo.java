/**
 * Project Name:HaiHeFinance
 * File Name:UserIntoPayment.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:00:08
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * @date: 2015-8-31 下午5:00:08
 * @author: XueBo Li
 * @version:
 * @description: 查询回款计划年月列表信息Bean
 * @see
 */
public class UserIntoPaymentInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private List<UserIntoPayment> dataList;

	private String signValue;

	public class UserIntoPayment {
		// 年月
		private String ny;
		// 已回款
		private String yhk;
		// 待回款
		private String dhk;
		// 总额
		private String ze;
		private String isCurrentMonth;
		// 笔数
		private String bs;

		public UserIntoPayment() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getNy() {
			return ny;
		}

		public void setNy(String ny) {
			this.ny = ny;
		}

		public String getYhk() {
			return yhk;
		}

		public void setYhk(String yhk) {
			this.yhk = yhk;
		}

		public String getDhk() {
			return dhk;
		}

		public void setDhk(String dhk) {
			this.dhk = dhk;
		}

		public String getZe() {
			return ze;
		}

		public void setZe(String ze) {
			this.ze = ze;
		}

		public String getIsCurrentMonth() {
			return isCurrentMonth;
		}

		public void setIsCurrentMonth(String isCurrentMonth) {
			this.isCurrentMonth = isCurrentMonth;
		}

		public String getBs() {
			return bs;
		}

		public void setBs(String bs) {
			this.bs = bs;
		}

	}

	public UserIntoPaymentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserIntoPaymentInfo(String messageType, String respCode,
			String respCodeDesc, String userId, List<UserIntoPayment> dataList,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.dataList = dataList;
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "UserIntoPaymentInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", dataList=" + dataList
				+ ", signValue=" + signValue + "]";
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

	public List<UserIntoPayment> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserIntoPayment> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

}
