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

import com.xunfenqi.R;
import com.xunfenqi.activity.LoginActivity;
import com.xunfenqi.activity.QianDaoActivity;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbFragment;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.view.AbPullToRefreshView;

import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @date: 2015-8-20 下午5:22:09
 * @author: XueBo Li
 * @version:
 * @description:主界面--首页碎片
 * @see
 */
public class InviteFragment extends AbFragment implements OnClickListener {

    private Activity mActivity;


    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        mActivity = this.getActivity();
        View view = initView(inflater);

        return view;
    }

    private View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_invite, null);
        AbPullToRefreshView ptrv = (AbPullToRefreshView) view.findViewById(R.id.ptrv_invite_frag);

        ptrv.setLoadMoreEnable(false);
        ptrv.setPullRefreshEnable(false);


        Button bt_qiandao = (Button) view.findViewById(R.id.bt_invite_frag_2qiandao);
        Button bt_share = (Button) view.findViewById(R.id.bt_invite_frag_2share);



        bt_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });


        bt_qiandao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().isLogin()) {
                    ActivityUtil.startActivity(mActivity, QianDaoActivity.class);
                } else {
                    ActivityUtil.startActivity(mActivity, LoginActivity.class);
                }
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


    }


    // 刷新
    protected void refreshTask() {
        doNetwork();
    }


    private void doNetwork() {
    }


    @Override
    public void onClick(View v) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle("分享标题--Title");
        oks.setTitleUrl("http://mob.com");
        oks.setText("分享测试文--Text");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(mActivity);
    }
}
