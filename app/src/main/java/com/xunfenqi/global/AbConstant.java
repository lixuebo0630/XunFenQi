package com.xunfenqi.global;

/**
 * @author Xuebo Li
 * @ClassName: AbConstant
 * @Description: 常量
 * @date 2015-8-14 下午2:44:32
 */
public class AbConstant {
    // DeBug
    public static final boolean DEBUG = false;
    public static final String URL = "http://60.205.156.131:8081/app_webservice_shopping/webservice/publicRequest?wsdl";

    // SOAP命名空间
    public static final String NAMESPACE = "http://service.app.shopping.com/";
    public static final String REFRESH_MYACCOUNT_ACTION = "android.com.haihefinance.broadcastreceiver.action.MYACTION";
    // public static final String BASE_URL =
    // "http://192.168.12.198:8889/app_webservice";
    public static final String BASE_URL = "http://223.202.60.29:80/app_webservice";
    public static final String code = "语音短信已发送，请注意接听来电";
    // SOAP调用的方法
    public static final String METHODNAME = "publicRequest";
    // 服务器版本
    public static final String VERSION = "10";
    // 客户端类型
    public static final String PHONETYPE = "001";
    // 异常:验证签名失败
    public static final String VERIFY_FAIL = "";
    public static final String JSON_ERROR = "数据错误,请重试";
    // 页面大小
    public static final String PAGESIZE = "9";
    public static final String PW_NINELOCK = "PW_NINELOCK";
    public static final String NINE_WRONG_COUNT = "NINE_WRONG_COUNT";

    public static final String MY_ACCOUNT_REFRESH = "my_account_refresh";
    public static final String SAFE_SETTING_REFRESH = "safe_setting_refresh";
    public static final String JIEKUAN_DETAIL_REFRESH = "jiekuan_refresh";
    // */ 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态
    public static final int POINT_STATE_SELECTED = 1; // 按下状态
    public static final int POINT_STATE_WRONG = 2; // 错误状态
    public static final String BANK_CARD_NUM = "bank_card_num"; // 银行卡号
    public static final String LIUSHUI_NUM = "liushui_num"; // 流水号
    public static final String RECHARGE_MONEY = "recharge_money"; // 充值金额
}
