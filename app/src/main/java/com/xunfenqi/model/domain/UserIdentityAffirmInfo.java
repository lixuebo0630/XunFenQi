package com.xunfenqi.model.domain;

/**
 * @author Xuebo Li
 * @ClassName: UserCenterInfo
 * @Description: 用户个人中心信息实体类
 * @date 2015-8-14 下午3:39:39
 */
public class UserIdentityAffirmInfo {

    private String messageType;
    private String respCode;
    private String respCodeDesc;
    private String userId;
    private String zzcc;//0成功 1失败

    private String signValue;

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

    public String getZzcc() {
        return zzcc;
    }

    public void setZzcc(String zzcc) {
        this.zzcc = zzcc;
    }
}
