package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.model.CarInfo;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    private String mTitle;


    public static void newInstance(Context context, String title, ArrayList<CarInfo> cars, ArrayList<UserInfo> users) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("title", title);
        Bundle bundle = new Bundle();
        if (cars != null) {
            bundle.putSerializable("cars", cars);
        }
        if (users != null) {
            bundle.putSerializable("users", users);
        }
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

        TitleFragment fragment = TitleFragment.newInstance(mTitle, true, true,
                mTitle.equals("轨迹回放") ? R.mipmap.ic_title_time : R.mipmap.ic_title_refresh, 0);
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