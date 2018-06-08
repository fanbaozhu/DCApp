package com.xunchijn.tongshan.map.view;

import android.content.Context;
import android.content.Intent;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.util.TitleFragment;
import com.xunchijn.tongshan.map.presenter.LocationPresenter;

public class LocationActivity extends AbsBaseActivity {

    public static void newInstance(Context context, String type, String id) {
        Intent intent = new Intent(context, LocationActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("地图定位", true, false);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        String type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");

        LocationFragment fragment = LocationFragment.newInstance(type, id);
        new LocationPresenter(fragment,this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment).commit();
    }
}
