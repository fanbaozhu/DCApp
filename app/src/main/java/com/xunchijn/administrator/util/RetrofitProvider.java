package com.xunchijn.administrator.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.xunchijn.administrator.baidumap.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class RetrofitProvider {
    //发布
    private static final String RELEASE = "http://192.168.1.221:8097";
    //预发布
    private static final String PRE_RELEASE = "http://192.168.1.221:8097";
    //开发
    private static final String DEVELOP = "http://192.168.1.221:8097";

    @NonNull
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

        return new Retrofit.Builder().baseUrl(DEVELOP)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    public static Retrofit getProgressClient(HttpUrl url, Interceptor projressInterceptor){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.connectTimeout(9,TimeUnit.SECONDS);
        if(projressInterceptor != null){
            builder.addNetworkInterceptor(projressInterceptor);
        }
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return new Retrofit.Builder().baseUrl(DEVELOP)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    public static String getDiskCacheDir(Context context){
        File cachePath;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir();
        }else{
            cachePath = context.getCacheDir();
        }
        if(cachePath == null){
            return "";
        }
        return cachePath.getPath();
    }
}
