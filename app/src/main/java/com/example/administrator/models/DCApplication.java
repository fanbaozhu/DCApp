package com.example.administrator.models;

import android.app.Application;

/**
 * Created by Administrator on 2017/10/16.
 */

public class DCApplication extends Application {
    String userName;
    String passWord;
    String deptId;
    public void  setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setPassWord(String passWord){
        this.passWord = passWord;

    }

    public String getPassWord(){
        return passWord;
    }

    public void setDeptId(String deptId)
    {
        this.deptId=deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }
}
