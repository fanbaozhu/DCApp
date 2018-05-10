package com.xunchijn.dcappv1.event.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.event.adapter.PictureAdapter;
import com.xunchijn.dcappv1.event.adapter.ReportSettingAdapter;
import com.xunchijn.dcappv1.event.adapter.SelectAdapter;
import com.xunchijn.dcappv1.event.contract.ReportContract;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.event.model.SettingItem;
import com.xunchijn.dcappv1.event.widget.SelectDialog;
import com.xunchijn.dcappv1.util.TestData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ReportFragment extends Fragment implements ReportContract.View {
    private ReportContract.Presenter mPresenter;
    private ReportSettingAdapter mSettingAdapter;
    private List<SettingItem> mSettingItems;
    private PictureAdapter mPictureAdapter;
    private List<String> mUrls = new ArrayList<>();
    private Activity mActivity;
    private File mPicture;
    private EditText mInputDescribe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        mInputDescribe = view.findViewById(R.id.edit_describe);
        initTitle();
        initPictureView(view);
        initSettingView(view);
        return view;
    }

    //初始化标题栏
    private void initTitle() {
        TitleFragment mTitleFragment = TitleFragment.newInstance("事件上报", true, true);

        getFragmentManager().beginTransaction().add(R.id.layout_title, mTitleFragment)
                .show(mTitleFragment).commit();

        mTitleFragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {
                Toast.makeText(getContext(), "返回", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConfirm() {
                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
                report();
            }
        });
    }

    private void report() {
        String describe = mInputDescribe.getText().toString();
        if (TextUtils.isEmpty(describe)) {
            showError("事件描述不能为空！");
            return;
        }
        if (mUrls == null || mUrls.size() == 0) {
            showError("请选择至少一张图片");
            return;
        }
        if (mPresenter != null) {
            mPresenter.uploadPictures(mUrls);
            mPresenter.report(describe, mUrls.toString(), "", "", "", "");
        }
    }

    private final int REQUEST_CODE_SHOW_PICTURE = 0x1000;

    //初始化图片列表
    private void initPictureView(View view) {
        RecyclerView mViewPictures = view.findViewById(R.id.recycler_view_picture);
        mViewPictures.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPictureAdapter = new PictureAdapter(mUrls, 2, true);
        mViewPictures.setAdapter(mPictureAdapter);
        mPictureAdapter.setItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onPictureClick(String url) {
                ShowPictureActivity.startShowPicture(mActivity, url, REQUEST_CODE_SHOW_PICTURE);
            }

            @Override
            public void onAddPicture() {
                new AlertDialog.Builder(getContext()).setItems(new String[]{"拍照", "相册"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    intentToCamera();
                                } else {
                                    intentToPick();
                                }
                            }
                        }).create().show();
            }
        });
    }

    private void initSettingView(View view) {
        RecyclerView mViewSettings = view.findViewById(R.id.recycler_view_setting);
        mViewSettings.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingItems = TestData.getSettingItems();
        mSettingAdapter = new ReportSettingAdapter(mSettingItems);
        mViewSettings.setAdapter(mSettingAdapter);
        mSettingAdapter.setItemClickListener(new ReportSettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                showSettingDialog(item.getTitle());
            }
        });
    }

    private void showSettingDialog(String title) {
        final SelectDialog dialog = new SelectDialog();
        dialog.setTitle(title);
        dialog.setList(TestData.getSelectItems(2));
        dialog.setItemClickListener(new SelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {
                dialog.refreshTitle(item.getName());
            }
        });
        dialog.show(getFragmentManager(), title);
    }

    private final int REQUEST_CODE_CAMERA = 0x1002;
    private final int REQUEST_CODE_CROP_PHOTO = 0x1003;
    private final int REQUEST_CODE_PICK_PHOTO = 0x1004;
    private final int REQUEST_CODE_PERMISSION = 0x1005;

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
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    //打开相册
    private void intentToPick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_PHOTO);
    }

    //权限返回结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                intentToCamera();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        //这段代码判断，在安卓7.0以前版本是不需要的。特此注意。不然这里也会抛出异常
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("circleCrop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 300);
        //intent.putExtra("scale",true);//自由截取
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_CROP_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SHOW_PICTURE && resultCode == RESULT_OK) {
            String url = data.getStringExtra("url");
            deletePicture(url);
            return;
        }
        //拍照，返回结果需要裁剪照片
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
//            startPhotoZoom(mUri);
            refreshPictures();
            return;
        }

        // 裁剪照片的处理结果
        if (requestCode == REQUEST_CODE_CROP_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                if (photo == null || mPicture == null) {
                    return;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(mPicture);
                    photo.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                photo.recycle();
                refreshPictures();
            }
            return;
        }
        //相册选择返回结果
        if (requestCode == REQUEST_CODE_PICK_PHOTO && resultCode == RESULT_OK) {
            //判断手机系统版本号
            if (Build.VERSION.SDK_INT >= 19) {
                //4.4及以上系统使用这个方法处理图片
                handleImageOnKitKat(data);
            } else {
                //4.4及以下系统使用这个方法处理图片
                handleImageBeforeKitKat(data);
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
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
        refreshPictures(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        refreshPictures(imagePath);
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

    //刷新图片适配器
    private void refreshPictures() {
        if (mPicture.exists()) {
            mUrls.add(String.format("file://%s", mPicture.getAbsolutePath()));
            mPictureAdapter.notifyDataSetChanged();
        }
    }

    private void refreshPictures(String url) {
        File file = new File(url);
        if (file.exists()) {
            mUrls.add(String.format("file://%s", url));
            mPictureAdapter.notifyDataSetChanged();
        }
    }

    private void deletePicture(String url) {
        if (mUrls.contains(url)) {
            mUrls.remove(url);
            mPictureAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showDepartment(List<NestingItem> list) {

    }

    @Override
    public void showSubDepartment(List<NestingItem> list) {

    }

    @Override
    public void showCheckType(List<NestingItem> list) {

    }

    @Override
    public void showCheckContent(List<NestingItem> list) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ReportContract.Presenter presenter) {
        mPresenter = presenter;
    }
}