/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-9下午2:28:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

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
import com.xunfenqi.model.domain.LoginInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 * 
 * @ClassName: ModifyNineLockActivity
 * @Description: 修改手势密码界面
 * @author Xuebo Li
 * @date 2015-10-30 上午10:25:55
 * 
 */
public class ModifyNineLockActivity extends BaseActivity implements
		OnClickListener {

	private TextView tv_modify_nine_lock_act_username;
	private EditText et_modify_nine_lock_act_pwd;
	private Button btn_modify_phonenum_setup1_act_next;

	private String loginName;

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_modify_nine_lock);

		tv_modify_nine_lock_act_username = (TextView) this
				.findViewById(R.id.tv_modify_nine_lock_act_username);
		et_modify_nine_lock_act_pwd = (EditText) this
				.findViewById(R.id.et_modify_nine_lock_act_pwd);
		btn_modify_phonenum_setup1_act_next = (Button) this
				.findViewById(R.id.btn_modify_phonenum_setup1_act_next);
		btn_modify_phonenum_setup1_act_next.setOnClickListener(this);
	}

	@Override
	public void initData() {

		loginName = MyApplication.getInstance().getLoginUser().getLoginName();
		tv_modify_nine_lock_act_username.setText("账户:  " + loginName);

	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "账号验证");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(45),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		String passWord = et_modify_nine_lock_act_pwd.getText().toString()
				.trim();
		if (passWord == null || "".equals(passWord)) {
			AbToastUtil.showToast(getApplicationContext(), "密码不能为空");
			return;
		}
		HaiHeApi.login(loginName, passWord, new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				LoginInfo loginInfo = HaiheReturnApi.loginReturn(content);
				if (loginInfo != null) {
					if (loginInfo.getRespCode().equals("000")) {
						ActivityUtil.startActivity(ModifyNineLockActivity.this,
								SetNineLockActivity.class);
					} else {
						AbToastUtil.showToastInThread(getApplicationContext(),
								loginInfo.getRespCodeDesc());
					}
				}
			}

			@Override
			public void onFailure(int statusCode, String content,
					Throwable error) {
				error.printStackTrace();
				AbToastUtil.showToastInThread(ModifyNineLockActivity.this,
						error.getMessage());
			}
		});
	}

}
