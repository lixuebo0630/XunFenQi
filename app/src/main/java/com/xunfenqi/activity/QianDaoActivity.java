/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

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
import java.util.List;

/**
 *
 */
public class QianDaoActivity extends BaseActivity {


    private TextView tv_kyjf;
    private TextView tv_mrkzqjf;
    private TextView tv_jlsm;
    private TextView tv_date1;
    private TextView tv_date2;
    private TextView tv_date3;
    private TextView tv_date4;
    private TextView tv_date5;
    private TextView tv_date6;
    private TextView tv_date7;
    private LoadingLayout loadingView;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_qiandao);

        tv_kyjf = (TextView) findViewById(R.id.tv_qiandao_act_kyjf);
        tv_mrkzqjf = (TextView) findViewById(R.id.tv_qiandao_act_mrkzqjf);
        tv_jlsm = (TextView) findViewById(R.id.tv_qiandao_act_jlsm);
        tv_date1 = (TextView) findViewById(R.id.tv_qiandao_act_date1);
        tv_date2 = (TextView) findViewById(R.id.tv_qiandao_act_date2);
        tv_date3 = (TextView) findViewById(R.id.tv_qiandao_act_date3);
        tv_date4 = (TextView) findViewById(R.id.tv_qiandao_act_date4);
        tv_date5 = (TextView) findViewById(R.id.tv_qiandao_act_date5);
        tv_date6 = (TextView) findViewById(R.id.tv_qiandao_act_date6);
        tv_date7 = (TextView) findViewById(R.id.tv_qiandao_act_date7);

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

                                tv_kyjf.setText(info.getJfze());
                                tv_mrkzqjf.setText(info.getMrjf());
                                List<QueryUserIntegralInfo.QianDaoBean> dataList = info.getDataList();
                                if (dataList != null && dataList.size() > 0) {

                                    for (int i = 0; i < dataList.size(); i++) {
                                        QueryUserIntegralInfo.QianDaoBean bean = dataList.get(i);

                                        if (i == 0) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date1.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date1.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date1.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date1.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date1.setText(bean.getQdrq());
                                        }


                                        if (i == 1) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date2.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date2.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date2.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date2.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date2.setText(bean.getQdrq());
                                        }

                                        if (i == 2) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date3.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date3.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date3.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date3.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date3.setText(bean.getQdrq());
                                        }

                                        if (i == 3) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date4.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date4.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date4.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date4.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date4.setText(bean.getQdrq());
                                        }


                                        if (i == 4) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date5.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date5.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date5.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date5.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date5.setText(bean.getQdrq());
                                        }


                                        if (i == 5) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date6.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date6.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date6.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date6.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date6.setText(bean.getQdrq());
                                        }


                                        if (i == 6) {
                                            if ("0".equals(bean.getSfqd())) {
                                                tv_date7.setBackgroundResource(R.drawable.shape_orange_bg_white_line);
                                                tv_date7.setTextColor(Color.parseColor("#FFFFFFFF"));
                                            } else if ("1".equals(bean.getSfqd())) {
                                                tv_date7.setTextColor(Color.parseColor("#ff9d50"));
                                                tv_date7.setBackgroundResource(R.drawable.shape_white_bg);
                                            }
                                            tv_date7.setText(bean.getQdrq());
                                        }

                                    }


                                }
                                tv_jlsm.setText(Html.fromHtml(info.getJlsm()));

                                loadingView.setStatus(LoadingLayout.Success);
                            } else {
                                AbToastUtil.showToastInThread(
                                        QianDaoActivity.this,
                                        info.getRespCodeDesc());
                                loadingView.setStatus(LoadingLayout.Error);

                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, final String content,
                                          Throwable error) {
                        loadingView.setStatus(LoadingLayout.Error);
                        error.printStackTrace();
                        AbToastUtil.showToastInThread(QianDaoActivity.this,
                                error.getMessage());
                    }
                });
            }
        }

    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的签到");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    private void qianDaoSubmit() {

        {
            String loginUid = MyApplication.getInstance().getLoginUid();
            if (loginUid != null && !"".equals(loginUid)) {
                HaiHeApi.userIntegralSignIn(loginUid,  new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        final UserIntegralSignInInfo info = HaiheReturnApi
                                .userIntegralSignIn(content);
                        if (info != null) {
                            if (info.getRespCode().equals("000")) {

                                new SweetAlertDialog(QianDaoActivity.this,
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
                                AbToastUtil.showToastInThread(QianDaoActivity.this,
                                        info.getRespCodeDesc());
                                return;
                            }
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(QianDaoActivity.this);
                        AbToastUtil.showToastInThread(QianDaoActivity.this, error.getMessage());
                    }

                });
            }
        }

    }
}
