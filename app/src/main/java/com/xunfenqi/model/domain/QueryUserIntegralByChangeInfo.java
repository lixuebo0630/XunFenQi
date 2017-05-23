package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: UserLoginInfo 
* @Description: 用户登录信息实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:41:38 
*
 */
public class QueryUserIntegralByChangeInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String kyjf;
	private String kdhjf;
	private String userId;
	private String dhje;
	private String jlsm;


	public String getJlsm() {
		return jlsm;
	}

	public void setJlsm(String jlsm) {
		this.jlsm = jlsm;
	}

	private String signValue;

	public String getKyjf() {
		return kyjf;
	}

	public void setKyjf(String kyjf) {
		this.kyjf = kyjf;
	}

	public String getKdhjf() {
		return kdhjf;
	}

	public void setKdhjf(String kdhjf) {
		this.kdhjf = kdhjf;
	}

	public String getDhje() {
		return dhje;
	}

	public void setDhje(String dhje) {
		this.dhje = dhje;
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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}


}
