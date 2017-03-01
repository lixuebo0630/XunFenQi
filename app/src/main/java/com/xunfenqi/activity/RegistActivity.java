/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

/**
 *
 */
public class RegistActivity extends BaseActivity {


	@Override
	public void initView() {
		setAbContentView(R.layout.activity_regist);
	}

	@Override
	public void initData() {}

	@Override
	public void initActionBar() {
		// 初始化ActionBar
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "注册");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}
}
