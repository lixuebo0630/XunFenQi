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
public class QueryProductListInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String totalCount;// 总行数
	private String pageCount;// 总页数
	private String currentPage;// 当前页
	private List<Product> dataList;
	private String signValue;

	public class Product implements Serializable {
		private String xmmc;// 项目名称
		private String nhsy;// 年华收益
		private String tzqx;// 投资年限
		private String plusRate;// 活动增加利率;
		private String diyu;//地区
		private String proName;
		
		
		public String getProName() {
			return proName;
		}

		public void setProName(String proName) {
			this.proName = proName;
		}

		public String getDiyu() {
			return diyu;
		}

		public void setDiyu(String diyu) {
			this.diyu = diyu;
		}

		private String xmjd;// 项目进度
		private String xmbj;// 项目本金
		private String qtje;// 起投金额
		private String cpid;// 项目Id
		private String isXsb;// 是否是新手标
		private String pStatus;// 产品状态 1，投资中 2，已满标 3，回款中 4，已结清

		public String getPlusRate() {
			return plusRate;
		}
		
		public void setPlusRate(String plusRate) {
			this.plusRate = plusRate;
		}
		public Product(String xmmc, String nhsy, String tzqx, String xmjd,
				String xmbj, String qtje, String cpid, String isXsb,
				String pStatus) {
			super();
			this.xmmc = xmmc;
			this.nhsy = nhsy;
			this.tzqx = tzqx;
			this.xmjd = xmjd;
			this.xmbj = xmbj;
			this.qtje = qtje;
			this.cpid = cpid;
			this.isXsb = isXsb;
			this.pStatus = pStatus;
		}

		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getXmmc() {
			return xmmc;
		}

		public void setXmmc(String xmmc) {
			this.xmmc = xmmc;
		}

		public String getNhsy() {
			return nhsy;
		}

		public void setNhsy(String nhsy) {
			this.nhsy = nhsy;
		}

		public String getTzqx() {
			return tzqx;
		}

		public void setTzqx(String tzqx) {
			this.tzqx = tzqx;
		}

		public String getXmjd() {
			return xmjd;
		}

		public void setXmjd(String xmjd) {
			this.xmjd = xmjd;
		}

		public String getXmbj() {
			return xmbj;
		}

		public void setXmbj(String xmbj) {
			this.xmbj = xmbj;
		}

		public String getQtje() {
			return qtje;
		}

		public void setQtje(String qtje) {
			this.qtje = qtje;
		}

		public String getCpid() {
			return cpid;
		}

		public void setCpid(String cpid) {
			this.cpid = cpid;
		}

		public String getIsXsb() {
			return isXsb;
		}

		public void setIsXsb(String isXsb) {
			this.isXsb = isXsb;
		}

		public String getpStatus() {
			return pStatus;
		}

		public void setpStatus(String pStatus) {
			this.pStatus = pStatus;
		}

		@Override
		public String toString() {
			return "Product [xmmc=" + xmmc + ", nhsy=" + nhsy + ", tzqx="
					+ tzqx + ", xmjd=" + xmjd + ", xmbj=" + xmbj + ", qtje="
					+ qtje + ", cpid=" + cpid + ", isXsb=" + isXsb
					+ ", pStatus=" + pStatus + "]";
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

	public List<Product> getDataList() {
		return dataList;
	}

	public void setDataList(List<Product> dataList) {
		this.dataList = dataList;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	@Override
	public String toString() {
		return "QueryProductListInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", totalCount=" + totalCount + ", pageCount=" + pageCount
				+ ", currentPage=" + currentPage + ", dataList=" + dataList
				+ ", signValue=" + signValue + "]";
	}

}
