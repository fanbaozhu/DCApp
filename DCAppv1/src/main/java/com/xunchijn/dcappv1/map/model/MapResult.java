package com.xunchijn.dcappv1.map.model;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.common.module.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MapResult {
    private ArrayList<User> userList;
    @SerializedName("userInfomation")
    private UserInfo mUserInfo;
    private ArrayList<Car> carList;
    @SerializedName("carInfomation")
    private CarInfo carInformation;
    private List<TraceInfo> userTraceList;
    private List<TraceInfo> carTraceList;

    public ArrayList<User> getUserList() {
        return userList;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public CarInfo getCarInformation() {
        return carInformation;
    }

    public List<TraceInfo> getUserTraceList() {
        return userTraceList;
    }

    public List<TraceInfo> getCarTraceList() {
        return carTraceList;
    }
}
