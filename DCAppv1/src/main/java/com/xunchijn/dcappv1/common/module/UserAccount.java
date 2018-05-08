package com.xunchijn.dcappv1.common.module;

public class UserAccount {
    private String mUserAccount;
    private String mUserPassword;

    public UserAccount(String userAccount, String userPassword) {
        mUserAccount = userAccount;
        mUserPassword = userPassword;
    }

    public String getUserAccount() {
        return mUserAccount;
    }

    public String getUserPassword() {
        return mUserPassword;
    }
}
