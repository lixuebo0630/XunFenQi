package com.xunfenqi.activity;

import android.graphics.Color;
import android.os.Bundle;
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
import com.xunfenqi.model.domain.VerifyUserTelInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 * 
 * @ClassName: ModifyPhoneNumSetup1Activity
 * @Description: 修改手机号界面1
 * @author Xuebo Li
 * @date 2015-10-12 上午10:13:53
 * 
 */

public class ModifyPhoneNumSetup1Activity extends BaseActivity implements
		OnClickListener {
	protected static final String TAG = "ModifyPhoneNumSetup1Activity";

	private TextView tv_old_phone_num,
			tv_btn_modify_phonenum_setup1_act_get_sound_verify_num;
	private EditText et_input_verify_num;
	private TextView tv_verify_num;
	private Button btn_next;

	private TimeCount time;
	private TimeCount1 yuyintime;

	private String verifyNum;
	private String verifyCode = "";// 服务器验证码
	private String oldPhoneNum;
	private String oldPhone;
	private String flag = "1";

	public static ModifyPhoneNumSetup1Activity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
	}

	@Override
	public void initView() {
		// 设置布局
		setAbContentView(R.layout.activity_modify_phonenum_setup1);

		time = new TimeCount(120000, 1000);
		yuyintime = new TimeCount1(120000, 1000);

		tv_old_phone_num = (TextView) this
				.findViewById(R.id.tv_modify_phonenum_setup1_act_old_phone_num);
		et_input_verify_num = (EditText) this
				.findViewById(R.id.et_modify_phonenum_setup1_act_input_verify_num);
		tv_verify_num = (TextView) this
				.findViewById(R.id.tv_btn_modify_phonenum_setup1_act_get_verify_num);
		tv_btn_modify_phonenum_setup1_act_get_sound_verify_num = (TextView) this
				.findViewById(R.id.tv_btn_modify_phonenum_setup1_act_get_sound_verify_num);
		btn_next = (Button) this
				.findViewById(R.id.btn_modify_phonenum_setup1_act_next);

		oldPhone = MyApplication.getInstance().getLoginUser().getTel();
		if (oldPhone != null) {
			String phone1 = oldPhone.substring(0, 3);
			String phone2 = oldPhone.substring(7, 11);
			oldPhoneNum = phone1 + "****" + phone2;
		}
		tv_old_phone_num.setText(oldPhoneNum);

		tv_verify_num.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		tv_btn_modify_phonenum_setup1_act_get_sound_verify_num
				.setOnClickListener(this);
	}

	@Override
	public void initData() {
	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "验证原手机");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_btn_modify_phonenum_setup1_act_get_verify_num:// 获取验证码
			// 开始计时
			time.start();
			// 获取验证码
			getVerifyNumber();
			break;
		case R.id.tv_btn_modify_phonenum_setup1_act_get_sound_verify_num:// 语音验证码
			sendAudioSms();
			break;

		case R.id.btn_modify_phonenum_setup1_act_next:// 执行下一步
			doNextPhone();
			break;

		default:
			break;
		}
	}

	private void sendAudioSms() {
		if (oldPhone == null || oldPhone.length() < 11) {
			AbToastUtil.showToast(getApplicationContext(), "数据异常");
			return;
		}
		if ("1".equals(flag)) {
			yuyintime.start();
		} else {
			AbToastUtil.showToast(getApplicationContext(), "操作过于频繁，请稍后再试");
			return;
		}
		HaiHeApi.sendAudioSms(oldPhone, "3", new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);

				AudioSmsInfo audioSmsInfo = HaiheReturnApi
						.sendAudioSms(content);

				if (audioSmsInfo != null) {
					if (audioSmsInfo.getRespCode().equals("000")) {
						AbToastUtil.showToastInThread(getApplicationContext(),
								AbConstant.code);
						verifyCode = audioSmsInfo.getVerifyCode().replace(" ",
								"");

					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								audioSmsInfo.getRespCodeDesc());
						return;
					}
					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToast(ModifyPhoneNumSetup1Activity.this,
						error.getMessage());
				error.printStackTrace();
			}
		});
	}

	private void doNextPhone() {
		verifyNum = et_input_verify_num.getText().toString().trim();
		if (null == verifyNum || TextUtils.isEmpty(verifyNum)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
			return;
		}

		if (!verifyCode.equals(verifyNum)) {
			AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
			return;
		}
		ActivityUtil.startActivityForStringData(
				ModifyPhoneNumSetup1Activity.this, null,
				ModifyPhoneNumSetup2Activity.class, null);
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

	/**
	 * 
	 * @Title: getVerifyNumber
	 * @Description: 获取验证码
	 * @param:
	 * @return void
	 * @throws
	 */
	private void getVerifyNumber() {

		HaiHeApi.getVerifyNumber(oldPhone, "4", new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);
				SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
						.sendSmsModelReturn(content);
				if (sendSmsModelInfo != null) {
					if (sendSmsModelInfo.getRespCode().equals("000")) {
						AbToastUtil.showToastInThread(getApplicationContext(),
								"验证码发送成功");
						verifyCode = sendSmsModelInfo.getVerifyCode();
					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								sendSmsModelInfo.getRespCodeDesc());
						return;
					}
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(
						ModifyPhoneNumSetup1Activity.this, error.getMessage());
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
			tv_verify_num.setText("重新获取验证码");
			tv_verify_num.setClickable(true);
			tv_verify_num.setBackgroundColor(Color.parseColor("#ffffff"));
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			tv_verify_num.setClickable(false);
			tv_verify_num.setBackgroundColor(Color.WHITE);
			tv_verify_num.setText(millisUntilFinished / 1000 + "秒后重新获取");
		}
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
		AbDialogUtil.getWaitDialog(ModifyPhoneNumSetup1Activity.this);
		HaiHeApi.verifyUserTel(phoneNumber, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
						.verifyUserTelReturn(content);
				if (verifyUserTelInfo != null) {
					if (verifyUserTelInfo.getRespCode().equals("000")) {
						String isExist = verifyUserTelInfo.getIsExist();
						if ("001".equals(isExist)) {// 手机号已存在
							AbToastUtil.showToast(getApplicationContext(),
									"手机号已存在");
							return;
						} else if ("002".equals(isExist)) {// 手机号不存在,跳转到下一页
							//TODO
//							// 跳转到注册第2步
//							ActivityUtil.startActivityForStringData(
//									ModifyPhoneNumSetup1Activity.this,
//									"phoneNumber", RegistSetup2Activity.class,
//									phoneNumber);
						} else {
							throw new IllegalArgumentException("未知异常");
						}

						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup1Activity.this);
					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								verifyUserTelInfo.getRespCodeDesc());
						AbDialogUtil
								.removeDialog(ModifyPhoneNumSetup1Activity.this);
					}

					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(
						ModifyPhoneNumSetup1Activity.this, error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ModifyPhoneNumSetup1Activity.this);
			}
		});

	}
}
