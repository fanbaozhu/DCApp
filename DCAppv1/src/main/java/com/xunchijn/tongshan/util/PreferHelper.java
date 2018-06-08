package com.xunchijn.tongshan.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.xunchijn.tongshan.common.module.UserAccount;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class PreferHelper {
    private static final String TAG = "PreferHelper";
    private static final String PREFERENCES = "PREFERENCES";
    private static final String USER_ACCOUNT = "UserName";
    private static final String USER_PASSWORD = "UserPassword";
    private static final String USER_EMPNAME = "UserEmpName";
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
        return mSharedPreferences.getString(key, "");
    }

    public UserAccount getUserAccount() {
        String userAccount = get(USER_ACCOUNT);
        String userPassword = get(USER_PASSWORD);
        String userEmpName = get(USER_EMPNAME);
        if (TextUtils.isEmpty(userAccount) || TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userEmpName)) {
            return null;
        }
        return new UserAccount(userAccount, userPassword, userEmpName);
    }

    public void saveUserAccount(UserAccount account) {
        if (account == null) {
            save(USER_ACCOUNT, "");
            save(USER_PASSWORD, "");
            save(USER_EMPNAME, "");
            return;
        }
        save(USER_ACCOUNT, account.getUserAccount());
        save(USER_PASSWORD, account.getUserPassword());
        save(USER_EMPNAME, account.getUserEmpName());
    }
}