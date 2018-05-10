package com.xunchijn.dcappv1.common.module;

import com.xunchijn.dcappv1.map.model.EmpItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class CommonResult {
    private UserInfo userInfo;
    private List<StatisticItem> StatisticList;
    private List<EmpItem> userInfomation;

    public List<EmpItem> getUserInfomation() {
        return userInfomation;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<StatisticItem> getStatisticList() {
        return StatisticList;
    }
}
