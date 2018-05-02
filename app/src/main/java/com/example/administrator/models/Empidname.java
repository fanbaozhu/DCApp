package com.example.administrator.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Empidname implements Serializable {
    //人员ID
    private String empid;
    //
    private String empname;

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpid() {

        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}
