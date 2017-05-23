/**
 * Project Name:HaiHeFinance
 * File Name:LoginActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-8-17下午3:28:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.global.EventPost;
import com.xunfenqi.model.domain.LoginInfo;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbWifiUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.SettingUtils;

import de.greenrobot.event.EventBus;


/**
 * @date: 2015-8-17 下午3:28:29
 * @author: XueBo Li
 * @version:
 * @description: 登录界面
 * @see
 */
public class LoginActivity extends AbActivity implements OnClickListener {

	protected static final String TAG = "LoginActivity";
	private EditText et_username;
	private EditText et_password;
	private Button bt_login;
	private TextView forget_password;
	private TextView bt_regist;
	private String inputUsername;
	private String inputPassword;
	private ImageView bt_finish;
	private ImageButton bt_show_pwd;

	private boolean isShowPwd = false;

	public static LoginActivity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		instance = this;
		initActionBar();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		instance = null;

	}

	public void initView() {
		setAbContentView(R.layout.activity_login);

		et_username = (EditText) findViewById(R.id.login_et_username);
		et_password = (EditText) findViewById(R.id.login_et_password);
		forget_password = (TextView) findViewById(R.id.btn_login_act_forgetpwd);
		bt_login = (Button) findViewById(R.id.login_bt_login);
		bt_regist = (TextView) findViewById(R.id.btn_login_act_to_regist);
		bt_finish = (ImageView) findViewById(R.id.iv_btn_login_act_finish);
		bt_show_pwd = (ImageButton) findViewById(R.id.ib_btn_login_show_pwd);

		forget_password.setOnClickListener(this);
		bt_login.setOnClickListener(this);
		bt_regist.setOnClickListener(this);
		bt_finish.setOnClickListener(this);
		bt_show_pwd.setOnClickListener(this);
	}

	public void initActionBar() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_bt_login:
			handleLogin();
			break;

		case R.id.btn_login_act_to_regist:
			ActivityUtil.startActivity(LoginActivity.this,
					RegistActivity.class);
			break;
		case R.id.btn_login_act_forgetpwd:
			ActivityUtil.startActivity(LoginActivity.this,
					ForgetPwsSetup1Activity.class);
			break;
		case R.id.iv_btn_login_act_finish:
			finish();
			break;
		case R.id.ib_btn_login_show_pwd:
			if (!isShowPwd) {
				isShowPwd = true;
				et_password
						.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				bt_show_pwd.setImageResource(R.drawable.login_show_pwd_y);
			} else {
				isShowPwd = false;
				et_password.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				bt_show_pwd.setImageResource(R.drawable.login_show_pwd_n);
			}
			break;
		default:
			break;
		}
	}

	private void handleLogin() {
		if (!prepareForLogin()) {
			return;
		}
		loginNet();

	}

	protected void loginNet() {
		AbDialogUtil.getWaitDialog(LoginActivity.this);
		HaiHeApi.login(inputUsername, inputPassword, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				LoginInfo loginReturn = HaiheReturnApi.loginReturn(content);
				if (loginReturn != null) {
					if (loginReturn.getRespCode().equals("000")) {
						// 防止用户串号
						if (loginReturn.getUserName().equals(inputUsername)
								|| loginReturn.getUserTel().equals(
										inputUsername)) {
							// 登录成功后清除之前在sp中保存的信息|||
							MyApplication.getInstance().cleanLoginInfo();
							MyApplication.getInstance().setLoginUid(
									loginReturn.getUserId());

							SettingUtils.getInstance(LoginActivity.this)
									.saveValue("LoginName", inputUsername);

							EventBus.getDefault().post(new EventPost());

							queryUserInfo(loginReturn.getUserId());
							EventBus.getDefault().post(AbConstant.MY_ACCOUNT_REFRESH);
						} else {
							AbToastUtil.showToastInThread(
									getApplicationContext(), "用户名或密码错误,请重新输入");
							return;
						}
					} else {
						AbDialogUtil.removeDialog(LoginActivity.this);
						AbToastUtil.showToastInThread(getApplicationContext(),
								loginReturn.getRespCodeDesc());
					}
				} else {
					AbDialogUtil.removeDialog(LoginActivity.this);
					AbToastUtil.showToastInThread(LoginActivity.this,
							"数据异常,请重试");
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				error.printStackTrace();
				AbToastUtil.showToastInThread(LoginActivity.this,
						error.getMessage());
			}
		});
	}

	private boolean prepareForLogin() {
		if (!AbWifiUtil.isConnectivity(LoginActivity.this)) {
			AbToastUtil.showToast(LoginActivity.this, "网络异常 请稍后再试或检查网络");
			return false;
		}
		inputUsername = et_username.getText().toString().trim();
		inputPassword = et_password.getText().toString().trim();
		if ("".equals(inputUsername)) {
			AbToastUtil.showToast(this, "用户名不能为空");
			return false;
		}
		if ("".equals(inputPassword)) {
			AbToastUtil.showToast(this, "密码不能为空");
			return false;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		// 跳转到首页
		CallBackManager.getInstance().sendSwitchRadio(0);
		LoginActivity.this.finish();
		super.onBackPressed();
	}

	protected void queryUserInfo(String userId) {
		/**
		 * 查询用户信息
		 */
		HaiHeApi.queryUserInfo2(userId, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				UserCenterInfo userCenterInfo = HaiheReturnApi
						.queryUserReturn2(content);
				if (userCenterInfo != null) {
					if ("000".equals(userCenterInfo.getRespCode())) {
						AbDialogUtil.removeDialog(LoginActivity.this);

						// 操作成功,给用户赋值
						MyApplication.getInstance()
								.saveUserInfo(userCenterInfo);
						// 跳转到设置手势密码界面
						ActivityUtil.startActivity(LoginActivity.this,
								SetNineLockActivity.class);
						// 设置标记

						if (MyApplication.getInstance().getNineFlag() != 3
								&& MyApplication.getInstance().getNineFlag() != 4) {// 为了回到标的详情(没登录状态)
							MyApplication.getInstance().setNineFlag(
									MyApplication.LOGIN_SUCCESS);
						}

						// 结束当前页面
						finish();
					} else {
						// 清除用户数据
						MyApplication.getInstance().cleanLoginInfo();
						AbToastUtil.showToastInThread(LoginActivity.this,
								userCenterInfo.getRespCodeDesc());
					}
				} else {
					AbDialogUtil.removeDialog(LoginActivity.this);
					AbToastUtil.showToastInThread(LoginActivity.this,
							"数据异常,请重新登录");
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbDialogUtil.removeDialog(LoginActivity.this);
				error.printStackTrace();
				AbToastUtil.showToastInThread(LoginActivity.this,
						error.getMessage());
			}
		});

	}
}
