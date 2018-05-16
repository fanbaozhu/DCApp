package com.xunchijn.dcappv1.common.module;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.statistic.StatisticItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class CommonResult {
    private UserInfo userInfo;
    private List<StatisticItem> StatisticList;
    @SerializedName("SearchItemList")
    private List<SearchItem> mSearchItemList;

    public List<SearchItem> getSearchItemList() {
        return mSearchItemList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<StatisticItem> getStatisticList() {
        return StatisticList;
    }
}
