package com.xunfenqi.model.domain;

import java.util.List;

/**
 * 
 * @ClassName: LoginInfo
 * @Description: 登录信息实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:37:40
 * 
 */

public class UserMyFriendsInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String signValue;

	private String userId;
	private String totalCount;
	private String pageCount;
	private String currentPage;
	private List<OneLevel> dataList;

	public UserMyFriendsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public UserMyFriendsInfo(String messageType, String respCode,
			String respCodeDesc, String signValue, String userId,
			String totalCount, String pageCount, String currentPage,
			List<OneLevel> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.signValue = signValue;
		this.userId = userId;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
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

	public List<OneLevel> getDataList() {
		return dataList;
	}

	public void setDataList(List<OneLevel> dataList) {
		this.dataList = dataList;
	}

	public class OneLevel {
		private String regDatetime;
		private String loginName;
		private String userName;
		private String zcqd;
		private List<TwoLevel> children;

		public OneLevel() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OneLevel(String regDatetime, String loginName, String userName,
				String zcqd, List<TwoLevel> children) {
			super();
			this.regDatetime = regDatetime;
			this.loginName = loginName;
			this.userName = userName;
			this.zcqd = zcqd;
			this.children = children;
		}

		public String getRegDatetime() {
			return regDatetime;
		}

		public void setRegDatetime(String regDatetime) {
			this.regDatetime = regDatetime;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getZcqd() {
			return zcqd;
		}

		public void setZcqd(String zcqd) {
			this.zcqd = zcqd;
		}

		public List<TwoLevel> getChildren() {
			return children;
		}

		public void setChildren(List<TwoLevel> children) {
			this.children = children;
		}
	}

	public class TwoLevel {
		private String regDatetime;
		private String loginName;
		private String userName;
		private String zcqd;

		public TwoLevel(String regDatetime, String loginName, String userName,
				String zcqd) {
			super();
			this.regDatetime = regDatetime;
			this.loginName = loginName;
			this.userName = userName;
			this.zcqd = zcqd;
		}

		public TwoLevel() {
			super();
		}

		public String getRegDatetime() {
			return regDatetime;
		}

		public void setRegDatetime(String regDatetime) {
			this.regDatetime = regDatetime;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getZcqd() {
			return zcqd;
		}

		public void setZcqd(String zcqd) {
			this.zcqd = zcqd;
		}
	}
}
