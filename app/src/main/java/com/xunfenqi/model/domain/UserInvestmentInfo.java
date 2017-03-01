/**
 * Project Name:HaiHeFinance
 * File Name:UserInvestMentInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午2:54:38
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2015-8-31 下午2:54:38
 * @author: XueBo Li
 * @version:
 * @description: 我的投资信息Bean
 * @see
 */
public class UserInvestmentInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String totalCount;
	private String pageCount;
	private String currentPage;
	private String userId;
	private List<UserInvest> dataList;
	private String signValue;

	public class UserInvest implements Serializable {
		private String xmmc;
		private String nhsy;
		private String tzlx;
		private String tzje;
		private String yqsy;
		private String tzsj;
		private String dqrq;
		private String tzzt;
		private String tzqx;
		private String cpid;
		private String ivid;
		private String zjll;
		private String qxrq;// 起息日期
		private String dhk;
		private String ssbj;
		private String sssy;
		private String plusRate;
		private String diyu;
		private String transferId;
		private String activityType;

		private String iszqzr;
		
		
		public String getActivityType() {
			return activityType;
		}

		public void setActivityType(String activityType) {
			this.activityType = activityType;
		}

		public String getTransferId() {
			return transferId;
		}

		public void setTransferId(String transferId) {
			this.transferId = transferId;
		}

		public String getIszqzr() {
			return iszqzr;
		}

		public void setIszqzr(String iszqzr) {
			this.iszqzr = iszqzr;
		}

		public String getDiyu() {
			return diyu;
		}

		public void setDiyu(String diyu) {
			this.diyu = diyu;
		}

		private String pageFlag;// 1 持有中 //2投标中 //3已结束

		public String getPageFlag() {
			return pageFlag;
		}

		public void setPageFlag(String pageFlag) {
			this.pageFlag = pageFlag;
		}

		public String getPlusRate() {
			return plusRate;
		}

		public void setPlusRate(String plusRate) {
			this.plusRate = plusRate;
		}

		public UserInvest() {
			super();
		}

		public UserInvest(String xmmc, String nhsy, String tzlx, String tzje,
				String yqsy, String tzsj, String dqrq, String tzzt,
				String tzqx, String cpid, String ivid, String zjll,
				String qxrq, String dhk, String ssbj, String sssy) {
			super();
			this.xmmc = xmmc;
			this.nhsy = nhsy;
			this.tzlx = tzlx;
			this.tzje = tzje;
			this.yqsy = yqsy;
			this.tzsj = tzsj;
			this.dqrq = dqrq;
			this.tzzt = tzzt;
			this.tzqx = tzqx;
			this.cpid = cpid;
			this.ivid = ivid;
			this.zjll = zjll;
			this.qxrq = qxrq;
			this.dhk = dhk;
			this.ssbj = ssbj;
			this.sssy = sssy;
		}

		public String getXmmc() {
			return xmmc;
		}

		public void setXmmc(String xmmc) {
			this.xmmc = xmmc;
		}

		public String getNhsy() {
			return nhsy;
		}

		public void setNhsy(String nhsy) {
			this.nhsy = nhsy;
		}

		public String getTzlx() {
			return tzlx;
		}

		public void setTzlx(String tzlx) {
			this.tzlx = tzlx;
		}

		public String getTzje() {
			return tzje;
		}

		public void setTzje(String tzje) {
			this.tzje = tzje;
		}

		public String getYqsy() {
			return yqsy;
		}

		public void setYqsy(String yqsy) {
			this.yqsy = yqsy;
		}

		public String getTzsj() {
			return tzsj;
		}

		public void setTzsj(String tzsj) {
			this.tzsj = tzsj;
		}

		public String getDqrq() {
			return dqrq;
		}

		public void setDqrq(String dqrq) {
			this.dqrq = dqrq;
		}

		public String getTzzt() {
			return tzzt;
		}

		public void setTzzt(String tzzt) {
			this.tzzt = tzzt;
		}

		public String getTzqx() {
			return tzqx;
		}

		public void setTzqx(String tzqx) {
			this.tzqx = tzqx;
		}

		public String getCpid() {
			return cpid;
		}

		public void setCpid(String cpid) {
			this.cpid = cpid;
		}

		public String getIvid() {
			return ivid;
		}

		public void setIvid(String ivid) {
			this.ivid = ivid;
		}

		public String getZjll() {
			return zjll;
		}

		public void setZjll(String zjll) {
			this.zjll = zjll;
		}

		public String getQxrq() {
			return qxrq;
		}

		public void setQxrq(String qxrq) {
			this.qxrq = qxrq;
		}

		public String getDhk() {
			return dhk;
		}

		public void setDhk(String dhk) {
			this.dhk = dhk;
		}

		public String getSsbj() {
			return ssbj;
		}

		public void setSsbj(String ssbj) {
			this.ssbj = ssbj;
		}

		public String getSssy() {
			return sssy;
		}

		public void setSssy(String sssy) {
			this.sssy = sssy;
		}

		@Override
		public String toString() {
			return "UserInvest [xmmc=" + xmmc + ", nhsy=" + nhsy + ", tzlx="
					+ tzlx + ", tzje=" + tzje + ", yqsy=" + yqsy + ", tzsj="
					+ tzsj + ", dqrq=" + dqrq + ", tzzt=" + tzzt + ", tzqx="
					+ tzqx + ", cpid=" + cpid + ", ivid=" + ivid + ", zjll="
					+ zjll + ", qxrq=" + qxrq + ", dhk=" + dhk + ", ssbj="
					+ ssbj + ", sssy=" + sssy + "]";
		}

	}

	public UserInvestmentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInvestmentInfo(String messageType, String respCode,
			String respCodeDesc, String totalCount, String pageCount,
			String currentPage, String userId, List<UserInvest> dataList,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.userId = userId;
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

	public List<UserInvest> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserInvest> dataList) {
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
		return "UserInvestmentInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", totalCount="
				+ totalCount + ", pageCount=" + pageCount + ", currentPage="
				+ currentPage + ", userId=" + userId + ", dataList=" + dataList
				+ ", signValue=" + signValue + "]";
	}

}
