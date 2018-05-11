package com.xunchijn.dcappv1.common.module;

import java.io.Serializable;

/**
 * Created by ZhouJiaWei on 2018/5/7 0007.
 */

public class UserInfo implements Serializable {
    //用户ID：标示唯一用户
    private String userId;
    //用户账号
    private String userAccount;
    //用户名字
    private String userName="001";
    //用户群组：标示是普通用户,还是管理员用户
    private String groupId;
    //用户注册时间戳
    private String registerTime;
    //用户最后登录时间戳
    private String lastLoginTime;
    //用户部门
    private String userDept="001";
    //用户状态
    private String userStatus="03";
    //用户区域
    private String userZoon="002";
    //用户最近一次上传的时间
    private String userGPSScanTime;
    //用户当前经纬度
    private String userPoint;
    //用户当前位置
    private String userAddress="005";

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserZoon() {
        return userZoon;
    }

    public String getUserGPSScanTime() {
        return userGPSScanTime;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public String getUserDept() {

        return userDept;
    }

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

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }
}
