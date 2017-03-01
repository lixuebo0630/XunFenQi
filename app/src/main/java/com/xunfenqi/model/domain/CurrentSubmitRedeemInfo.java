package com.xunfenqi.model.domain;

/**\
 * 
* @ClassName: CurrentSubmitRedeemInfo 
* @Description: 确认赎回
* @author Xuebo Li
* @date 2016-4-20 下午2:30:02 
*
 */
public class CurrentSubmitRedeemInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;

	private String userId;
	private String shje;
	private String signValue;

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

	public String getShje() {
		return shje;
	}

	public void setShje(String shje) {
		this.shje = shje;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "CurrentSubmitRedeemInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", userId=" + userId + ", shje=" + shje + ", signValue="
				+ signValue + "]";
	}

}
