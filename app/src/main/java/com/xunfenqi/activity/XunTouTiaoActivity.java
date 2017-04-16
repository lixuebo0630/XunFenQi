/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.XunTouTiaoAdapter;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.QueryNewsInfo;
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
public class XunTouTiaoActivity extends BaseActivity {


    private LoadingLayout loadingView;
    private AbPullToRefreshView ptrv;
    private ListView listView;

    private List<QueryNewsInfo.QueryNews> list = null;
    private List<QueryNewsInfo.QueryNews> newList = null;
    // 数据适配器
    private XunTouTiaoAdapter xunTouTiaoAdapter;
    private List<QueryNewsInfo.QueryNews> queryNewsList;

    private int currentPage = 1;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_xun_toutiao);
        loadingView = (LoadingLayout) findViewById(R.id.loading_myred_act);
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myred_act);
        listView = (ListView) findViewById(R.id.lv_myred_act);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((list != null && (list.size() >= position + 1))) {

                    QueryNewsInfo.QueryNews queryNews = list.get(position);

                    Intent intent2 = new Intent(XunTouTiaoActivity.this, H5Activity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("title", "讯头条");
                    mBundle.putString("url",
                            queryNews.getXwurl());
                    intent2.putExtras(mBundle);
                    XunTouTiaoActivity.this.startActivity(intent2);
                    //  ActivityUtil.startActivityForStringData(XunTouTiaoActivity.this, "jkid", JIeKuanDetailActivity.class, userMyJiekuan.getJkid());

                }
            }
        });

        loadingView.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                currentPage = 1;
                doNetWork();
            }
        });

    }

    @Override
    public void initData() {


        // ListView数据
        list = new ArrayList<QueryNewsInfo.QueryNews>();
        xunTouTiaoAdapter = new XunTouTiaoAdapter(this, list,
                R.layout.list_item_xun_toutiao, new String[]{
                "tv_xun_toutiao_item_title", "tv_xun_toutiao_item_new_flag",
                "tv_xun_toutiao_item_time", "tv_xun_toutiao_item_content"
        }, new int[]{
                R.id.tv_xun_toutiao_item_title, R.id.tv_xun_toutiao_item_new_flag,
                R.id.tv_xun_toutiao_item_time, R.id.tv_xun_toutiao_item_content});
        listView.setAdapter(xunTouTiaoAdapter);
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
        HaiHeApi.queryNews(currentPage + "", "5",
                new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        QueryNewsInfo queryNewsInfo = HaiheReturnApi
                                .queryNews(content);
                        if (queryNewsInfo != null) {
                            if ("000".equals(queryNewsInfo.getRespCode())) {

                                // 判断总页数和当前页
                                if (Integer.parseInt(queryNewsInfo
                                        .getCurrentPage()) == Integer
                                        .parseInt(queryNewsInfo.getPageCount())) {
                                    ptrv.setLoadMoreEnable(false);
                                    ptrv.getFooterView().hide();
                                } else {
                                    ptrv.setLoadMoreEnable(true);
                                }
                                ptrv.onFooterLoadFinish();
                                processData(queryNewsInfo, currentPage);
                            } else {
                                loadingView.setStatus(LoadingLayout.Error);
                                AbToastUtil.showToastInThread(XunTouTiaoActivity.this,
                                        queryNewsInfo.getRespCodeDesc());
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode,
                                          final String content, Throwable error) {
                        error.printStackTrace();
                        currentPage--;
                        loadingView.setStatus(LoadingLayout.Error);
                        AbToastUtil.showToastInThread(XunTouTiaoActivity.this,
                                error.getMessage());
                    }
                });

    }


    private void processData(QueryNewsInfo queryNewsInfo, int currentPage) {
        // 如果currentPage 是1,刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        newList = new ArrayList<QueryNewsInfo.QueryNews>();
        queryNewsList = queryNewsInfo.getNewsList();
        for (int i = 0; i < queryNewsList.size(); i++) {
            QueryNewsInfo.QueryNews queryNews = queryNewsList.get(i);
            newList.add(queryNews);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<QueryNewsInfo.QueryNews>) newList);
                xunTouTiaoAdapter.notifyDataSetChanged();
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
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "讯头条");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
