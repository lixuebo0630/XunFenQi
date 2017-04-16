/**
 * Project Name:HaiHeFinance
 * File Name:MyAccountFragment.java
 * Package Name:com.chinazccy.manpuman.fragment
 * Date:2015-8-20下午5:25:30
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.EnchashmentRecordItemAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbFragment;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.global.EnchashmentRecordClickCallback;
import com.xunfenqi.model.domain.UserPresentRecordInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.view.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName: EnchashmentRecordlFragment
 * @Description: 提现记录
 * @author Xuebo Li
 * @date 2015-10-14 上午10:24:53
 *
 */
public class EnchashmentRecordlFragment extends AbFragment implements
        EnchashmentRecordClickCallback {
    private Activity mActivity;

    private ListView lv_content;
    private AbPullToRefreshView ptrv;
    // 数据
    private List<Map<String, Object>> list = null;
    private List<Map<String, Object>> newList = null;
    // 数据适配器
    private EnchashmentRecordItemAdapter myListViewAdapter;
    private List<UserPresentRecordInfo.UserPresentRecord> userPresentRecords;
    private int currentPage = 1;
    private String presentStatus = "a-b-c";

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        CallBackManager.getInstance().setEnchashmentRecordClickCallback(this);
        mActivity = this.getActivity();
        View view = initView(inflater);
        // 初始化数据
        initData();
        this.setAbFragmentOnLoadListener(new AbFragmentOnLoadListener() {
            @Override
            public void onLoad() {
                refreshTask("");
            }
        });
        return view;
    }

    /**
     *
     * @Title: initData
     * @Description: 初始化数据
     * @param:
     * @return void
     * @throws
     */
    private void initData() {
        // ListView数据
        list = new ArrayList<Map<String, Object>>();

        myListViewAdapter = new EnchashmentRecordItemAdapter(mActivity, list,
                R.layout.layout_enchashment_record_frag_list_item,
                new String[]{"tv_enchashment_record_frag_item_date",
                        "tv_enchashment_record_frag_item_time",
                        "tv_enchashment_record__frag_enchashment_money",
                        "tv_enchashment_record_frag_item_state",
                        "tv_enchashment_record_frag_item_bank_up",
                        "tv_enchashment_record_frag_item_bank_down",
                        "iv_enchashment_record_frag_item_fail_state",
                        "ll_btn_enchashment_record_frag_item_state"},
                new int[]{R.id.tv_enchashment_record_frag_item_date,
                        R.id.tv_enchashment_record_frag_item_time,
                        R.id.tv_enchashment_record__frag_enchashment_money,
                        R.id.tv_enchashment_record_frag_item_state,
                        R.id.tv_enchashment_record_frag_item_bank_up,
                        R.id.tv_enchashment_record_frag_item_bank_down,
                        R.id.iv_enchashment_record_frag_item_fail_state,
                        R.id.ll_btn_enchashment_record_frag_item_state});

        lv_content.setAdapter(myListViewAdapter);

        ptrv.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                loadMoreTask(presentStatus);
            }
        });
    }

    /**
     *
     * @Title: initView
     * @Description: 初始化布局
     * @param: @param inflater
     * @param: @return
     * @return View
     * @throws
     */
    private View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_recharge_record, null);

        lv_content = (ListView) view.findViewById(R.id.lv_recharge_record_frag);

        ptrv = (AbPullToRefreshView) view
                .findViewById(R.id.ptrv_recharge_record_frag);
        // 设置为不可刷新
        ptrv.setPullRefreshEnable(false);
        return view;
    }

    @Override
    protected void lazyLoad() {
    }

    // 加载更多
    protected void loadMoreTask(String presentStatus) {
        currentPage++;
        doNetwork(presentStatus);
    }

    // 刷新
    protected void refreshTask(String presentStatus) {
        currentPage = 1;
        doNetwork(presentStatus);
    }

    // presentStatus a 成功 b 失败 c 处理中 传值用-分割 例：a-b-c
    private void doNetwork(String presentStatus) {
        // showLoadView();
        String loginUid = MyApplication.getInstance().getLoginUid();
        if (!TextUtils.isEmpty(loginUid)) {
            HaiHeApi.userPresentRecord(loginUid, currentPage + "", "15",
                    presentStatus, new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {

                            UserPresentRecordInfo userPresentRecordInfo = HaiheReturnApi
                                    .userPresentRecord(content);
                            if (userPresentRecordInfo != null) {
                                if (userPresentRecordInfo.getRespCode().equals(
                                        "000")) {
                                    userPresentRecords = userPresentRecordInfo
                                            .getDataList();
                                    // 判断总页数和当前页
                                    if (Integer.parseInt(userPresentRecordInfo
                                            .getCurrentPage()) == Integer
                                            .parseInt(userPresentRecordInfo
                                                    .getPageCount())) {
                                        ptrv.setLoadMoreEnable(false);
                                        ptrv.getFooterView().hide();
                                    } else {
                                        ptrv.setLoadMoreEnable(true);
                                        ptrv.getFooterView().show();
                                    }
                                    processData(userPresentRecordInfo,
                                            currentPage);
                                } else {
                                    AbToastUtil.showToastInThread(mActivity,
                                            userPresentRecordInfo
                                                    .getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            currentPage--;
                            error.printStackTrace();
                            AbToastUtil.showToastInThread(mActivity,
                                    error.getMessage());
                        }
                    });
        }

    }

    /**
     *
     */
    private void processData(UserPresentRecordInfo userPresentRecordInfo,
                             int currentPage) {
        // 如果currentPage 是0,刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        newList = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = null;
        userPresentRecords = userPresentRecordInfo.getDataList();
        for (int i = 0; i < userPresentRecords.size(); i++) {
            map = new HashMap<String, Object>();
            UserPresentRecordInfo.UserPresentRecord userPresentRecord = userPresentRecords.get(i);

            // TODO 时间格式截取 备注问题
            map.put("tv_enchashment_record_frag_item_date",
                    userPresentRecord.getTxDay());
            map.put("tv_enchashment_record_frag_item_time",
                    userPresentRecord.getTxTime());
            map.put("tv_enchashment_record__frag_enchashment_money",
                    userPresentRecord.getTxje());
            map.put("tv_enchashment_record_frag_item_state",
                    userPresentRecord.getTxzt());
            map.put("tv_enchashment_record_frag_item_bank_up",
                    userPresentRecord.getBankName());
            map.put("beizhu", userPresentRecord.getBeizhu());
            map.put("tv_enchashment_record_frag_item_bank_down", "尾号("
                    + userPresentRecord.getBankNo() + ")");
            newList.add(map);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<Map<String, Object>>) newList);
                myListViewAdapter.notifyDataSetChanged();
                newList.clear();
            }

        }

        if (list.size() <= 0) {
            showEmptyView();
            return;
        }
        ptrv.onFooterLoadFinish();
        showContentView();
    }

    /**
     * 设置用到的资源（需要实现）
     */
    public void setResource() {

        this.setEmptyMessage("暂无提现记录");

        this.setRefreshDrawable(R.drawable.ic_error_page);
        this.setRefreshMessage("请求出错,点击我重试");

    }

    @Override
    public void onPause() {
        super.onPause();
        presentStatus = "a-b-c";
    }

    @Override
    public void switchPresentStatus(String presentStatus) {
        currentPage = 1;
        doNetwork(presentStatus);
        this.presentStatus = presentStatus;

    }

}
