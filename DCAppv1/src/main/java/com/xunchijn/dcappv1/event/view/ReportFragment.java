package com.xunchijn.dcappv1.event.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.event.TestData;
import com.xunchijn.dcappv1.event.adapter.PictureAdapter;
import com.xunchijn.dcappv1.event.adapter.ReportSettingAdapter;
import com.xunchijn.dcappv1.event.model.SettingItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ReportFragment extends Fragment {
    private ReportSettingAdapter mSettingAdapter;
    private List<SettingItem> mSettingItems;
    private PictureAdapter mPictureAdapter;
    private List<String> mUrls = new ArrayList<>();
    private Activity mActivity;
    private File mPicture;
    private Uri mUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
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
            }
        });
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
                Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                intent.putExtra("url", url);
                startActivityForResult(intent, REQUEST_CODE_SHOW_PICTURE);
            }

            @Override
            public void onAddPicture() {
                intentToCamera();
            }
        });
    }

    private final int REQUEST_CODE_SETTING = 0x1001;

    private void initSettingView(View view) {
        RecyclerView mViewSettings = view.findViewById(R.id.recycler_view_setting);
        mViewSettings.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingItems = TestData.getSettingItems();
        mSettingAdapter = new ReportSettingAdapter(mSettingItems);
        mViewSettings.setAdapter(mSettingAdapter);
        mSettingAdapter.setItemClickListener(new ReportSettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                intent.putExtra("item", item);
                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });
    }

    private final int REQUEST_CODE_CAMERA = 0x1002;
    private final int REQUEST_CODE_CROP_PHOTO = 0x1003;
    private final int REQUEST_CODE_PERMISSION = 0x1004;

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
        mUri = FileProvider.getUriForFile(mActivity, "com.xunchijn.dcappv1.fileprovider", mPicture);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
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
        if (requestCode == REQUEST_CODE_SETTING && resultCode == RESULT_OK) {
            SettingItem item = (SettingItem) data.getSerializableExtra("setting");
            if (item != null) {
                mSettingItems.remove(item.getIndex());
                mSettingItems.add(item.getIndex(), item);
                mSettingAdapter.notifyItemChanged(item.getIndex());
            }
            return;
        }
        //拍照，返回结果需要裁剪照片
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            startPhotoZoom(mUri);
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
                refreshPictures();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //刷新图片适配器
    private void refreshPictures() {
        if (mPicture.exists()) {
            mUrls.add(String.format("file://%s", mPicture.getAbsolutePath()));
            mPictureAdapter.notifyDataSetChanged();
        }
    }
}
