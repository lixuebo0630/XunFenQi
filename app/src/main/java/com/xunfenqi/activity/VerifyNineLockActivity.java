/** * Project Name:HaiHeAndroid
 * File Name:VerifyNineLockActivity.java
 * Package Name:com.haihejinrong.ninelock
 * Date:2015-8-13下午2:19:35
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.service.HaiheService;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.DoubleClickExitHelper;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.ninelock.GestureContentView;
import com.xunfenqi.view.ninelock.GestureDrawline.GestureCallBack;

/**
 * @date: 2015-8-13 下午2:19:35
 * @author: XueBo Li
 * @version:
 * @description: 验证手势解锁界面
 * @see
 */
public class VerifyNineLockActivity extends FragmentActivity implements
		OnClickListener {

	/** 手机号码 */
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private RelativeLayout mTopLayout;
	private TextView mTextTitle;
	private ImageView mImgUserLogo;
	private TextView mTextPhoneNumber;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	//private TextView mTextOther;
	private String mParamPhoneNumber;
	private long mExitTime = 0;
	private int mParamIntentCode;

	private DoubleClickExitHelper mDoubleClickExit;
	private String password;

	private int wrongCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		password = SettingUtils.getInstance(VerifyNineLockActivity.this)
				.getValue(AbConstant.PW_NINELOCK, "");
		if (TextUtils.isEmpty(password) || password.length() < 4) {
			SettingUtils.getInstance(getApplicationContext()).saveValue("lock",
					false);
			finish();
			return;
		}

		setContentView(R.layout.activity_gesture_verify);

		mDoubleClickExit = new DoubleClickExitHelper(this);
		ObtainExtraData();
		setUpViews();
		setUpListeners();
	}

	private void ObtainExtraData() {
		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
	}

	private void setUpViews() {
		mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
		mTextPhoneNumber = (TextView) findViewById(R.id.tv_act_verify_nine_text_phone_number);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		//mTextOther = (TextView) findViewById(R.id.text_other_account);
		String userName = MyApplication.getInstance().getLoginUser()
				.getUserName();
		if (!userName.equals("")) {
			mTextPhoneNumber.setText("欢迎您，" + userName);
		}
		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this, true, password,
				new GestureCallBack() {
					@Override
					public void onGestureCodeInput(String inputCode) {
					}

					@Override
					public void checkedSuccess() {
						mGestureContentView.clearDrawlineState(0L);
						SettingUtils.getInstance(getApplicationContext())
								.saveValue("lock", false);
						SettingUtils.getInstance(getApplicationContext())
								.saveValue(AbConstant.NINE_WRONG_COUNT, 0);
						HaiheService.setFlag(false);
						VerifyNineLockActivity.this.finish();
					}

					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						wrongCount++;
						mTextTip.setText(Html
								.fromHtml("<font color='#24c4ff'>错误次数"
										+ wrongCount + "，还可以输入"
										+ (5 - wrongCount) + "次</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils
								.loadAnimation(VerifyNineLockActivity.this,
										R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);

						if ((5 - wrongCount) <= 0) {
							AbToastUtil.showToast(getApplicationContext(),
									"您已连输5次输错手势,请重新登录");

							MyApplication.getInstance().cleanLoginInfo();

							ActivityUtil.startActivityAndFinish(
									VerifyNineLockActivity.this,
									LoginActivity.class);

						}
					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}

	private void setUpListeners() {
		mTextForget.setOnClickListener(this);
		//mTextOther.setOnClickListener(this);
	}

	private String getProtectedMobile(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(phoneNumber.subSequence(0, 3));
		builder.append("****");
		builder.append(phoneNumber.subSequence(7, 11));
		return builder.toString();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 忘记手势密码
		case R.id.text_forget_gesture:
			new SweetAlertDialog(VerifyNineLockActivity.this,
					SweetAlertDialog.CUSTOM_IMAGE_TYPE)
					.setTitleText("")
					.setContentText("忘记手势密码,需重新登录")
					.setCancelText("取消")
					.setConfirmText("确认")
					.showCancelButton(true)
					.setCancelClickListener(
							new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sDialog) {
									sDialog.dismiss();
								}
							})
					.setConfirmClickListener(
							new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sDialog) {
									sDialog.dismiss();
									MyApplication.getInstance()
											.cleanLoginInfo();
									ActivityUtil.startActivity(
											VerifyNineLockActivity.this,
											LoginActivity.class);
								}
							}).show();
			break;
		// 切换到其他账号
//		case R.id.text_other_account:
//			ActivityUtil.startActivity(VerifyNineLockActivity.this,
//					LoginActivity.class);
//			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否退出应用
			return mDoubleClickExit.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		wrongCount = 0;
	}
}
