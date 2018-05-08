package com.xunchijn.dcappv1.event.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;

public class ReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_report);

        ReportFragment reportFragment = new ReportFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, reportFragment)
                .show(reportFragment)
                .commit();
    }
}
