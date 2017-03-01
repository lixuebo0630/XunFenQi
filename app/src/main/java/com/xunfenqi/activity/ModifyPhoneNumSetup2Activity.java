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
import com.xunfenqi.model.domain.UserEditTelInfo;
import com.xunfenqi.model.domain.VerifyUserTelInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.MatchUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

/**
 * 
 * @ClassName: ModifyPhoneNumSetup1Activity
 * @Description: 修改手机号界面2
 * @author Xuebo Li
 * @date 2015-10-12 上午10:13:53
 * 
 */

public class ModifyPhoneNumSetup2Activity extends BaseActivity implements
		OnClickListener {
	protected static final String TAG = "ModifyPhoneNumSetup2Activity";

	private TextView et_new_phonenum,
			tv_btn_modify_phonenum_setup2_act_get_sound_verify_num;
	private EditText et_input_verify_num;
	private TextView tv_btn_verify_num;
	private Button btn_conmit;

	private TimeCount time;
	private TimeCount1 yuyintime;

	private String phoneNumber;
	private String input_verify_num;
	private String verifyCode="";
	private String flag = "1";

	@Override
	public void initView() {
		// 设置布局
		setAbContentView(R.layout.activity_modify_phonenum_setup2);

		time = new TimeCount(120000, 1000);
		yuyintime = new TimeCount1(120000, 1000);

		et_new_phonenum = (TextView) this
				.findViewById(R.id.et_modify_phonenum_setup2_act_new_phonenum);
		et_input_verify_num = (EditText) this
				.findViewById(R.id.et_modify_phonenum_setup2_act_input_verify_num);
		tv_btn_verify_num = (TextView) this
				.findViewById(R.id.tv_btn_modify_phonenum_setup2_act_get_verify_num);
		tv_btn_modify_phonenum_setup2_act_get_sound_verify_num = (TextView) this
				.findViewById(R.id.tv_btn_modify_phonenum_setup2_act_get_sound_verify_num);
		btn_conmit = (Button) this
				.findViewById(R.id.btn_modify_phonenum_setup2_act_conmit);

		tv_btn_verify_num.setOnClickListener(this);
		btn_conmit.setOnClickListener(this);
		tv_btn_modify_phonenum_setup2_act_get_sound_verify_num
				.setOnClickListener(this);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "验证新手机");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_btn_modify_phonenum_setup2_act_get_verify_num:// 获取验证码
			// 获取验证码
			getVerifyNumber();
			break;
		case R.id.tv_btn_modify_phonenum_setup2_act_get_sound_verify_num:// 语音验证码
			sendAudioSms();
			break;
		case R.id.btn_modify_phonenum_setup2_act_conmit:// 提交
			doSubmit();
			break;
		default:
			break;
		}
	}

	private void sendAudioSms() {
		phoneNumber = et_new_phonenum.getText().toString().trim();
		if (phoneNumber == null || TextUtils.isEmpty(phoneNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}

		AbDialogUtil.getWaitDialog(ModifyPhoneNumSetup2Activity.this);
		HaiHeApi.verifyUserTel(phoneNumber, new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")
							|| verifyUserTelInfo.getRespCode().equals("112")) {
						String isExist = verifyUserTelInfo.getIsExist();

						if ("001".equals(isExist)) {// 手机号已存在
							AbToastUtil.showToast(getApplicationContext(),
									"手机号已存在,请重新输入");
							AbDialogUtil
									.removeDialog(ModifyPhoneNumSetup2Activity.this);
							return;
						} else {
							if ("1".equals(flag)) {
								yuyintime.start();
							} else {
								AbToastUtil.showToast(getApplicationContext(),
										"操作过于频繁，请稍后再试");
								AbDialogUtil
										.removeDialog(ModifyPhoneNumSetup2Activity.this);
								return;
							}
							HaiHeApi.sendAudioSms(phoneNumber, "4",
									new AbSoapListener() {

										@Override
										public void onSuccess(int statusCode,
												String content) {
											AbLogUtil.d(TAG, content);

											AudioSmsInfo audioSmsInfo = HaiheReturnApi
													.sendAudioSms(content);

											if (audioSmsInfo != null) {
												if (audioSmsInfo.getRespCode()
														.equals("000")) {
													AbToastUtil
															.showToast(
																	getApplicationContext(),
																	AbConstant.code);
													verifyCode = audioSmsInfo
															.getVerifyCode()
															.replace(" ", "");

												} else {
													AbToastUtil
															.showToast(
																	getApplicationContext(),
																	audioSmsInfo
																			.getRespCodeDesc());
													return;
												}
												// TODO 这里判断其他情况
											}

										}

										@Override
										public void onFailure(int statusCode,
												String content, Throwable error) {
											AbToastUtil
													.showToast(
															ModifyPhoneNumSetup2Activity.this,
															error.getMessage());
											error.printStackTrace();
										}
									});
						}
						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup2Activity.this);
					} else {
						AbToastUtil.showToast(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup2Activity.this);
					}

					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToast(ModifyPhoneNumSetup2Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ModifyPhoneNumSetup2Activity.this);
			}
		});

	}

	private void doSubmit() {
		String pNumber = et_new_phonenum.getText().toString().trim();
		input_verify_num = et_input_verify_num.getText().toString().trim();
		if (pNumber == null || TextUtils.isEmpty(pNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}
		if (null == input_verify_num || TextUtils.isEmpty(input_verify_num)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
			return;
		}
		if (!MatchUtil.isPhoneNum(pNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不合法");
			return;
		}
		if (!verifyCode.equals(input_verify_num)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
			return;
		}

		if(!pNumber.equals(phoneNumber)){
			AbToastUtil.showToast(getApplicationContext(), "请重新发送验证码");
			return;
		}
		
		
		HaiHeApi.userEditTel(MyApplication.getInstance().getLoginUser()
				.getUserId(), phoneNumber, new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);

				UserEditTelInfo userEditTel = HaiheReturnApi
						.userEditTel(content);

				if (userEditTel != null) {
					if (userEditTel.getRespCode().equals("000")) {
						final String tel = userEditTel.getUserTel();
						new SweetAlertDialog(ModifyPhoneNumSetup2Activity.this,
								SweetAlertDialog.CUSTOM_IMAGE_TYPE)
								.setTitleText("提示")
								.setContentText("手机号修改成功")
								.setConfirmText("确认")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												if (ModifyPhoneNumSetup1Activity.instance != null) {
													ModifyPhoneNumSetup1Activity.instance
															.finish();
												}
												ModifyPhoneNumSetup2Activity.this
														.finish();
												SettingUtils
														.getInstance(
																ModifyPhoneNumSetup2Activity.this)
														.saveValue(
																SettingUtils.TEL,
																tel);
												sDialog.dismiss();

											}
										}).show();
					} else {
						AbToastUtil.showToast(getApplicationContext(),
								userEditTel.getRespCodeDesc());
						return;
					}
					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToast(ModifyPhoneNumSetup2Activity.this,
						error.getMessage());
				error.printStackTrace();
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
	private void getVerifyNumber() {
		phoneNumber = et_new_phonenum.getText().toString().trim();

		if (phoneNumber == null || TextUtils.isEmpty(phoneNumber)) {

			AbToastUtil.showToast(getApplicationContext(), "手机号不能为空");
			return;
		}
		if (!MatchUtil.isPhoneNum(phoneNumber)) {
			AbToastUtil.showToast(getApplicationContext(), "手机号不合法");
			return;
		}

		VerifyPhoneNumber(phoneNumber);// 验证手机号是否存在
	}

	/**
	 * 
	 * @Title: VerifyPhoneNumber
	 * @Description: 验证手机号是否存在
	 * @param:
	 * @return void
	 * @throws
	 */
	private void VerifyPhoneNumber(final String phoneNumber) {
		AbDialogUtil.getWaitDialog(ModifyPhoneNumSetup2Activity.this);
		HaiHeApi.verifyUserTel(phoneNumber, new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")
							|| verifyUserTelInfo.getRespCode().equals("112")) {
						String isExist = verifyUserTelInfo.getIsExist();

						if ("001".equals(isExist)) {// 手机号已存在
							AbToastUtil.showToast(getApplicationContext(),
									"手机号已存在,请重新输入");
							AbDialogUtil
									.removeDialog(ModifyPhoneNumSetup2Activity.this);
							return;
						} else {
							// 开始计时
							time.start();
							HaiHeApi.getVerifyNumber(phoneNumber, "5",
									new AbSoapListener() {

										@Override
										public void onSuccess(int statusCode,
												String content) {
											AbLogUtil.d(TAG, content);

											SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
													.sendSmsModelReturn(content);

											if (sendSmsModelInfo != null) {
												if (sendSmsModelInfo
														.getRespCode().equals(
																"000")) {
													AbToastUtil
															.showToast(
																	getApplicationContext(),
																	"验证码发送成功");
													verifyCode = sendSmsModelInfo
															.getVerifyCode();

												} else {
													AbToastUtil
															.showToast(
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
											AbToastUtil
													.showToast(
															ModifyPhoneNumSetup2Activity.this,
															error.getMessage());
											error.printStackTrace();
										}
									});
						}
						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup2Activity.this);
					} else {
						AbToastUtil.showToast(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup2Activity.this);
					}

					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToast(ModifyPhoneNumSetup2Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ModifyPhoneNumSetup2Activity.this);
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
			tv_btn_verify_num.setText("重新获取验证码");
			tv_btn_verify_num.setClickable(true);
			tv_btn_verify_num.setBackgroundColor(Color.parseColor("#ffffff"));
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			tv_btn_verify_num.setClickable(false);
			tv_btn_verify_num.setBackgroundColor(Color.WHITE);
			tv_btn_verify_num.setText(millisUntilFinished / 1000 + "秒后重新获取");
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
