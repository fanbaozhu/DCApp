package com.xunchijn.dcappv1.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;

public abstract class AbsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_layout);
        initTitle();
        initContent();
    }

    public abstract void initTitle();

    public abstract void initContent();
}
