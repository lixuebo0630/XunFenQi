/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CompressPhotoThread;
import com.xunfenqi.model.domain.UserLoansDetailInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.net.soap.UpLoadUtils;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.DirUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.io.File;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class UpLoadOtherActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_TAKE_PHOTO_ID_CARD = 0;

    private static final String TAKE_PHOTO_PATH_ID_CARD = "xfq/photo/IDCARDOTHER.jpg";

    private ImageView mIdCardView;
    File fileA = new File(Environment.getExternalStorageDirectory(), TAKE_PHOTO_PATH_ID_CARD);


    @Override
    public void initView() {

        setAbContentView(R.layout.activity_upload_other);

        mIdCardView = (ImageView) findViewById(R.id.iv_activity_upload_id_card_zheng);

        findViewById(R.id.btn_upload_act_confirm).setOnClickListener(this);
        mIdCardView.setOnClickListener(this);
    }

    @Override
    public void initData() {

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
            case R.id.iv_activity_upload_id_card_zheng:
                takePicture(REQUEST_CODE_TAKE_PHOTO_ID_CARD);
                break;
            case R.id.btn_upload_act_confirm:

                Bitmap mIdCardBitmap = BitmapFactory.decodeFile(fileA.getPath());
                if (mIdCardBitmap == null) {
                    AbToastUtil.showToast(getApplicationContext(), "请拍摄照片");
                    return;
                }
                doNetWork();
                break;
        }
    }


    private void takePicture(int requestCode) {
        String path = null;
        switch (requestCode) {
            case 0:
                path = TAKE_PHOTO_PATH_ID_CARD;
                break;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), path);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filePath = null;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TAKE_PHOTO_ID_CARD:
                    filePath = Environment.getExternalStorageDirectory() + "/" + TAKE_PHOTO_PATH_ID_CARD;
                    AbDialogUtil.getWaitDialog(UpLoadOtherActivity.this);
                    new CompressPhotoThread(mHandler, filePath, REQUEST_CODE_TAKE_PHOTO_ID_CARD).start();
                    break;
            }
        }
    }


    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200: //图片
                    int type = (int) msg.obj;
                    String path = "";
                    ImageView distView = null;
                    switch (type) {
                        case 0:
                            path = TAKE_PHOTO_PATH_ID_CARD;
                            distView = mIdCardView;
                            break;
                    }

                    Glide.with(UpLoadOtherActivity.this)//
                            .load(Environment.getExternalStorageDirectory() + "/" + path)//
                            .skipMemoryCache(true)//
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(distView);

                    distView.setTag(path);
                    AbDialogUtil.removeDialog(UpLoadOtherActivity.this);
                    break;
            }
        }
    };

    private void doNetWork() {

        String loginUid = MyApplication.getInstance().getLoginUid();

        if (loginUid != null) {
            AbDialogUtil.getWaitDialog(this);
            HaiHeApi.userUploadImg(loginUid, UpLoadUtils.getPhotoData(fileA), "0", "", "0","","0",new AbSoapListener() {


                @Override
                public void onSuccess(int statusCode, String content) {

                    UserLoansDetailInfo info = HaiheReturnApi.
                            userUpLoadImg(content);
                    if (info != null) {
                        if ("000".equals(info.getRespCode())) {
                            EventBus.getDefault().post(AbConstant.UP_LOAD_PHOTO_REFRESH);
                            AbToastUtil.showToast(getApplicationContext(), "上传成功");

                            UpLoadOtherActivity.this.finish();
                            DirUtils.delFolder(fileA.getPath());
                        } else {
                            AbToastUtil.showToastInThread(
                                    UpLoadOtherActivity.this,
                                    info.getRespCodeDesc());
                        }
                    }
                    AbDialogUtil.removeDialog(UpLoadOtherActivity.this);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbDialogUtil.removeDialog(UpLoadOtherActivity.this);
                    AbToastUtil.showToastInThread(UpLoadOtherActivity.this,

                            error.getMessage());
                }
            });
        }
    }


}
