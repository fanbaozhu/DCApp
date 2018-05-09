package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       initView();
    }

    private void initView(){
        setContentView(R.layout.activity_statistic);
    }
}
