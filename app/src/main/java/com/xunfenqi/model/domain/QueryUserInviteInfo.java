package com.xunfenqi.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: QueryProductListInfo
 * @Description: 查询产品列表实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:38:58
 * 
 */
public class QueryUserInviteInfo {

	private String messageType;
	private String userId;
	private String respCode;
	private String respCodeDesc;
	private String totalCount;// 总行数
	private String pageCount;// 总页数
	private String currentPage;// 当前页
	private List<UserInvite> dataList;
	private String signValue;

	public class UserInvite implements Serializable {
		private String zcrq;
		private String yhm;//
		private String zcqd;//


		public String getZcrq() {
			return zcrq;
		}

		public void setZcrq(String zcrq) {
			this.zcrq = zcrq;
		}

		public String getYhm() {
			return yhm;
		}

		public void setYhm(String yhm) {
			this.yhm = yhm;
		}

		public String getZcqd() {
			return zcqd;
		}

		public void setZcqd(String zcqd) {
			this.zcqd = zcqd;
		}
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public List<UserInvite> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserInvite> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
