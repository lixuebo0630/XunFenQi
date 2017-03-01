/**
 * Project Name:HaiHeFinance
 * File Name:HomeFragment.java
 * Package Name:com.haihefinance.fragment
 * Date:2015-8-20下午5:22:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.activity.LoginActivity;
import com.xunfenqi.activity.MessageActivity;
import com.xunfenqi.activity.MyInviteActivity;
import com.xunfenqi.activity.MyJieKuanActivity;
import com.xunfenqi.activity.MyRedActivity;
import com.xunfenqi.activity.MyZhangDanActivity;
import com.xunfenqi.activity.RegistActivity;
import com.xunfenqi.activity.SafeSettingActivity;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.MyBaseFragment;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.widget.RunningTextView;

import de.greenrobot.event.EventBus;

import static com.xunfenqi.R.id.loading;

/**
 * @date: 2015-8-20 下午5:22:09
 * @author: XueBo Li
 * @version:
 * @description:主界面--首页碎片
 * @see
 */
public class MyFragment extends MyBaseFragment implements OnClickListener {

    private Activity mActivity;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private RelativeLayout rl_my_invite, rl_safe_setting;
    private AbPullToRefreshView ptrv;
    private RelativeLayout rl_unlogin;
    private TextView tv_username, tv_lastdate_1, tv_lastdate_2, tv_dhze, tv_kyed, tv_kyye;
    private ImageView iv_message;
    private RunningTextView tv_byyh;
    private LoadingLayout loadingView;


    private int mCurIndex = -1;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        loadingView.setStatus(LoadingLayout.Loading);
        doNetwork();
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        mActivity = this.getActivity();
        EventBus.getDefault().register(this);
        View view = initView(inflater);

        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_my, null);


        isPrepared = true;
        loadingView = (LoadingLayout) view.findViewById(loading);
        loadingView.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                doNetwork();
            }
        });

        rl_unlogin = (RelativeLayout) view
                .findViewById(R.id.rl_myact_act_unlogin);
        ptrv = (AbPullToRefreshView) view.findViewById(R.id.ptrv_my_frag);

        if (MyApplication.getInstance().isLogin()) {
            lazyLoad();
            rl_unlogin.setVisibility(View.GONE);
            ptrv.setVisibility(View.VISIBLE);
        } else {
            loadingView.setStatus(LoadingLayout.Success);
            rl_unlogin.setVisibility(View.VISIBLE);
            ptrv.setVisibility(View.GONE);
        }


        view.findViewById(R.id.bt_myaccount_frag_unlogin_tologin)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getInstance().setNineFlag(1);
                        ActivityUtil.startActivity(mActivity,
                                LoginActivity.class);
                    }
                });
        view.findViewById(R.id.bt_myaccount_frag_unlogin_toregist)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.startActivity(mActivity,
                                RegistActivity.class);
                    }
                });
        tv_username = (TextView) view.findViewById(R.id.tv_my_frag_top_username);
        tv_lastdate_1 = (TextView) view.findViewById(R.id.tv_my_frag_lastdate_1);
        tv_lastdate_2 = (TextView) view.findViewById(R.id.tv_my_frag_lastdate_2);
        tv_dhze = (TextView) view.findViewById(R.id.tv_my_frag_dhze);
        tv_kyed = (TextView) view.findViewById(R.id.tv_my_frag_kyed);
        tv_byyh = (RunningTextView) view.findViewById(R.id.tv_my_frag_byyh);
        tv_kyye = (TextView) view.findViewById(R.id.tv_my_account_frag_kyye);

        Button bt_ljhk = (Button) view.findViewById(R.id.btn_my_frag_ljhk);
        Button bt_tixian = (Button) view.findViewById(R.id.btn_myaccount_frag_bottom_tixian);
        Button bt_recharge = (Button) view.findViewById(R.id.btn_myaccount_frag_bottom_recharge);
        rl_my_invite = (RelativeLayout) view.findViewById(R.id.rl_btn_myaccount_frag_invite_friends);
        rl_safe_setting = (RelativeLayout) view.findViewById(R.id.rl_btn_myaccount_frag_safesetting);
        iv_message = (ImageView) view.findViewById(R.id.iv_btn_frag_myaccount_message);


        view.findViewById(R.id.rl_btn_myaccount_frag_my_red).setOnClickListener(this);
        view.findViewById(R.id.rl_btn_myaccount_frag_bottom_wdjk).setOnClickListener(this);
        rl_safe_setting.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        bt_tixian.setOnClickListener(this);
        bt_recharge.setOnClickListener(this);
        rl_my_invite.setOnClickListener(this);

        bt_ljhk.setOnClickListener(this);
        ptrv.setLoadMoreEnable(false);
        ptrv.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                doNetwork();
            }
        });
        this.setAbFragmentOnLoadListener(new AbFragmentOnLoadListener() {
            @Override
            public void onLoad() {
                showContentView();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        if (MyApplication.getInstance().isLogin()) {
            rl_unlogin.setVisibility(View.GONE);
            ptrv.setVisibility(View.VISIBLE);
        } else {
            rl_unlogin.setVisibility(View.VISIBLE);
            ptrv.setVisibility(View.GONE);
        }
    }


    // 刷新
    protected void refreshTask() {
        doNetwork();
    }


    private void doNetwork() {

        {
            String loginUid = MyApplication.getInstance().getLoginUid();

            if (loginUid != null) {

                HaiHeApi.queryUseInfo(loginUid, new AbSoapListener() {

                    @Override
                    public void onSuccess(int statusCode, String content) {

                        mHasLoadedOnce = true;
                        UserCenterInfo info = HaiheReturnApi.
                                queryUserRetur(content);
                        if (info != null) {
                            if ("000".equals(info.getRespCode())) {
                                // 操作成功,给用户赋值
                                MyApplication.getInstance()
                                        .saveUserInfo(info);
                                tv_byyh.setText(info.getByyh());
                                tv_dhze.setText(info.getDhze());
                                tv_kyed.setText(info.getKyed());
                                tv_kyye.setText(info.getKyye());
                                tv_username.setText(info.getLoginName());
                                tv_lastdate_1.setText(info.getZhhkry());
                                tv_lastdate_2.setText(info.getZhhkrr());
                                loadingView.setStatus(LoadingLayout.Success);
                            } else {

                                loadingView.setStatus(LoadingLayout.Error);
                                AbToastUtil.showToastInThread(
                                        mActivity,
                                        info.getRespCodeDesc());
                            }
                        }
                        ptrv.onHeaderRefreshFinish();
                        AbDialogUtil.removeDialog(mActivity);
                    }

                    @Override
                    public void onFailure(int statusCode, final String content,
                                          Throwable error) {
                        error.printStackTrace();
                        loadingView.setStatus(LoadingLayout.No_Network);
                        ptrv.onHeaderRefreshFinish();
                        AbToastUtil.showToastInThread(mActivity,

                                error.getMessage());
                    }
                });
            }
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_btn_myaccount_frag_invite_friends:

                ActivityUtil.startActivity(mActivity, MyInviteActivity.class);
                break;
            case R.id.rl_btn_myaccount_frag_safesetting:

                ActivityUtil.startActivity(mActivity, SafeSettingActivity.class);
                break;
            case R.id.iv_btn_frag_myaccount_message:

                ActivityUtil.startActivity(mActivity, MessageActivity.class);
                break;
            case R.id.rl_btn_myaccount_frag_my_red:

                ActivityUtil.startActivity(mActivity, MyRedActivity.class);
                break;
            case R.id.btn_my_frag_ljhk://立即还款

                ActivityUtil.startActivity(mActivity, MyZhangDanActivity.class);
                break;
            case R.id.btn_myaccount_frag_bottom_tixian://提现

                ActivityUtil.startActivity(mActivity, MessageActivity.class);
                break;
            case R.id.btn_myaccount_frag_bottom_recharge://充值

                ActivityUtil.startActivity(mActivity, MessageActivity.class);
                break;
            case R.id.rl_btn_myaccount_frag_bottom_wdjk://我的借款

                ActivityUtil.startActivity(mActivity, MyJieKuanActivity.class);
                break;
            default:
                break;
        }
    }


    public void onEvent(String event) {
        switch (event) {
            case AbConstant.MY_ACCOUNT_REFRESH:
                doNetwork();
                break;

            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}