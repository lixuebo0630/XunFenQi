/**
 * Project Name:HaiHeFinance
 * File Name:NoDoubleClickListener.java
 * Package Name:com.haihefinance.base
 * Date:2015-8-26下午3:06:14
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.base;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * @date: 2015-8-26 下午3:06:14
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public abstract class NoDoubleClickListener implements OnClickListener {

	public static final int MIN_CLICK_DELAY_TIME = 1000;
	private long lastClickTime = 0;

	@Override
	public void onClick(View v) {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
			lastClickTime = currentTime;
		//	AbLogUtil.e(MyApplication.getInstance(), "NoDoubleClickListener");
			onNoDoubleClick(v);
		}
	}

	public abstract void onNoDoubleClick(View v);
}