/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.LoginInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.EditTextWithDelete;
import com.xunfenqi.view.titlebar.AbTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RZActivity extends BaseActivity implements
        OnClickListener {
    protected static final String TAG = "ForgetPwsSetup2Activity";
    @BindView(R.id.et_rz_act_username)
    EditTextWithDelete etRzActUsername;
    @BindView(R.id.et_rz_act_password)
    EditTextWithDelete etRzActPassword;
    private String rzFlag = "";
    private String username;
    private String password;

    @Override
    public void initView() {
        rzFlag = getIntent().getStringExtra("rzFlag");
        setAbContentView(R.layout.activity_rz);

        findViewById(R.id.btn_forget_pwd2_act_confirm).setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        username = etRzActUsername.getText().toString().trim();
        password = etRzActPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入密码");
            return;
        }
        // 验证成功
        doLogion();
    }

    private void doLogion() {
        AbDialogUtil.getWaitDialog(this);
        HaiHeApi.userEditCredit(MyApplication.getInstance().getLoginUid(), rzFlag, username, password, new AbSoapListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                LoginInfo info = HaiheReturnApi.userEditCredit(content);
                if (info != null) {

                    if (info.getRespCode().equals("000")) {
                        AbDialogUtil.removeDialog(RZActivity.this);

                        String fqlx = info.getFqlx();
                        if ("4".equals(fqlx)) {
                            SettingUtils.getInstance(RZActivity.this).saveValue("sjfwm",
                                    "0");
                        } else if ("1".equals(fqlx)) {
                            SettingUtils.getInstance(RZActivity.this).saveValue("jdrz",
                                    "0");
                        } else if ("3".equals(fqlx)) {

                            SettingUtils.getInstance(RZActivity.this).saveValue("wxwrz",
                                    "0");
                        }

                        RZActivity.this.finish();


                        AbToastUtil.showToastInThread(getApplicationContext(), "操作成功");
                    } else {
                        AbToastUtil.showToastInThread(getApplicationContext(),
                                info.getRespCodeDesc());
                        AbDialogUtil.removeDialog(RZActivity.this);
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbToastUtil.showToastInThread(RZActivity.this,
                        error.getMessage());
                error.printStackTrace();
                AbDialogUtil.removeDialog(RZActivity.this);
            }
        });
    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "信息认证");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }
}
