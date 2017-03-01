package com.xunfenqi;

import com.xunfenqi.application.MyApplication;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.JsonConstant;
import com.xunfenqi.net.network.DES;
import com.xunfenqi.net.network.NetWorkUtils;
import com.xunfenqi.net.network.RSAUtil;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.Md5Util;

import org.json.JSONObject;


/**
 * |
 *
 * @author Xuebo Li
 * @ClassName:
 * @Description: 封装访问网络参数
 * @date 2015-8-17 上午9:41:17
 */

public class HaiHeApi {

    private static final String TAG = "";

    /**
     * 登录
     *
     * @param inputUsername 用户输入的用户名
     * @param inputPassword 用户输入的密码
     * @param listener
     */
    public static void login(String inputUsername, String inputPassword,
                             AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "userLogin" + inputUsername
                    + Md5Util.md5Diagest(inputPassword)
                    + AbConstant.PHONETYPE;
            AbLogUtil.i(TAG, Md5Util.md5Diagest(inputPassword));
            JSONObject json = new JSONObject();
            json.put("version", AbConstant.VERSION);
            json.put("phoneType", AbConstant.PHONETYPE);
            json.put("messageType", "userLogin");
            json.put("userName", inputUsername);
            json.put("loginPassword",
                    Md5Util.md5Diagest(inputPassword));
            json.put("signValue", RSAUtil.RSAEncodeSign(data));
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录 密码不加密
     *
     * @param inputUsername 用户输入的用户名
     * @param inputPassword 用户输入的密码
     * @param listener
     */
    public static void login1(String inputUsername, String inputPassword,
                              AbSoapListener listener) {
        try {

            String data = AbConstant.VERSION + "userLogin" + inputUsername
                    + inputPassword + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put("version", AbConstant.VERSION);
            json.put("phoneType", AbConstant.PHONETYPE);
            json.put("messageType", "userLogin");
            json.put("userName", inputUsername);
            json.put("loginPassword", inputPassword);

            json.put("signValue", RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @throws
     * @Title: regist
     * @Description: 注册
     * @param: @param inputUsername 用户名
     * @param: @param inputPassword 密码
     * @param: @param userTel 手机号
     * @param: @param inviteCode 邀请码(选填)
     * @param: @param listener
     */
    public static void regist(String inputUsername, String inputPassword,
                              String userTel, String inviteCode, AbSoapListener listener) {
        try {

            String data = AbConstant.VERSION + "userRegister" + inputUsername
                    + Md5Util.md5Diagest(DES.encrypt(inputPassword)) + userTel
                    + AbConstant.PHONETYPE + inviteCode;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.MESSAGETYPE, "userRegister");
            json.put(JsonConstant.USERNAME, inputUsername);
            json.put(JsonConstant.USERTEL, userTel);
            json.put(JsonConstant.INVITECODE, inviteCode);

            json.put(JsonConstant.LOGINPASSWORD,
                    Md5Util.md5Diagest(DES.encrypt(inputPassword)));
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            AbLogUtil.d(TAG, "注册发送的数据:" + json.toString());

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户信息 (个人中心)
     *
     * @param listener
     */
    public static void queryUseInfo(String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "userCenter" + userId
                    + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "userCenter");
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户信息 (个人中心)
     *
     * @param listener
     */
    public static void queryUserInfo2(String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "userCenter" + userId
                    + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "userCenter");
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证用户名是否存在
     *
     * @param inputUsername 用户输入的用户名
     * @param listener
     */
    public static void verifyUsername(String inputUsername,
                                      AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "verifyUsername" + inputUsername
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put("version", AbConstant.VERSION);
            json.put("phoneType", AbConstant.PHONETYPE);
            json.put("messageType", "verifyUsername");
            json.put("userName", inputUsername);
            json.put("signValue", RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证手机号是否存在
     *
     * @param inputNumber 用户输入的手机号
     * @param listener
     */
    public static void verifyUserTel(String inputNumber, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "verifyUserTel" + inputNumber
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "verifyUserTel");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERTEL, inputNumber);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询产品列表
     *
     * @param currentPage 当前页
     * @param listener
     */
    public static void queryProductList(int currentPage, String orderByType,
                                        String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "queryProductListVersionThree"
                    + AbConstant.PHONETYPE + currentPage + ""
                    + AbConstant.PAGESIZE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put("orderByType", orderByType);
            json.put(JsonConstant.MESSAGETYPE, "queryProductListVersionThree");
            json.put(JsonConstant.PRODUCTTYPE, "");
            json.put(JsonConstant.STARTPAGE, currentPage + "");
            json.put(JsonConstant.PAGESIZE, AbConstant.PAGESIZE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码
     *
     * @param userTel    用户手机号码
     * @param verifyType 验证码类型
     * @param listener
     */
    public static void getVerifyNumber(String userTel, String verifyType,
                                       AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "sendSmsModel"
                    + AbConstant.PHONETYPE + userTel + verifyType;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "sendSmsModel");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERTEL, userTel);
            json.put(JsonConstant.VERIFYTYPE, verifyType);
            json.put(JsonConstant.BORROWPEOPLE, "");
            json.put(JsonConstant.BMPHONE, "");
            json.put(JsonConstant.AREA, "");

            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询产品详细
     *
     * @param cpid     产品ID
     * @param listener
     */
    public static void queryProductDetail(String userId, String cpid,
                                          AbSoapListener listener) {
        try {

            String data = AbConstant.VERSION + "queryProductDetail" + cpid
                    + userId + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "queryProductDetail");
            json.put(JsonConstant.PRODUCTID, cpid);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 产品购买
     *
     * @param productId   产品id
     * @param userId      用户id
     * @param paySum      购买金额
     * @param payPassword 支付密码
     * @param sendSmsCode 验证码
     * @param listener
     */
    public static void productPay(String productId, String userId,
                                  String paySum, String payPassword, String yhqId,
                                  String sendSmsCode, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "productPayVersionTwo" + paySum
                    + payPassword + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "productPayVersionTwo");
            json.put(JsonConstant.PRODUCTID, productId);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PAYSUM, paySum);
            json.put("fingerprint", "");
            json.put("couponsId", yhqId);
            json.put(JsonConstant.PAYPASSWORD, payPassword);
            json.put(JsonConstant.SENDSMSCODE, sendSmsCode);
            // // TODO 后期要改
            // json.put(JsonConstant.JKXY, "111");
            //
            // SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            // String htbh = df.format(new Date());
            // json.put(JsonConstant.HTBH, htbh);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void productPayTyb(String productId, String userId,
                                     String paySum, String payPassword, String yhqId,
                                     String sendSmsCode, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "productPayTyb" + paySum
                    + payPassword + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "productPayTyb");
            json.put(JsonConstant.PRODUCTID, productId);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PAYSUM, paySum);
            json.put("fingerprint", "");
            json.put("couponsId", yhqId);
            json.put(JsonConstant.PAYPASSWORD, payPassword);
            json.put(JsonConstant.SENDSMSCODE, sendSmsCode);
            // // TODO 后期要改
            // json.put(JsonConstant.JKXY, "111");
            //
            // SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            // String htbh = df.format(new Date());
            // json.put(JsonConstant.HTBH, htbh);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userInvestment
     * @Description: 我的投资信息接口
     * @param: @param userId
     * @param: @param startPage 起始页
     * @param: @param pageSize 每页条数
     * @param: @param imStatus 投资状态 空是查询所有 0为投资中 1为收益中 3 为完成收款
     */
    public static void userInvestment(String userId, String startPage,
                                      String pageSize, String imStatus, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userInvestment" + userId
                    + startPage + pageSize + imStatus + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userInvestment");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.IM_STATUS, imStatus);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userInvestmentEarnings
     * @Description: 投资收益信息/回款计划明细
     * @param: @param userId 用户ID
     * @param: @param investmentId 投资理财ID
     * @param: @param listener
     */
    public static void userInvestmentEarnings(String userId,
                                              String investmentId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userInvestmentEarnings"
                    + userId + investmentId + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userInvestmentEarnings");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.INVESTMENT_ID, investmentId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userIncomeSatement
     * @Description: 查询收支明细接口
     * @param: @param userId 用户id
     * @param: @param startPage 起始页
     * @param: @param pageSize 每页条数
     * @param: @param IncomeType 收支类型 空: 查询全部 0:充值 1:提现 2:回款 5:投资 7:红包奖励
     * @param: @param listener
     */
    public static void userIncomeSatement(String userId, String startPage,
                                          String pageSize, String IncomeType, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userIncomeSatement" + userId
                    + startPage + pageSize + IncomeType + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIncomeSatement");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.INCOME_TYPE, IncomeType);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userRechargeRecord
     * @Description: 充值明细接口
     * @param: @param userId
     * @param: @param startPage
     * @param: @param pageSize
     * @param: @param rechargeStatus 充值状态 空 查询全部 0:未支付 1:已支付
     * @param: @param listener
     */
    public static void userRechargeRecord(String userId, String startPage,
                                          String pageSize, String rechargeStatus, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userRechargeRecord" + userId
                    + startPage + pageSize + rechargeStatus
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userRechargeRecord");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.RECHARGE_STATUS, rechargeStatus);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userPresentRecord
     * @Description: 提现明细接口
     * @param: @param userId
     * @param: @param startPage
     * @param: @param pageSize
     * @param: @param presentStatus
     * @param: @param listener
     */
    public static void userPresentRecord(String userId, String startPage,
                                         String pageSize, String presentStatus, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userPresentRecord" + userId
                    + startPage + pageSize + presentStatus
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userPresentRecord");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PRESENT_STATUS, presentStatus);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userIntoPayment
     * @Description: 查询回款计划年月列表信息接口
     * @param: @param userId
     * @param: @param listener
     */
    public static void userIntoPayment(String userId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userIntoPayment" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoPayment");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userIntoPaymentByYM
     * @Description: 查询回款计划年月列表信息接口
     * @param: @param userId
     * @param: @param tzyy 投资年月 年月格式:2015-05 (选填)
     * @param: @param listener
     */
    public static void userIntoPaymentByYM(String userId, String tzyy,
                                           AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userIntoPaymentByYM" + userId
                    + tzyy + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoPaymentByYM");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.TZYY, tzyy);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userIntoPaymentByYM
     * @Description: 查询回款计划年月列表信息接口(带日历)
     * @param: @param userId
     * @param: @param tzyy 投资年月 年月格式:2015-05 (选填)
     * @param: @param listener
     */
    public static void userIntoPaymentByYMNew(String userId, String tzyy,
                                              AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userIntoPaymentByYMNew"
                    + userId + tzyy + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoPaymentByYMNew");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.TZYY, tzyy);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userMyRed
     * @Description: 查询我的红包列表信息
     * @param: @param userId
     * @param: @param lqzt 领取状态
     * @param: @param startPage
     * @param: @param pageSize
     * @param: @param listener
     */
    public static void userMyRed(String userId, String lqzt, String startPage,
                                 String pageSize, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userMyRed" + userId + startPage
                    + pageSize + lqzt + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyRed");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.LQZT, lqzt);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void userLoans(String userId, String startPage,
                                 String pageSize, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userLoans" + userId + startPage + pageSize + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userLoans");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void queryNews(String startPage,
                                 String pageSize, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "queryNews" + startPage + pageSize + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "queryNews");
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void userLoansDetail(String userId, String loanId
            , AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userLoansDetail" + userId + loanId + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userLoansDetail");
            json.put(JsonConstant.USERID, userId);
            json.put("loanId", loanId);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userGetMyRed
     * @Description: 领取红包接口
     * @param: @param userId
     * @param: @param redBagId 红包id
     * @param: @param listener
     */
    public static void userGetMyRed(String userId, String redBagId,
                                    AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userGetMyRed" + userId
                    + redBagId + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userGetMyRed");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.RED_BAG_ID, redBagId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void userLoansPayBackDao(String userId, String loanId, String payPw, String payBackSum,
                                           AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userLoansPayBackDao" + userId+loanId+payBackSum
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userLoansPayBackDao");
            json.put(JsonConstant.USERID, userId);
            json.put("payPw", payPw);
            json.put("payBackSum", payBackSum);
            json.put("loanId", loanId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));


            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userMessage
     * @Description: 消息列表的接口
     * @param: @param userId
     * @param: @param startPage
     * @param: @param pageSize
     * @param: @param listener
     */
    public static void userMessage(String userId, String startPage,
                                   String pageSize, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userMessage" + userId
                    + startPage + pageSize + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMessage");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userMessageDetail
     * @Description: 查看消息详细
     * @param: @param userId
     * @param: @param messageId 消息ID
     * @param: @param flagType 标记类型 1全部已读 2查看信息
     * @param: @param listener
     */
    public static void userMessageDetail(String userId, String messageId,
                                         String flagType, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userMessageDetail" + userId
                    + flagType + AbConstant.PHONETYPE;
            AbLogUtil.d(TAG, data);
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMessageDetail");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGE_ID, messageId);
            json.put(JsonConstant.FLAG_TYPE, flagType);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
            AbLogUtil.d(TAG, json.toString());
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userSecurityInfomation
     * @Description: 查询个人资料接口
     * @param: @param userId
     * @param: @param listener
     */
    public static void userSecurityInfomation(String userId,
                                              AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userSecurityInfomation"
                    + userId + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userSecurityInfomation");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userSecurityInfomationEdit
     * @Description: 修改个人资料接口
     * @param: @param userId
     * @param: @param zgxl 最高学历
     * @param: @param byyx 毕业院校
     * @param: @param hyzk 婚姻状况
     * @param: @param sshy 所属行业
     * @param: @param srfw 年收入范围
     * @param: @param lxmc 联系人名称
     * @param: @param gx 与您关系
     * @param: @param lxfs 联系方式
     * @param: @param listener
     */
    public static void userSecurityInfomationEdit(String userId, String zgxl,
                                                  String byyx, String hyzk, String sshy, String srfw, String lxmc,
                                                  String gx, String lxfs, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userSecurityInfomationEdit"
                    + userId + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userSecurityInfomationEdit");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.ZGXL, zgxl);
            json.put(JsonConstant.BYYX, byyx);
            json.put(JsonConstant.HYZK, hyzk);
            json.put(JsonConstant.SSHY, sshy);
            json.put(JsonConstant.SRFW, srfw);
            json.put(JsonConstant.LXMC, lxmc);
            json.put(JsonConstant.GX, gx);
            json.put(JsonConstant.LXFS, lxfs);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userSecuritySafe
     * @Description: 查看安全信息接口
     * @param: @param userId
     * @param: @param listener
     */
    public static void userSecuritySafe(String userId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userSecuritySafe" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userSecuritySafe");
            json.put(JsonConstant.USERID, userId);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @throws
     * @Title: userSecuritySafe
     * @Description: 修改登录密码
     * @param: @param userId
     * @param: @param listener
     */
    public static void userEditPassword(String userId, String oldPassword,
                                        String newPassword, AbSoapListener listener) {

        try {
            oldPassword = Md5Util.md5Diagest(DES.encrypt(oldPassword));
            newPassword = Md5Util.md5Diagest(DES.encrypt(newPassword));

            String data = AbConstant.VERSION + "userEditPassword" + userId
                    + oldPassword + newPassword + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userEditPassword");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.OLDPASSWORD, oldPassword);
            json.put(JsonConstant.NEWPASSWORD, newPassword);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 设置交易密码
    public static void userEditPayPw(String loginUid, String payPws,
                                     AbSoapListener abSoapListener) {
        try {
            payPws = Md5Util.md5Diagest(DES.encrypt(payPws));

            String data = AbConstant.VERSION + "userEditPayPw" + loginUid
                    + payPws + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userEditPayPw");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.NEWPAYPW, payPws);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 修改交易密码
    public static void userUpdatePayPw(String loginUid, String newPayPw,
                                       String oldPayPw, AbSoapListener abSoapListener) {
        try {
            newPayPw = Md5Util.md5Diagest(DES.encrypt(newPayPw));
            oldPayPw = Md5Util.md5Diagest(DES.encrypt(oldPayPw));

            String data = AbConstant.VERSION + "userModifyPayPw" + loginUid
                    + newPayPw + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userModifyPayPw");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.NEWPAYPW, newPayPw);
            json.put(JsonConstant.OLDPAYPW, oldPayPw);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 修改手机号
    public static void userEditTel(String loginUid, String tel,
                                   AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userEditTel" + loginUid + tel
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userEditTel");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.USERTEL, tel);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户进入提现页面
    public static void userIntoEnchashment(String loginUid,
                                           AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userIntoEnchashmentTwo"
                    + loginUid + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoEnchashmentTwo");
            json.put(JsonConstant.USERID, loginUid);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户提现
    public static void userEnchashmentSumTwo(String loginUid, String txje1,
                                             String payPws, String branchBankName, String proName,
                                             String proCode, String cityCode, String cityName,
                                             AbSoapListener abSoapListener) {
        try {
            payPws = Md5Util.md5Diagest(DES.encrypt(payPws));
            String data = AbConstant.VERSION + "userEnchashmentSumTwo"
                    + loginUid + txje1 + payPws + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userEnchashmentSumTwo");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.PAYPWS, payPws);
            json.put(JsonConstant.TXJE, txje1);
            json.put(JsonConstant.ZHMC, branchBankName);
            json.put("proName", proName);
            json.put("proCode", proCode);
            json.put("cityName", cityName);
            json.put("cityCode", cityCode);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userIntoIndexTwo(String userId,
                                        AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "intoIndex"
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "intoIndex");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 冻结金额
    public static void userFrozenAmount(String loginUid, String startPage,
                                        String pageSize, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userFrozenAmount" + loginUid
                    + startPage + pageSize + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userFrozenAmount");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//	// 充值接口
//	public static void rechargeRzAndCz(PayOrder order,
//			AbSoapListener abSoapListener) {
//		try {
//			if (order != null) {
//
//				String data = AbConstant.VERSION + "rechargeRzAndCz"
//						+ AbConstant.PHONETYPE + order.getUser_id()
//						+ order.getNo_order();
//				JSONObject json = new JSONObject();
//				json.put(JsonConstant.VERSION, AbConstant.VERSION);
//				json.put(JsonConstant.MESSAGETYPE, "rechargeRzAndCz");
//				json.put(JsonConstant.USERID, order.getUser_id());
//				json.put(JsonConstant.MONEY, order.getMoney_order());
//				json.put(JsonConstant.SFZH, order.getId_no());
//				json.put(JsonConstant.ZSXM, order.getAcct_name());
//				json.put("ddh", order.getNo_order());
//				json.put(JsonConstant.YHK, order.getCard_no());
//				json.put(JsonConstant.NO_AGREE, order.getNo_agree());
//				json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
//				json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
//				NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
//						abSoapListener);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    // 用户进入充值接口
    public static void userIntoRecharge(String userid,
                                        AbSoapListener abSoapListener) {
        try {

            String data = AbConstant.VERSION + "userIntoRecharge" + userid
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoRecharge");
            json.put(JsonConstant.USERID, userid);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 电子合同
    public static void userViewDzht(String cpid, String userId,
                                    AbSoapListener abSoapListener) {
        try {

            String data = AbConstant.VERSION + "userViewDzht" + cpid + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userViewDzht");
            json.put(JsonConstant.USERID, userId);
            json.put("dzhtId", cpid);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 语音验证码
    public static void sendAudioSms(String userTel, String verifyType,
                                    AbSoapListener abSoapListener) {
        try {

            String data = AbConstant.VERSION + "sendAudioSms"
                    + AbConstant.PHONETYPE + userTel + verifyType;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "sendAudioSms");
            json.put("userTel", userTel);
            json.put("verifyType", verifyType);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 找回密码2步
    public static void userSetPassword(String userTel, String newPassword,
                                       AbSoapListener abSoapListener) {
        try {
            newPassword = Md5Util.md5Diagest(DES.encrypt(newPassword));
            String data = AbConstant.VERSION + "userSetPassword" + userTel
                    + newPassword + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userSetPassword");
            json.put("userTel", userTel);
            json.put("newPassword", newPassword);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 意见反馈
    public static void userFeedback(String userId, String contact,
                                    String content, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userFeedback"
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userFeedback");
            json.put("userId", userId);
            json.put("contact", contact);
            json.put("content", content);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 债权转让
    public static void userMyTransfer(String userId, String transferType,
                                      String startPage, String pageSize, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyTransfer" + userId
                    + transferType + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyTransfer");
            json.put(JsonConstant.USERID, userId);
            json.put("transferType", transferType);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 礼券
    public static void userMyYhq(String loginUid, String type,
                                 String startPage, String pageSize, AbSoapListener abSoapListener) {

        try {
            String data = AbConstant.VERSION + "userMyYhq" + loginUid
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyYhq");
            json.put(JsonConstant.USERID, loginUid);
            json.put("yhqStatus", type);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 撤销债权转让
    public static void userMyTransferCx(String userId, String transferId,
                                        AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyTransferCx"
                    + AbConstant.PHONETYPE + userId + transferId;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyTransferCx");
            json.put(JsonConstant.USERID, userId);
            json.put("transferId", transferId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入申请转让
    public static void userMyTransferSqzr(String userId, String transferId,
                                          AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyTransferSqzr"
                    + AbConstant.PHONETYPE + userId + transferId;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyTransferSqzr");
            json.put(JsonConstant.USERID, userId);
            json.put("transferId", transferId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 提交申请转让
    public static void userMyTransferZr(String userId, String transferId,
                                        String sum, String payPassword, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyTransferZr"
                    + AbConstant.PHONETYPE + userId + transferId + sum;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyTransferZr");
            json.put(JsonConstant.USERID, userId);
            json.put("fId", transferId);
            json.put("transferSum", sum);
            json.put("payPassword",
                    Md5Util.md5Diagest(DES.encrypt(payPassword)));
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 我的好友
    public static void userMyFriends(String userId, String startPage,
                                     String pageSize, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyFriends" + userId
                    + startPage + pageSize + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyFriends");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入邀请好友界面
    public static void userIntoInviteFriends(String userId,
                                             AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userIntoInviteFriends"
                    + AbConstant.PHONETYPE + userId;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userIntoInviteFriends");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 债权转让列表
    public static void queryProductList4zhaiquan(int currentPage,
                                                 String orderByType, String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "userTransferListTwo"
                    + currentPage + "" + AbConstant.PAGESIZE
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put("orderByType", orderByType);
            json.put(JsonConstant.MESSAGETYPE, "userTransferListTwo");
            json.put(JsonConstant.PRODUCTTYPE, "");
            json.put(JsonConstant.STARTPAGE, currentPage + "");
            json.put(JsonConstant.PAGESIZE, AbConstant.PAGESIZE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 我的佣金
    public static void userMyIncome(String loginUid, String year,
                                    AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyIncome" + loginUid
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyIncome");
            json.put(JsonConstant.USERID, loginUid);
            json.put("year", year);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 我的佣金
    public static void userMyIncomeVersionTwo(String loginUid, String year,
                                              AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userMyIncomeVersionTwo"
                    + loginUid + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userMyIncomeVersionTwo");
            json.put(JsonConstant.USERID, loginUid);
            json.put("year", year);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询债权转让产品详细
     * <p>
     * 产品ID
     *
     * @param listener
     */
    public static void queryProduct4zhaiquanDetail(String userId, String pId,
                                                   AbSoapListener listener) {
        try {

            String data = AbConstant.VERSION + "userTransferDetail"
                    + AbConstant.PHONETYPE + pId;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userTransferDetail");
            json.put("pId", pId);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 购买债权
    public static void userTransferPay(String userId, String payPws, int money,
                                       String productId, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userTransferPay"
                    + AbConstant.PHONETYPE + userId + productId;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userTransferPay");
            json.put("productId", productId);
            json.put(JsonConstant.USERID, userId);
            json.put("payPassword", payPws);
            json.put("principalSum", money);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 编辑分支行名称
    public static void userEditZhmcTwo(String userId, String yhkh, String zhmc,
                                       String proName, String proCode, String cityName, String cityCode,
                                       AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userEditZhmcTwo" + userId
                    + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userEditZhmcTwo");
            json.put(JsonConstant.USERID, userId);
            json.put("yhkh", yhkh);
            json.put("zhmc", zhmc);
            json.put("proName", proName);
            json.put("proCode", proCode);
            json.put("cityName", cityName);
            json.put("cityCode", cityCode);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入账户总资产
    public static void userAssetDetail(String userId,
                                       AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "userAssetDetail"
                    + AbConstant.PHONETYPE + userId;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "userAssetDetail");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入活期账户
    public static void currentUserCenter(String userId,
                                         AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentUserCenter" + userId
                    + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserCenter");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入普通转活期账户
    public static void currentInitOrdinaryToCurrent(String userId,
                                                    AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentInitOrdinaryToCurrent"
                    + userId + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentInitOrdinaryToCurrent");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 确认普通转活期账户
    public static void currentOrdinaryToCurrent(String userId, String puzcje,
                                                String payPw, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentOrdinaryToCurrent"
                    + userId + puzcje + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();
            payPw = Md5Util.md5Diagest(DES.encrypt(payPw));

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentOrdinaryToCurrent");
            json.put(JsonConstant.USERID, userId);
            json.put("puzcje", puzcje);
            json.put("payPw", payPw);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 进入活期转普户
    public static void currentToOrdinaryPager(String userId,
                                              AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentToOrdinaryPager"
                    + userId + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentToOrdinaryPager");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 确认活期转普通账户
    public static void currentToOrdinaryBegin(String userId, String hqzcje,
                                              String payPw, AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentToOrdinaryBegin"
                    + userId + hqzcje + AbConstant.PHONETYPE;
            JSONObject json = new JSONObject();
            payPw = Md5Util.md5Diagest(DES.encrypt(payPw));

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentToOrdinaryBegin");
            json.put(JsonConstant.USERID, userId);
            json.put("hqzcje", hqzcje);
            json.put("payPw", payPw);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户进入活期提现页面
    public static void currentUserIntoEnchashment(String loginUid,
                                                  AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentUserIntoEnchashment"
                    + loginUid + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserIntoEnchashment");
            json.put(JsonConstant.USERID, loginUid);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户活期体现
    public static void currentUserEnchashmentSum(String loginUid, String txje1,
                                                 String payPws, String branchBankName, String proName,
                                                 String proCode, String cityCode, String cityName,
                                                 AbSoapListener abSoapListener) {
        try {
            payPws = Md5Util.md5Diagest(DES.encrypt(payPws));
            String data = AbConstant.VERSION + "currentUserEnchashmentSum"
                    + loginUid + txje1 + payPws + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserEnchashmentSum");
            json.put(JsonConstant.USERID, loginUid);
            json.put(JsonConstant.PAYPWS, payPws);
            json.put(JsonConstant.TXJE, txje1);
            json.put("branchBankName", branchBankName);
            json.put("proName", proName);
            json.put("proCode", proCode);
            json.put("cityName", cityName);
            json.put("cityCode", cityCode);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
            AbLogUtil.d(TAG, json.toString());
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户进入活期理财页面
    public static void currentinitCurrentDetail(String loginUid,
                                                AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentinitCurrentDetail"
                    + loginUid + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentinitCurrentDetail");
            json.put(JsonConstant.USERID, loginUid);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户进入活期赎回页面
    public static void currentIntoRedeem(String loginUid,
                                         AbSoapListener abSoapListener) {
        try {
            String data = AbConstant.VERSION + "currentIntoRedeem" + loginUid
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentIntoRedeem");
            json.put(JsonConstant.USERID, loginUid);

            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    abSoapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 活期提现记录
    public static void currentUserPresentRecord(String userId,
                                                String startPage, String pageSize, String presentStatus,
                                                AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "currentUserPresentRecord"
                    + userId + startPage + pageSize + presentStatus
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserPresentRecord");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put(JsonConstant.PRESENT_STATUS, presentStatus);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 活期赎回
    public static void currentSubmitRedeem(String userId, String shje,
                                           String payPw, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "currentSubmitRedeem" + userId
                    + shje + AbConstant.PHONETYPE;
            payPw = Md5Util.md5Diagest(DES.encrypt(payPw));
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentSubmitRedeem");
            json.put(JsonConstant.USERID, userId);
            json.put("shje", shje);
            json.put("payPw", payPw);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 活期购买
    public static void currentByCurrent(String userId, String tzje,
                                        String rate, String payPw, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "currentByCurrent" + userId
                    + tzje + rate + AbConstant.PHONETYPE;
            payPw = Md5Util.md5Diagest(DES.encrypt(payPw));
            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentByCurrent");
            json.put(JsonConstant.USERID, userId);
            json.put("tzje", tzje);
            json.put("payPw", payPw);
            json.put("rate", rate);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));
            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void currentUserDayDetail(String userId, String startPage,
                                            String pageSize, String IncomeType, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "currentUserDayDetail" + userId
                    + startPage + pageSize + IncomeType + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserDayDetail");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put("type", IncomeType);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void currentUserIncomeSatement(String userId,
                                                 String startPage, String pageSize, String IncomeType,
                                                 AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "currentUserIncomeSatement"
                    + userId + startPage + pageSize + IncomeType
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "currentUserIncomeSatement");
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.STARTPAGE, startPage);
            json.put(JsonConstant.PAGESIZE, pageSize);
            json.put("incomeType", IncomeType);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSplashImg(AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "startPage"
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.MESSAGETYPE, "startPage");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void queryUserIntegral(String userId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "queryUserIntegral" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "queryUserIntegral");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void userIntegralSignIn(String userId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "userIntegralSignIn" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "userIntegralSignIn");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询个人利率
     *
     * @param userId
     * @param listener
     */
    public static void queryUserRate(String userId, AbSoapListener listener) {

        try {
            String data = AbConstant.VERSION + "queryUserRate" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();
            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "queryUserRate");
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 查询产品列表
     *
     * @param currentPage 当前页
     * @param listener
     */
    public static void queryUserInvite(int currentPage,
                                       String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "queryUserInvite" + userId + currentPage + AbConstant.PAGESIZE
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "queryUserInvite");
            json.put(JsonConstant.STARTPAGE, currentPage + "");
            json.put(JsonConstant.PAGESIZE, AbConstant.PAGESIZE);
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 /**
     * 查询产品列表
     *
     * @param listener
     */
    public static void userMonthLoansDetailDao(
                                       String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "userMonthLoansDetailDao" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);
            json.put(JsonConstant.MESSAGETYPE, "userMonthLoansDetailDao");
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询产品列表
     *
     * @param listener
     */
    public static void applyLoan(String loanType, String loanDeadline, String piriodUint,
                                 String loanSum, String loanExplain, String trueMoney,
                                 String loanCost, String loanHanding,
                                 String userId, AbSoapListener listener) {
        try {
            String data = AbConstant.VERSION + "applyLoan" + userId
                    + AbConstant.PHONETYPE;

            JSONObject json = new JSONObject();

            json.put(JsonConstant.VERSION, AbConstant.VERSION);
            json.put(JsonConstant.PHONETYPE, AbConstant.PHONETYPE);
            json.put(JsonConstant.USERID, userId);


            json.put("loanType", loanType);
            json.put("loanDeadline", loanDeadline);
            json.put("piriodUint", piriodUint);
            json.put("loanSum", loanSum);
            json.put("loanExplain", loanExplain);
            json.put("trueMoney", trueMoney);
            json.put("loanCost", loanCost);
            json.put("loanHanding", loanHanding);
            json.put(JsonConstant.MESSAGETYPE, "applyLoan");
            json.put(JsonConstant.SIGNVALUE, RSAUtil.RSAEncodeSign(data));

            NetWorkUtils.call(MyApplication.getInstance(), json.toString(),
                    listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
