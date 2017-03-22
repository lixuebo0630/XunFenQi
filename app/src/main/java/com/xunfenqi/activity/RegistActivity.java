/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.RegistInfo;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.ButtonUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener {

    EditText et_phonenumber, et_ljqd;
    EditText et_visitnumber;
    EditText et_input_verify_number;
    EditText et_inputpassword;
    TextView tv_getVerifyNumber;


    // 服务器得到验证码
    private String verifyCode = "";
    // 计时器
    private TimeCount time;
    private String password;
    private String inputVerifyNumber;
    private String inviteCode;
    private String phoneNumber;
    private String ljqd;

    @Override
    public void initView() {
        setAbContentView(R.layout.activity_regist);

        time = new TimeCount(120000, 1000);

        // 初始化控件
        et_phonenumber = (EditText) this
                .findViewById(R.id.et_regist_act_phonenum);


        // 初始化控件
        et_inputpassword = (EditText) this
                .findViewById(R.id.et_regist_act_pwd);
        et_visitnumber = (EditText) this
                .findViewById(R.id.et_regist_act_visitnum);
        et_input_verify_number = (EditText) findViewById(R.id.et_regist_act_input_verify_number);
        et_ljqd = (EditText) findViewById(R.id.et_regist_act_ljqd);
        // et_input_verify_number.setKeyListener(null);

        tv_getVerifyNumber = (TextView) findViewById(R.id.tv_regist_act_getVerifyNumber);

        findViewById(R.id.bt_regist_act_regist).setOnClickListener(this);

        tv_getVerifyNumber.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "注册");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regist_act_regist:// 执行注册
                if (ButtonUtils.isFastDoubleClick()) {
                    return;
                } else {
                    doRegist();
                }
                break;
            case R.id.tv_regist_act_getVerifyNumber:// 获取验证码

                String phonenumber = et_phonenumber.getText().toString().trim();


                if (TextUtils.isEmpty(phonenumber)) {
                    AbToastUtil.showToast(this, "请输入手机号");
                    return;
                }
               

                // 开始计时
                time.start();
                // 获取验证码
                getVerifyNumber(phonenumber);
                break;

            default:
                break;
        }
    }

    private void doRegist() {
        phoneNumber = et_phonenumber.getText().toString().trim();
        password = et_inputpassword.getText().toString().trim();
        inputVerifyNumber = et_input_verify_number.getText().toString().trim();
        ljqd = et_ljqd.getText().toString().trim();
        // 邀请码
        inviteCode = et_visitnumber.getText().toString().trim();

        if (TextUtils.isEmpty(inputVerifyNumber)) {
            AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
            return;
        }

        if (password == null || TextUtils.isEmpty(password)) {
            AbToastUtil.showToast(getApplicationContext(), "密码不能为空");
            return;
        }
        if (antiSqlValid(password)) {
            if (password.length() < 6) {
                AbToastUtil.showToast(getApplicationContext(), "密码长度不能小于6");
                return;
            }
            if (password.length() > 20) {
                AbToastUtil.showToast(getApplicationContext(), "密码长度不能大于20");
                return;
            }
            if (password.matches("^[0-9]*$")) {
                AbToastUtil.showToast(getApplicationContext(), "密码不能全为数字");
                return;
            }
            if (password.matches("[a-zA-Z]+")) {
                AbToastUtil.showToast(getApplicationContext(), "密码不能全为英文字母");
                return;
            }
        } else {
            AbToastUtil.showToast(getApplicationContext(), "密码不能输入非法字符");
            return;
        }

        if (verifyCode != null) {
            if (!verifyCode.equals(inputVerifyNumber)) {
                AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
                return;
            }
            // 验证成功,请求网络,执行注册
            doNetRegist();
            // AbToastUtil.showToast(getApplicationContext(), "验证成功");
        }
    }

    private void doNetRegist() {
        AbDialogUtil.getWaitDialog(this);
        HaiHeApi.regist(phoneNumber, password, ljqd, phoneNumber, inviteCode,
                new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        RegistInfo registInfo = HaiheReturnApi
                                .registReturn(content);
                        if (registInfo != null) {
                            if (registInfo.getRespCode().equals("000")) {
                                MyApplication.getInstance().cleanLoginInfo();
                                // 设置用户id
                                MyApplication.getInstance().setLoginUid(
                                        registInfo.getUserId());
                                // 设置标记
                                MyApplication.getInstance().setNineFlag(
                                        MyApplication.REGIST_SUCCESS);
                                // 清除数据
                                // 查询用户信息
                                EventBus.getDefault().post(
                                        AbConstant.MY_ACCOUNT_REFRESH);
                                queryUserInfo(registInfo.getUserId());
                                AbToastUtil.showToastInThread(
                                        getApplicationContext(),
                                        registInfo.getRespCodeDesc());
                                AbDialogUtil
                                        .removeDialog(RegistActivity.this);
                            } else {
                                AbToastUtil.showToastInThread(
                                        getApplicationContext(),
                                        registInfo.getRespCodeDesc());
                                AbDialogUtil
                                        .removeDialog(RegistActivity.this);
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        AbToastUtil.showToastInThread(
                                RegistActivity.this, error.getMessage());
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(RegistActivity.this);
                    }
                });


    }


    /**
     * @param phonenumber
     * @return void
     * @throws
     * @Title: getVerifyNumber
     * @Description: 获取验证码
     * @param:
     */
    private void getVerifyNumber(String phonenumber) {


        HaiHeApi.getVerifyNumber(phonenumber, "1", new AbSoapListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
                        .sendSmsModelReturn(content);

                if (sendSmsModelInfo != null) {
                    if (sendSmsModelInfo.getRespCode().equals("000")) {
                        AbToastUtil.showToastInThread(getApplicationContext(),
                                "验证码发送成功");
                        verifyCode = sendSmsModelInfo.getVerifyCode();

                    } else {
                        AbToastUtil.showToastInThread(getApplicationContext(),
                                sendSmsModelInfo.getRespCodeDesc());
                        return;
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbToastUtil.showToastInThread(RegistActivity.this,
                        error.getMessage());
                error.printStackTrace();
            }
        });
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_getVerifyNumber.setText("重新获取验证码");
            tv_getVerifyNumber.setClickable(true);
            tv_getVerifyNumber.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_getVerifyNumber.setClickable(false);
            tv_getVerifyNumber.setBackgroundColor(Color.WHITE);
            tv_getVerifyNumber.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
    }


    private boolean antiSqlValid(String oldPws) {
        oldPws = oldPws.toLowerCase();
        String reg = "and|exec|insert|select|delete|update|count|drop|chr|mid|master|truncate|"
                + "char|declare|sitename|net user|xp_cmdshell|create|drop|"
                + "table|from|grant|use|group_concat|column_name|"
                + "information_schema.columns|table_schema|union|where|order|by|count|like|%|&|\\*|'|,|\"|;|>|</i";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(oldPws);
        if (matcher.find()) {
            return false;
        } else {
            return true;
        }
    }


    protected void queryUserInfo(String userId) {
        /**
         * 查询用户信息
         */
        HaiHeApi.queryUseInfo(userId, new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                UserCenterInfo userCenterInfo = HaiheReturnApi
                        .queryUserRetur(content);
                if (userCenterInfo != null) {
                    if ("000".equals(userCenterInfo.getRespCode())) {
                        // 操作成功,给用户赋值
                        MyApplication.getInstance()
                                .saveUserInfo(userCenterInfo);

                        // 注册成功,跳转到设置手势密码界面
                        ActivityUtil.startActivityAndFinish(
                                RegistActivity.this,
                                SetNineLockActivity.class);
                    } else {
                        // 清除用户数据
                        MyApplication.getInstance().cleanLoginInfo();
                        AbToastUtil.showToastInThread(
                                RegistActivity.this,
                                userCenterInfo.getRespCodeDesc());
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                error.printStackTrace();
                AbToastUtil.showToastInThread(RegistActivity.this,
                        error.getMessage());
            }
        });

    }

}
