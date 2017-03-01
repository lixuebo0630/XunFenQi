/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-9下午2:28:29
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
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.AudioSmsInfo;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.model.domain.UserEditPayPwInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: SetDealPwdActivity
 * @Description: 设置交易密码
 * @author Xuebo Li
 * @date 2015-9-24 下午4:59:44
 * 
 */

public class SetDealPwdActivity extends BaseActivity implements OnClickListener {

	protected static final String TAG = "RegistSetup2Activity";

	private EditText payPwsText;
	private EditText payConfirmPwsText;
	private TextView tv_usernameText, tv_btn_set_dealpwd_act_getsoundverify;
	private TextView tv_verifyNumberText;
	private Button btn_confirm;
	private EditText et_input_verify_number;
	private SweetAlertDialog waitSLoadingDialog;

	// 计时器
	private TimeCount time;
	private TimeCount1 yuyintime;

	private String payPws;
	private String payConfirmPws;
	private String phoneNumber;
	private String verifyCode = "";
	private String inputVerifyNumber;

	private String flag = "1";

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_set_deal_pwd);

		// 计时器
		time = new TimeCount(120000, 1000);
		yuyintime = new TimeCount1(120000, 1000);

		payPwsText = (EditText) this
				.findViewById(R.id.et_set_dealpwd_act_deal_pwd);
		payConfirmPwsText = (EditText) this
				.findViewById(R.id.et_set_dealpwd_act_confim_deal_pwd);
		tv_usernameText = (TextView) this
				.findViewById(R.id.tv_set_dealpwd_act_username);
		tv_verifyNumberText = (TextView) this
				.findViewById(R.id.tv_btn_set_dealpwd_act_getVerifyNumber);
		et_input_verify_number = (EditText) this
				.findViewById(R.id.et_set_dealpwd_act_input_verify_number);
		btn_confirm = (Button) this
				.findViewById(R.id.btn_set_dealpwd_act_confirm);
		tv_btn_set_dealpwd_act_getsoundverify = (TextView) this
				.findViewById(R.id.tv_btn_set_dealpwd_act_getsoundverify);
		String loginName = MyApplication.getInstance().getLoginUser()
				.getLoginName();
		phoneNumber = MyApplication.getInstance().getLoginUser().getTel();
		tv_usernameText.setText("用户名：" + loginName);

		tv_verifyNumberText.setOnClickListener(this);
		btn_confirm.setOnClickListener(this);
		tv_btn_set_dealpwd_act_getsoundverify.setOnClickListener(this);

	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_btn_set_dealpwd_act_getVerifyNumber:// 获取验证码
			// 开始计时
			time.start();
			// 获取验证码
			getVerifyNumber();
			break;

		case R.id.tv_btn_set_dealpwd_act_getsoundverify:// 获取语音验证码
			sendAudioSms();
			break;

		case R.id.btn_set_dealpwd_act_confirm:// 提交
			doSubmit();
			break;

		default:
			break;
		}

	}

	private void sendAudioSms() {
		if (phoneNumber == null || phoneNumber.length() < 11) {
			AbToastUtil.showToast(getApplicationContext(), "数据异常");
			return;
		}
		if ("1".equals(flag)) {
			yuyintime.start();
		} else {
			AbToastUtil.showToast(getApplicationContext(), "操作过于频繁，请稍后再试");
			return;
		}
		HaiHeApi.sendAudioSms(phoneNumber, "2", new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);

				AudioSmsInfo audioSmsInfo = HaiheReturnApi
						.sendAudioSms(content);

				if (audioSmsInfo != null) {
					if (audioSmsInfo.getRespCode().equals("000")) {
						AbToastUtil.showToast(getApplicationContext(),
								AbConstant.code);
						verifyCode = audioSmsInfo.getVerifyCode().replace(" ",
								"");

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
				AbToastUtil.showToast(SetDealPwdActivity.this,
						error.getMessage());
				error.printStackTrace();
			}
		});
	}

	private void doSubmit() {
		payPws = payPwsText.getText().toString().trim();
		payConfirmPws = payConfirmPwsText.getText().toString().trim();
		inputVerifyNumber = et_input_verify_number.getText().toString().trim();

		if (inputVerifyNumber == null || TextUtils.isEmpty(inputVerifyNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
			return;
		}
		if (payPws == null || "".equals(payPws)) {
			AbToastUtil.showToast(getApplicationContext(), "交易密码不能为空");
			return;
		}
		if (payConfirmPws == null || "".equals(payConfirmPws)) {
			AbToastUtil.showToast(getApplicationContext(), "确认交易密码不能为空");
			return;
		}
		if (antiSqlValid(payPws)) {
			if (payPws.length() < 6) {
				AbToastUtil.showToast(getApplicationContext(), "交易密码长度不能小于6");
				return;
			}
			if (payPws.length() > 20) {
				AbToastUtil.showToast(getApplicationContext(), "交易密码长度不能大于20");
				return;
			}
			if (payPws.matches("^[0-9]*$")) {
				AbToastUtil.showToast(getApplicationContext(), "交易密码不能全为数字");
				return;
			}
			if (payPws.matches("[a-zA-Z]+")) {
				AbToastUtil.showToast(getApplicationContext(), "交易密码不能全为英文字母");
				return;
			}
		} else {
			AbToastUtil.showToast(getApplicationContext(), "交易密码不能输入非法字符");
			return;
		}
		if (!payPws.equals(payConfirmPws)) {
			AbToastUtil.showToast(getApplicationContext(), "交易密码与确认密码不一致");
			return;
		}
		if (!verifyCode.equals(inputVerifyNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
			return;
		}

		updatePayPws();// 修改交易密码
	}

	private void updatePayPws() {
		HaiHeApi.userEditPayPw(MyApplication.getInstance().getLoginUid(),
				payPws, new AbSoapListener() {
					@Override
					public void onSuccess(int statusCode, String content) {
						UserEditPayPwInfo userEditPayPw = HaiheReturnApi
								.userEditPayPw(content);
						if (userEditPayPw != null) {
							if (userEditPayPw.getRespCode().equals("000")) {
								SettingUtils.getInstance(
										SetDealPwdActivity.this).saveValue(
										SettingUtils.PW, "0");
								new SweetAlertDialog(SetDealPwdActivity.this,
										SweetAlertDialog.CUSTOM_IMAGE_TYPE)
										.setTitleText("提示")
										.setContentText("交易密码设置成功")
										.setConfirmText("确认")
										.setConfirmClickListener(
												new SweetAlertDialog.OnSweetClickListener() {
													@Override
													public void onClick(
															SweetAlertDialog sDialog) {
														if (ModifyDealPwdActivity.instance != null) {
															ModifyDealPwdActivity.instance
																	.finish();
															ModifyDealPwdActivity.instance = null;
														}
//														if (RnameAuthActivity.instance != null) {
//															RnameAuthActivity.instance
//																	.finish();
//															RnameAuthActivity.instance = null;
//														}
														SetDealPwdActivity.this
																.finish();
													}
												}).show();
							} else {
								AbToastUtil.showToastInThread(getApplicationContext(),
										userEditPayPw.getRespCodeDesc());
							}
						}

					}

					@Override
					public void onFailure(int statusCode, String content,
							Throwable error) {
						error.printStackTrace();
						AbToastUtil.showToastInThread(SetDealPwdActivity.this,
								error.getMessage());
					}
				});

	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "设置交易密码");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	/**
	 * 
	 * @Title: getVerifyNumber
	 * @Description: 获取验证码
	 * @param:
	 * @return void
	 * @throws
	 */
	private void getVerifyNumber() {
		if (phoneNumber == null || phoneNumber.length() < 11) {
			AbToastUtil.showToast(getApplicationContext(), "数据异常");
			return;
		}

		HaiHeApi.getVerifyNumber(phoneNumber, "3", new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);

				SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
						.sendSmsModelReturn(content);

				if (sendSmsModelInfo != null) {
					if (sendSmsModelInfo.getRespCode().equals("000")) {
						AbToastUtil.showToast(getApplicationContext(),
								"验证码发送成功");
						verifyCode = sendSmsModelInfo.getVerifyCode();

					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								sendSmsModelInfo.getRespCodeDesc());
						return;
					}
					// TODO 这里判断其他情况
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(SetDealPwdActivity.this,
						error.getMessage());
				error.printStackTrace();
			}
		});
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
			tv_verifyNumberText.setText("重新获取验证码");
			tv_verifyNumberText.setClickable(true);
			tv_verifyNumberText.setBackgroundColor(Color.parseColor("#ffffff"));
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			tv_verifyNumberText.setClickable(false);
			tv_verifyNumberText.setBackgroundColor(Color.WHITE);
			tv_verifyNumberText.setText(millisUntilFinished / 1000 + "秒后重新获取");
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

	private boolean antiSqlValid(String oldPws) {
		oldPws = oldPws.toLowerCase();
		String reg = "and|exec|insert|select|delete|update|count|drop|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|order|by|count|like|%|&|\\*|'|,|\"|;|>|</i";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(oldPws);
		if (matcher.find()) {
			return false;
		} else {
			return true;
		}
	}

}
