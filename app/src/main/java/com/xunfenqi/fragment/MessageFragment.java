/**
 * Project Name:HaiHeFinance
 * File Name:MyAccountFragment.java
 * Package Name:com.haihefinance.fragment
 * Date:2015-8-20下午5:25:30
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.MessageCenterListAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbFragment;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.global.CheckAllMessageCallback;
import com.xunfenqi.model.domain.UserMessageDetailInfo;
import com.xunfenqi.model.domain.UserMessageInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.view.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName: MessageFragment
 * @Description: 消息界面
 * @author Xuebo Li
 * @date 2015-10-28 下午5:24:43
 *
 */
public class MessageFragment extends AbFragment implements CheckAllMessageCallback {

    private static final String TAG = "MessageFragment";
    private AbPullToRefreshView ptrv;
    private Activity mActivity;
    // 数据
    private List<UserMessageInfo.UserMessage> list = null;
    private List<UserMessageInfo.UserMessage> newList = null;
    private ListView lv_list;
    private StringBuilder sb;

    private List<UserMessageInfo.UserMessage> userMessages;
    // //存放消息id的集合
    // private List<String> idList = new ArrayList<String>();

    private int currentPage = 1;
    // 数据适配器
    private MessageCenterListAdapter adapter;
    private String ids = "";

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        CallBackManager.getInstance().setCheckAllMessageCallback(this);
        mActivity = this.getActivity();
        View view = initView(inflater);
        initData();
        this.setAbFragmentOnLoadListener(new AbFragmentOnLoadListener() {

            @Override
            public void onLoad() {
                refreshTask();
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
        list = new ArrayList<UserMessageInfo.UserMessage>();
        if (adapter == null) {
            adapter = new MessageCenterListAdapter(mActivity, list,
                    new MessageCenterListAdapter.MessageCenterCallback() {

                        @Override
                        public void readMessage(int position,
                                                boolean isExpanded, String state, String msg_id) {
                        }
                    });
        } else {
            adapter.notifyDataSetChanged();
        }
        lv_list.setAdapter(adapter);

        lv_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                AbToastUtil.showToast(mActivity, "" + position);
            }
        });
        ptrv.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                loadMoreTask();
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
        sb = new StringBuilder();
        View view = inflater.inflate(R.layout.frag_message, null);
        lv_list = (ListView) view.findViewById(R.id.lv_message_activity_list);
        ptrv = (AbPullToRefreshView) view.findViewById(R.id.ptrv_message_act);
        ptrv.setPullRefreshEnable(false);

        return view;
    }

    protected void checkAllMessage() {
        String loginUid = MyApplication.getInstance().getLoginUid();
        AbDialogUtil.getWaitDialog(mActivity);
        if (loginUid != null) {
            HaiHeApi.userMessageDetail(loginUid, "", "3", new AbSoapListener() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    UserMessageDetailInfo userMessageInfo = HaiheReturnApi
                            .userMessageDetail(content);
                    if (userMessageInfo != null) {
                        if (userMessageInfo.getRespCode().equals("000")) {
                            if (list != null) {
                                for (UserMessageInfo.UserMessage userMessage : list) {
                                    userMessage.setCkzt("已读取");
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            AbToastUtil.showToastInThread(mActivity,
                                    userMessageInfo.getRespCodeDesc());
                        }
                    }
                    AbDialogUtil.removeDialog(mActivity);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbDialogUtil.removeDialog(mActivity);
                    AbToastUtil.showToastInThread(mActivity, error.getMessage());
                }
            });
        }

    }

    // 加载更多
    protected void loadMoreTask() {
        currentPage++;
        doNetwork();
    }

    // 刷新
    protected void refreshTask() {
        currentPage = 1;
        doNetwork();
    }

    private void doNetwork() {
        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            HaiHeApi.userMessage(loginUid, currentPage + "", "15",
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            UserMessageInfo userMessageInfo = HaiheReturnApi
                                    .userMessage(content);
                            if (userMessageInfo != null) {
                                if (userMessageInfo.getRespCode().equals("000")) {
                                    userMessages = userMessageInfo
                                            .getDataList();
                                    // 判断总页数和当前页
                                    if (Integer.parseInt(userMessageInfo
                                            .getCurrentPage()) == Integer
                                            .parseInt(userMessageInfo
                                                    .getPageCount())) {
                                        ptrv.setLoadMoreEnable(false);
                                    } else {
                                        ptrv.setLoadMoreEnable(true);
                                        ptrv.getFooterView().show();
                                    }
                                    processData(userMessageInfo, currentPage);
                                } else {
                                    AbToastUtil.showToastInThread(mActivity,
                                            userMessageInfo.getRespCodeDesc());
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

    private void processData(UserMessageInfo userMessageInfo, int currentPage) {
        // 如果currentPage 是0,刷新数据
        if (currentPage == 1) {
            list.clear();
        }
        newList = new ArrayList<UserMessageInfo.UserMessage>();
        Map<String, Object> map = null;
        userMessages = userMessageInfo.getDataList();
        for (int i = 0; i < userMessages.size(); i++) {
            UserMessageInfo.UserMessage userMessage = userMessages.get(i);
            newList.add(userMessage);
            if (newList != null && newList.size() > 0) {
                list.addAll((List<UserMessageInfo.UserMessage>) newList);
                adapter.notifyDataSetChanged();
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
        this.setEmptyMessage("暂无消息");
        this.setRefreshDrawable(R.drawable.ic_error_page);
        this.setRefreshMessage("请求出错,点击我重试");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        /* 设置Intent对象的action属性 */
        intent.setAction(AbConstant.REFRESH_MYACCOUNT_ACTION);
		/* 发布广播 */
        mActivity.sendBroadcast(intent);

    }

    @Override
    public void checkAll() {
        checkAllMessage();
    }
}
