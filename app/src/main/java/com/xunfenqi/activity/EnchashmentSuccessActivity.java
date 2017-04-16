/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.chinazccy.manpuman.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 *
 * @ClassName: EnchashmentSuccessActivity
 * @Description: 提现成功界面
 * @author Xuebo Li
 * @date 2015-10-16 上午10:42:50
 *
 */
public class EnchashmentSuccessActivity extends BaseActivity implements
        OnClickListener {
    private Button bt_to_my_account, bt_to_invest;

    @Override
    public void initView() {
        setAbContentView(R.layout.activity_enchashment_success);
        bt_to_my_account = (Button) findViewById(R.id.bt_enchashment_act_to_my_account);
        bt_to_invest = (Button) findViewById(R.id.bt_enchashment_act_to_invest);
        bt_to_invest.setOnClickListener(this);
        bt_to_my_account.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "提现成功");
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), 0,
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_enchashment_act_to_my_account:

                finish();
                ActivityUtil.startActivity(this,MainActivity.class);
                CallBackManager.getInstance().sendSwitchRadio(2);
                Intent intent = new Intent();
            /* 设置Intent对象的action属性 */
                intent.setAction(AbConstant.REFRESH_MYACCOUNT_ACTION);
			/* 发布广播 */
                sendBroadcast(intent);

                break;
            case R.id.bt_enchashment_act_to_invest:
                finish();
                ActivityUtil.startActivity(this,MainActivity.class);
                CallBackManager.getInstance().sendSwitchRadio(0);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
		/* 设置Intent对象的action属性 */
        intent.setAction(AbConstant.REFRESH_MYACCOUNT_ACTION);
		/* 发布广播 */
        sendBroadcast(intent);
    }

}
