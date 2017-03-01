/**
 * Project Name:HaiHeAndroid
 * File Name:SplashActivity.java
 * Package Name:com.haihejinrong.activity
 * Date:2015-8-13下午5:49:52
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbActivity;
import com.xunfenqi.model.domain.SplashImg;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.SettingUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @date: 2015-8-13 下午5:49:52
 * @author: XueBo Li
 * @version:
 * @description:SplashActivity
 * @see
 */
public class SplashActivity extends AbActivity {

	public static final int GOMAIN = 1;// 跳转变量
	public static final int MFLAG = 2;// 跳转变量
	public static final int GOMAINNEXT = 3;// 跳转变量

	private Handler handler = new Handler();
	private RelativeLayout rl_top;
	private RelativeLayout rl_bottom;
	private RelativeLayout mAid_next;
	private ImageView iv_bottom;
	private TextView tv_time;
	public int time = 4;
	private boolean Tag = true;
	private Runnable runnable;
	private Timer mTimer;

	private ImageLoader imageLoader = ImageLoader.getInstance();

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {


			switch (msg.what) {
			case MFLAG:
				if (0 >= time) {
					tv_time.setText(0 + "");
				} else {
					tv_time.setText(time + "");
				}
				break;

			case GOMAIN:

				if (Tag) {
					firstActivity();
				}
				break;
			default:
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initImageLoader(this);
		initView();
		getImagePath();
		mTimer = new Timer(true);
		try {
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					time--;
					Message msg = Message.obtain();
					msg.what = MFLAG;
					mHandler.sendMessage(msg);
				}
			}, 1000, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mHandler.postDelayed(runnable = new Runnable() {
			@Override
			public void run() {
				Message msg = Message.obtain();
				msg.what = GOMAIN;
				mHandler.sendMessage(msg);
			}
		}, 4000);

		iv_bottom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		mAid_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (runnable != null) {
							mHandler.removeCallbacks(runnable);
						}
						// 点击跳过,立刻停止
						mTimer.cancel();
						Message msg = Message.obtain();
						msg.what = GOMAIN;
						mHandler.sendMessage(msg);
					}
				}, 0);

			}
		});

	}

	DisplayImageOptions options = new DisplayImageOptions.Builder()
			// .showImageOnLoading(R.drawable.ic_error_page)
			// .showImageOnFail(R.drawable.ic_error_page)
			.cacheInMemory(true).cacheOnDisk(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();

	private void getImagePath() {
		HaiHeApi.getSplashImg(new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				SplashImg imgInfo = HaiheReturnApi.getSplashImg(content);
				if (imgInfo != null) {
					String imgPath = imgInfo.getImgPath();
					initData();
					imageLoader.displayImage(imgPath, iv_bottom, options);
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				initData();
			}
		});
	}

	public void initView() {

		setAbContentView(R.layout.activity_splash);

		rl_top = (RelativeLayout) findViewById(R.id.rl_splash_act_top);
		rl_bottom = (RelativeLayout) findViewById(R.id.rl_splash_act_bottom);
		mAid_next = (RelativeLayout) findViewById(R.id.aid_next);
		iv_bottom = (ImageView) findViewById(R.id.iv_splash_act_bottom);
		tv_time = (TextView) findViewById(R.id.tv_time);
		SettingUtils.getInstance(getApplicationContext()).saveValue("lock",
				true);
	}

	public void initData() {

			showBottom();

	}

	private void showBottom() {

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {


				rl_top.setVisibility(View.GONE);
				rl_bottom.setVisibility(View.VISIBLE);
				Message msg = Message.obtain();
				msg.what = MFLAG;
				mHandler.sendMessage(msg);

			}
		}, 1000);

	}

	private void firstActivity() {
		try {
			boolean isFirstStart = MyApplication.isFristStart();

			int versionCode = getPackageManager().getPackageInfo(
					getPackageName(), PackageManager.GET_CONFIGURATIONS).versionCode;

			int oldVersionCode = SettingUtils.getInstance(getApplication())
					.getValue("vcode", 1);

			if (isFirstStart || versionCode != oldVersionCode) {
				MyApplication.setFristStart(false);

				SettingUtils.getInstance(MyApplication.getInstance())
						.saveValue("vcode", versionCode);
//
				ActivityUtil.startActivityAndFinish(SplashActivity.this,
						GuideViewPagerActivity.class);

			} else {

				ActivityUtil.startActivityAndFinish(SplashActivity.this,
						MainActivity.class);
			}

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		// 极光推送统计
//		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 极光推送统计
//		JPushInterface.onPause(this);

	}

	@Override
	protected void onDestroy() {
		mTimer.cancel();
		super.onDestroy();
	}

	/**
	 * ImageLoader 图片组件初始化
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove
				.build();
		ImageLoader.getInstance().init(config);
	}
}
