/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.widget.ListView;

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
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {

        jkid = getIntent().getStringExtra("jkid");

        AbToastUtil.showToast(this, jkid);
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
