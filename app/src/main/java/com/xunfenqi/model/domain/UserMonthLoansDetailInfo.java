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
public class UserMonthLoansDetailInfo {

	private String messageType;
	private String userId;
	private String respCode;
	private String respCodeDesc;
	private String zje;// 总行数
	private String hkrq;// 总页数
	private String bs;// 当前页
	private String hkrqr;
	private String hkrqy;
	private List<UserMonthLoansDetail> dataList;
	private String signValue;

	public class UserMonthLoansDetail implements Serializable {
		private String lsh;
		private String je;
		private String fqid;
		private String jkid;
		private String qs;
		private String zt;

		public String getZt() {
			return zt;
		}

		public void setZt(String zt) {
			this.zt = zt;
		}

		public String getHkrqr() {
			return hkrqr;
		}

		public String getJkid() {
			return jkid;
		}

		public void setJkid(String jkid) {
			this.jkid = jkid;
		}

		public String getLsh() {
			return lsh;
		}

		public void setLsh(String lsh) {
			this.lsh = lsh;
		}

		public String getJe() {
			return je;
		}

		public void setJe(String je) {
			this.je = je;
		}

		public String getFqid() {
			return fqid;
		}

		public void setFqid(String fqid) {
			this.fqid = fqid;
		}

		public String getQs() {
			return qs;
		}

		public void setQs(String qs) {
			this.qs = qs;
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

	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}

	public String getHkrq() {
		return hkrq;
	}

	public void setHkrq(String hkrq) {
		this.hkrq = hkrq;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public List<UserMonthLoansDetail> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserMonthLoansDetail> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}


	public String getHkrqr() {
		return hkrqr;
	}

	public void setHkrqr(String hkrqr) {
		this.hkrqr = hkrqr;
	}

	public String getHkrqy() {
		return hkrqy;
	}

	public void setHkrqy(String hkrqy) {
		this.hkrqy = hkrqy;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
}
