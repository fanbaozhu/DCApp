package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.map.presenter.LocationPresenter;
import com.xunchijn.dcappv1.map.presenter.TracePresenter;

public class MapActivity extends AppCompatActivity {
    private String mTitle;


    public static void newInstance(Context context, String title, String type, String id, boolean isAll) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("title", title);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("type", type);
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
        fragment.setRightDrawableId(mTitle.equals("轨迹回放") ? R.mipmap.ic_title_select_time : R.mipmap.ic_title_more);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, fragment)
                .show(fragment).commit();
        fragment.setConfirmListener(new TitleFragment.OnItemClickListener() {
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
        if (mTitle.equals("轨迹回放")) {
            initTraceFragment(bundle);
        } else {
            initLocationFragment(bundle);
        }
    }

    private void initTraceFragment(Bundle bundle) {
        TraceFragment fragment = TraceFragment.newInstance(bundle);
        new TracePresenter(fragment);
        addFragment(fragment);
    }

    private void initLocationFragment(Bundle bundle) {
        LocationFragment fragment = LocationFragment.newInstance(bundle);
        new LocationPresenter(fragment);
        addFragment(fragment);
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment).commit();
    }
}