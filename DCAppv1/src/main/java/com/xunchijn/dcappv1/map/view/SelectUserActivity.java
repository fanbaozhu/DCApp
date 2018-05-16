package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

public class SelectUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_layout);
        initTitle();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("选择人员", true, true);
        titleFragment.setConfirmListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {

            }

            @Override
            public void onConfirm() {

            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    private void initContent() {
        SelectFragment selectFragment = new SelectFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, selectFragment)
                .show(selectFragment)
                .commit();
    }
}
