/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.model.domain.QueryUserIntegralByChangeInfo;
import com.xunfenqi.model.domain.UserIntegralSignInInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 *
 */
public class DuiHuanActivity extends BaseActivity {


    @BindView(R.id.tv_qiandao_act_kyjf)
    TextView tvQiandaoActKyjf;
    @BindView(R.id.tv_qiandao_act_kyjf2)
    TextView tvQiandaoActKyjf2;
    @BindView(R.id.tv_qiandao_act_dhje)
    TextView tvQiandaoActDhje;
    @BindView(R.id.tv_qiandao_act_jlsm)
    TextView tvQiandaoActJlsm;
    @BindView(R.id.loading_qiandao_act)
    LoadingLayout loadingQiandaoAct;
    @BindView(R.id.btn_duihuan_act_confirm)
    Button btnDuihuanActConfirm;
    private LoadingLayout loadingView;
    private String kdhjf;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_duihuan);

        loadingView = (LoadingLayout) findViewById(R.id.loading_qiandao_act);
        loadingView.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                doNetwork();
            }
        });
        findViewById(R.id.btn_duihuan_act_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDuiHuan();
            }
        });

    }

    private void doDuiHuan() {
        AbDialogUtil.getWaitDialog(DuiHuanActivity.this);
        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null && !"".equals(loginUid)) {
            HaiHeApi.userInviteExchange(loginUid, kdhjf, new AbSoapListener() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    final UserIntegralSignInInfo info = HaiheReturnApi
                            .userInviteExchange(content);
                    if (info != null) {
                        if (info.getRespCode().equals("000")) {
                            if ("1".equals(info.getDhzt())) {
                                new SweetAlertDialog(DuiHuanActivity.this,
                                        SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                        .setTitleText("提示")
                                        .setContentText("兑换成功")
                                        .setConfirmText("确认")
                                        .setConfirmClickListener(
                                                new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(
                                                            SweetAlertDialog sDialog) {

                                                        EventBus.getDefault().post(AbConstant.MY_ACCOUNT_REFRESH);
                                                        sDialog.dismiss();
                                                        CallBackManager.getInstance().sendSwitchRadio(2);
                                                        DuiHuanActivity.this.finish();

                                                    }
                                                }).show();
                            }
                        } else {
                            AbToastUtil.showToastInThread(DuiHuanActivity.this,
                                    info.getRespCodeDesc());
                            AbDialogUtil.removeDialog(DuiHuanActivity.this);
                            return;
                        }
                    }
                    AbDialogUtil.removeDialog(DuiHuanActivity.this);

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


    @Override
    public void initData() {

        doNetwork();
    }

    private void doNetwork() {
        {

            loadingView.setStatus(LoadingLayout.Loading);
            String loginUid = MyApplication.getInstance().getLoginUid();

            if (loginUid != null) {

                HaiHeApi.queryUserIntegralByChange(loginUid, new AbSoapListener() {


                    @Override
                    public void onSuccess(int statusCode, String content) {
                        QueryUserIntegralByChangeInfo info = HaiheReturnApi
                                .queryUserIntegralByChange(content);
                        if (info != null) {
                            if ("000".equals(info.getRespCode())) {

                                kdhjf = info.getKdhjf();
                                loadingView.setStatus(LoadingLayout.Success);
                                tvQiandaoActDhje.setText(info.getDhje());
                                tvQiandaoActJlsm.setText(Html.fromHtml(info.getJlsm()));
                                tvQiandaoActKyjf.setText(info.getKyjf());
                                tvQiandaoActKyjf2.setText(info.getKdhjf());
                            }


                        } else {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
