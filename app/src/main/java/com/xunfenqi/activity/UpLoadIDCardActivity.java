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
import com.xunfenqi.utils.PermissionUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.io.File;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class UpLoadIDCardActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_TAKE_PHOTO_ID_CARD = 0;
    private static final int REQUEST_CODE_TAKE_PHOTO_BANK_CARD = 1;
    private static final int REQUEST_CODE_TAKE_PHOTO_BANK_CARD_HAND = 2;

    private static final String TAKE_PHOTO_PATH_ID_CARD = "xfq/photo/IDCARDZ.jpg";
    private static final String TAKE_PHOTO_PATH_BANK_CARD = "xfq/photo/IDCARDF.jpg";
    private static final String TAKE_PHOTO_PATH_BANK_CARD_HAND = "xfq/photo/IDCARDSC.jpg";

    private ImageView mIdCardView;
    private ImageView mBankCardView, mHandCardView;
    File fileA = new File(Environment.getExternalStorageDirectory(), TAKE_PHOTO_PATH_ID_CARD);
    File fileB = new File(Environment.getExternalStorageDirectory(), TAKE_PHOTO_PATH_BANK_CARD);
    File fileC = new File(Environment.getExternalStorageDirectory(), TAKE_PHOTO_PATH_BANK_CARD_HAND);


    @Override
    public void initView() {

        setAbContentView(R.layout.activity_upload_idcard);

        mIdCardView = (ImageView) findViewById(R.id.iv_activity_upload_id_card_zheng);
        mBankCardView = (ImageView) findViewById(R.id.iv_activity_upload_id_card_fan);
        mHandCardView = (ImageView) findViewById(R.id.iv_activity_upload_id_card_shouchi);

        findViewById(R.id.btn_upload_act_confirm).setOnClickListener(this);
        mIdCardView.setOnClickListener(this);
        mBankCardView.setOnClickListener(this);
        mHandCardView.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "身份认证");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        PermissionUtils.checkPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        PermissionUtils.checkPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
        switch (v.getId()) {
            case R.id.iv_activity_upload_id_card_zheng:
                takePicture(REQUEST_CODE_TAKE_PHOTO_ID_CARD);
                break;
            case R.id.iv_activity_upload_id_card_fan:
                takePicture(REQUEST_CODE_TAKE_PHOTO_BANK_CARD);
                break;
            case R.id.iv_activity_upload_id_card_shouchi:
                takePicture(REQUEST_CODE_TAKE_PHOTO_BANK_CARD_HAND);
                break;
            case R.id.btn_upload_act_confirm:

                Bitmap mIdCardBitmap = BitmapFactory.decodeFile(fileA.getPath());
                Bitmap mBankCardBitmap = BitmapFactory.decodeFile(fileB.getPath());
                Bitmap mHandCardBitmap = BitmapFactory.decodeFile(fileC.getPath());
                if (mIdCardBitmap == null) {
                    AbToastUtil.showToast(getApplicationContext(), "请拍摄身份证正面");
                    return;
                }
                if (mBankCardBitmap == null) {
                    AbToastUtil.showToast(getApplicationContext(), "请拍摄身份证背面");
                    return;
                }
                if (mHandCardBitmap == null) {
                    AbToastUtil.showToast(getApplicationContext(), "请拍摄手持身份证照片");
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
            case 1:
                path = TAKE_PHOTO_PATH_BANK_CARD;
                break;
            case 2:
                path = TAKE_PHOTO_PATH_BANK_CARD_HAND;
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
                    AbDialogUtil.getWaitDialog(UpLoadIDCardActivity.this);
                    new CompressPhotoThread(mHandler, filePath, REQUEST_CODE_TAKE_PHOTO_ID_CARD).start();
                    break;
                case REQUEST_CODE_TAKE_PHOTO_BANK_CARD:
                    filePath = Environment.getExternalStorageDirectory() + "/" + TAKE_PHOTO_PATH_BANK_CARD;
                    AbDialogUtil.getWaitDialog(UpLoadIDCardActivity.this);
                    new CompressPhotoThread(mHandler, filePath, REQUEST_CODE_TAKE_PHOTO_BANK_CARD).start();
                    break;
                case REQUEST_CODE_TAKE_PHOTO_BANK_CARD_HAND:
                    filePath = Environment.getExternalStorageDirectory() + "/" + TAKE_PHOTO_PATH_BANK_CARD_HAND;
                    AbDialogUtil.getWaitDialog(UpLoadIDCardActivity.this);
                    new CompressPhotoThread(mHandler, filePath, REQUEST_CODE_TAKE_PHOTO_BANK_CARD_HAND).start();
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
                        case 1:
                            path = TAKE_PHOTO_PATH_BANK_CARD;
                            distView = mBankCardView;
                            break;
                        case 2:
                            path = TAKE_PHOTO_PATH_BANK_CARD_HAND;
                            distView = mHandCardView;
                            break;
                    }

                    Glide.with(UpLoadIDCardActivity.this)//
                            .load(Environment.getExternalStorageDirectory() + "/" + path)//
                            .skipMemoryCache(true)//
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(distView);

                    distView.setTag(path);
                    AbDialogUtil.removeDialog(UpLoadIDCardActivity.this);
                    break;
            }
        }
    };

    private void doNetWork() {

        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            AbDialogUtil.getWaitDialog(this);
            HaiHeApi.userUploadImg(loginUid, UpLoadUtils.getPhotoData(fileA), "1", UpLoadUtils.getPhotoData(fileB), "1", UpLoadUtils.getPhotoData(fileC), "2", new AbSoapListener() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    UserLoansDetailInfo info = HaiheReturnApi.
                            userUpLoadImg(content);
                    if (info != null) {
                        if ("000".equals(info.getRespCode())) {
                            EventBus.getDefault().post(AbConstant.UP_LOAD_PHOTO_REFRESH);
                            AbToastUtil.showToast(getApplicationContext(), "上传成功");

                            UpLoadIDCardActivity.this.finish();
                            DirUtils.delFolder(fileA.getPath());
                            DirUtils.delFolder(fileB.getPath());
                        } else {
                            AbToastUtil.showToastInThread(
                                    UpLoadIDCardActivity.this,
                                    info.getRespCodeDesc());
                        }
                    }
                    AbDialogUtil.removeDialog(UpLoadIDCardActivity.this);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbDialogUtil.removeDialog(UpLoadIDCardActivity.this);
                    AbToastUtil.showToastInThread(UpLoadIDCardActivity.this,

                            error.getMessage());
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DirUtils.delFolder(fileA.getPath());
        DirUtils.delFolder(fileB.getPath());
        DirUtils.delFolder(fileC.getPath());

    }
}
