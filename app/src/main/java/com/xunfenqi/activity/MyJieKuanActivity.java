/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.MyJieKuanAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserMyJiekuanInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class MyJieKuanActivity extends BaseActivity {


    private LoadingLayout loadingView;
    private AbPullToRefreshView ptrv;
    private ListView listView;

    private List<UserMyJiekuanInfo.UserMyJiekuan> list = null;
    private List<UserMyJiekuanInfo.UserMyJiekuan> newList = null;
    // 数据适配器
    private MyJieKuanAdapter myJieKuanAdapter;
    private List<UserMyJiekuanInfo.UserMyJiekuan> userMyJiekuanList;

    private int currentPage = 1;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_my_red);
        loadingView = (LoadingLayout) findViewById(R.id.loading_myred_act);
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myred_act);
        listView = (ListView) findViewById(R.id.lv_myred_act);
        listView.setDivider(getResources().getDrawable(R.drawable.draw_diver));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((list != null && (list.size() >= position + 1))) {

                    UserMyJiekuanInfo.UserMyJiekuan userMyJiekuan = list.get(position);

                    ActivityUtil.startActivityForStringData(MyJieKuanActivity.this, "jkid", JIeKuanDetailActivity.class, userMyJiekuan.getJkid());

                }
            }
        });

    }

    @Override
    public void initData() {


        // ListView数据
        list = new ArrayList<UserMyJiekuanInfo.UserMyJiekuan>();
        myJieKuanAdapter = new MyJieKuanAdapter(this, list,
                R.layout.list_item_my_jiekuan, new String[]{
                "tv_myjiekuan_item_ddh", "tv_myjiekuan_item_fqze",
                "tv_myjiekuan_item_jklx", "tv_myjiekuan_item_jksj",
                "tv_myjiekuan_item_yhqs", "tv_myjiekuan_item_syyh", "bt_myjiekuan_opertation"}, new int[]{
                R.id.tv_myjiekuan_item_ddh, R.id.tv_myjiekuan_item_fqze,
                R.id.tv_myjiekuan_item_jklx, R.id.tv_myjiekuan_item_jksj,
                R.id.tv_myjiekuan_item_yhqs, R.id.tv_myjiekuan_item_syyh, R.id.bt_myjiekuan_opertation});
        listView.setAdapter(myJieKuanAdapter);
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
            HaiHeApi.userLoans(loginUid, currentPage + "", "5",
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            UserMyJiekuanInfo userMyJiekuanInfo = HaiheReturnApi
                                    .userLoans(content);
                            if (userMyJiekuanInfo != null) {
                                if ("000".equals(userMyJiekuanInfo.getRespCode())) {

                                    // 判断总页数和当前页
                                    if (Integer.parseInt(userMyJiekuanInfo
                                            .getCurrentPage()) == Integer
                                            .parseInt(userMyJiekuanInfo.getPageCount())) {
                                        ptrv.setLoadMoreEnable(false);
                                        ptrv.getFooterView().hide();
                                    } else {
                                        ptrv.setLoadMoreEnable(true);
                                    }
                                    ptrv.onFooterLoadFinish();
                                    processData(userMyJiekuanInfo, currentPage);
                                } else {
                                    loadingView.setStatus(LoadingLayout.Error);
                                    AbToastUtil.showToastInThread(MyJieKuanActivity.this,
                                            userMyJiekuanInfo.getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            error.printStackTrace();
                            currentPage--;
                            loadingView.setStatus(LoadingLayout.Error);
                            AbToastUtil.showToastInThread(MyJieKuanActivity.this,
                                    error.getMessage());
                        }
                    });
        }
    }


    private void processData(UserMyJiekuanInfo userMyJiekuanInfo, int currentPage) {
        // 如果currentPage 是1,刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        newList = new ArrayList<UserMyJiekuanInfo.UserMyJiekuan>();
        userMyJiekuanList = userMyJiekuanInfo.getDataList();
        for (int i = 0; i < userMyJiekuanList.size(); i++) {
            UserMyJiekuanInfo.UserMyJiekuan myJiekuan = userMyJiekuanList.get(i);
            newList.add(myJiekuan);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<UserMyJiekuanInfo.UserMyJiekuan>) newList);
                myJieKuanAdapter.notifyDataSetChanged();
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
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的借款");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
