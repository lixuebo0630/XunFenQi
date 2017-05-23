/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.JIeKuanDetailAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserLoansDetailInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class JIeKuanDetailActivity extends BaseActivity {


    private LoadingLayout loadingView;
    private AbPullToRefreshView ptrv;
    private ListView listView;

    private List<UserLoansDetailInfo.UserLoansDetail> list = null;
    private List<UserLoansDetailInfo.UserLoansDetail> newList = null;
    // 数据适配器
    private JIeKuanDetailAdapter jIeKuanDetailAdapter;
    private List<UserLoansDetailInfo.UserLoansDetail> userLoansDetailList;

    private int currentPage = 1;
    private String jkid = "";
    private String zt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {


        Bundle data = getIntent().getBundleExtra("data");

        jkid = data.getString("jkid");

        zt = data.getString("zt");


        setAbContentView(R.layout.activity_my_red);
        loadingView = (LoadingLayout) findViewById(R.id.loading_myred_act);
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myred_act);
        listView = (ListView) findViewById(R.id.lv_myred_act);
        listView.setDivider(getResources().getDrawable(R.drawable.draw_diver));
    }

    @Override
    public void initData() {

        // ListView数据
        list = new ArrayList<UserLoansDetailInfo.UserLoansDetail>();
        jIeKuanDetailAdapter = new JIeKuanDetailAdapter(this, list,
                R.layout.layout_jiekuan_detail_list_item, new String[]{
                "tv_jiekuan_detail_item_qs", "tv_jiekuan_detail_item_sxf",
                "tv_jiekuan_detail_item_hkr", "rl_jiekuan_detail_item_sfyq",
                "tv_jiekuan_detail_item_sfyq", "rl_jiekuan_detail_item_yqts",
                "tv_jiekuan_detail_item_yqts", "bt_jiekuan_detail_item_wyhk", "iv_jiekuan_detail_item_yhflag"}, new int[]{
                R.id.tv_jiekuan_detail_item_qs, R.id.tv_jiekuan_detail_item_sxf,
                R.id.tv_jiekuan_detail_item_hkr, R.id.rl_jiekuan_detail_item_sfyq,
                R.id.tv_jiekuan_detail_item_sfyq, R.id.rl_jiekuan_detail_item_yqts,
                R.id.tv_jiekuan_detail_item_yqts, R.id.bt_jiekuan_detail_item_wyhk, R.id.iv_jiekuan_detail_item_yhflag});
        listView.setAdapter(jIeKuanDetailAdapter);

        ptrv.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                refreshTask();
            }
        });
        ptrv.setLoadMoreEnable(false);
        loadingView.setStatus(LoadingLayout.Loading);
        doNetWork();
    }


    // 刷新
    protected void refreshTask() {
        currentPage = 1;
        doNetWork();
    }


    private void doNetWork() {
        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            HaiHeApi.userLoansDetail(loginUid, jkid,
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            UserLoansDetailInfo userLoansDetailInfo = HaiheReturnApi
                                    .userLoansDetail(content);
                            if (userLoansDetailInfo != null) {
                                if ("000".equals(userLoansDetailInfo.getRespCode())) {
                                    ptrv.onFooterLoadFinish();
                                    processData(userLoansDetailInfo);
                                } else {
                                    AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                            userLoansDetailInfo.getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            error.printStackTrace();
                            AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                    error.getMessage());
                        }
                    });
        }
    }


    private void processData(UserLoansDetailInfo userLoansDetailInfo) {
        // 如果currentPage 是1,刷新数据
        list.clear();

        newList = new ArrayList<UserLoansDetailInfo.UserLoansDetail>();
        userLoansDetailList = userLoansDetailInfo.getDataList();
        for (int i = 0; i < userLoansDetailList.size(); i++) {
            UserLoansDetailInfo.UserLoansDetail userLoansDetail = userLoansDetailList.get(i);
            newList.add(userLoansDetail);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<UserLoansDetailInfo.UserLoansDetail>) newList);
                jIeKuanDetailAdapter.notifyDataSetChanged();
                newList.clear();
            }
        }
        if (list.size() <= 0) {
            loadingView.setStatus(LoadingLayout.Empty);
            return;
        }
        loadingView.setStatus(LoadingLayout.Success);
    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "借款明细");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
        if ("还款中".equals(zt)) {
            tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), 0,
                    UIUtils.dip2px(14));
            TextView tv = new TextView(this);
            tv.setText("提前还款");
            tv.setPadding(10, 10, 10, 10);
            tv.setTextSize(14);
            tv.setTextColor(Color.WHITE);
            tTitleBar.getRightLayout().setPadding(0, 0, UIUtils.px2dip(60), 0);
            tTitleBar.addRightView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new SweetAlertDialog(JIeKuanDetailActivity.this,
                            SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("提示")
                            .setContentText("确定申请提前还款吗?")
                            .setCancelText("取消")
                            .setConfirmText("确认")
                            .showCancelButton(true)
                            .setCancelClickListener(
                                    new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    })
                            .setConfirmClickListener(
                                    new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {


                                            repayments();
                                            sDialog.dismiss();

                                        }
                                    }).show();

                }
            });

        }
    }

    private void repayments() {


        String loginUid = MyApplication.getInstance().getLoginUid();
        AbDialogUtil.getWaitDialog(this);
        if (loginUid != null) {
            HaiHeApi.userApplyRefund(loginUid, jkid,
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            AbDialogUtil.removeDialog(JIeKuanDetailActivity.this);
                            UserLoansDetailInfo userLoansDetailInfo = HaiheReturnApi
                                    .userApplyRefund(content);
                            if (userLoansDetailInfo != null) {
                                if ("000".equals(userLoansDetailInfo.getRespCode())) {
                                    if ("1".equals(userLoansDetailInfo.getSqzt())) {
                                        AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                                "申请成功,请等待客服联系");
                                        JIeKuanDetailActivity.this.finish();
                                    } else {
                                        AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                                userLoansDetailInfo.getRespCodeDesc());
                                    }
                                } else {
                                    AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                            userLoansDetailInfo.getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            AbDialogUtil.removeDialog(JIeKuanDetailActivity.this);
                            error.printStackTrace();
                            AbToastUtil.showToastInThread(JIeKuanDetailActivity.this,
                                    error.getMessage());
                        }
                    });
        }
    }


    public void onEvent(String event) {
        switch (event) {
            case AbConstant.JIEKUAN_DETAIL_REFRESH:
                initData();
                break;

            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
