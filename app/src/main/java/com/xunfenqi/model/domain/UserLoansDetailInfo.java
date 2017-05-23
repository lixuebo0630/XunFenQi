/**
 * Project Name:HaiHeFinance
 * File Name:UserMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:41:26
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.model.domain;

import java.util.List;


public class UserLoansDetailInfo {
    private String messageType;

    private String respCode;
    private String respCodeDesc;
    private String userId;
    private String cczz;
    private String orderInfo;
    private List<UserLoansDetail> dataList;
    private String loanId;
    private String signValue;
    private String sqzt;
    private String sczt;

    public String getSczt() {
        return sczt;
    }

    public void setSczt(String sczt) {
        this.sczt = sczt;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getCczz() {
        return cczz;
    }

    public void setCczz(String cczz) {
        this.cczz = cczz;
    }

    public class UserLoansDetail {
        private String zt;

        private String qs;
        private String ze;
        private String sxf;
        private String yqje;
        private String hkrq;
        private String sfyq;
        private String yqts;
        private String sfhk;
        private String mxid;
        private String lsh;
        private String orderInfo;

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }

        public String getLsh() {
            return lsh;
        }

        public void setLsh(String lsh) {
            this.lsh = lsh;
        }

        public String getMxid() {
            return mxid;
        }

        public void setMxid(String mxid) {
            this.mxid = mxid;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getQs() {
            return qs;
        }

        public void setQs(String qs) {
            this.qs = qs;
        }

        public String getZe() {
            return ze;
        }

        public void setZe(String ze) {
            this.ze = ze;
        }

        public String getSxf() {
            return sxf;
        }

        public void setSxf(String sxf) {
            this.sxf = sxf;
        }

        public String getYqje() {
            return yqje;
        }

        public void setYqje(String yqje) {
            this.yqje = yqje;
        }

        public String getHkrq() {
            return hkrq;
        }

        public void setHkrq(String hkrq) {
            this.hkrq = hkrq;
        }

        public String getSfyq() {
            return sfyq;
        }

        public void setSfyq(String sfyq) {
            this.sfyq = sfyq;
        }

        public String getYqts() {
            return yqts;
        }

        public void setYqts(String yqts) {
            this.yqts = yqts;
        }

        public String getSfhk() {
            return sfhk;
        }

        public void setSfhk(String sfhk) {
            this.sfhk = sfhk;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserLoansDetail> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserLoansDetail> dataList) {
        this.dataList = dataList;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }
}
