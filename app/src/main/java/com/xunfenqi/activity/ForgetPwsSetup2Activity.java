/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.LoginInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @date: 2015-9-17 下午1:40:51
 * @author: XueBo Li
 * @version:
 * @description: 找回密码
 * @see
 */
public class ForgetPwsSetup2Activity extends BaseActivity implements
		OnClickListener {
	protected static final String TAG = "ForgetPwsSetup2Activity";
	private EditText pwd2_newpwdText;
	private EditText pwd2_confirm_newpwdText;
	private Button bt_pwd2_confirm;
	private String newPws;
	private String confirm;
	private String userTel;

	@Override
	public void initView() {
		userTel = (String) getIntent().getExtras().get("userTel");
		setAbContentView(R.layout.activity_forget_pwd_setup2);

		pwd2_newpwdText = (EditText) this
				.findViewById(R.id.et_forget_pwd2_act_newpwd);
		pwd2_confirm_newpwdText = (EditText) this
				.findViewById(R.id.et_forget_pwd2_act_confirm_newpwd);
		bt_pwd2_confirm = (Button) this
				.findViewById(R.id.btn_forget_pwd2_act_confirm);
		bt_pwd2_confirm.setOnClickListener(this);

	}

	@Override
	public void initData() {
	}

	@Override
	public void onClick(View v) {
		newPws = pwd2_newpwdText.getText().toString().trim();
		confirm = pwd2_confirm_newpwdText.getText().toString().trim();

		if (TextUtils.isEmpty(newPws)) {
			AbToastUtil.showToast(getApplicationContext(), "密码不能为空");
			return;
		}

		if (TextUtils.isEmpty(confirm)) {
			AbToastUtil.showToast(getApplicationContext(), "确认密码不能为空");
			return;
		}
		if (antiSqlValid(newPws)) {
			if (newPws.length() < 6) {
				AbToastUtil.showToast(getApplicationContext(), "密码长度不能小于6");
				return;
			}
			if (newPws.length() > 20) {
				AbToastUtil.showToast(getApplicationContext(), "密码长度不能大于20");
				return;
			}
			if (newPws.matches("^[0-9]*$")) {
				AbToastUtil.showToast(getApplicationContext(), "密码不能全为数字");
				return;
			}
			if (newPws.matches("[a-zA-Z]+")) {
				AbToastUtil.showToast(getApplicationContext(), "密码不能全为英文字母");
				return;
			}
		} else {
			AbToastUtil.showToast(getApplicationContext(), "密码不能输入非法字符");
			return;
		}

		if (!newPws.equals(confirm)) {
			AbToastUtil.showToast(getApplicationContext(), "密码与确认密码不一致");
			return;
		}
		// 验证成功
		doLogion();
	}

	private void doLogion() {
		HaiHeApi.userSetPassword(userTel, newPws, new AbSoapListener() {

			@Override
			public void onSuccess(int statusCode, String content) {
				AbLogUtil.d(TAG, content);
				LoginInfo info = HaiheReturnApi.userSetPassword(content);
				if (info != null) {
					if (info.getRespCode().equals("000")) {
						ActivityUtil.startActivity(
								ForgetPwsSetup2Activity.this,
								LoginActivity.class);
					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								info.getRespCodeDesc());
						AbDialogUtil.removeDialog(ForgetPwsSetup2Activity.this);
					}

					// TODO 这里判断其他情况
				}

			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				AbToastUtil.showToastInThread(ForgetPwsSetup2Activity.this,
						error.getMessage());
				error.printStackTrace();
				AbDialogUtil.removeDialog(ForgetPwsSetup2Activity.this);
			}
		});
	}

	@Override
	public void initActionBar() {
		// 初始化ActionBar
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "修改密码");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
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
