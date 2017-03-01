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
 * @description: 查询我的红包列表信息
 * @see
 */
public class UserMyRedInfo {
	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String lqzt;// -1全部 0未领取 2已领取

	private String totalCount;

	private String pageCount;

	private String currentPage;

	private List<UserMyRed> dataList;

	private String signValue;

	public class UserMyRed {
		// 金额
		private String jine;
		// 发放日期
		private String ffrq;
		// 可领日期
		private String klrq;
		// 状态
		private String lqzt;
		// 备注
		private String beizhu;
		private String rbid;
		private String hblx;
		private String lqrq;

		private  String hbid;
		private String bt;//标题

		public String getLqrq() {
			return lqrq;
		}

		public void setLqrq(String lqrq) {
			this.lqrq = lqrq;
		}

		public String getHbid() {
			return hbid;
		}

		public void setHbid(String hbid) {
			this.hbid = hbid;
		}

		public String getBt() {
			return bt;
		}

		public void setBt(String bt) {
			this.bt = bt;
		}

		public UserMyRed() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserMyRed(String jine, String ffrq, String klrq, String lqzt,
				String beizhu,String rbid) {
			super();
			this.jine = jine;
			this.ffrq = ffrq;
			this.klrq = klrq;
			this.lqzt = lqzt;
			this.beizhu = beizhu;
			this.rbid = rbid;
			
		}

		public String getJine() {
			return jine;
		}

		public void setJine(String jine) {
			this.jine = jine;
		}

		public String getFfrq() {
			return ffrq;
		}

		public void setFfrq(String ffrq) {
			this.ffrq = ffrq;
		}

		public String getKlrq() {
			return klrq;
		}

		public void setKlrq(String klrq) {
			this.klrq = klrq;
		}

		public String getLqzt() {
			return lqzt;
		}

		public void setLqzt(String lqzt) {
			this.lqzt = lqzt;
		}

		public String getBeizhu() {
			return beizhu;
		}

		public void setBeizhu(String beizhu) {
			this.beizhu = beizhu;
		}
		

		public String getRbid() {
			return rbid;
		}

		public void setRbid(String rbid) {
			this.rbid = rbid;
		}

		

		public String getHblx() {
			return hblx;
		}

		public void setHblx(String hblx) {
			this.hblx = hblx;
		}

		@Override
		public String toString() {
			return "UserMyRed [jine=" + jine + ", ffrq=" + ffrq + ", klrq="
					+ klrq + ", lqzt=" + lqzt + ", beizhu=" + beizhu + "]";
		}

	}

	public UserMyRedInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMyRedInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String lqzt, String totalCount,
			String pageCount, String currentPage, List<UserMyRed> dataList,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.lqzt = lqzt;
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

	public String getLqzt() {
		return lqzt;
	}

	public void setLqzt(String lqzt) {
		this.lqzt = lqzt;
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

	public List<UserMyRed> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserMyRed> dataList) {
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
				+ userId + ", lqzt=" + lqzt + ", totalCount=" + totalCount
				+ ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", dataList=" + dataList + ", signValue=" + signValue + "]";
	}

}
