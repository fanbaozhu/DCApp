package com.xunchijn.sichuan.event.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.adapter.PictureAdapter;
import com.xunchijn.sichuan.adapter.SettingAdapter;
import com.xunchijn.sichuan.base.BaseConfig;
import com.xunchijn.sichuan.common.module.SettingItem;
import com.xunchijn.sichuan.event.presenter.ReportContract;
import com.xunchijn.sichuan.util.PhotoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.xunchijn.sichuan.util.PhotoUtils.REQUEST_CODE_CAMERA;
import static com.xunchijn.sichuan.util.PhotoUtils.REQUEST_CODE_PICK_PHOTO;

public class ReportFragment extends Fragment implements ReportContract.View {
    private List<String> mUrls = new ArrayList<>();
    private SettingAdapter mSettingAdapter;
    private ReportContract.Presenter mPresenter;
    private List<SettingItem> mSettingItems;
    private PictureAdapter mPictureAdapter;
    private EditText mInputDescribe;
    private Activity mActivity;
    private PhotoUtils mPhotoUtils;

    private final int REQUEST_CODE_SHOW_PICTURE = 0x001;
    private final int REQUEST_CODE_SELECT_OPTIONS = 0x002;
    private final int REQUEST_CODE_SELECT_POSITION = 0x003;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mPhotoUtils = new PhotoUtils(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        mInputDescribe = view.findViewById(R.id.edit_describe);

        initPictureView(view);
        initSettingView(view);
        return view;
    }

    private void initSettingView(View view) {
        RecyclerView mViewSettings = view.findViewById(R.id.recycler_view_setting);
        mViewSettings.setLayoutManager(new LinearLayoutManager(getContext()));

        mSettingItems = BaseConfig.getSettingItems();
        mSettingAdapter = new SettingAdapter(mSettingItems);
        mViewSettings.setAdapter(mSettingAdapter);
        mSettingAdapter.setItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                if (item.getIndex() == 0) {
                    Toast.makeText(getContext(), "定位", Toast.LENGTH_SHORT).show();
                    startActivityForResult(new Intent(getContext(), PositionActivity.class), REQUEST_CODE_SELECT_POSITION);
                } else {
                    startActivityForResult(new Intent(getContext(), SelectOptionsActivity.class), REQUEST_CODE_SELECT_OPTIONS);
                }
            }
        });
    }

    public void Report() {
        String describe = mInputDescribe.getText().toString();
        if (TextUtils.isEmpty(describe)) {
            showError("事件描述不能为空！");
            return;
        }
        if (mUrls == null || mUrls.size() == 0) {
            showError("请选择至少一张图片");
            return;
        }
        String position = mSettingItems.get(0).getSubtitle();
        String point = mSettingItems.get(0).getId();
        if (TextUtils.isEmpty(position) || TextUtils.isEmpty(point)) {
            showError("请设置所在位置");
            return;
        }
        String mSubDepartmentId = mSettingItems.get(2).getId();
        if (TextUtils.isEmpty(mSubDepartmentId)) {
            showError("请设置子部门");
            return;
        }
        String mTypeId = mSettingItems.get(3).getId();
        if (TextUtils.isEmpty(position)) {
            showError("请设置考核类型");
            return;
        }
        String mContentId = mSettingItems.get(4).getId();
        if (TextUtils.isEmpty(position)) {
            showError("请设置考核内容");
            return;
        }
        if (mPresenter != null) {
            mPresenter.report(describe, mUrls, position, point, mSubDepartmentId, mTypeId, mContentId, "巡查员");
        }
    }

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
                                    mPhotoUtils.intentToCamera();
                                } else {
                                    mPhotoUtils.intentToPick();
                                }
                            }
                        }).create().show();
            }
        });
    }

    //权限返回结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mPhotoUtils.REQUEST_CODE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPhotoUtils.intentToCamera();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //地图定位
        if (requestCode == REQUEST_CODE_SELECT_POSITION && resultCode == RESULT_OK) {
            String position = data.getStringExtra("position");
            String point = data.getStringExtra("point");
            if (TextUtils.isEmpty(position) || TextUtils.isEmpty(point)) {
                showError("定位失败，请重新设置所在位置");
            } else {
                mSettingItems.get(0).setSubtitle(position);
                mSettingItems.get(0).setId(point);
                mSettingAdapter.notifyDataSetChanged();
            }
            return;
        }
        //拍照，返回结果需要裁剪照片
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            refreshPictures();
            return;
        }
        //相册选择返回结果
        if (requestCode == REQUEST_CODE_PICK_PHOTO && resultCode == RESULT_OK) {
            refreshPictures(mPhotoUtils.getImageUrl(data));
            return;
        }
        //删除照片
        if (requestCode == REQUEST_CODE_SHOW_PICTURE && resultCode == RESULT_OK) {
            String url = data.getStringExtra("url");
            deletePicture(url);
            return;
        }
        //设置选项
        if (requestCode == REQUEST_CODE_SELECT_OPTIONS && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra("args");
            if (bundle == null) {
                return;
            }
            ArrayList<SettingItem> list = (ArrayList<SettingItem>) bundle.getSerializable("selected");
            if (list != null) {
                SettingItem item = mSettingItems.get(0);
                mSettingItems.clear();
                mSettingItems.add(item);
                mSettingItems.addAll(list);
                mSettingAdapter.notifyDataSetChanged();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //刷新图片适配器
    private void refreshPictures() {
        File file = mPhotoUtils.getPicture();
        if (file.exists()) {
            mUrls.add(file.getAbsolutePath());
            mPictureAdapter.notifyDataSetChanged();
        }
    }

    private void refreshPictures(String url) {
        File file = new File(url);
        if (file.exists()) {
            mUrls.add(url);
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
    public void reportSuccess() {
        showError("上报成功");
        mActivity.onBackPressed();
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