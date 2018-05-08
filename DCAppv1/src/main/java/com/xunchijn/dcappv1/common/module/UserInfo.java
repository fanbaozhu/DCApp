package com.xunchijn.dcappv1.common.module;

/**
 * Created by ZhouJiaWei on 2018/5/7 0007.
 */

public class UserInfo {
    //用户ID：标示唯一用户
    private String userId;
    //用户账号
    private String userAccount;
    //用户名字
    private String userName;
    //用户群组：标示是普通用户,还是管理员用户
    private String groupId;
    //用户注册时间戳
    private String registerTime;
    //用户最后登录时间戳
    private String lastLoginTime;

    public String getUserId() {
        return userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }
}
