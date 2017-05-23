/**
 * Project Name:HaiHeFinance
 * File Name:UserMyRedInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-8-31下午5:41:26
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.model.domain;

public class UserUpLoadAuthorInfo {
    private String messageType;

    private String respCode;
    private String respCodeDesc;
    private String userId;

    private String idrzzt;
    private String qtrzzt;
    private String qtzs;

    private String signValue;

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

    public String getIdrzzt() {
        return idrzzt;
    }

    public void setIdrzzt(String idrzzt) {
        this.idrzzt = idrzzt;
    }

    public String getQtrzzt() {
        return qtrzzt;
    }

    public void setQtrzzt(String qtrzzt) {
        this.qtrzzt = qtrzzt;
    }

    public String getQtzs() {
        return qtzs;
    }

    public void setQtzs(String qtzs) {
        this.qtzs = qtzs;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
