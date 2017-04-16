/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.view.View;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 *
 */
public class HuanKuanSuccessActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void initView() {

        setAbContentView(R.layout.activity_huankuan_success);
        findViewById(R.id.bt_my_apply_success_act_2home).setOnClickListener(this);
        findViewById(R.id.bt_my_apply_success_act_2myaccount).setOnClickListener(this);


    }

    @Override
    public void initData() {
    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "还款成功");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_my_apply_success_act_2home:
                ActivityUtil.startActivityAndFinish(this,MainActivity.class);
                CallBackManager.getInstance().sendSwitchRadio(0);
                break;
            case R.id.bt_my_apply_success_act_2myaccount:
                ActivityUtil.startActivityAndFinish(this,MainActivity.class);
                CallBackManager.getInstance().sendSwitchRadio(2);
                break;

            default:
                break;
        }
    }
}
