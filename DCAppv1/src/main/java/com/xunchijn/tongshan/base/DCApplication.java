package com.xunchijn.tongshan.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class DCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}