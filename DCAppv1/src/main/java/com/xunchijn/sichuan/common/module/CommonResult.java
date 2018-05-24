package com.xunchijn.sichuan.common.module;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.sichuan.base.UserInfo;
import com.xunchijn.sichuan.statistic.model.StatisticItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class CommonResult {
    private UserInfo userInfo;
    private List<StatisticItem> StatisticList;
    @SerializedName("SearchItemList")
    private List<SearchItem> mSearchItemList;
    private Integer feedbackStatus;
    private Integer resetPassStatus;
    private List<MessageItem> messages;

    public List<SearchItem> getSearchItemList() {
        return mSearchItemList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<StatisticItem> getStatisticList() {
        return StatisticList;
    }

    public Integer getFeedbackStatus() {
        return feedbackStatus;
    }

    public Integer getResetPassStatus() {
        return resetPassStatus;
    }

    public List<MessageItem> getMessages() {
        return messages;
    }
}
