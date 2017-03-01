/**
 * Project Name:HaiHeFinance
 * File Name:UserIntoPayment.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:00:08
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.model.domain;

import java.util.List;

/**
 * 
 * @ClassName: UserIntoIndex
 * @Description: 用户进入首页实体
 * @author Xuebo Li
 * @date 2015-10-22 上午11:48:31
 * 
 */
public class UserIntoIndex {

	private String messageType;
	private String respCode;
	private String respCodeDesc;

	private String signValue;
	private List<ImageUrl> lbUrlList;// 轮播图集合
	private List<ImageUrl> lbUrlListNew;// 轮播图集合
	private List<Hhdt> newsList;



	public class ImageUrl {
		private String url;
		private String urlh5;
		private String flag;

		public ImageUrl() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ImageUrl(String url, String urlh5, String flag) {
			super();
			this.url = url;
			this.urlh5 = urlh5;
			this.flag = flag;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUrlh5() {
			return urlh5;
		}

		public void setUrlh5(String urlh5) {
			this.urlh5 = urlh5;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

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



	public class Hhdt {
		private String content;
		private String contentUrl;
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContentUrl() {
			return contentUrl;
		}

		public void setContentUrl(String contentUrl) {
			this.contentUrl = contentUrl;
		}

	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<ImageUrl> getLbUrlList() {
		return lbUrlList;
	}

	public void setLbUrlList(List<ImageUrl> lbUrlList) {
		this.lbUrlList = lbUrlList;
	}

	public List<ImageUrl> getLbUrlListNew() {
		return lbUrlListNew;
	}

	public void setLbUrlListNew(List<ImageUrl> lbUrlListNew) {
		this.lbUrlListNew = lbUrlListNew;
	}

	public List<Hhdt> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Hhdt> newsList) {
		this.newsList = newsList;
	}
}
