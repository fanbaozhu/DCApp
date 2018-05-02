package com.example.administrator.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Deptidname implements Serializable {
    //部门号
    private String code;
    //部门名称
    private String dept;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDept() {

        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
