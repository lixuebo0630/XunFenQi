package com.xunfenqi.model.domain;

import java.util.List;

/**
 * 
 * @ClassName: UserMyTransferInfo
 * @Description: 我的债权转让实体
 * @author Xuebo Li
 * @date 2015-12-8 下午5:01:11
 * 
 */
public class UserMyTransferInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String totalCount;
	private String pageCount;
	private String currentPage;
	private List<UserMyTransfer> dataList;

	private String signValue;

	public class UserMyTransfer {
		private String id;
		private String xmmc;
		private String tzje;
		private String nhll;
		private String yqsy;
		private String xmqx;
		private String daihuikuan;
		private String yihuikuan;
		private String tzsj;
		private String dqsj;
		private String zrbj;
		private String sysj;
		private String yjsr;
		private String zrjd;
		private String sqzrsj;
		private String zrdqsj;
		private String syqx;
		private String zrcgsj;

		public UserMyTransfer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserMyTransfer(String id, String xmmc, String tzje, String nhll,
				String yqsy, String xmqx, String daihuikuan, String yihuikuan,
				String tzsj, String dqsj, String zrbj, String sysj,
				String yjsr, String zrjd, String sqzrsj, String zrdqsj,
				String syqx, String zrcgsj) {
			super();
			this.id = id;
			this.xmmc = xmmc;
			this.tzje = tzje;
			this.nhll = nhll;
			this.yqsy = yqsy;
			this.xmqx = xmqx;
			this.daihuikuan = daihuikuan;
			this.yihuikuan = yihuikuan;
			this.tzsj = tzsj;
			this.dqsj = dqsj;
			this.zrbj = zrbj;
			this.sysj = sysj;
			this.yjsr = yjsr;
			this.zrjd = zrjd;
			this.sqzrsj = sqzrsj;
			this.zrdqsj = zrdqsj;
			this.syqx = syqx;
			this.zrcgsj = zrcgsj;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getXmmc() {
			return xmmc;
		}

		public void setXmmc(String xmmc) {
			this.xmmc = xmmc;
		}

		public String getTzje() {
			return tzje;
		}

		public void setTzje(String tzje) {
			this.tzje = tzje;
		}

		public String getNhll() {
			return nhll;
		}

		public void setNhll(String nhll) {
			this.nhll = nhll;
		}

		public String getYqsy() {
			return yqsy;
		}

		public void setYqsy(String yqsy) {
			this.yqsy = yqsy;
		}

		public String getXmqx() {
			return xmqx;
		}

		public void setXmqx(String xmqx) {
			this.xmqx = xmqx;
		}

		public String getDaihuikuan() {
			return daihuikuan;
		}

		public void setDaihuikuan(String daihuikuan) {
			this.daihuikuan = daihuikuan;
		}

		public String getYihuikuan() {
			return yihuikuan;
		}

		public void setYihuikuan(String yihuikuan) {
			this.yihuikuan = yihuikuan;
		}

		public String getTzsj() {
			return tzsj;
		}

		public void setTzsj(String tzsj) {
			this.tzsj = tzsj;
		}

		public String getDqsj() {
			return dqsj;
		}

		public void setDqsj(String dqsj) {
			this.dqsj = dqsj;
		}

		public String getZrbj() {
			return zrbj;
		}

		public void setZrbj(String zrbj) {
			this.zrbj = zrbj;
		}

		public String getSysj() {
			return sysj;
		}

		public void setSysj(String sysj) {
			this.sysj = sysj;
		}

		public String getYjsr() {
			return yjsr;
		}

		public void setYjsr(String yjsr) {
			this.yjsr = yjsr;
		}

		public String getZrjd() {
			return zrjd;
		}

		public void setZrjd(String zrjd) {
			this.zrjd = zrjd;
		}

		public String getSqzrsj() {
			return sqzrsj;
		}

		public void setSqzrsj(String sqzrsj) {
			this.sqzrsj = sqzrsj;
		}

		public String getZrdqsj() {
			return zrdqsj;
		}

		public void setZrdqsj(String zrdqsj) {
			this.zrdqsj = zrdqsj;
		}

		public String getSyqx() {
			return syqx;
		}

		public void setSyqx(String syqx) {
			this.syqx = syqx;
		}

		public String getZrcgsj() {
			return zrcgsj;
		}

		public void setZrcgsj(String zrcgsj) {
			this.zrcgsj = zrcgsj;
		}

	}

	public UserMyTransferInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMyTransferInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String totalCount,
			String pageCount, String currentPage,
			List<UserMyTransfer> dataList, String signValue) {
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

	public List<UserMyTransfer> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserMyTransfer> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

}
