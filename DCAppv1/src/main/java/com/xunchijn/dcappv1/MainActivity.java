package com.xunchijn.dcappv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.event.view.ReportFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportFragment reportFragment = new ReportFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, reportFragment)
                .show(reportFragment)
                .commit();
    }
}
