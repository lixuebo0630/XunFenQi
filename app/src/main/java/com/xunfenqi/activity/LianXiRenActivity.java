/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.VerifyUserTelInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.PermissionUtils;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.EditTextWithDelete;
import com.xunfenqi.view.titlebar.AbTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *
 */
public class LianXiRenActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_lianxiren_act_name1)
    EditTextWithDelete etLianxirenActName1;
    @BindView(R.id.tv_btn_lianxiren_act_select1)
    TextView tvBtnLianxirenActSelect1;
    @BindView(R.id.et_lianxirenact_phonenum1)
    EditTextWithDelete etLianxirenactPhonenum1;
    @BindView(R.id.et_lianxiren_act_name2)
    EditTextWithDelete etLianxirenActName2;
    @BindView(R.id.tv_btn_lianxiren_act_select2)
    TextView tvBtnLianxirenActSelect2;
    @BindView(R.id.et_lianxirenact_phonenum2)
    EditTextWithDelete etLianxirenactPhonenum2;
    @BindView(R.id.btn_forget_pwd2_act_confirm)
    Button btnForgetPwd2ActConfirm;
    @BindView(R.id.et_lianxirenact_sf1)
    EditTextWithDelete etLianxirenactSf1;
    @BindView(R.id.et_lianxirenact_sf2)
    EditTextWithDelete etLianxirenactSf2;

    // 声明姓名，电话
    private String username1, usernumber1, username2, usernumber2;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_lianxiren);
        ButterKnife.bind(this);
        tvBtnLianxirenActSelect1.setOnClickListener(this);
        tvBtnLianxirenActSelect2.setOnClickListener(this);
        btnForgetPwd2ActConfirm.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "联系人信息");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn_lianxiren_act_select1:
                PermissionUtils.checkPermission(this, "android.permission.READ_CONTACTS");
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI), 0);
                break;
            case R.id.tv_btn_lianxiren_act_select2:

                PermissionUtils.checkPermission(this, "android.permission.READ_CONTACTS");
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI), 1);

                break;
            case R.id.btn_forget_pwd2_act_confirm:
                doSubmit();
                break;

            default:
                break;
        }
    }

    private void doSubmit() {

        String name1 = etLianxirenActName1.getText().toString().trim();
        String name2 = etLianxirenActName2.getText().toString().trim();
        String num1 = etLianxirenactPhonenum1.getText().toString().trim();
        String num2 = etLianxirenactPhonenum2.getText().toString().trim();
        String sf1 = etLianxirenactSf1.getText().toString().trim();
        String sf2 = etLianxirenactSf2.getText().toString().trim();


        if (TextUtils.isEmpty(name1)) {
            AbToastUtil.showToast(this, "请输入联系人一姓名");
            return;
        }
        if (TextUtils.isEmpty(num1)) {
            AbToastUtil.showToast(this, "请输入联系人一手机号");
            return;
        }
        if (TextUtils.isEmpty(sf1)) {
            AbToastUtil.showToast(this, "请输入联系人一与本人关系");
            return;
        }
        if (TextUtils.isEmpty(name2)) {
            AbToastUtil.showToast(this, "请输入联系人二姓名");
            return;
        }
        if (TextUtils.isEmpty(num2)) {
            AbToastUtil.showToast(this, "请输入联系人二手机号");
            return;
        }

        if (TextUtils.isEmpty(sf2)) {
            AbToastUtil.showToast(this, "请输入联系人二与本人关系");
            return;
        }
        if (name1.equals(name2) && num1.equals(num2)) {
            AbToastUtil.showToast(this, "不能选择同一个联系人");
            return;
        }


        AbDialogUtil.getWaitDialog(LianXiRenActivity.this);
        HaiHeApi.userEditConnectPeople(MyApplication.getInstance().getLoginUid(),
                name1, num1, name2, num2, sf1, sf2, new AbSoapListener() {

                    @Override
                    public void onSuccess(int statusCode, String content) {
                        VerifyUserTelInfo verifyUserTelInfo = HaiheReturnApi
                                .userEditConnectPeople(content);
                        if (verifyUserTelInfo != null) {
                            if (verifyUserTelInfo.getRespCode().equals("000")
                                    ) {

                                SettingUtils.getInstance(LianXiRenActivity.this ).saveValue("lxr","0");
                                AbToastUtil.showToastInThread(getApplicationContext(),
                                        "添加联系人信息成功");
                                LianXiRenActivity.this.finish();
                            } else {
                                AbToastUtil.showToastInThread(getApplicationContext(),
                                        verifyUserTelInfo.getRespCodeDesc());
                            }
                            AbDialogUtil
                                    .removeDialog(LianXiRenActivity.this);

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {
                        AbToastUtil.showToast(LianXiRenActivity.this,
                                error.getMessage());
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(LianXiRenActivity.this);
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            // ContentProvider展示数据类似一个单个数据库表
            // ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
            ContentResolver reContentResolverol = getContentResolver();
            // URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
            Uri contactData = data.getData();
            // 查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();
            // 获得DATA表中的名字
            if (requestCode == 0) {

                username1 = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            } else {
                username2 = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            }
            // 条件为联系人ID
            String contactId = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
            Cursor phone = reContentResolverol.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + contactId, null, null);
            while (phone.moveToNext()) {
                if (requestCode == 0) {

                    usernumber1 = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    etLianxirenActName1.setText(username1);
                    etLianxirenactPhonenum1.setText(usernumber1);

                } else {
                    usernumber2 = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    etLianxirenActName2.setText(username2);
                    etLianxirenactPhonenum2.setText(usernumber2);
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
