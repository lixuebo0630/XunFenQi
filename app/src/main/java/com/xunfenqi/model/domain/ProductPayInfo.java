package com.xunfenqi.model.domain;

/**
 * 
* @ClassName: ProductPayInfo 
* @Description: 购买产品实体类
* @author Xuebo Li
* @date 2015-8-14 下午3:38:26 
*
 */
public class ProductPayInfo {

	private String messageType;

	private String respCode;

	private String respCodeDesc;

	private String productId;

	private String userId;

	private String paySum;
	
	private String yqsy;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaySum() {
		return paySum;
	}

	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
	

	public String getYqsy() {
		return yqsy;
	}

	public void setYqsy(String yqsy) {
		this.yqsy = yqsy;
	}

	public ProductPayInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductPayInfo(String messageType, String respCode,
			String respCodeDesc, String productId, String userId,
			String paySum, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.productId = productId;
		this.userId = userId;
		this.paySum = paySum;
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "ProductPayInfo [messageType=" + messageType + ", respCode="
				+ respCode + ", respCodeDesc=" + respCodeDesc + ", productId="
				+ productId + ", userId=" + userId + ", paySum=" + paySum
				+ ", signValue=" + signValue + "]";
	}

}
