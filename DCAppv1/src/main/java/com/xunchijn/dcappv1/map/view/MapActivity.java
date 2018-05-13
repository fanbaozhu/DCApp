package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

public class MapActivity extends AppCompatActivity {
    private String mTitle;


    public static void newInstance(Context context, String title, String id, boolean isAll) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("title", title);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putBoolean("isAll", isAll);
        intent.putExtra("args", bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initTitle() {
        mTitle = getIntent().getStringExtra("title");

        TitleFragment fragment = TitleFragment.newInstance(mTitle, true, true);
        fragment.setRightDrawableId(mTitle.equals("轨迹回放") ? R.mipmap.ic_title_time : R.mipmap.ic_title_more);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, fragment)
                .show(fragment).commit();
        fragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {

            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_map);
        initTitle();
        Bundle bundle = getIntent().getBundleExtra("args");
        if (bundle == null) {
            return;
        }
        Fragment fragment;
        if (mTitle.equals("轨迹回放")) {
            fragment = TraceFragment.newInstance(bundle);
        } else {
            fragment = LocationFragment.newInstance(bundle);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment).commit();
    }
}