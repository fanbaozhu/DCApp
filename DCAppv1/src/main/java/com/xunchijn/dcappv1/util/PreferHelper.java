package com.xunchijn.dcappv1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class PreferHelper {
    private static final String TAG = "PreferHelper";
    private static final String PREFERENCES = "PREFERENCES";
    private static final String USERNAME = "UserName";
    private static final String USERPASSWORD = "UserPassword";
    private SharedPreferences mSharedPreferences;

    public PreferHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    private void save(String key, String value) {
        if (mSharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String get(String key) {
        if (mSharedPreferences == null) {
            return "";
        }
        return mSharedPreferences.getString(key,"");
    }
}