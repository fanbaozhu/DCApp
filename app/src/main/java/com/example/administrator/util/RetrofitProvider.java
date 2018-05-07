package com.example.administrator.util;

import com.example.administrator.baidumap.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class Retrofit {
    //发布
    private static final String RELEASE = "http://192.168.1.221:8097";
    //预发布
    private static final String PRE_RELEASE = "http://192.168.1.221:8097";
    //开发
    private static final String DEVELOP = "http://192.168.1.221:8097";

    public static Retrofit get(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //读取超时
        builder.readTimeout(10, TimeUnit.SECONDS);
        //连接超时
        builder.connectTimeout(9, TimeUnit.SECONDS);

        //DeBug模式下
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        //选择使用的网络传输版本

        return new Retrofit.Builder
    }
}
