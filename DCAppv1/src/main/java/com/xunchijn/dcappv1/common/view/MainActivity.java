package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.map.view.SelectActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initView();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("大厂APP", true, true,
                R.mipmap.ic_title_search_64, R.mipmap.ic_title_user);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
        titleFragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {

            }

            @Override
            public void onConfirm() {
//                SelectActivity.newInstance(MainActivity.this, "人员");
            }
        });
    }

    private void initView() {
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mainFragment)
                .show(mainFragment)
                .commit();
    }

}
