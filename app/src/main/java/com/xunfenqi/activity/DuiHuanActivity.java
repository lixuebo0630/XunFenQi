/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.view.View;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.QueryUserIntegralInfo;
import com.xunfenqi.model.domain.UserIntegralSignInInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.HashMap;

/**
 *
 */
public class DuiHuanActivity extends BaseActivity {


    private LoadingLayout loadingView;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_qiandao);

        loadingView = (LoadingLayout) findViewById(R.id.loading_qiandao_act);
        loadingView.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                doNetwork();
            }
        });
        findViewById(R.id.bt_qiandao_act_qd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qianDaoSubmit();

            }
        });
    }


    @Override
    public void initData() {

        doNetwork();
    }

    private void doNetwork() {
        {

            loadingView.setStatus(LoadingLayout.Loading);
            String loginUid = MyApplication.getInstance().getLoginUid();

            if (loginUid != null) {

                HaiHeApi.queryUserIntegral(loginUid, new AbSoapListener() {

                    private HashMap<String, String> map;

                    @Override
                    public void onSuccess(int statusCode, String content) {
                        QueryUserIntegralInfo info = HaiheReturnApi
                                .queryUserIntegral(content);
                        if (info != null) {
                            if ("000".equals(info.getRespCode())) {


                                loadingView.setStatus(LoadingLayout.Success);
                            }


                        } else {
//                                AbToastUtil.showToastInThread(
//                                        DuiHuanActivity.this,
//                                        info.getRespCodeDesc());
                            loadingView.setStatus(LoadingLayout.Error);

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, final String content,
                                          Throwable error) {
                        loadingView.setStatus(LoadingLayout.Error);
                        error.printStackTrace();
                        AbToastUtil.showToastInThread(DuiHuanActivity.this,
                                error.getMessage());
                    }
                });
            }
        }

    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的兑换");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    private void qianDaoSubmit() {
        {
            String loginUid = MyApplication.getInstance().getLoginUid();
            if (loginUid != null && !"".equals(loginUid)) {
                HaiHeApi.userIntegralSignIn(loginUid, new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        final UserIntegralSignInInfo info = HaiheReturnApi
                                .userIntegralSignIn(content);
                        if (info != null) {
                            if (info.getRespCode().equals("000")) {

                                new SweetAlertDialog(DuiHuanActivity.this,
                                        SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                        .setTitleText("提示")
                                        .setContentText("签到成功")
                                        .setConfirmText("确认")
                                        .setConfirmClickListener(
                                                new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(
                                                            SweetAlertDialog sDialog) {
                                                        doNetwork();
                                                        sDialog.dismiss();
                                                    }
                                                }).show();


                            } else {
                                AbToastUtil.showToastInThread(DuiHuanActivity.this,
                                        info.getRespCodeDesc());
                                return;
                            }
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(DuiHuanActivity.this);
                        AbToastUtil.showToastInThread(DuiHuanActivity.this, error.getMessage());
                    }

                });
            }
        }

    }
}
