package com.xunchijn.dcappv1.event.model;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.User;

import java.util.ArrayList;
import java.util.List;

public class EventResult {
    private List<SelectItem> checkDepartment;
    private List<SelectItem> checkSubDepartment;
    private List<SelectItem> checkType;
    private List<SelectItem> checkContent;
    private String fileName;//上传文件返回的文件名
    private Integer reportStatus;//上报事件状态标志
    private List<EventEntity> eventHistory;
    @SerializedName("userInfomation")
    private UserInfo mUserInfo;
    private ArrayList<User> userList;
    private ArrayList<CarInfo> carList;

    public List<SelectItem> getCheckDepartment() {
        return checkDepartment;
    }

    public List<SelectItem> getCheckSubDepartment() {
        return checkSubDepartment;
    }

    public List<SelectItem> getCheckType() {
        return checkType;
    }

    public List<SelectItem> getCheckContent() {
        return checkContent;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public List<EventEntity> getEventHistory() {
        return eventHistory;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<CarInfo> getCarList() {
        return carList;
    }
}
