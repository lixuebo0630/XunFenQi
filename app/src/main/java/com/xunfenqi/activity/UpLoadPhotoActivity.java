/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserUpLoadAuthorInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class UpLoadPhotoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_idcard_state, tv_other_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_upload_photo);

        findViewById(R.id.rl_upload_act_other_state).setOnClickListener(this);
        findViewById(R.id.rl_upload_act_id_card_state).setOnClickListener(this);

        tv_idcard_state = (TextView) findViewById(R.id.tv_upload_act_id_card_state);
        tv_other_state = (TextView) findViewById(R.id.tv_upload_act_other_state);

    }

    @Override
    public void initData() {
        AbDialogUtil.getWaitDialog(this);
        doNetWork();
    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "上传照片");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_upload_act_id_card_state:
                ActivityUtil.startActivity(this, UpLoadIDCardActivity.class);
                break;
            case R.id.rl_upload_act_other_state:
                ActivityUtil.startActivity(this, UpLoadOtherActivity.class);
                break;
        }
    }

    private void doNetWork() {

        String loginUid = MyApplication.getInstance().getLoginUid();

        if (loginUid != null) {

            HaiHeApi.userUploadAuthor(loginUid, new AbSoapListener() {

                @Override
                public void onSuccess(int statusCode, String content) {

                    UserUpLoadAuthorInfo info = HaiheReturnApi.
                            userUpLoadAuthor(content);
                    if (info != null) {
                        if ("000".equals(info.getRespCode())) {
                            tv_idcard_state.setText(info.getIdrzzt());
                            tv_other_state.setText(info.getQtrzzt());
                        } else {
                            AbToastUtil.showToastInThread(
                                    UpLoadPhotoActivity.this,
                                    info.getRespCodeDesc());
                        }
                    }
                    AbDialogUtil.removeDialog(UpLoadPhotoActivity.this);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbDialogUtil.removeDialog(UpLoadPhotoActivity.this);
                    AbToastUtil.showToastInThread(UpLoadPhotoActivity.this,

                            error.getMessage());
                }
            });
        }
    }

    public void onEvent(String event) {
        switch (event) {
            case AbConstant.UP_LOAD_PHOTO_REFRESH:
                doNetWork();
                break;

            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
