package com.xunchijn.dcappv1.event.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 11:05
 * Description:
 **/


public class StatisticActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    public void initView(){
        setContentView(R.layout.activity_statistic);
    }
}
