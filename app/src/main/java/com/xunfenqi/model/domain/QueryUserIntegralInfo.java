package com.xunfenqi.model.domain;

import java.util.List;

/**
 * 
* @ClassName: UserLoginInfo 
* @Description: 用户登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:41:38 
*
 */
public class QueryUserIntegralInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String jfze;
	private String mrjf;
	private String userId;
	private String jlsm;

	public String getJlsm() {
		return jlsm;
	}

	public void setJlsm(String jlsm) {
		this.jlsm = jlsm;
	}

	private String signValue;
	private List<QianDaoBean> dataList;


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

	public String getJfze() {
		return jfze;
	}

	public void setJfze(String jfze) {
		this.jfze = jfze;
	}

	public String getMrjf() {
		return mrjf;
	}

	public void setMrjf(String mrjf) {
		this.mrjf = mrjf;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<QianDaoBean> getDataList() {
		return dataList;
	}

	public void setDataList(List<QianDaoBean> dataList) {
		this.dataList = dataList;
	}

	public class QianDaoBean{
		private String qdrq;
		private String sfqd;


		public String getQdrq() {
			return qdrq;
		}

		public void setQdrq(String qdrq) {
			this.qdrq = qdrq;
		}

		public String getSfqd() {
			return sfqd;
		}

		public void setSfqd(String sfqd) {
			this.sfqd = sfqd;
		}
	}

}
