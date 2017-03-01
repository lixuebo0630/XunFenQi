/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-9下午2:28:29
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
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserEditPasswordInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.dialog.SweetAlertDialog;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @ClassName: ModifyLoginPwdActivity
 * @Description: 修改登录密码界面
 * @author Xuebo Li
 * @date 2015-9-24 下午5:25:32
 * 
 */
public class ModifyLoginPwdActivity extends BaseActivity {
	
	private EditText oldPwsText;
	private EditText newPwsText;
	private EditText confirmPwsText;
	private Button updatePws;
	
	private String oldPws;
	private String newPws;
	private String confirmPws;
	
	private SweetAlertDialog waitSLoadingDialog;

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_modify_login_pwd);
		oldPwsText=(EditText) this.findViewById(R.id.et_modify_pwd_act_old_pwd);
		newPwsText=(EditText) this.findViewById(R.id.et_modify_pwd_act_new_pwd);
		confirmPwsText=(EditText) this.findViewById(R.id.et_modify_pwd_act_confirm_new_pwd);
		updatePws= (Button) findViewById(R.id.btn_modify_pwd_act_modify);
		updatePws.setOnClickListener(pwsClickListener);
	}

	OnClickListener pwsClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			oldPws=oldPwsText.getText().toString().trim();
			newPws=newPwsText.getText().toString().trim();
			confirmPws=confirmPwsText.getText().toString().trim();
			if(TextUtils.isEmpty(oldPws)){
				AbToastUtil.showToast(getApplicationContext(), "原始密码不能为空");
				return;
			}
			if(TextUtils.isEmpty(newPws)){
				AbToastUtil.showToast(getApplicationContext(), "新密码不能为空");
				return;
			}
			if(TextUtils.isEmpty(confirmPws)){
				AbToastUtil.showToast(getApplicationContext(), "确认密码不能为空");
				return;
			}
			if(antiSqlValid(newPws)){
				if(newPws.length()<6){
					AbToastUtil.showToast(getApplicationContext(), "新密码长度不能小于6");
					return;
				}
				if(newPws.length()>20){
					AbToastUtil.showToast(getApplicationContext(), "新密码长度不能大于20");
					return;			
				}
				if(newPws.matches("^[0-9]*$")){
					AbToastUtil.showToast(getApplicationContext(), "新密码不能全为数字");
					return;
				}
				if(newPws.matches("[a-zA-Z]+")){
					AbToastUtil.showToast(getApplicationContext(), "新密码不能全为英文字母");
					return;
				}
			}else{
				AbToastUtil.showToast(getApplicationContext(), "新密码不能输入非法字符");
				return;
			}
			if(antiSqlValid(confirmPws)){
				if(confirmPws.length()<6){
					AbToastUtil.showToast(getApplicationContext(), "确认密码长度不能小于6");
					return;
				}
				if(confirmPws.length()>20){
					AbToastUtil.showToast(getApplicationContext(), "确认密码长度不能大于20");
					return;			
				}
				if(confirmPws.matches("^[0-9]*$")){
					AbToastUtil.showToast(getApplicationContext(), "确认密码不能全为数字");
					return;
				}
				if(confirmPws.matches("[a-zA-Z]+")){
					AbToastUtil.showToast(getApplicationContext(), "确认密码不能全为英文字母");
					return;
				}
			}else{
				AbToastUtil.showToast(getApplicationContext(), "确认密码不能输入非法字符");
				return;
			}
			
			if(!newPws.equals(confirmPws)){
				AbToastUtil.showToast(getApplicationContext(), "新密码与确认密码不一致");
				return;
			}
			
			
			waitSLoadingDialog = AbDialogUtil.getWaitSLoadingDialog(
					ModifyLoginPwdActivity.this, "请稍候...");
			waitSLoadingDialog.show();
			
			doWork(oldPws,newPws);
		}
		
		private boolean antiSqlValid(String oldPws) {
			oldPws=oldPws.toLowerCase();
			String reg = "and|exec|insert|select|delete|update|count|drop|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|order|by|count|like|%|&|\\*|'|,|\"|;|>|</i";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(oldPws);
			if(matcher.find()){
				return false;
			}else{
				return true;
			}
		}
	};
	
	private void doWork(String oldPassword, String newPassword) {
		HaiHeApi.userEditPassword(MyApplication.getInstance().getLoginUid(), oldPassword, newPassword,new AbSoapListener() {
			@Override
			public void onSuccess(int statusCode, String content) {
				waitSLoadingDialog.dismiss();
				UserEditPasswordInfo userEditPassword = HaiheReturnApi.userEditPassword(content);
				if(userEditPassword!=null){
					if(userEditPassword.getRespCode().equals("000")){
						new SweetAlertDialog(ModifyLoginPwdActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
						.setTitleText("提示")
						.setContentText("修改成功，请重新登录")
						.setConfirmText("确认")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(SweetAlertDialog sDialog) {
										sDialog.dismiss();

										ActivityUtil.startActivityAndFinish(ModifyLoginPwdActivity.this, LoginActivity.class);
									}
								}).show();
					}else{
						AbToastUtil.showToastInThread(getApplicationContext(), userEditPassword.getRespCodeDesc());
					}
				}
				
			}
			
			@Override
			public void onFailure(int statusCode, String content, Throwable error) {
				waitSLoadingDialog.dismiss();
				error.printStackTrace();
				AbToastUtil.showToastInThread(ModifyLoginPwdActivity.this, error.getMessage());
				
			}
		});
		
	}
	
	@Override
	public void initData() {

	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "修改密码");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

}
