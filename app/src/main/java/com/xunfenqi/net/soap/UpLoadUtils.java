package com.xunfenqi.net.soap;


import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by lixuebo on 17/5/12.
 */

public class UpLoadUtils {

    public static String getPhotoData(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            String uploadBuffer = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码
            fis.close();
            System.out.println("uploadBuffer:" + uploadBuffer);
            return uploadBuffer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
