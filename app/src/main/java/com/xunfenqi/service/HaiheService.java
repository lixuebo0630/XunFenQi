/** Project Name:HaiHeFinance
 * File Name:HaiheService.java
 * Package Name:com.haihefinance.service
 * Date:2015-8-18下午4:05:48
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.service;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.SettingUtils;

/**
 * @date: 2015-8-18 下午4:05:48
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class HaiheService extends Service {

	public static final String TAG = HaiheService.class.getSimpleName();
	// 开始记录
	public static final int START_RECORD = 0;
	// 结束记录
	public static final int CANCEL_RECORD = 1;
	// 退出
	public static final int LOGOUT = 2;

	public static boolean flag = false;

	public static synchronized boolean isFlag() {
		return flag;
	}

	public static synchronized void setFlag(boolean flag) {
		HaiheService.flag = flag;
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			AbLogUtil.d(TAG, "flag = " + isFlag());
			switch (msg.what) {
			case LOGOUT:
				if (!isFlag()) {
					return;
				}
				AbLogUtil.d(TAG, "退出手势密码");
				SettingUtils.getInstance(getApplicationContext()).saveValue(
						"lock", true);
				stopSelf();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			switch (intent.getFlags()) {
			case START_RECORD:
				AbLogUtil.d(getApplicationContext(), "START_RECORD");
				setFlag(true);
				mHandler.removeMessages(LOGOUT);
				// 发一个延迟消息
				mHandler.sendEmptyMessageDelayed(LOGOUT, 300000);
				break;
			case CANCEL_RECORD:
				mHandler.removeMessages(LOGOUT);
				AbLogUtil.d(getApplicationContext(), "CANCEL_RECORD");
				setFlag(false);

				break;
			default:
				break;
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
