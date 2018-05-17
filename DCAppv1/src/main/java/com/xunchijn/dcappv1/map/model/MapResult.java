package com.xunchijn.dcappv1.map.model;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.base.CarInfo;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.base.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MapResult {
    @SerializedName("userInfomation")
    private UserInfo mUserInfo;
    @SerializedName("carInfomation")
    private CarInfo carInformation;
    private List<SelectItem> userList;
    private List<SelectItem> carList;
    private List<TraceInfo> userTraceList;
    private List<TraceInfo> carTraceList;

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public List<SelectItem> getUserList() {
        return userList;
    }

    public List<SelectItem> getCarList() {
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
