package com.xunchijn.sichuan.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    public static final int REQUEST_CODE_PICK_PHOTO = 0x1001;
    public static final int REQUEST_CODE_CAMERA = 0x1002;
    public final int REQUEST_CODE_PERMISSION = 0x10003;
    private Activity mActivity;
    private File mPicture;

    public PhotoUtils(Activity context) {
        mActivity = context;
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
        File file = new File(mActivity.getCacheDir(), uri.getLastPathSegment());
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

    //调用相机拍照
    public void intentToCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION);
                return;
            }
        }
        String fileName = String.format("%sDC_%s_img.jpg", mActivity.getExternalCacheDir(), System.currentTimeMillis());
        mPicture = new File(fileName);
        Uri mUri = FileProvider.getUriForFile(mActivity, "com.xunchijn.dcappv1.fileprovider", mPicture);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        mActivity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    //打开相册
    public void intentToPick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        mActivity.startActivityForResult(intent, REQUEST_CODE_PICK_PHOTO);
    }

    public File getPicture() {
        return mPicture;
    }

    public String getImageUrl(Intent data) {
        //判断手机系统版本号
        if (Build.VERSION.SDK_INT >= 19) {
            //4.4及以上系统使用这个方法处理图片
            return handleImageOnKitKat(data);
        } else {
            //4.4及以下系统使用这个方法处理图片
            return handleImageBeforeKitKat(data);
        }
    }

    @TargetApi(19)
    private String handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(mActivity, uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docID = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docID.split(":")[1];//解析出数字格式的ID
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docID));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri,则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        return imagePath;
    }

    private String handleImageBeforeKitKat(Intent data) {
        return getImagePath(data.getData(), null);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = mActivity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
