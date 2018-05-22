package com.xunchijn.dcappv1.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    private Context mContext;

    public PhotoUtils(Context context) {
        mContext = context;
    }

    public List<String> CompressPictures(List<String> urls) {
        if (urls == null || urls.size() == 0) {
            return null;
        }
        List<String> results = new ArrayList<>();
        for (String url : urls) {
            results.add(qualityCompress(url));
        }
        return results;
    }

    private String qualityCompress(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        Uri uri = Uri.parse(url);
        Bitmap bitmap = BitmapFactory.decodeFile(url);

        if (bitmap == null) {
            return "";
        }
        Log.d("Photo", "qualityCompress: " + bitmap.getByteCount());
        File file = new File(mContext.getCacheDir(), uri.getLastPathSegment());
        // 0-100 100为不压缩
        int quality = 60;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        Log.d("Photo", "qualityCompress: " + bitmap.getByteCount());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

}
