/**
 * Project Name:HaiHeFinance
 * File Name:UserMessageInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-25下午2:18:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;


/**
 * @date: 2015-9-25 下午2:18:09
 * @author: XueBo Li
 * @version:
 * @description: 站内消息接口实体
 * @see
 */
public class UserMessageDetailInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String userId;

	private String flagType; // 1全部已读 2查看信息

	private String signValue;

	private List<UserMessageDetail> dataList;

	public class UserMessageDetail {
		private String xxlx; // 消息类型
		private String xxbt; // 消息标题

		private String xxnr; // 消息内容
		private String fssj; // 发送时间
		private String ckzt; // 查看状态
		private String xxid; // 消息ID

		public UserMessageDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserMessageDetail(String xxlx, String xxbt, String xxnr,
				String fssj, String ckzt, String xxid) {
			super();
			this.xxlx = xxlx;
			this.xxbt = xxbt;
			this.xxnr = xxnr;
			this.fssj = fssj;
			this.ckzt = ckzt;
			this.xxid = xxid;
		}

		public String getXxlx() {
			return xxlx;
		}

		public void setXxlx(String xxlx) {
			this.xxlx = xxlx;
		}

		public String getXxbt() {
			return xxbt;
		}

		public void setXxbt(String xxbt) {
			this.xxbt = xxbt;
		}

		public String getXxnr() {
			return xxnr;
		}

		public void setXxnr(String xxnr) {
			this.xxnr = xxnr;
		}

		public String getFssj() {
			return fssj;
		}

		public void setFssj(String fssj) {
			this.fssj = fssj;
		}

		public String getCkzt() {
			return ckzt;
		}

		public void setCkzt(String ckzt) {
			this.ckzt = ckzt;
		}

		public String getXxid() {
			return xxid;
		}

		public void setXxid(String xxid) {
			this.xxid = xxid;
		}

		@Override
		public String toString() {
			return "UserMessageDetail [xxlx=" + xxlx + ", xxbt=" + xxbt
					+ ", xxnr=" + xxnr + ", fssj=" + fssj + ", ckzt=" + ckzt
					+ ", xxid=" + xxid + "]";
		}

	}

	public UserMessageDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMessageDetailInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String flagType,
			String signValue, List<UserMessageDetail> dataList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.flagType = flagType;
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

	public String getFlagType() {
		return flagType;
	}

	public void setFlagType(String flagType) {
		this.flagType = flagType;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<UserMessageDetail> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserMessageDetail> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "UserMessageDetailInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", flagType=" + flagType
				+ ", signValue=" + signValue + ", dataList=" + dataList + "]";
	}

}
