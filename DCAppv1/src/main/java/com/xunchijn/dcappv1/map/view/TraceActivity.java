package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.util.TitleFragment;
import com.xunchijn.dcappv1.map.presenter.TracePresenter;

public class TraceActivity extends AbsBaseActivity {

    public static void newInstance(Context context, String type, String id) {
        Intent intent = new Intent(context, TraceActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("轨迹回放", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }

    @Override
    public void initContent() {
        String type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");
        TraceFragment fragment = TraceFragment.newInstance(type, id);
        new TracePresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment).commit();
    }
}