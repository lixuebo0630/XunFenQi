package com.xunfenqi.global;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.xunfenqi.utils.BitmapUtils;

import java.io.File;


public class CompressPhotoThread extends Thread{

    private final int mType;
    private String mFilePath;
    private Handler mHandler;

    public CompressPhotoThread(Handler handler, String filePath, int type) {
        this.mHandler = handler;
        this.mFilePath = filePath;
        this.mType = type;
    }

    @Override
    public void run() {
        File picture = new File(mFilePath);
        //对图片进行压缩
        Bitmap bm = BitmapUtils.compressBySize(picture.getAbsolutePath(), 800, 600);
        try {
            picture.delete();
            BitmapUtils.saveFile(bm, mFilePath);
            Message message = Message.obtain();
            message.what = 200;
            message.obj = mType;
            mHandler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
