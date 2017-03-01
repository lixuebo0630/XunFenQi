package com.xunfenqi;


import com.xunfenqi.application.MyApplication;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.ApplyLoanInfo;
import com.xunfenqi.model.domain.AudioSmsInfo;
import com.xunfenqi.model.domain.CouponInfo;
import com.xunfenqi.model.domain.CurrentByCurrentInfo;
import com.xunfenqi.model.domain.CurrentInitOrdinaryToCurrentInfo;
import com.xunfenqi.model.domain.CurrentIntoRedeemInfo;
import com.xunfenqi.model.domain.CurrentOrdinaryToCurrentInfo;
import com.xunfenqi.model.domain.CurrentSubmitRedeemInfo;
import com.xunfenqi.model.domain.CurrentToOrdinaryBeginInfo;
import com.xunfenqi.model.domain.CurrentToOrdinaryPagerInfo;
import com.xunfenqi.model.domain.CurrentUserCenterDao;
import com.xunfenqi.model.domain.CurrentUserDayDetailInfo;
import com.xunfenqi.model.domain.CurrentUserEnchashmentSumInfo;
import com.xunfenqi.model.domain.CurrentUserIncomeSatementInfo;
import com.xunfenqi.model.domain.CurrentUserIntoEnchashmentInfo;
import com.xunfenqi.model.domain.CurrentUserPresentRecordInfo;
import com.xunfenqi.model.domain.CurrentinitCurrentDetailInfo;
import com.xunfenqi.model.domain.FreezeMoneyInfo;
import com.xunfenqi.model.domain.HetongInfo;
import com.xunfenqi.model.domain.LoginInfo;
import com.xunfenqi.model.domain.MyCommissionInfo;
import com.xunfenqi.model.domain.ProductPayInfo;
import com.xunfenqi.model.domain.QueryNewsInfo;
import com.xunfenqi.model.domain.QueryProductDetailInfo;
import com.xunfenqi.model.domain.QueryProductList4zhaiquanInfo;
import com.xunfenqi.model.domain.QueryProductListInfo;
import com.xunfenqi.model.domain.QueryUserIntegralInfo;
import com.xunfenqi.model.domain.QueryUserInviteInfo;
import com.xunfenqi.model.domain.QueryUserRateInfo;
import com.xunfenqi.model.domain.RechargeRzAndCzInfo;
import com.xunfenqi.model.domain.RegistInfo;
import com.xunfenqi.model.domain.RemarkInfo;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.model.domain.SplashImg;
import com.xunfenqi.model.domain.TransferZrInfo;
import com.xunfenqi.model.domain.UserAssetDetailInfo;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.model.domain.UserEditPasswordInfo;
import com.xunfenqi.model.domain.UserEditPayPwInfo;
import com.xunfenqi.model.domain.UserEditTelInfo;
import com.xunfenqi.model.domain.UserEditZhmcInfo;
import com.xunfenqi.model.domain.UserGetMyRedInfo;
import com.xunfenqi.model.domain.UserIncomeSatementInfo;
import com.xunfenqi.model.domain.UserIntegralSignInInfo;
import com.xunfenqi.model.domain.UserIntoEnchashment;
import com.xunfenqi.model.domain.UserIntoIndex;
import com.xunfenqi.model.domain.UserIntoInviteFriendsInfo;
import com.xunfenqi.model.domain.UserIntoPaymentByYMNewInfo;
import com.xunfenqi.model.domain.UserIntoPaymentInfo;
import com.xunfenqi.model.domain.UserIntoRechargeInfo;
import com.xunfenqi.model.domain.UserInvestmentEarningsInfo;
import com.xunfenqi.model.domain.UserInvestmentInfo;
import com.xunfenqi.model.domain.UserLoansDetailInfo;
import com.xunfenqi.model.domain.UserLoansPayBackDaoInfo;
import com.xunfenqi.model.domain.UserMessageDetailInfo;
import com.xunfenqi.model.domain.UserMessageInfo;
import com.xunfenqi.model.domain.UserMonthLoansDetailInfo;
import com.xunfenqi.model.domain.UserMyFriendsInfo;
import com.xunfenqi.model.domain.UserMyJiekuanInfo;
import com.xunfenqi.model.domain.UserMyRedInfo;
import com.xunfenqi.model.domain.UserMyTransferCxInfo;
import com.xunfenqi.model.domain.UserMyTransferInfo;
import com.xunfenqi.model.domain.UserMyTransferSqzrInfo;
import com.xunfenqi.model.domain.UserPresentRecordInfo;
import com.xunfenqi.model.domain.UserRechargeRecordInfo;
import com.xunfenqi.model.domain.UserSecurityInfomationEditInfo;
import com.xunfenqi.model.domain.UserSecurityInfomationInfo;
import com.xunfenqi.model.domain.UserSecuritySafeInfo;
import com.xunfenqi.model.domain.UserTransferDetailInfo;
import com.xunfenqi.model.domain.VerifyUserTelInfo;
import com.xunfenqi.model.domain.VerifyUsernameInfo;
import com.xunfenqi.net.network.RSAUtil;
import com.xunfenqi.utils.AbJsonUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;


public class HaiheReturnApi {

    private static final String TAG = "HaiheReturnApi";

    /**
     * @param @param  content
     * @param @return
     * @return LoginInfo
     * @throws
     * @throws
     * @Title: loginReturn
     * @Description: TODO 登录接口的返回数据处理
     */
    public static LoginInfo loginReturn(String content) {
        AbLogUtil.d(TAG, "登录接口返回的数据:" + content);

        LoginInfo loginInfo = (LoginInfo) AbJsonUtil.fromJson(content,
                LoginInfo.class);
        if (loginInfo != null) {

            String verifyData = loginInfo.getMessageType()
                    + loginInfo.getRespCode() + loginInfo.getUserName()
                    + loginInfo.getUserId();

            boolean result = RSAUtil.verifyResult(verifyData,
                    loginInfo.getSignValue());

            if (result) {
                return loginInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return RegistInfo
     * @throws
     * @Title: registReturn
     * @Description: 注册接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static RegistInfo registReturn(String content) {
        AbLogUtil.d(TAG, "注册返回的数据:" + content);

        RegistInfo registInfo = (RegistInfo) AbJsonUtil.fromJson(content,
                RegistInfo.class);
        if (registInfo != null) {

            String verifyData = registInfo.getMessageType()
                    + registInfo.getRespCode() + registInfo.getUserName()
                    + registInfo.getUserId();

            boolean result = RSAUtil.verifyResult(verifyData,
                    registInfo.getSignValue());

            if (result) {
                return registInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param @param  content
     * @param @return
     * @return UserCenterInfo
     * @throws
     * @Title: queryUserReturn
     * @Description: TODO 查询个人中心接口的返回数据处理
     */
    public static UserCenterInfo queryUserRetur(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询用户信息返回的数据:" + content);

        UserCenterInfo userCenterInfo = (UserCenterInfo) AbJsonUtil.fromJson(
                content, UserCenterInfo.class);

        if (userCenterInfo != null) {


            // 需要验证的数据
            String verifyData = userCenterInfo.getMessageType()
                    + userCenterInfo.getRespCode()
                    + userCenterInfo.getLoginName()
                    + userCenterInfo.getUserId() + userCenterInfo.getTel();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    userCenterInfo.getSignValue());

            if (verifyResult) {
                return userCenterInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param @param  content
     * @param @return
     * @return UserCenterInfo
     * @throws
     * @Title: queryUserReturn
     * @Description: TODO 查询个人中心接口的返回数据处理
     */
    public static UserCenterInfo queryUserReturn2(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询用户信息返回的数据:" + content);

        UserCenterInfo userCenterInfo = (UserCenterInfo) AbJsonUtil.fromJson(
                content, UserCenterInfo.class);

        if (userCenterInfo != null) {
            // 需要验证的数据
            String verifyData = userCenterInfo.getMessageType()
                    + userCenterInfo.getRespCode() + userCenterInfo.getLoginName() + userCenterInfo.getUserId()
                    + userCenterInfo.getTel();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    userCenterInfo.getSignValue());

            if (verifyResult) {
                return userCenterInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param @param  content
     * @param @return
     * @return VerifyUsernameInfo
     * @throws
     * @Title: verifyUsernameReturn
     * @Description: TODO 验证用户名是否存在的返回数据处理
     */
    public static VerifyUsernameInfo verifyUsernameReturn(String content) {
        AbLogUtil.d(TAG, "验证用户名是否存在返回的数据:" + content);

        VerifyUsernameInfo verifyUsernameInfo = (VerifyUsernameInfo) AbJsonUtil
                .fromJson(content, VerifyUsernameInfo.class);

        if (verifyUsernameInfo != null) {

            String verifyData = verifyUsernameInfo.getMessageType()
                    + verifyUsernameInfo.getRespCode()
                    + verifyUsernameInfo.getUserName()
                    + verifyUsernameInfo.getIsExist();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    verifyUsernameInfo.getSignValue());

            if (verifyResult) {
                return verifyUsernameInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param @param  content
     * @param @return
     * @return VerifyUserTelInfo
     * @throws
     * @Title: verifyUserTelReturn
     * @Description: TODO 验证手机号是否存在接口返回数据处理
     */
    public static VerifyUserTelInfo verifyUserTelReturn(String content) {
        AbLogUtil.d(TAG, "验证手机号是否存在返回的数据:" + content);

        VerifyUserTelInfo verifyUserTelInfo = (VerifyUserTelInfo) AbJsonUtil
                .fromJson(content, VerifyUserTelInfo.class);

        if (verifyUserTelInfo != null) {
            // 需要验签的数据
            String verifyData = verifyUserTelInfo.getMessageType()
                    + verifyUserTelInfo.getRespCode()
                    + verifyUserTelInfo.getUserTel()
                    + verifyUserTelInfo.getIsExist();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    verifyUserTelInfo.getSignValue());

            if (verifyResult) {
                return verifyUserTelInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param
     * @return SendSmsModelInfo
     * @throws
     * @Title: sendSmsModelReturn
     * @Description: 发送验证码接口返回的数据处理
     */
    public static SendSmsModelInfo sendSmsModelReturn(String content) {
        AbLogUtil.d(TAG, "发送验证码返回的数据:" + content);
        // 转为对象
        SendSmsModelInfo sendSmsModelInfo = (SendSmsModelInfo) AbJsonUtil
                .fromJson(content, SendSmsModelInfo.class);

        if (sendSmsModelInfo != null) {

            String verifyData = sendSmsModelInfo.getMessageType()
                    + sendSmsModelInfo.getRespCode()
                    + sendSmsModelInfo.getUserTel()
                    + sendSmsModelInfo.getVerifyType()
                    + sendSmsModelInfo.getVerifyCode();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    sendSmsModelInfo.getSignValue());
            if (verifyResult) {
                return sendSmsModelInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @param @param  content
     * @param @return
     * @return QueryProductDetailInfo
     * @throws
     * @Title: queryProductDetailReturn
     * @Description: TODO 查询产品详细接口返回数据处理
     */
    public static QueryProductDetailInfo queryProductDetailReturn(String content) {
        AbLogUtil.d(TAG, "查询产品详细返回的数据" + content);

        QueryProductDetailInfo queryProductDetailInfo = (QueryProductDetailInfo) AbJsonUtil
                .fromJson(content, QueryProductDetailInfo.class);

        if (queryProductDetailInfo != null) {

            String verifyData = queryProductDetailInfo.getMessageType()
                    + queryProductDetailInfo.getRespCode()
                    + queryProductDetailInfo.getKyed()
                    + queryProductDetailInfo.getTzxe()
                    + queryProductDetailInfo.getCpxmjd();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    queryProductDetailInfo.getSignValue());

            if (verifyResult) {
                return queryProductDetailInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return ProductPayInfo
     * @throws
     * @Title: productPayReturn
     * @Description: 购买产品接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static ProductPayInfo productPayReturn(String content) {
        AbLogUtil.d(TAG, "购买产品返回的数据" + content);

        ProductPayInfo productPayInfo = (ProductPayInfo) AbJsonUtil.fromJson(
                content, ProductPayInfo.class);

        if (productPayInfo != null) {

            String verifyData = productPayInfo.getMessageType()
                    + productPayInfo.getRespCode()
                    + productPayInfo.getProductId()
                    + productPayInfo.getUserId() + productPayInfo.getPaySum();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    productPayInfo.getSignValue());

            if (verifyResult) {
                return productPayInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return QueryProductListInfo
     * @throws
     * @Title: queryProductListReturn
     * @Description: 查询产品列表接口数据处理
     * @param: @param content
     * @param: @return
     */
    public static QueryProductListInfo queryProductListReturn(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询产品列表返回的数据:" + content);
        QueryProductListInfo productListInfo = (QueryProductListInfo) AbJsonUtil
                .fromJson(content, QueryProductListInfo.class);

        if (productListInfo != null) {

            String data = productListInfo.getMessageType()
                    + productListInfo.getRespCode()
                    + productListInfo.getTotalCount()
                    + productListInfo.getPageCount()
                    + productListInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    productListInfo.getSignValue());

            if (verifyResult) {
                return productListInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static QueryUserInviteInfo queryUserInvite(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的邀请列表返回的数据:" + content);
        QueryUserInviteInfo queryUserInviteInfo = (QueryUserInviteInfo) AbJsonUtil
                .fromJson(content, QueryUserInviteInfo.class);

        if (queryUserInviteInfo != null) {

            String data = queryUserInviteInfo.getMessageType()
                    + queryUserInviteInfo.getRespCode() + queryUserInviteInfo.getUserId()
                    + queryUserInviteInfo.getTotalCount()
                    + queryUserInviteInfo.getPageCount()
                    + queryUserInviteInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    queryUserInviteInfo.getSignValue());

            if (verifyResult) {
                return queryUserInviteInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static UserMonthLoansDetailInfo userMonthLoansDetailDao(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的账单列表返回的数据:" + content);
        UserMonthLoansDetailInfo userMonthLoansDetailInfo = (UserMonthLoansDetailInfo) AbJsonUtil
                .fromJson(content, UserMonthLoansDetailInfo.class);

        if (userMonthLoansDetailInfo != null) {

            String data = userMonthLoansDetailInfo.getMessageType()
                    + userMonthLoansDetailInfo.getRespCode() + userMonthLoansDetailInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMonthLoansDetailInfo.getSignValue());

            if (verifyResult) {
                return userMonthLoansDetailInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserInvestmentInfo
     * @throws
     * @Title: userInvestmentInfo
     * @Description: 我的投资信息返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserInvestmentInfo userInvestmentInfo(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "我的投资信息返回的数据:" + content);
        UserInvestmentInfo userInvestmentInfo = (UserInvestmentInfo) AbJsonUtil
                .fromJson(content, UserInvestmentInfo.class);

        if (userInvestmentInfo != null) {

            String data = userInvestmentInfo.getMessageType()
                    + userInvestmentInfo.getRespCode()
                    + userInvestmentInfo.getUserId()
                    + userInvestmentInfo.getTotalCount()
                    + userInvestmentInfo.getPageCount()
                    + userInvestmentInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userInvestmentInfo.getSignValue());

            if (verifyResult) {
                return userInvestmentInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserInvestmentEarningsInfo
     * @throws
     * @Title: userInvestmentEarningsInfo
     * @Description: 投资收益信息/回款计划明细接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserInvestmentEarningsInfo userInvestmentEarningsInfo(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "投资收益信息/回款计划明细接口返回的数据:"
                + content);
        UserInvestmentEarningsInfo userInvestmentEarningsInfo = (UserInvestmentEarningsInfo) AbJsonUtil
                .fromJson(content, UserInvestmentEarningsInfo.class);

        if (userInvestmentEarningsInfo != null) {

            String data = userInvestmentEarningsInfo.getMessageType()
                    + userInvestmentEarningsInfo.getRespCode()
                    + userInvestmentEarningsInfo.getUserId()
                    + userInvestmentEarningsInfo.getInvestmentId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userInvestmentEarningsInfo.getSignValue());

            if (verifyResult) {
                return userInvestmentEarningsInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserIncomeSatementInfo
     * @throws
     * @Title: userIncomeSatementInfo
     * @Description: 查询收支明细接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserIncomeSatementInfo userIncomeSatementInfo(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询收支明细接口返回的数据:" + content);
        UserIncomeSatementInfo userIncomeSatementInfo = (UserIncomeSatementInfo) AbJsonUtil
                .fromJson(content, UserIncomeSatementInfo.class);
        if (userIncomeSatementInfo != null) {

            String data = userIncomeSatementInfo.getMessageType()
                    + userIncomeSatementInfo.getRespCode()
                    + userIncomeSatementInfo.getUserId()
                    + userIncomeSatementInfo.getTotalCount()
                    + userIncomeSatementInfo.getPageCount()
                    + userIncomeSatementInfo.getCurrentPage()
                    + userIncomeSatementInfo.getIncomeType();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userIncomeSatementInfo.getSignValue());

            if (verifyResult) {
                return userIncomeSatementInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserRechargeRecordInfo
     * @throws
     * @Title: userRechargeRecordInfo
     * @Description: 查询充值明细接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserRechargeRecordInfo userRechargeRecordInfo(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询充值明细接口返回的数据:" + content);
        UserRechargeRecordInfo userRechargeRecordInfo = (UserRechargeRecordInfo) AbJsonUtil
                .fromJson(content, UserRechargeRecordInfo.class);
        if (userRechargeRecordInfo != null) {

            String data = userRechargeRecordInfo.getMessageType()
                    + userRechargeRecordInfo.getRespCode()
                    + userRechargeRecordInfo.getUserId()
                    + userRechargeRecordInfo.getTotalCount()
                    + userRechargeRecordInfo.getPageCount()
                    + userRechargeRecordInfo.getCurrentPage()
                    + userRechargeRecordInfo.getRechargeStatus();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userRechargeRecordInfo.getSignValue());

            if (verifyResult) {
                return userRechargeRecordInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserPresentRecordInfo
     * @throws
     * @Title: userPresentRecord
     * @Description: 提现明细接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserPresentRecordInfo userPresentRecord(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "提现明细接口返回的数据:" + content);
        UserPresentRecordInfo userPresentRecordInfo = (UserPresentRecordInfo) AbJsonUtil
                .fromJson(content, UserPresentRecordInfo.class);

        if (userPresentRecordInfo != null) {

            String data = userPresentRecordInfo.getMessageType()
                    + userPresentRecordInfo.getRespCode()
                    + userPresentRecordInfo.getUserId()
                    + userPresentRecordInfo.getTotalCount()
                    + userPresentRecordInfo.getPageCount()
                    + userPresentRecordInfo.getCurrentPage()
                    + userPresentRecordInfo.getPresentStatus();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userPresentRecordInfo.getSignValue());

            if (verifyResult) {
                return userPresentRecordInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserIntoPaymentInfo
     * @throws
     * @Title: userIntoPayment
     * @Description: 查询回款计划年月列表信息返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserIntoPaymentInfo userIntoPayment(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询回款计划年月列表信息的数据:" + content);
        UserIntoPaymentInfo userIntoPaymentInfo = (UserIntoPaymentInfo) AbJsonUtil
                .fromJson(content, UserIntoPaymentInfo.class);
        if (userIntoPaymentInfo != null) {

            String data = userIntoPaymentInfo.getMessageType()
                    + userIntoPaymentInfo.getRespCode()
                    + userIntoPaymentInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userIntoPaymentInfo.getSignValue());

            if (verifyResult) {
                return userIntoPaymentInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserIntoPaymentByYMInfo
     * @throws
     * @Title: userIntoPaymentByYM
     * @Description: 查询回款计划某月列表信息
     * @param: @param content
     * @param: @return
     */
    public static UserIntoPaymentByYMNewInfo userIntoPaymentByYM(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询回款计划某月列表信息的数据:" + content);
        UserIntoPaymentByYMNewInfo userIntoPaymentByYMInfo = (UserIntoPaymentByYMNewInfo) AbJsonUtil
                .fromJson(content, UserIntoPaymentByYMNewInfo.class);

        if (userIntoPaymentByYMInfo != null) {

            String data = userIntoPaymentByYMInfo.getMessageType()
                    + userIntoPaymentByYMInfo.getRespCode()
                    + userIntoPaymentByYMInfo.getUserId()
                    + userIntoPaymentByYMInfo.getTzyy();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userIntoPaymentByYMInfo.getSignValue());

            if (verifyResult) {
                return userIntoPaymentByYMInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserIntoPaymentByYMInfo
     * @throws
     * @Title: userIntoPaymentByYM
     * @Description: 查询回款计划某月列表信息
     * @param: @param content
     * @param: @return
     */
    public static UserIntoPaymentByYMNewInfo userIntoPaymentByYMNew(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询回款计划某月列表信息的数据(带日历):"
                + content);
        UserIntoPaymentByYMNewInfo userIntoPaymentByYMNewInfo = (UserIntoPaymentByYMNewInfo) AbJsonUtil
                .fromJson(content, UserIntoPaymentByYMNewInfo.class);

        if (userIntoPaymentByYMNewInfo != null) {

            String data = userIntoPaymentByYMNewInfo.getMessageType()
                    + userIntoPaymentByYMNewInfo.getRespCode()
                    + userIntoPaymentByYMNewInfo.getUserId()
                    + userIntoPaymentByYMNewInfo.getTzyy();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userIntoPaymentByYMNewInfo.getSignValue());

            if (verifyResult) {
                return userIntoPaymentByYMNewInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserMyRedInfo
     * @throws
     * @Title: userMyRed
     * @Description: 查询我的红包列表信息返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserMyRedInfo userMyRed(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的红包列表信息返回数据:" + content);
        UserMyRedInfo userMyRedInfo = (UserMyRedInfo) AbJsonUtil.fromJson(
                content, UserMyRedInfo.class);
        if (userMyRedInfo != null) {

            String data = userMyRedInfo.getMessageType()
                    + userMyRedInfo.getRespCode() + userMyRedInfo.getUserId()
                    + userMyRedInfo.getTotalCount()
                    + userMyRedInfo.getPageCount()
                    + userMyRedInfo.getCurrentPage() + userMyRedInfo.getLqzt();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMyRedInfo.getSignValue());

            if (verifyResult) {
                return userMyRedInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    public static UserMyJiekuanInfo userLoans(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的借款列表信息返回数据:" + content);
        UserMyJiekuanInfo userMyJiekuanInfo = (UserMyJiekuanInfo) AbJsonUtil.fromJson(
                content, UserMyJiekuanInfo.class);
        if (userMyJiekuanInfo != null) {

            String data = userMyJiekuanInfo.getMessageType()
                    + userMyJiekuanInfo.getRespCode() + userMyJiekuanInfo.getUserId()
                    + userMyJiekuanInfo.getTotalCount()
                    + userMyJiekuanInfo.getPageCount()
                    + userMyJiekuanInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMyJiekuanInfo.getSignValue());

            if (verifyResult) {
                return userMyJiekuanInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }


    public static QueryNewsInfo queryNews(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询新闻列表信息返回数据:" + content);
        QueryNewsInfo queryNewsInfo = (QueryNewsInfo) AbJsonUtil.fromJson(
                content, QueryNewsInfo.class);
        if (queryNewsInfo != null) {

            String data = queryNewsInfo.getMessageType()
                    + queryNewsInfo.getRespCode()
                    + queryNewsInfo.getTotalCount()
                    + queryNewsInfo.getPageCount()
                    + queryNewsInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    queryNewsInfo.getSignValue());

            if (verifyResult) {
                return queryNewsInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    public static UserLoansDetailInfo userLoansDetail(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询借款详细返回数据:" + content);
        UserLoansDetailInfo userLoansDetailInfo = (UserLoansDetailInfo) AbJsonUtil.fromJson(
                content, UserLoansDetailInfo.class);
        if (userLoansDetailInfo != null) {

            String data = userLoansDetailInfo.getMessageType()
                    + userLoansDetailInfo.getRespCode() + userLoansDetailInfo.getUserId()
                    + userLoansDetailInfo.getLoanId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userLoansDetailInfo.getSignValue());

            if (verifyResult) {
                return userLoansDetailInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserGetMyRedInfo
     * @throws
     * @Title: userGetMyRed
     * @Description: 领取红包接口
     * @param: @param content
     * @param: @return
     */
    public static UserGetMyRedInfo userGetMyRed(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "领取红包接口返回数据:" + content);
        UserGetMyRedInfo userGetMyRedInfo = (UserGetMyRedInfo) AbJsonUtil
                .fromJson(content, UserGetMyRedInfo.class);
        if (userGetMyRedInfo != null) {

            String data = userGetMyRedInfo.getMessageType()
                    + userGetMyRedInfo.getRespCode()
                    + userGetMyRedInfo.getUserId()
                    + userGetMyRedInfo.getRedBagId()
                    + userGetMyRedInfo.getLqzt();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userGetMyRedInfo.getSignValue());

            if (verifyResult) {
                return userGetMyRedInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserMessageInfo
     * @throws
     * @Title: userMessage
     * @Description: 站内消息接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserMessageInfo userMessage(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "站内消息接口返回数据:" + content);
        UserMessageInfo userMessageInfo = (UserMessageInfo) AbJsonUtil
                .fromJson(content, UserMessageInfo.class);
        if (userMessageInfo != null) {

            String data = userMessageInfo.getMessageType()
                    + userMessageInfo.getRespCode()
                    + userMessageInfo.getUserId()
                    + userMessageInfo.getTotalCount()
                    + userMessageInfo.getPageCount()
                    + userMessageInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMessageInfo.getSignValue());

            if (verifyResult) {
                return userMessageInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserMessageInfo
     * @throws
     * @Title: userMessage
     * @Description: 站内消息接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserLoansPayBackDaoInfo userLoansPayBackDao(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户还款接口返回数据:" + content);
        UserLoansPayBackDaoInfo userLoansPayBackDaoInfo = (UserLoansPayBackDaoInfo) AbJsonUtil
                .fromJson(content, UserLoansPayBackDaoInfo.class);
        if (userLoansPayBackDaoInfo != null) {

            String data = userLoansPayBackDaoInfo.getMessageType()
                    + userLoansPayBackDaoInfo.getRespCode()
                    + userLoansPayBackDaoInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userLoansPayBackDaoInfo.getSignValue());

            if (verifyResult) {
                return userLoansPayBackDaoInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserMessageDetailInfo
     * @Title: userMessageDetail
     * @Description:查看消息详情返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserMessageDetailInfo userMessageDetail(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查看消息详情返回数据:" + content);
        UserMessageDetailInfo userMessageDetailInfo = (UserMessageDetailInfo) AbJsonUtil
                .fromJson(content, UserMessageDetailInfo.class);
        if (userMessageDetailInfo != null) {

            String data = userMessageDetailInfo.getMessageType()
                    + userMessageDetailInfo.getRespCode()
                    + userMessageDetailInfo.getUserId()
                    + userMessageDetailInfo.getFlagType();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMessageDetailInfo.getSignValue());

            if (verifyResult) {
                return userMessageDetailInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserSecurityInfomationInfo
     * @throws
     * @Title: userSecurityInfomation
     * @Description: 查看个人资料接口数据返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserSecurityInfomationInfo userSecurityInfomation(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查看个人资料返回数据:" + content);
        UserSecurityInfomationInfo userSecurityInfomationInfo = (UserSecurityInfomationInfo) AbJsonUtil
                .fromJson(content, UserSecurityInfomationInfo.class);
        if (userSecurityInfomationInfo != null) {

            String data = userSecurityInfomationInfo.getMessageType()
                    + userSecurityInfomationInfo.getRespCode()
                    + userSecurityInfomationInfo.getYhm()
                    + userSecurityInfomationInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userSecurityInfomationInfo.getSignValue());

            if (verifyResult) {
                return userSecurityInfomationInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserSecurityInfomationEditInfo
     * @throws
     * @Title: userSecurityInfomationEdit
     * @Description: 修改个人资料接口返回数据处理
     * @param: @param content
     * @param: @return
     */
    public static UserSecurityInfomationEditInfo userSecurityInfomationEdit(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "修改个人资料返回数据:" + content);
        UserSecurityInfomationEditInfo userSecurityInfomationEditInfo = (UserSecurityInfomationEditInfo) AbJsonUtil
                .fromJson(content, UserSecurityInfomationEditInfo.class);
        if (userSecurityInfomationEditInfo != null) {

            String data = userSecurityInfomationEditInfo.getMessageType()
                    + userSecurityInfomationEditInfo.getRespCode()
                    + userSecurityInfomationEditInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userSecurityInfomationEditInfo.getSignValue());

            if (verifyResult) {
                return userSecurityInfomationEditInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserSecuritySafeInfo
     * @throws
     * @Title: userSecuritySafe
     * @Description: 查看安全中心数据返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserSecuritySafeInfo userSecuritySafe(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查看安全中心信息返回的数据:" + content);
        UserSecuritySafeInfo userSecuritySafeInfo = (UserSecuritySafeInfo) AbJsonUtil
                .fromJson(content, UserSecuritySafeInfo.class);
        if (userSecuritySafeInfo != null) {

            String data = userSecuritySafeInfo.getMessageType()
                    + userSecuritySafeInfo.getRespCode()
                    + userSecuritySafeInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userSecuritySafeInfo.getSignValue());

            if (verifyResult) {
                return userSecuritySafeInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }


    public static QueryUserRateInfo queryUserRate(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查看个人利率返回的数据:" + content);
        QueryUserRateInfo queryUserRateInfo = (QueryUserRateInfo) AbJsonUtil
                .fromJson(content, QueryUserRateInfo.class);
        if (queryUserRateInfo != null) {

            String data = queryUserRateInfo.getMessageType()
                    + queryUserRateInfo.getRespCode()
                    + queryUserRateInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    queryUserRateInfo.getSignValue());

            if (verifyResult) {
                return queryUserRateInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    public static ApplyLoanInfo applyLoan(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "申请借款返回的数据:" + content);
        ApplyLoanInfo applyLoanInfo = (ApplyLoanInfo) AbJsonUtil
                .fromJson(content, ApplyLoanInfo.class);
        if (applyLoanInfo != null) {

            String data = applyLoanInfo.getMessageType()
                    + applyLoanInfo.getRespCode()
                    + applyLoanInfo.getUserId() + applyLoanInfo.getOrderId() + applyLoanInfo.getApplyStatus();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    applyLoanInfo.getSignValue());

            if (verifyResult) {
                return applyLoanInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserSecuritySafeInfo
     * @throws
     * @Title: userSecuritySafe
     * @Description: 查看修改登录密码返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserEditPasswordInfo userEditPassword(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "修改登录密码返回的数据:" + content);
        UserEditPasswordInfo userEditPassword = (UserEditPasswordInfo) AbJsonUtil
                .fromJson(content, UserEditPasswordInfo.class);
        if (userEditPassword != null) {

            String data = userEditPassword.getMessageType()
                    + userEditPassword.getRespCode()
                    + userEditPassword.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userEditPassword.getSignValue());

            if (verifyResult) {
                return userEditPassword;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    /**
     * @return UserSecuritySafeInfo
     * @throws
     * @Title: userSecuritySafe
     * @Description: 查看设置交易密码返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserEditPayPwInfo userEditPayPw(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "设置交易密码返回的数据:" + content);
        UserEditPayPwInfo userEditPayPw = (UserEditPayPwInfo) AbJsonUtil
                .fromJson(content, UserEditPayPwInfo.class);
        if (userEditPayPw != null) {

            String data = userEditPayPw.getMessageType()
                    + userEditPayPw.getRespCode() + userEditPayPw.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userEditPayPw.getSignValue());

            if (verifyResult) {
                return userEditPayPw;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserSecuritySafeInfo
     * @throws
     * @Title: userSecuritySafe
     * @Description: 查看修改交易密码返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserEditPayPwInfo userUpdatePayPw(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "修改交易密码返回的数据:" + content);
        UserEditPayPwInfo userEditPayPw = (UserEditPayPwInfo) AbJsonUtil
                .fromJson(content, UserEditPayPwInfo.class);
        if (userEditPayPw != null) {

            String data = userEditPayPw.getMessageType()
                    + userEditPayPw.getRespCode() + userEditPayPw.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userEditPayPw.getSignValue());

            if (verifyResult) {
                return userEditPayPw;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @return UserSecuritySafeInfo
     * @throws
     * @Title: userSecuritySafe
     * @Description: 修改手机号返回处理
     * @param: @param content
     * @param: @return
     */
    public static UserEditTelInfo userEditTel(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "修改手机号返回的数据:" + content);
        UserEditTelInfo userEditTel = (UserEditTelInfo) AbJsonUtil.fromJson(
                content, UserEditTelInfo.class);
        if (userEditTel != null) {

            String data = userEditTel.getMessageType()
                    + userEditTel.getRespCode() + userEditTel.getUserId()
                    + userEditTel.getUserTel();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userEditTel.getSignValue());

            if (verifyResult) {
                return userEditTel;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户进入提现页面
    public static UserIntoEnchashment userIntoEnchashment(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户进入提现页面返回的数据:" + content);
        UserIntoEnchashment info = (UserIntoEnchashment) AbJsonUtil.fromJson(
                content, UserIntoEnchashment.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getCardlast() + info.getKyye();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户体现
    public static UserIntoEnchashment userEnchashmentSum(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "确认提现页面返回的数据:" + content);
        UserIntoEnchashment info = (UserIntoEnchashment) AbJsonUtil.fromJson(
                content, UserIntoEnchashment.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户体现
    public static UserIntoIndex userIntoIndex(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "首页返回的数据:" + content);
        UserIntoIndex info = (UserIntoIndex) AbJsonUtil.fromJson(content,
                UserIntoIndex.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 冻结金额
    public static FreezeMoneyInfo userFrozenAmount(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询冻结金额信息返回数据:" + content);
        FreezeMoneyInfo freezeMoneyInfo = (FreezeMoneyInfo) AbJsonUtil
                .fromJson(content, FreezeMoneyInfo.class);
        if (freezeMoneyInfo != null) {

            String data = freezeMoneyInfo.getMessageType()
                    + freezeMoneyInfo.getRespCode()
                    + freezeMoneyInfo.getUserId()
                    + freezeMoneyInfo.getTotalCount()
                    + freezeMoneyInfo.getPageCount()
                    + freezeMoneyInfo.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    freezeMoneyInfo.getSignValue());

            if (verifyResult) {
                return freezeMoneyInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户进入充值接口
    public static UserIntoRechargeInfo userIntoRecharge(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户进入充值返回数据:" + content);
        UserIntoRechargeInfo userIntoRechargeInfo = (UserIntoRechargeInfo) AbJsonUtil
                .fromJson(content, UserIntoRechargeInfo.class);
        if (userIntoRechargeInfo != null) {

            String data = userIntoRechargeInfo.getMessageType()
                    + userIntoRechargeInfo.getRespCode()
                    + userIntoRechargeInfo.getUserId()
                    + userIntoRechargeInfo.getCardlast()
                    + userIntoRechargeInfo.getKyye();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userIntoRechargeInfo.getSignValue());

            if (verifyResult) {
                return userIntoRechargeInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户充值接口
    public static RechargeRzAndCzInfo rechargeRzAndCz(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户点击充值访问后台返回数据:" + content);
        RechargeRzAndCzInfo rechargeRzAndCzInfo = (RechargeRzAndCzInfo) AbJsonUtil
                .fromJson(content, RechargeRzAndCzInfo.class);
        if (rechargeRzAndCzInfo != null) {

            String data = rechargeRzAndCzInfo.getMessageType()
                    + rechargeRzAndCzInfo.getRespCode()
                    + rechargeRzAndCzInfo.getDdFlag();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    rechargeRzAndCzInfo.getSignValue());

            if (verifyResult) {
                return rechargeRzAndCzInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 电子合同
    public static HetongInfo userViewDzht(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "电子合同返回数据:" + content);
        HetongInfo hetongInfo = (HetongInfo) AbJsonUtil.fromJson(content,
                HetongInfo.class);
        if (hetongInfo != null) {

            String data = hetongInfo.getMessageType()
                    + hetongInfo.getRespCode();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    hetongInfo.getSignValue());

            if (verifyResult) {
                return hetongInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 语音验证码
    public static AudioSmsInfo sendAudioSms(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "语音验证码返回数据:" + content);
        AudioSmsInfo audioSms = (AudioSmsInfo) AbJsonUtil.fromJson(content,
                AudioSmsInfo.class);
        if (audioSms != null) {

            String data = audioSms.getMessageType() + audioSms.getRespCode()
                    + audioSms.getUserTel() + audioSms.getVerifyType()
                    + audioSms.getVerifyCode();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    audioSms.getSignValue());

            if (verifyResult) {
                return audioSms;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 找回密码2步
    public static LoginInfo userSetPassword(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "找回密码:" + content);
        LoginInfo info = (LoginInfo) AbJsonUtil.fromJson(content,
                LoginInfo.class);
        if (info != null) {
            String data = info.getmessageType() + info.getRespCode()
                    + info.getUserTel();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    // 意见反馈
    public static RemarkInfo userFeedback(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "意见反馈:" + content);
        RemarkInfo info = (RemarkInfo) AbJsonUtil.fromJson(content,
                RemarkInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 我的佣金
    public static MyCommissionInfo userMyIncome(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的佣金息返回数据:" + content);
        MyCommissionInfo info = (MyCommissionInfo) AbJsonUtil.fromJson(content,
                MyCommissionInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 我的优惠券
    public static CouponInfo userMyCoupon(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询我的优惠券列表信息返回数据:" + content);
        CouponInfo couponInfo = (CouponInfo) AbJsonUtil.fromJson(content,
                CouponInfo.class);
        if (couponInfo != null) {

            String data = couponInfo.getMessageType()
                    + couponInfo.getRespCode() + couponInfo.getUserId()
                    + couponInfo.getYhqStatus();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    couponInfo.getSignValue());

            if (verifyResult) {
                return couponInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 我的债权转让
    public static UserMyTransferInfo userMyTransfer(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "我的债权转让:" + content);
        UserMyTransferInfo userMyTransferInfo = (UserMyTransferInfo) AbJsonUtil
                .fromJson(content, UserMyTransferInfo.class);
        if (userMyTransferInfo != null) {

            String data = userMyTransferInfo.getMessageType()
                    + userMyTransferInfo.getRespCode()
                    + userMyTransferInfo.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMyTransferInfo.getSignValue());

            if (verifyResult) {
                return userMyTransferInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 撤销债权转让
    public static UserMyTransferCxInfo userMyTransferCx(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "撤销债权转让:" + content);
        UserMyTransferCxInfo userMyTransferCxInfo = (UserMyTransferCxInfo) AbJsonUtil
                .fromJson(content, UserMyTransferCxInfo.class);
        if (userMyTransferCxInfo != null) {

            String data = userMyTransferCxInfo.getMessageType()
                    + userMyTransferCxInfo.getUserId()
                    + userMyTransferCxInfo.getTransferId();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMyTransferCxInfo.getSignValue());

            if (verifyResult) {
                return userMyTransferCxInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 撤销债权转让
    public static UserMyTransferSqzrInfo userMyTransferSqzr(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "进入申请转让:" + content);
        UserMyTransferSqzrInfo userMyTransferSqzrInfo = (UserMyTransferSqzrInfo) AbJsonUtil
                .fromJson(content, UserMyTransferSqzrInfo.class);
        if (userMyTransferSqzrInfo != null) {

            String data = userMyTransferSqzrInfo.getMessageType()
                    + userMyTransferSqzrInfo.getUserId()
                    + userMyTransferSqzrInfo.getTransferId();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    userMyTransferSqzrInfo.getSignValue());

            if (verifyResult) {
                return userMyTransferSqzrInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 提交债权转让
    public static TransferZrInfo userMyTransferZr(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "提交债权转让:" + content);
        TransferZrInfo info = (TransferZrInfo) AbJsonUtil.fromJson(content,
                TransferZrInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getUserId()
                    + info.getfId();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 我的好友
    public static UserMyFriendsInfo userMyFriends(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "我的好友:" + content);
        UserMyFriendsInfo info = (UserMyFriendsInfo) AbJsonUtil.fromJson(
                content, UserMyFriendsInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getTotalCount()
                    + info.getPageCount() + info.getCurrentPage();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 我的好友
    public static UserIntoInviteFriendsInfo userIntoInviteFriends(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "进入邀请好友:" + content);
        UserIntoInviteFriendsInfo info = (UserIntoInviteFriendsInfo) AbJsonUtil
                .fromJson(content, UserIntoInviteFriendsInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static QueryProductList4zhaiquanInfo queryProductList4zhaiquanReturn(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "查询产品列表债权转让返回的数据:" + content);
        QueryProductList4zhaiquanInfo productListInfo = (QueryProductList4zhaiquanInfo) AbJsonUtil
                .fromJson(content, QueryProductList4zhaiquanInfo.class);

        if (productListInfo != null) {

            String data = productListInfo.getMessageType()
                    + productListInfo.getRespCode()
                    + productListInfo.getTotalCount()
                    + productListInfo.getPageCount();
            boolean verifyResult = RSAUtil.verifyResult(data,
                    productListInfo.getSignValue());

            if (verifyResult) {
                return productListInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    /**
     * @param @param  content
     * @param @return
     * @return QueryProductDetailInfo
     * @throws
     * @Title: queryProductDetailReturn
     * @Description: TODO 查询产品详细接口返回数据处理
     */
    public static UserTransferDetailInfo queryProductDetail4zhaiquanReturn(
            String content) {
        AbLogUtil.d(TAG, "查询债权转让产品详细返回的数据" + content);

        UserTransferDetailInfo userTransferDetailInfo = (UserTransferDetailInfo) AbJsonUtil
                .fromJson(content, UserTransferDetailInfo.class);

        if (userTransferDetailInfo != null) {

            String verifyData = userTransferDetailInfo.getMessageType()
                    + userTransferDetailInfo.getPId();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    userTransferDetailInfo.getSignValue());

            if (verifyResult) {
                return userTransferDetailInfo;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static ProductPayInfo userTransferPay(String content) {
        AbLogUtil.d(TAG, "购买债权转让返回的数据" + content);

        ProductPayInfo info = (ProductPayInfo) AbJsonUtil.fromJson(content,
                ProductPayInfo.class);

        if (info != null) {

            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getProductId();

            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static UserEditZhmcInfo userEditZhmc(String content) {
        AbLogUtil.d(TAG, "修改支行名称返回的数据" + content);

        UserEditZhmcInfo info = (UserEditZhmcInfo) AbJsonUtil.fromJson(content,
                UserEditZhmcInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static UserAssetDetailInfo UserAssetDetail(String content) {
        AbLogUtil.d(TAG, "进入账户总资产返回的数据" + content);

        UserAssetDetailInfo info = (UserAssetDetailInfo) AbJsonUtil.fromJson(
                content, UserAssetDetailInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentUserCenterDao currentUserCenterDao(String content) {
        AbLogUtil.d(TAG, "进入活期账户返回的数据" + content);

        CurrentUserCenterDao info = (com.xunfenqi.model.domain.CurrentUserCenterDao) AbJsonUtil
                .fromJson(content, CurrentUserCenterDao.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getZzc();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentInitOrdinaryToCurrentInfo currentInitOrdinaryToCurrent(
            String content) {
        AbLogUtil.d(TAG, "进入普户转活期页返回的数据" + content);

        CurrentInitOrdinaryToCurrentInfo info = (CurrentInitOrdinaryToCurrentInfo) AbJsonUtil
                .fromJson(content, CurrentInitOrdinaryToCurrentInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentOrdinaryToCurrentInfo currentOrdinaryToCurrent(
            String content) {
        AbLogUtil.d(TAG, "确认普户转活期页返回的数据" + content);

        CurrentOrdinaryToCurrentInfo info = (CurrentOrdinaryToCurrentInfo) AbJsonUtil
                .fromJson(content, CurrentOrdinaryToCurrentInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getPuzcje();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentToOrdinaryPagerInfo currentToOrdinaryPager(
            String content) {
        AbLogUtil.d(TAG, "进入活期转普户页返回的数据" + content);

        CurrentToOrdinaryPagerInfo info = (CurrentToOrdinaryPagerInfo) AbJsonUtil
                .fromJson(content, CurrentToOrdinaryPagerInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getHqykye();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentToOrdinaryBeginInfo currentToOrdinaryBegin(
            String content) {
        AbLogUtil.d(TAG, "确认活期转普户返回的数据" + content);

        CurrentToOrdinaryBeginInfo info = (CurrentToOrdinaryBeginInfo) AbJsonUtil
                .fromJson(content, CurrentToOrdinaryBeginInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getHqzcje();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentinitCurrentDetailInfo currentinitCurrentDetail(
            String content) {
        AbLogUtil.d(TAG, "进入活期理财买进返回的数据:" + content);

        CurrentinitCurrentDetailInfo info = (CurrentinitCurrentDetailInfo) AbJsonUtil
                .fromJson(content, CurrentinitCurrentDetailInfo.class);

        if (info != null) {
            String verifyData = info.getMessageType() + info.getRespCode()
                    + info.getUserId();
            boolean verifyResult = RSAUtil.verifyResult(verifyData,
                    info.getSignValue());
            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentUserPresentRecordInfo currentUserPresentRecord(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "活期提现明细接口返回的数据:" + content);
        CurrentUserPresentRecordInfo userPresentRecordInfo = (CurrentUserPresentRecordInfo) AbJsonUtil
                .fromJson(content, CurrentUserPresentRecordInfo.class);

        if (userPresentRecordInfo != null) {

            String data = userPresentRecordInfo.getMessageType()
                    + userPresentRecordInfo.getRespCode()
                    + userPresentRecordInfo.getUserId()
                    + userPresentRecordInfo.getTotalCount()
                    + userPresentRecordInfo.getPageCount()
                    + userPresentRecordInfo.getCurrentPage()
                    + userPresentRecordInfo.getPresentStatus();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    userPresentRecordInfo.getSignValue());

            if (verifyResult) {
                return userPresentRecordInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    public static CurrentSubmitRedeemInfo currentSubmitRedeem(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "确认赎回接口返回的数据:" + content);
        CurrentSubmitRedeemInfo currentSubmitRedeemInfo = (CurrentSubmitRedeemInfo) AbJsonUtil
                .fromJson(content, CurrentSubmitRedeemInfo.class);

        if (currentSubmitRedeemInfo != null) {

            String data = currentSubmitRedeemInfo.getMessageType()
                    + currentSubmitRedeemInfo.getRespCode()
                    + currentSubmitRedeemInfo.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    currentSubmitRedeemInfo.getSignValue());

            if (verifyResult) {
                return currentSubmitRedeemInfo;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;

    }

    // 用户进入活期提现页面
    public static CurrentUserIntoEnchashmentInfo currentUserIntoEnchashment(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户进入活期提现页面返回的数据:" + content);
        CurrentUserIntoEnchashmentInfo info = (CurrentUserIntoEnchashmentInfo) AbJsonUtil
                .fromJson(content, CurrentUserIntoEnchashmentInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getCardlast() + info.getKyye();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户活期提现页面
    public static CurrentUserEnchashmentSumInfo currentUserEnchashmentSum(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户活期提现页面返回的数据:" + content);
        CurrentUserEnchashmentSumInfo info = (CurrentUserEnchashmentSumInfo) AbJsonUtil
                .fromJson(content, CurrentUserEnchashmentSumInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户进入赎回页面
    public static CurrentIntoRedeemInfo currentIntoRedeem(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户进入赎回页面返回的数据:" + content);
        CurrentIntoRedeemInfo info = (CurrentIntoRedeemInfo) AbJsonUtil
                .fromJson(content, CurrentIntoRedeemInfo.class);
        if (info != null) {

            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getShje();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;

            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户确认购买页面
    public static CurrentByCurrentInfo currentByCurrent(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "用户确认购买返回的数据:" + content);
        CurrentByCurrentInfo info = (CurrentByCurrentInfo) AbJsonUtil.fromJson(
                content, CurrentByCurrentInfo.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getTzje();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    // 用户确认购买页面
    public static CurrentUserDayDetailInfo currentUserDayDetail(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "活期收益明细返回的数据:" + content);
        CurrentUserDayDetailInfo info = (CurrentUserDayDetailInfo) AbJsonUtil
                .fromJson(content, CurrentUserDayDetailInfo.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getTotalCount()
                    + info.getPageCount() + info.getCurrentPage();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static CurrentUserIncomeSatementInfo currentUserIncomeSatement(
            String content) {
        AbLogUtil.d(MyApplication.getInstance(), "活期资金明细返回的数据:" + content);
        CurrentUserIncomeSatementInfo info = (CurrentUserIncomeSatementInfo) AbJsonUtil
                .fromJson(content, CurrentUserIncomeSatementInfo.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode()
                    + info.getUserId() + info.getTotalCount()
                    + info.getPageCount() + info.getCurrentPage()
                    + info.getIncomeType();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static SplashImg getSplashImg(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "splash活动图片返回:" + content);
        SplashImg info = (SplashImg) AbJsonUtil.fromJson(content,
                SplashImg.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

    public static QueryUserIntegralInfo queryUserIntegral(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "进入我的签到返回:" + content);
        QueryUserIntegralInfo info = (QueryUserIntegralInfo) AbJsonUtil.fromJson(content,
                QueryUserIntegralInfo.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode() + info.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }


    public static UserIntegralSignInInfo userIntegralSignIn(String content) {
        AbLogUtil.d(MyApplication.getInstance(), "签到返回:" + content);
        UserIntegralSignInInfo info = (UserIntegralSignInInfo) AbJsonUtil.fromJson(content,
                UserIntegralSignInInfo.class);
        if (info != null) {
            String data = info.getMessageType() + info.getRespCode() + info.getUserId();

            boolean verifyResult = RSAUtil.verifyResult(data,
                    info.getSignValue());

            if (verifyResult) {
                return info;
            } else {
                AbToastUtil.showToastInThread(MyApplication.getInstance(),
                        AbConstant.VERIFY_FAIL);
            }
        } else {
            AbToastUtil.showToastInThread(MyApplication.getInstance(),
                    AbConstant.JSON_ERROR);
        }
        return null;
    }

}
