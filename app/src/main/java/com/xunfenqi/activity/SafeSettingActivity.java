/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-9下午2:28:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.AppManager;
import com.xunfenqi.global.EventPost;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.model.domain.UserSecuritySafeInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import de.greenrobot.event.EventBus;

/**
 * @date: 2015-9-9 下午2:28:29
 * @author: XueBo Li
 * @version:
 * @description: 安全设置界面
 * @see
 */
public class SafeSettingActivity extends BaseActivity implements
		OnClickListener {
	// 显示账户的View
	private TextView tv_count_name;
	private RelativeLayout bt_rname_auth;
	private TextView tv_rname_auth;
	private RelativeLayout bt_bind_bankcard;
	private TextView tv_bind_bankcard;
	private RelativeLayout bt_deal_pwd;
	private TextView tv_deal_pwd;
	private RelativeLayout bt_modify_login_pwd;
	private RelativeLayout bt_modify_ninelock_pwd;
	private RelativeLayout bt_modify_phone_number;
	private RelativeLayout bt__logout;

	private String pw;
	private String jjmm;
	private String zsxmAndSfzh;
	private String cardlast;
	private String ssyh;
	private String zhmc;
	private String proName;
	private String cityName;
	private String proCode;
	private String cityCode;
	private String imagePath;

	public static SafeSettingActivity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		EventBus.getDefault().register(this);
	}

	@Override
	public void initView() {
		AppManager.getAppManager().addActivity(this);
		setAbContentView(R.layout.activity_safe_setting);
		tv_count_name = (TextView) findViewById(R.id.tv_safe_setting_act_count_name);
		bt_rname_auth = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_rname_auth);
		tv_rname_auth = (TextView) findViewById(R.id.tv__safe_set_act_rname_auth);
		bt_bind_bankcard = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_bind_bank_card);
		tv_bind_bankcard = (TextView) findViewById(R.id.tv__safe_set_act_bind_bank_card);
		bt_deal_pwd = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_deal_pwd);
		tv_deal_pwd = (TextView) findViewById(R.id.tv__safe_set_act_deal_pwd);
		bt_modify_login_pwd = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_modify_login_pwd);
		bt_modify_ninelock_pwd = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_modify_ninelock_pwd);
		bt_modify_phone_number = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_modify_phone_number);
		bt__logout = (RelativeLayout) findViewById(R.id.rl_btn_safe_set_act_logout);
		bt_rname_auth.setOnClickListener(this);
		bt_bind_bankcard.setOnClickListener(this);
		bt_deal_pwd.setOnClickListener(this);
		bt_modify_login_pwd.setOnClickListener(this);
		bt_modify_ninelock_pwd.setOnClickListener(this);
		bt_modify_phone_number.setOnClickListener(this);
		bt__logout.setOnClickListener(this);

	}

	@Override
	public void initData() {
		String loginUid = MyApplication.getInstance().getLoginUid();

		if (loginUid != null) {

			AbDialogUtil.getWaitDialog(this);
			HaiHeApi.userSecuritySafe(loginUid, new AbSoapListener() {

				@Override
				public void onSuccess(int statusCode, String content) {
					UserSecuritySafeInfo info = HaiheReturnApi
							.userSecuritySafe(content);
					if (info != null) {
						if ("000".equals(info.getRespCode())) {
							proName = info.getProName();
							cityName = info.getCityName();
							zsxmAndSfzh = info.getZsxmAndSfzh();
							cardlast = info.getCardlast();
							zhmc = info.getZhmc();
							proCode = info.getCityCode();
							cityCode = info.getCityCode();
							imagePath = info.getImgPath();
							SettingUtils.getInstance(SafeSettingActivity.this)
									.saveValue("fzhmc", zhmc);

							String zsxm = info.getZsxm();
							jjmm = info.getJjmm();
							ssyh = info.getSsyh();
							if (zsxmAndSfzh != null && !"".equals(zsxmAndSfzh)
									&& !"null".equals(zsxmAndSfzh)) {
								tv_rname_auth.setText(zsxmAndSfzh);
								tv_rname_auth.setCompoundDrawables(null, null,
										null, null);

							} else {
								tv_rname_auth.setText("未认证，点击认证");
							}
							if (cardlast != null && !"".equals(cardlast)
									&& !"null".equals(cardlast)) {
								tv_bind_bankcard.setText(cardlast);
							} else {
								tv_bind_bankcard.setText("未绑定，点击绑定");
							}
							if (jjmm != null && !"".equals(jjmm)
									&& !"null".equals(jjmm)) {
								tv_deal_pwd.setText("修改交易密码");
							} else {
								tv_deal_pwd.setText("未设置，点击设置");
							}
							String logName = MyApplication.getInstance()
									.getLoginUser().getLoginName();
							tv_count_name.setText("账户:  " + logName);


						} else {
							AbToastUtil.showToastInThread(
									SafeSettingActivity.this,
									info.getRespCodeDesc());
						}
					}
					AbDialogUtil.removeDialog(SafeSettingActivity.this);
				}

				@Override
				public void onFailure(int statusCode, final String content,
						Throwable error) {
					error.printStackTrace();
					AbToastUtil.showToastInThread(SafeSettingActivity.this,
							error.getMessage());
					AbDialogUtil.removeDialog(SafeSettingActivity.this);
				}
			});
		}
	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "安全设置");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_btn_safe_set_act_rname_auth:
//			if (zsxmAndSfzh != null && !"".equals(zsxmAndSfzh)
//					&& !"null".equals(zsxmAndSfzh)) {
//				bt_rname_auth.setClickable(false);
//			} else {
//				ActivityUtil.startActivity(this, RnameAuthActivity.class);
//			}
			break;
		case R.id.rl_btn_safe_set_act_bind_bank_card: // 跳转到银行界面
//			// TODO 这里判断是否绑定过
//			if (cardlast != null && !"".equals(cardlast)
//					&& !"null".equals(cardlast)) {
//				Intent intent = new Intent(SafeSettingActivity.this,
//						BankCardActivity.class);
//				Bundle mBundle = new Bundle();
//				mBundle.putString("zhmc", zhmc);
//				mBundle.putString("cardlast", cardlast);
//				mBundle.putString("ssyh", ssyh);
//				mBundle.putString("proName", proName);
//				mBundle.putString("cityName", cityName);
//				mBundle.putString("proCode", proCode);
//				mBundle.putString("cityCode", cityCode);
//				mBundle.putString("imagePath", imagePath);
//
//				intent.putExtras(mBundle);
//				startActivity(intent);
//			} else {
//				ActivityUtil.startActivity(this, RnameAuthActivity.class);
//			}
			break;
		case R.id.rl_btn_safe_set_act_deal_pwd:
			if (jjmm != null && !"".equals(jjmm) && !"null".equals(jjmm)) {
				ActivityUtil.startActivity(this, ModifyDealPwdActivity.class);
			} else {
				ActivityUtil.startActivity(this, SetDealPwdActivity.class);
			}
			break;
		case R.id.rl_btn_safe_set_act_modify_login_pwd:
			ActivityUtil.startActivity(this, ModifyLoginPwdActivity.class);
			break;
		case R.id.rl_btn_safe_set_act_modify_ninelock_pwd:
			ActivityUtil.startActivity(this, ModifyNineLockActivity.class);
			break;
		case R.id.rl_btn_safe_set_act_modify_phone_number:// 跳转到修改手机号码界面
			ActivityUtil
					.startActivity(this, ModifyPhoneNumSetup1Activity.class);
			break;
		case R.id.rl_btn_safe_set_act_logout:// 退出应用程序
			new SweetAlertDialog(SafeSettingActivity.this,
					SweetAlertDialog.CUSTOM_IMAGE_TYPE)
					.setTitleText("提示")
					.setContentText("您确定要退出当前账户吗?")
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


									MyApplication.getInstance().Logout();
									ActivityUtil.startActivityAndFinish(
											SafeSettingActivity.this,
											MainActivity.class);
								}
							}).show();

			break;

		default:
			break;
		}
	}

	public void onEvent(String event) {
		switch (event) {
		case AbConstant.SAFE_SETTING_REFRESH:
			initData();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		UserCenterInfo loginUser = MyApplication.getInstance().getLoginUser();
		String pw = loginUser.getPw();
		String sfzh = loginUser.getSfzh();
		String yhkh = loginUser.getYhkh();
		// 这里判断是否认证绑卡过
		if (tv_deal_pwd != null) {
			if ("0".equals(pw)) {
				tv_deal_pwd.setText("修改交易密码");
			} else if ("1".equals(pw)) {
				tv_deal_pwd.setText("未设置，点击设置");
			}
		}
		if (tv_rname_auth != null) {
			if (!TextUtils.isEmpty(sfzh)) {
				tv_rname_auth.setText(sfzh);
			} else {
				tv_rname_auth.setText("未认证，点击认证");
			}
		}
		if (tv_bind_bankcard != null) {
			if (!TextUtils.isEmpty(yhkh)) {
				tv_bind_bankcard.setText(yhkh);
			} else {
				tv_bind_bankcard.setText("未绑定，点击绑定");
			}
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		EventBus.getDefault().post(new EventPost());
		instance = null;
		EventBus.getDefault().unregister(this);
	}
}
