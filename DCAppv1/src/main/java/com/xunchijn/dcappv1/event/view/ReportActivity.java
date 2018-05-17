package com.xunchijn.dcappv1.event.view;

import android.content.Intent;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.util.TitleFragment;
import com.xunchijn.dcappv1.util.TitleFragment.OnItemClickListener;
import com.xunchijn.dcappv1.event.presenter.ReportPresenter;

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
        new ReportPresenter(mReportFragment);
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
