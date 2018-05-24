package com.xunchijn.sichuan.event.view;

import android.content.Intent;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.event.presenter.ReportPresenter;
import com.xunchijn.sichuan.util.TitleFragment;
import com.xunchijn.sichuan.util.TitleFragment.OnItemClickListener;

public class ReportActivity extends AbsBaseActivity {
    private ReportFragment mReportFragment;

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
