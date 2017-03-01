/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.widget.ListView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.MyredAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserMyRedInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class MyRedActivity extends BaseActivity {


    private LoadingLayout loadingView;
    private AbPullToRefreshView ptrv;
    private ListView listView;

    private List<UserMyRedInfo.UserMyRed> list = null;
    private List<UserMyRedInfo.UserMyRed> newList = null;
    // 数据适配器
    private MyredAdapter myRedListViewAdapter;
    private List<UserMyRedInfo.UserMyRed> userMyRedInfoList;

    private int currentPage = 1;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_my_red);
        loadingView = (LoadingLayout) findViewById(R.id.loading_myred_act);
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myred_act);
        listView = (ListView) findViewById(R.id.lv_myred_act);

    }

    @Override
    public void initData() {


        // ListView数据
        list = new ArrayList<UserMyRedInfo.UserMyRed>();
        myRedListViewAdapter = new MyredAdapter(this, list,
                R.layout.layout_coupon_list_item, new String[]{
                "tv_myred_act_list_item_bt", "tv_myred_act_list_item_beizhu",
                "tv_myred_act_list_item_ffrq", "tv_myred_act_list_item_lqrq",
                "tv_myred_act_list_item_jine", "bt_myred_act_list_item_operate", "iv_myred_act_list_item_state", "ll_myred_act_list_item_lqrq"}, new int[]{
                R.id.tv_myred_act_list_item_bt, R.id.tv_myred_act_list_item_beizhu,
                R.id.tv_myred_act_list_item_ffrq, R.id.tv_myred_act_list_item_lqrq,
                R.id.tv_myred_act_list_item_jine, R.id.bt_myred_act_list_item_operate, R.id.iv_myred_act_list_item_state,R.id.ll_myred_act_list_item_lqrq});
        listView.setAdapter(myRedListViewAdapter);
        ptrv.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                loadMoreTask();
            }
        });


        ptrv.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                refreshTask();
            }
        });

        loadingView.setStatus(LoadingLayout.Loading);
        doNetWork();
    }


    // 刷新
    protected void refreshTask() {
        currentPage = 1;
        doNetWork();
    }

    // 加载更多
    protected void loadMoreTask() {
        currentPage++;
        doNetWork();
    }

    private void doNetWork() {
        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            HaiHeApi.userMyRed(loginUid, "-1", currentPage + "", "15",
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            UserMyRedInfo userMyRed = HaiheReturnApi
                                    .userMyRed(content);
                            if (userMyRed != null) {
                                if ("000".equals(userMyRed.getRespCode())) {

                                    // 判断总页数和当前页
                                    if (Integer.parseInt(userMyRed
                                            .getCurrentPage()) == Integer
                                            .parseInt(userMyRed.getPageCount())) {
                                        ptrv.setLoadMoreEnable(false);
                                        ptrv.getFooterView().hide();
                                    } else {
                                        ptrv.setLoadMoreEnable(true);
                                    }
                                    ptrv.onFooterLoadFinish();
                                    processData(userMyRed, currentPage);
                                } else {
                                    AbToastUtil.showToastInThread(MyRedActivity.this,
                                            userMyRed.getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            error.printStackTrace();
                            currentPage--;
                            AbToastUtil.showToastInThread(MyRedActivity.this,
                                    error.getMessage());
                        }
                    });
        }
    }


    private void processData(UserMyRedInfo userMyRed, int currentPage) {
        // 如果currentPage 是1,刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        newList = new ArrayList<UserMyRedInfo.UserMyRed>();
        userMyRedInfoList = userMyRed.getDataList();
        for (int i = 0; i < userMyRedInfoList.size(); i++) {
            UserMyRedInfo.UserMyRed myRed = userMyRedInfoList.get(i);
            newList.add(myRed);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<UserMyRedInfo.UserMyRed>) newList);
                myRedListViewAdapter.notifyDataSetChanged();
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
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的红包");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
