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


public class MyCommissionInfo {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;
	private List<MyCommission> dataList;

	private String signValue;

	public class MyCommission {
		private String yearmonth;
		private String settleStatus;
		private String label1;
		private String label2;
		private String label3;
		private String label4;
		private String label5;
		private String label6;
		public String getYearmonth() {
			return yearmonth;
		}
		public void setYearmonth(String yearmonth) {
			this.yearmonth = yearmonth;
		}
		public String getSettleStatus() {
			return settleStatus;
		}
		public void setSettleStatus(String settleStatus) {
			this.settleStatus = settleStatus;
		}
		public String getLabel1() {
			return label1;
		}
		public void setLabel1(String label1) {
			this.label1 = label1;
		}
		public String getLabel2() {
			return label2;
		}
		public void setLabel2(String label2) {
			this.label2 = label2;
		}
		public String getLabel3() {
			return label3;
		}
		public void setLabel3(String label3) {
			this.label3 = label3;
		}
		public String getLabel4() {
			return label4;
		}
		public void setLabel4(String label4) {
			this.label4 = label4;
		}
		public String getLabel5() {
			return label5;
		}
		public void setLabel5(String label5) {
			this.label5 = label5;
		}
		public String getLabel6() {
			return label6;
		}
		public void setLabel6(String label6) {
			this.label6 = label6;
		}
		
		
	}

	public MyCommissionInfo() {
		super();
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




	public List<MyCommission> getDataList() {
		return dataList;
	}

	public void setDataList(List<MyCommission> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}



}
