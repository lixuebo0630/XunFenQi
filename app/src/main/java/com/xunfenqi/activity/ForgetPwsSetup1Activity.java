/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.AudioSmsInfo;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.model.domain.VerifyUserTelInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.MatchUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 * @date: 2015-9-17 下午1:40:51
 * @author: XueBo Li
 * @version:
 * @description: 找回密码
 * @see
 */
public class ForgetPwsSetup1Activity extends BaseActivity implements
		OnClickListener {

	protected static final String TAG = "ForgetPwsSetup1Activity";
	private EditText pwd1_phonenumberText;
	// 获取验证码的按钮
	private TextView tv_getVerifyNumber;
	// 获取语音验证码
	private TextView tv_soundverify;
	private EditText verify_numberText;
	private Button bt_forget_pwd1;
	// 服务器得到验证码
	private String verifyCode="";
	// 计时器
	private TimeCount time;
	private TimeCount1 yuyintime;
	private String pwd1_phone;
	private String verify_number;

	private String flag = "1";

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_forget_pwd_setup1);

		// 计时器
		time = new TimeCount(120000, 1000);
		yuyintime = new TimeCount1(120000, 1000);
		pwd1_phonenumberText = (EditText) this
				.findViewById(R.id.et_forget_pwd1_phonenumber);
		verify_numberText = (EditText) this
				.findViewById(R.id.et_forget_pwd1_input_verify_number);
		tv_getVerifyNumber = (TextView) this
				.findViewById(R.id.btn_forget_pwd2_act_getVerifyNumber);
		tv_soundverify = (TextView) this
				.findViewById(R.id.btn_forget_pwd1_act_getsoundverify);
		bt_forget_pwd1 = (Button) findViewById(R.id.btn_forget_pwd1_act_next);

		tv_getVerifyNumber.setOnClickListener(this);
		bt_forget_pwd1.setOnClickListener(this);
		tv_soundverify.setOnClickListener(this);

	}

	@Override
	public void initData() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_forget_pwd2_act_getVerifyNumber:// 获取验证码
			pwd1_phone = pwd1_phonenumberText.getText().toString().trim();
			// 获取验证码
			getVerifyNumber(pwd1_phone);
			break;

		case R.id.btn_forget_pwd1_act_getsoundverify:// 获取语音验证码
			sendAudioSms();
			break;
		case R.id.btn_forget_pwd1_act_next:// 下一步
			forgetPwd1();
			break;

		default:
			break;
		}
	}

	private void sendAudioSms() {
		pwd1_phone = pwd1_phonenumberText.getText().toString().trim();
		if (TextUtils.isEmpty(pwd1_phone)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}
		if (!MatchUtil.isPhoneNum(pwd1_phone)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不合法");
			return;
		}

		HaiHeApi.verifyUserTel(pwd1_phone, new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")
							|| verifyUserTelInfo.getRespCode().equals("112")) {
						String isExist = verifyUserTelInfo.getIsExist();

						if ("001".equals(isExist)) {// 手机号存在
							// 开始计时
							if ("1".equals(flag)) {
								yuyintime.start();
							} else {
								AbToastUtil.showToast(getApplicationContext(),
										"操作过于频繁，请稍后再试");
								return;
							}
							// 发送短信
							sendAudioSms(pwd1_phone);

						} else if ("002".equals(isExist)) {
							AbToastUtil.showToast(getApplicationContext(),
									"手机号不存在");
							return;
						} else {
							throw new IllegalArgumentException("未知异常");
						}

						AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
					} else {
						AbToastUtil.showToast(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
					}

					// TODO 这里判断其他情况
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(ForgetPwsSetup1Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);

			}

			private void sendAudioSms(String pwd1_phone) {
				HaiHeApi.sendAudioSms(pwd1_phone, "6", new AbSoapListener() {
					@Override
					public void onSuccess(int statusCode, String content) {
						AbLogUtil.d(TAG, content);
						AudioSmsInfo audioSmsInfo = HaiheReturnApi
								.sendAudioSms(content);
						if (audioSmsInfo != null) {
							if (audioSmsInfo.getRespCode().equals("000")) {
								AbToastUtil.showToast(getApplicationContext(),
										AbConstant.code);
								verifyCode = audioSmsInfo.getVerifyCode()
										.replace(" ", "");
							} else {
								AbToastUtil.showToast(getApplicationContext(),
										audioSmsInfo.getRespCodeDesc());
								return;
							}
							// TODO 这里判断其他情况
						}

					}

					@Override
					public void onFailure(int statusCode, String content,
							Throwable error) {
						AbToastUtil.showToastInThread(
								ForgetPwsSetup1Activity.this,
								error.getMessage());
						error.printStackTrace();
					}
				});
			}
		});

	}

	private void forgetPwd1() {
		String pwdPhone = pwd1_phonenumberText.getText().toString().trim();
		verify_number = verify_numberText.getText().toString().trim();
		if (pwdPhone == null || TextUtils.isEmpty(pwdPhone)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}
		if (!MatchUtil.isPhoneNum(pwdPhone)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不合法");
			return;
		}
		if (verify_number == null || TextUtils.isEmpty(verify_number)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
			return;
		}

		if (!verifyCode.equals(verify_number)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
			return;
		}
		if(!pwdPhone.equals(pwd1_phone)){
			AbToastUtil.showToast(getApplicationContext(), "请重新发送验证码");
			return;
		}

		HaiHeApi.verifyUserTel(pwd1_phone, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")
							|| verifyUserTelInfo.getRespCode().equals("112")) {
						String isExist = verifyUserTelInfo.getIsExist();
						if ("001".equals(isExist)) {
							ActivityUtil.startActivityForStringData(
									ForgetPwsSetup1Activity.this, "userTel",
									ForgetPwsSetup2Activity.class,
									verifyUserTelInfo.getUserTel());

						} else if ("002".equals(isExist)) {// 手机号不存在
							AbToastUtil.showToastInThread(
									getApplicationContext(), "手机号不存在");
							AbDialogUtil
									.removeDialog(ForgetPwsSetup1Activity.this);
							return;
						} else {
							AbToastUtil.showToastInThread(
									getApplicationContext(), "未知错误,请重试");
						}

					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
					}
					// TODO 这里判断其他情况
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(ForgetPwsSetup1Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
			}
		});

	}

	/**
	 * 
	 * @Title: getVerifyNumber
	 * @Description: 获取验证码
	 * @param:
	 * @return void
	 * @throws
	 */
	private void getVerifyNumber(final String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}
		if (!MatchUtil.isPhoneNum(phoneNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不合法");
			return;
		}
		HaiHeApi.verifyUserTel(phoneNumber, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")
							|| verifyUserTelInfo.getRespCode().equals("112")) {
						String isExist = verifyUserTelInfo.getIsExist();
						if ("001".equals(isExist)) {// 手机号存在
							// 开始计时
							time.start();
							// 发送短信
							sendMessage(phoneNumber);
						} else if ("002".equals(isExist)) {
							AbToastUtil.showToast(getApplicationContext(),
									"手机号不存在");
							return;
						} else {
							throw new IllegalArgumentException("未知异常");
						}
						AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);
					}
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(ForgetPwsSetup1Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ForgetPwsSetup1Activity.this);

			}

			private void sendMessage(String phoneNumber) {
				HaiHeApi.getVerifyNumber(phoneNumber, "8",
						new AbSoapListener() {
							@Override
							public void onSuccess(int statusCode, String content) {
								AbLogUtil.d(TAG, content);
								SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
										.sendSmsModelReturn(content);

								if (sendSmsModelInfo != null) {
									if (sendSmsModelInfo.getRespCode().equals(
											"000")) {
										AbToastUtil.showToast(
												getApplicationContext(),
												"验证码发送成功");
										verifyCode = sendSmsModelInfo
												.getVerifyCode();
									} else {
										AbToastUtil.showToastInThread(
												getApplicationContext(),
												sendSmsModelInfo
														.getRespCodeDesc());
										return;
									}
									// TODO 这里判断其他情况
								}

							}

							@Override
							public void onFailure(int statusCode,
									String content, Throwable error) {
								AbToastUtil.showToastInThread(
										ForgetPwsSetup1Activity.this,
										error.getMessage());
								error.printStackTrace();
							}
						});

			}
		});
	}

	@Override
	public void initActionBar() {
		// 初始化ActionBar
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "找回密码");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	/**
	 * 
	 * @ClassName: TimeCount
	 * @Description: 计时器
	 * @author Xuebo Li
	 * @date 2015-9-15 下午3:59:06
	 * 
	 */
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			tv_getVerifyNumber.setText("重新获取验证码");
			tv_getVerifyNumber.setClickable(true);
			tv_getVerifyNumber.setBackgroundColor(Color.parseColor("#ffffff"));
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			tv_getVerifyNumber.setClickable(false);
			tv_getVerifyNumber.setBackgroundColor(Color.WHITE);
			tv_getVerifyNumber.setText(millisUntilFinished / 1000 + "秒后重新获取");
		}
	}

	class TimeCount1 extends CountDownTimer {

		public TimeCount1(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			flag = "1";
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			flag = "2";
		}
	}
}
