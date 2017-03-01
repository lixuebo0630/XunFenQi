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
public class QueryProductList4zhaiquanInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String totalCount;// 总行数
	private String pageCount;// 总页数
	private String currentPage;// 当前页
	private List<Product4zhaiquan> dataList;
	private String signValue;

	public class Product4zhaiquan implements Serializable {
		private String id;
		private String xmmc;
		private String nhll;
		private String zrje;
		private  String xmqx;
		private String xmjd;
		private String sysj;
		private String zrzt;
		private String diyu;
		private String proName;
		
		public String getDiyu() {
			return diyu;
		}
		public void setDiyu(String diyu) {
			this.diyu = diyu;
		}
		public String getProName() {
			return proName;
		}
		public void setProName(String proName) {
			this.proName = proName;
		}
		public Product4zhaiquan() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Product4zhaiquan(String id, String xmmc, String nhll,
				String zrje, String xmqx, String xmjd, String sysj, String zrzt) {
			super();
			this.id = id;
			this.xmmc = xmmc;
			this.nhll = nhll;
			this.zrje = zrje;
			this.xmqx = xmqx;
			this.xmjd = xmjd;
			this.sysj = sysj;
			this.zrzt = zrzt;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getXmmc() {
			return xmmc;
		}
		public void setXmmc(String xmmc) {
			this.xmmc = xmmc;
		}
		public String getNhll() {
			return nhll;
		}
		public void setNhll(String nhll) {
			this.nhll = nhll;
		}
		public String getZrje() {
			return zrje;
		}
		public void setZrje(String zrje) {
			this.zrje = zrje;
		}
		public String getXmqx() {
			return xmqx;
		}
		public void setXmqx(String xmqx) {
			this.xmqx = xmqx;
		}
		public String getXmjd() {
			return xmjd;
		}
		public void setXmjd(String xmjd) {
			this.xmjd = xmjd;
		}
		public String getSysj() {
			return sysj;
		}
		public void setSysj(String sysj) {
			this.sysj = sysj;
		}
		public String getZrzt() {
			return zrzt;
		}
		public void setZrzt(String zrzt) {
			this.zrzt = zrzt;
		}


	}

	public QueryProductList4zhaiquanInfo() {
		super();
	}

	public QueryProductList4zhaiquanInfo(String messageType, String respCode,
			String respCodeDesc, String totalCount, String pageCount,
			String currentPage, List<Product4zhaiquan> dataList,
			String signValue) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.dataList = dataList;
		this.signValue = signValue;
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

	public List<Product4zhaiquan> getDataList() {
		return dataList;
	}

	public void setDataList(List<Product4zhaiquan> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

}
