package com.xunchijn.dcappv1.event.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

public class ShowPictureActivity extends AppCompatActivity {
    private String url;

    //ReportFragment照片看整图的时候调用的方法
    public static void startShowPicture(Activity activity, String url, int requestCode) {
        Intent intent = new Intent(activity, ShowPictureActivity.class);
        intent.putExtra("url", url);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        initTitle();
        showPicture();
    }

    //标题、返回、删除
    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("图片详情", true, true);
        titleFragment.setRightDrawableId(R.mipmap.ic_picture_delete);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onConfirm() {
                new AlertDialog.Builder(ShowPictureActivity.this)
                        .setMessage("确定不使用这张图片了吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                setResult(RESULT_OK, new Intent().putExtra("url", url));
                                finish();
                            }
                        }).create().show();
            }
        });

    }

    //展示图片
    private void showPicture() {
        ImageView ivShowPicture = findViewById(R.id.iv_showPicture);
        url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(this).load(url).apply(new RequestOptions().skipMemoryCache(true).centerCrop()).into(ivShowPicture);
    }
}
