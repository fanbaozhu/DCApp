package com.xunchijn.tongshan.event.view;

import android.content.Context;
import android.content.Intent;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.event.presenter.ReportPresenter;
import com.xunchijn.tongshan.util.TitleFragment;
import com.xunchijn.tongshan.util.TitleFragment.OnItemClickListener;

public class ReportActivity extends AbsBaseActivity {
    private ReportFragment mReportFragment;

    public static void start(Context context, boolean haveCheckType) {
        Intent intent = new Intent(context, ReportActivity.class);
        intent.putExtra("haveCheckType", haveCheckType);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("事件上报", true, true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();

        titleFragment.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                mReportFragment.Report();
            }
        });
    }

    @Override
    public void initContent() {
        mReportFragment = new ReportFragment();
        boolean haveCheckType = getIntent().getBooleanExtra("haveCheckType", true);
        mReportFragment.setHaveCheckType(haveCheckType);
        new ReportPresenter(mReportFragment, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mReportFragment)
                .show(mReportFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mReportFragment != null) {
            mReportFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
