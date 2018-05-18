package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.map.presenter.TracePresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

public class TraceActivity extends AbsBaseActivity {
    private TraceFragment mTraceFragment;

    public static void newInstance(Context context, String type, String id) {
        Intent intent = new Intent(context, TraceActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("轨迹回放", true, true);
        titleFragment.setRightDrawableId(R.mipmap.ic_trace_follow);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                mTraceFragment.setFollow();
            }
        });
    }

    @Override
    public void initContent() {
        String type = getIntent().getStringExtra("type");
        String id = getIntent().getStringExtra("id");
        mTraceFragment = TraceFragment.newInstance(type, id);
        new TracePresenter(mTraceFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mTraceFragment)
                .show(mTraceFragment).commit();
    }
}