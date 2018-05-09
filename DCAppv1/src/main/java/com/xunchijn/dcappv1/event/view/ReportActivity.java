package com.xunchijn.dcappv1.event.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;

public class ReportActivity extends AppCompatActivity {
    private ReportFragment reportFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_report);

        reportFragment = new ReportFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, reportFragment)
                .show(reportFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (reportFragment != null) {
            reportFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
