/**
 * Project Name:HaiHeFinance
 * File Name:MessageActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-10下午5:51:25
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.fragment.MessageFragment;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


public class MessageActivity extends BaseActivity {

	private FragmentManager fragmentManager;
	private MessageFragment messageFragment;

	@Override
	public void initView() {

		setAbContentView(R.layout.activity_message);
	}

	@Override
	public void initData() {
		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		messageFragment = new MessageFragment();
		transaction.replace(R.id.fl_message_frag_content, messageFragment)
				.commit();
	}

	@Override
	public void initActionBar() {
		// 初始化ActionBar
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "消息");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), 0,
				UIUtils.dip2px(14));
		// 添加右边注册按钮
		TextView tv = new TextView(this);
		tv.setText("全部已读");
		tv.setTextSize(14);
		tv.setTextColor(Color.WHITE);
		tTitleBar.getRightLayout().setPadding(0, 0, UIUtils.px2dip(60), 0);
		tTitleBar.addRightView(tv);

		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CallBackManager.getInstance().checkAllMsg();
			}
		});

	}

}
