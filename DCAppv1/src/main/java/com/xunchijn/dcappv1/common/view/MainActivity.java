package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTitle();
        initView();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("大厂APP", false, false, 0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    private void initView() {
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mainFragment)
                .show(mainFragment)
                .commit();
    }
}
