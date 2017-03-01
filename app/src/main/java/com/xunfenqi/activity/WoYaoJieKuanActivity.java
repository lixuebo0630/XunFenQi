/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.fragment.wyjiekuan.FenQiFragment;
import com.xunfenqi.fragment.wyjiekuan.YingJiFragment;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;
import com.xunfenqi.view.widget.SegmentButton2;


/**
 *
 */
public class WoYaoJieKuanActivity extends BaseActivity {


    private SegmentButton2 segmentButton;
    private FrameLayout fl_content;
    private FenQiFragment fenQiFragment;
    private YingJiFragment yingJiFragment;
    private FragmentManager fragmentManager;
//    private  static WoYaoJieKuanActivity instance = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        instance = this;
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        instance = this;
//    }

    @Override
    public void initView() {
        setAbContentView(R.layout.activity_woyao_jiekuan);
        segmentButton = (SegmentButton2) findViewById(R.id.sb_woyao_jiekuan_act);


        segmentButton.setOnCheckedChangeListener(new SegmentButton2.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(int position, Button button) {
                // TODO Auto-generated method stub
                setTabSelection(position + 1);
            }

        });


        fl_content = (FrameLayout) findViewById(R.id.fl_woyao_jiekuan_act);
    }

    @Override
    public void initData() {
        // 得到管理器
        fragmentManager = getFragmentManager();
        setTabSelection(1);

    }

    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
        switch (index) {
            case 1:
                if (fenQiFragment == null) {
                    // 如果sanBiaoFragment为空，则创建一个并添加到界面上
                    fenQiFragment = new FenQiFragment();
                    transaction.add(R.id.fl_woyao_jiekuan_act,
                            fenQiFragment);
                } else {
                    // 如果keepHaveFragment不为空，则直接将它显示出来
                    transaction.replace(R.id.fl_woyao_jiekuan_act,
                            fenQiFragment);


                }
                break;
            case 2:
                if (yingJiFragment == null) {
                    yingJiFragment = new YingJiFragment();
                    transaction.add(R.id.fl_woyao_jiekuan_act,
                            yingJiFragment);
                } else {
                    transaction.replace(R.id.fl_woyao_jiekuan_act,
                            yingJiFragment);

                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fenQiFragment != null) {
            transaction.remove(fenQiFragment);
        }
        if (yingJiFragment != null) {
            transaction.remove(yingJiFragment);
        }
    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我要借款");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
