package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.map.presenter.DetailsPresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

public class DetailsCarActivity extends AbsBaseActivity {
    private static String ID = "eventId";
    public static void newInstance(Context context, String type, String id) {
        Intent intent = new Intent(context, DetailsCarActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("车辆信息", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }

    @Override
    public void initContent() {
        String eventId = getIntent().getStringExtra(ID);
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        DetailsFragment detailsFragment = DetailsFragment.newInstance(eventId, type);
        new DetailsPresenter(detailsFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, detailsFragment)
                .show(detailsFragment).commit();
    }
}

