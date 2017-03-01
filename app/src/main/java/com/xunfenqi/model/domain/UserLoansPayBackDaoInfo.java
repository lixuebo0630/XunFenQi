/**
 * Project Name:HaiHeFinance
 * File Name:UserMessageInfo.java
 * Package Name:com.haihefinance.model.domain
 * Date:2015-9-25下午2:18:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.model.domain;

/**
 * @date: 2015-9-25 下午2:18:09
 * @author: XueBo Li
 * @version:
 * @description: 站内消息接口实体
 * @see
 */
public class UserLoansPayBackDaoInfo {

    private String messageType;
    private String respCode;
    private String respCodeDesc;
    private String userId;

    private String hkzt;

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

    public String getHkzt() {
        return hkzt;
    }

    public void setHkzt(String hkzt) {
        this.hkzt = hkzt;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }
}
