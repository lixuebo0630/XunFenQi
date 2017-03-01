package com.xunfenqi.model.domain;

/**
 * 
 * @ClassName: UserIntoInviteFriendsInfo
 * @Description: 用户进入邀请好友实体类
 * @author Xuebo Li
 * @date 2015-12-16 下午5:50:47
 * 
 */
public class UserIntoInviteFriendsInfo {

	private String messageType;
	private String respCode;
	private String respCodeDesc;
	private String userId;
	private String hygs;
	private String byysyj;
	private String wldsyj;
	private String year;

	public String getWldsyj() {
		return wldsyj;
	}

	public void setWldsyj(String wldsyj) {
		this.wldsyj = wldsyj;
	}

	private String ljysyj;
	private String tjlj;
	private String yqm;
	private String signValue;

	public UserIntoInviteFriendsInfo(String messageType, String respCode,
			String respCodeDesc, String userId, String hygs, String byysyj,
			String ljysyj, String tjlj, String yqm, String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.userId = userId;
		this.hygs = hygs;
		this.byysyj = byysyj;
		this.ljysyj = ljysyj;
		this.tjlj = tjlj;
		this.yqm = yqm;
		this.signValue = signValue;
	}

	public UserIntoInviteFriendsInfo() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getHygs() {
		return hygs;
	}

	public void setHygs(String hygs) {
		this.hygs = hygs;
	}

	public String getByysyj() {
		return byysyj;
	}

	public void setByysyj(String byysyj) {
		this.byysyj = byysyj;
	}

	public String getLjysyj() {
		return ljysyj;
	}

	public void setLjysyj(String ljysyj) {
		this.ljysyj = ljysyj;
	}

	public String getTjlj() {
		return tjlj;
	}

	public void setTjlj(String tjlj) {
		this.tjlj = tjlj;
	}

	public String getYqm() {
		return yqm;
	}

	public void setYqm(String yqm) {
		this.yqm = yqm;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
}
