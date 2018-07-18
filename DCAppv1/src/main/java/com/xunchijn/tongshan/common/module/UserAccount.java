package com.xunchijn.tongshan.common.module;

public class UserAccount {
    private String mUserAccount;
    private String mUserPassword;
    private String mUserEmpName;



    public UserAccount(String userAccount, String userPassword, String userEmpName) {
        mUserAccount = userAccount;
        mUserPassword = userPassword;
        mUserEmpName = userEmpName;

    }

    public String getUserEmpName() {
        return mUserEmpName;
    }

    public String getUserAccount() {
        return mUserAccount;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserAccount(String userAccount) {
        mUserAccount = userAccount;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }
}
