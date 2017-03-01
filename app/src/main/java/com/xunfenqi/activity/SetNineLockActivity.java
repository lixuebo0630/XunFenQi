/**
`1 * Project Name:HaiHeAndroid
 * File Name:SetNineLockActivity.java
 * Package Name:com.haihejinrong.ninelock
 * Date:2015-8-13下午2:04:55
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.view.ninelock.GestureContentView;
import com.xunfenqi.view.ninelock.GestureDrawline.GestureCallBack;

/**
 * @date: 2015-8-13 下午2:04:55
 * @author: XueBo Li
 * @version:
 * @description:手势解锁设置界面
 * @see
 */
public class SetNineLockActivity extends Activity implements OnClickListener {

	/** 手机号码 */
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	/** 首次提示绘制手势密码，可以选择跳过 */
	public static final String PARAM_IS_FIRST_ADVICE = "PARAM_IS_FIRST_ADVICE";
	private TextView mTextTitle;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextReset;
	private String mParamSetUpcode = null;
	private String mParamPhoneNumber;
	private boolean mIsFirstInput = true;
	private String mFirstPassword = null;
	private String mConfirmPassword = null;
	private int mParamIntentCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		initActionBar();
	}

	public void initView() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set_ninelock);
		setUpViews();
		setUpListeners();
	}

	public void initActionBar() {

	}

	private void setUpViews() {

		mTextTitle = (TextView) findViewById(R.id.tv_set_nine_text_phone_number);
		UserCenterInfo loginUser = MyApplication.getInstance().getLoginUser();
		if (loginUser != null) {
			setTextTitle(loginUser);
		} else {
			AbToastUtil.showToast(getApplicationContext(), "数据异常,请重新登录");
			ActivityUtil.startActivityAndFinish(this, LoginActivity.class);
		}

		mTextReset = (TextView) findViewById(R.id.text_reset);
		mTextReset.setClickable(false);
		mTextTip = (TextView) findViewById(R.id.tv_set_nine_text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this, false, "",
				new GestureCallBack() {
					@Override
					public void onGestureCodeInput(String inputCode) {
						// 判断手势密码长度
						if (!isInputPassValidate(inputCode)) {
							mTextTip.setText(Html
									.fromHtml("<font color='#24c4ff'>最少链接4个点, 请重新输入</font>"));
							mGestureContentView.clearDrawlineState(500L);
							return;
						}
						if (mIsFirstInput) {
							mFirstPassword = inputCode;
							mGestureContentView.clearDrawlineState(0L);
							mTextReset.setClickable(true);
							mTextReset
									.setText(getString(R.string.reset_gesture_code));
							mTextTip.setText(Html
									.fromHtml("<font color='#FA8E15'>请再绘制进行确认</font>"));
						} else {
							if (inputCode.equals(mFirstPassword)) {
								mGestureContentView.clearDrawlineState(0L);
								mTextTip.setText(Html
										.fromHtml("<font color='#FA8E15'>设置成功</font>"));

								SettingUtils.getInstance(
										getApplicationContext()).saveValue(
										"lock", false);

								SettingUtils.getInstance(
										SetNineLockActivity.this).saveValue(
										AbConstant.PW_NINELOCK, inputCode);
								int nineFlag = MyApplication.getInstance()
										.getNineFlag();
								if (nineFlag == 1) {// 跳转到首页
									ActivityUtil.startActivityAndFinish(
											SetNineLockActivity.this,
											MainActivity.class);
								} else if (nineFlag == 2) {// 跳转到注册成功页
									ActivityUtil.startActivityAndFinish(
											SetNineLockActivity.this,
											RegistSuccessActivity.class);
								} else if (nineFlag == 3) {// 修改手势密码
									// SetNineLockActivity.this.finish();
								}
								else if (nineFlag == 4) {
//									if (HuoQiMaiJinActivity.instance != null) {
//										HuoQiMaiJinActivity.instance.finish();
//									}
//
//									ActivityUtil.startActivityAndFinish(
//									 		SetNineLockActivity.this,
//											HuoQiMaiJinActivity.class);
								}
								SetNineLockActivity.this.finish();
							} else {
								mTextTip.setText(Html
										.fromHtml("<font color='#24c4ff'>确认失败,请重新设置手势密码</font>"));
								// 左右移动动画
								Animation shakeAnimation = AnimationUtils
										.loadAnimation(
												SetNineLockActivity.this,
												R.anim.shake);
								mTextTip.startAnimation(shakeAnimation);
								// 保持绘制的线，1.5秒后清除
								mGestureContentView.clearDrawlineState(1300L);
								mIsFirstInput = true;
								return;
							}
						}
						mIsFirstInput = false;
					}

					@Override
					public void checkedSuccess() {

					}

					@Override
					public void checkedFail() {

					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}

	/**
	 * 
	 * @Title: setTextTitle
	 * @Description: 设置用户名
	 * @param: @param loginUser
	 * @return void
	 * @throws
	 */
	private void setTextTitle(UserCenterInfo loginUser) {
		if (!"".equals(loginUser.getUserName())
				|| "null".equals(loginUser.getUserName())) {
			mTextTitle.setText("欢迎您，" + loginUser.getUserName());
		}
	}

	private void setUpListeners() {
		mTextReset.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_reset:
			mIsFirstInput = true;
			mTextTip.setText(getString(R.string.set_gesture_pattern));
			break;
		default:
			break;
		}
	}

	private boolean isInputPassValidate(String inputPassword) {
		if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
			return false;
		}
		return true;
	}

}
