package com.xunfenqi.model.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @ClassName: QueryProductDetailInfo
 * @Description: 查询产品详细实体类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:38:41
 * 
 */
public class QueryProductDetailInfo {

	private String messageType;

	private String respCode;
	private String respCodeDesc;
	private String kyed;// 可用额度
	private String tzxe;// 投资限额
	private String cpxmjd;// 产品项目进度
	private String diyu;
	private String nhll;// 年化利率
	private String xmje;// 项目金额
	private String syje;// 项目金额
	private String sfkzz;// 是否可转债 1为可转
	private String zztj;// 转债条件
	private String xmmc;// 项目名称
	private String sysj;// 剩余时间
	private String proName;
	private String qtje;
	private String xsb;
	private String yqsy;

	public String getYqsy() {
		return yqsy;
	}

	public void setYqsy(String yqsy) {
		this.yqsy = yqsy;
	}

	public String getXsb() {
		return xsb;
	}

	public void setXsb(String xsb) {
		this.xsb = xsb;
	}

	public String getQtje() {
		return qtje;
	}

	public void setQtje(String qtje) {
		this.qtje = qtje;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSysj() {
		return sysj;
	}

	public void setSysj(String sysj) {
		this.sysj = sysj;
	}

	public String getDiyu() {
		return diyu;
	}

	public void setDiyu(String diyu) {
		this.diyu = diyu;
	}

	private String xmqx;// 项目期限
	private String plusRate;
	private String otherRate;
	private String pStatus;// 项目状态 1，投资中 2，已满标 3，回款中 4，已结清
	private String qtjeWai;
	private String productId;
	private String isXinshou;

	private String signValue;

	private List<ProductDetail> touziList;

	private List<Enterprise> enterpriseList;

	private ArrayList<CouponInfo.Coupon> myYhqList;

	private List<Loanee> loaneeList;

	public class ProductDetail {

		private String tzje;// 投资金额

		private String tzrq;// 投资日期

		private String tzr;// 投资人

		public String getTzje() {
			return tzje;
		}

		public void setTzje(String tzje) {
			this.tzje = tzje;
		}

		public String getTzrq() {
			return tzrq;
		}

		public void setTzrq(String tzrq) {
			this.tzrq = tzrq;
		}

		public String getTzr() {
			return tzr;
		}

		public void setTzr(String tzr) {
			this.tzr = tzr;
		}

		public ProductDetail(String tzje, String tzrq, String tzr) {
			super();
			this.tzje = tzje;
			this.tzrq = tzrq;
			this.tzr = tzr;
		}

		public ProductDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "ProductDetail [tzje=" + tzje + ", tzrq=" + tzrq + ", tzr="
					+ tzr + "]";
		}

	}

	public QueryProductDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryProductDetailInfo(String messageType, String respCode,
			String respCodeDesc, String kyed, String tzxe, String cpxmjd,
			String nhll, String xmje, String syje, String sfkzz, String zztj,
			String xmmc, String xmqx, String pStatus, String signValue,
			List<ProductDetail> touziList, List<Enterprise> enterpriseList,
			List<Loanee> loaneeList) {
		super();
		this.messageType = messageType;
		this.respCode = respCode;
		this.respCodeDesc = respCodeDesc;
		this.kyed = kyed;
		this.tzxe = tzxe;
		this.cpxmjd = cpxmjd;
		this.nhll = nhll;
		this.xmje = xmje;
		this.syje = syje;
		this.sfkzz = sfkzz;
		this.zztj = zztj;
		this.xmmc = xmmc;
		this.xmqx = xmqx;
		this.pStatus = pStatus;
		this.signValue = signValue;
		this.touziList = touziList;
		this.enterpriseList = enterpriseList;
		this.loaneeList = loaneeList;
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

	public String getPlusRate() {
		return plusRate;
	}

	public void setPlusRate(String plusRate) {
		this.plusRate = plusRate;
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

	public String getKyed() {
		return kyed;
	}

	public void setKyed(String kyed) {
		this.kyed = kyed;
	}

	public String getTzxe() {
		return tzxe;
	}

	public void setTzxe(String tzxe) {
		this.tzxe = tzxe;
	}

	public String getCpxmjd() {
		return cpxmjd;
	}

	public void setCpxmjd(String cpxmjd) {
		this.cpxmjd = cpxmjd;
	}

	public String getNhll() {
		return nhll;
	}

	public void setNhll(String nhll) {
		this.nhll = nhll;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
	}

	public String getSfkzz() {
		return sfkzz;
	}

	public void setSfkzz(String sfkzz) {
		this.sfkzz = sfkzz;
	}

	public String getZztj() {
		return zztj;
	}

	public void setZztj(String zztj) {
		this.zztj = zztj;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmqx() {
		return xmqx;
	}

	public void setXmqx(String xmqx) {
		this.xmqx = xmqx;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public List<ProductDetail> getTouziList() {
		return touziList;
	}

	public void setTouziList(List<ProductDetail> touziList) {
		this.touziList = touziList;
	}

	public List<Enterprise> getEnterpriseList() {
		return enterpriseList;
	}

	public void setEnterpriseList(List<Enterprise> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}

	public List<Loanee> getLoaneeList() {
		return loaneeList;
	}

	public void setLoaneeList(List<Loanee> loaneeList) {
		this.loaneeList = loaneeList;
	}

	public String getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(String otherRate) {
		this.otherRate = otherRate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQtjeWai() {
		return qtjeWai;
	}

	public void setQtjeWai(String qtjeWai) {
		this.qtjeWai = qtjeWai;
	}

	public String getIsXinshou() {
		return isXinshou;
	}

	public void setIsXinshou(String isXinshou) {
		this.isXinshou = isXinshou;
	}

	public ArrayList<CouponInfo.Coupon> getMyYhqList() {
		return myYhqList;
	}

	public void setMyYhqList(ArrayList<CouponInfo.Coupon> myYhqList) {
		this.myYhqList = myYhqList;
	}

	@Override
	public String toString() {
		return "QueryProductDetailInfo [messageType=" + messageType
				+ ", respCode=" + respCode + ", respCodeDesc=" + respCodeDesc
				+ ", kyed=" + kyed + ", tzxe=" + tzxe + ", cpxmjd=" + cpxmjd
				+ ", nhll=" + nhll + ", xmje=" + xmje + ", syje=" + syje
				+ ", sfkzz=" + sfkzz + ", zztj=" + zztj + ", xmmc=" + xmmc
				+ ", xmqx=" + xmqx + ", pStatus=" + pStatus + ", signValue="
				+ signValue + ", touziList=" + touziList + ", enterpriseList="
				+ enterpriseList + ", loaneeList=" + loaneeList + "]";
	}

}
