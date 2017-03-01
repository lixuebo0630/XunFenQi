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



        Button bt_qiandao = (Button) view.findViewById(R.id.bt_invite_frag_2qiandao);


        bt_qiandao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.getInstance().isLogin()){
                    ActivityUtil.startActivity(mActivity, QianDaoActivity.class);
                }else {
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


}
