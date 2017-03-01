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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserEditPayPwInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.ButtonUtils;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * |
 * 
 * @ClassName: ModifyDealPwdActivity
 * @Description: 修改交易密码
 * @author Xuebo Li
 * @date 2015-10-15 下午3:01:13
 * 
 */
public class ModifyDealPwdActivity extends BaseActivity implements
		OnClickListener {

	private TextView tv_forget_deal_pwd;
	private TextView tv_dealpwd_act_username;
	private TextView et_old_dealpwd;
	private TextView et_new_dealpwd;
	private TextView et_confirm_new_dealpwd;
	private Button btn_confim;

	private String old_dealpwd;
	private String new_dealpwd;
	private String confim_new_dealpwd;

	public static ModifyDealPwdActivity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
	}

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_modify_deal_pwd);

		tv_forget_deal_pwd = (TextView) this
				.findViewById(R.id.tv_btn_modify_dealpwd_act_forget_deal_pwd);
		tv_dealpwd_act_username = (TextView) this
				.findViewById(R.id.tv_modify_dealpwd_act_username);
		et_old_dealpwd = (TextView) this
				.findViewById(R.id.et_modify_dealpwd_act_old_dealpwd);
		et_new_dealpwd = (TextView) this
				.findViewById(R.id.et_modify_dealpwd_act_new_dealpwd);
		et_confirm_new_dealpwd = (TextView) this
				.findViewById(R.id.et_modify_dealpwd_act_confirm_new_dealpwd);
		btn_confim = (Button) this
				.findViewById(R.id.btn_modify_dealpwd_act_confim);

		String loginName = MyApplication.getInstance().getLoginUser()
				.getLoginName();

		tv_dealpwd_act_username.setText("用户名：" + loginName);

		tv_forget_deal_pwd.setOnClickListener(this);
		btn_confim.setOnClickListener(this);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "修改交易密码");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_btn_modify_dealpwd_act_forget_deal_pwd:
			ActivityUtil.startActivity(this, SetDealPwdActivity.class);
			break;
		case R.id.btn_modify_dealpwd_act_confim: // 提交
			// TODO 这里判断是否绑定过
			if(ButtonUtils.isFastDoubleClick()){
				return;
			}else{
				doSumbit();
			}
			break;

		default:
			break;
		}
	}

	private void doSumbit() {
		old_dealpwd = et_old_dealpwd.getText().toString().trim();
		new_dealpwd = et_new_dealpwd.getText().toString().trim();
		confim_new_dealpwd = et_confirm_new_dealpwd.getText().toString().trim();
		if (old_dealpwd == null || "".equals(old_dealpwd)) {
			AbToastUtil.showToast(getApplicationContext(), "原交易密码不能为空");
			return;
		}
		if (new_dealpwd == null || "".equals(new_dealpwd)) {
			AbToastUtil.showToast(getApplicationContext(), "新交易密码不能为空");
			return;
		}
		if (confim_new_dealpwd == null || "".equals(confim_new_dealpwd)) {
			AbToastUtil.showToast(getApplicationContext(), "确认交易密码不能为空");
			return;
		}

		if (antiSqlValid(new_dealpwd)) {
			if (new_dealpwd.length() < 6) {
				AbToastUtil.showToast(getApplicationContext(), "新交易密码长度不能小于6");
				return;
			}
			if (new_dealpwd.length() > 20) {
				AbToastUtil.showToast(getApplicationContext(), "新交易密码长度不能大于20");
				return;
			}
			if (new_dealpwd.matches("^[0-9]*$")) {
				AbToastUtil.showToast(getApplicationContext(), "新交易密码不能全为数字");
				return;
			}
			if (new_dealpwd.matches("[a-zA-Z]+")) {
				AbToastUtil.showToast(getApplicationContext(), "新交易密码不能全为英文字母");
				return;
			}
		} else {
			AbToastUtil.showToast(getApplicationContext(), "新交易密码不能输入非法字符");
			return;
		}
		if (!new_dealpwd.equals(confim_new_dealpwd)) {
			AbToastUtil.showToast(getApplicationContext(), "新交易密码与确认密码不一致");
			return;
		}

		upodatePayPw();
	}

	private void upodatePayPw() {
		HaiHeApi.userUpdatePayPw(MyApplication.getInstance().getLoginUid(),
				new_dealpwd, old_dealpwd, new AbSoapListener() {

					@Override
					public void onSuccess(int statusCode, String content) {
						UserEditPayPwInfo userEditPayPw = HaiheReturnApi
								.userUpdatePayPw(content);
						if (userEditPayPw != null) {
							if (userEditPayPw.getRespCode().equals("000")) {
								SettingUtils.getInstance(
										ModifyDealPwdActivity.this).saveValue(
										SettingUtils.PW, "0");
								new SweetAlertDialog(
										ModifyDealPwdActivity.this,
										SweetAlertDialog.CUSTOM_IMAGE_TYPE)
										.setTitleText("提示")
										.setContentText("交易密码修改成功")
										.setConfirmText("确认")
										.setConfirmClickListener(
												new SweetAlertDialog.OnSweetClickListener() {
													@Override
													public void onClick(
															SweetAlertDialog sDialog) {
														ModifyDealPwdActivity.this
																.finish();
													}
												}).show();
							} else {
								AbToastUtil.showToast(getApplicationContext(),
										userEditPayPw.getRespCodeDesc());
							}
						}

					}

					@Override
					public void onFailure(int statusCode, String content,
							Throwable error) {
						error.printStackTrace();
						AbToastUtil.showToast(ModifyDealPwdActivity.this,
								error.getMessage());

					}
				});
	}

	private boolean antiSqlValid(String new_dealpwd2) {
		new_dealpwd2 = new_dealpwd2.toLowerCase();
		String reg = "and|exec|insert|select|delete|update|count|drop|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|order|by|count|like|%|&|\\*|'|,|\"|;|>|</i";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(new_dealpwd2);
		if (matcher.find()) {
			return false;
		} else {
			return true;
		}
	}

}
