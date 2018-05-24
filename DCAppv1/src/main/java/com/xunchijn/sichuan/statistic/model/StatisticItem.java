package com.xunchijn.sichuan.statistic.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.sichuan.util.TimeUtils;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 10:41
 * Description:统计报表列表项
 **/


public class StatisticItem {

    @SerializedName("rfidScanTime")
    private String mRFIDScanTime;
    @SerializedName("truckNumber")
    private String mTruckNumber;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("Address")
    private String mAddress;

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getRFIDScanTime() {
        if (TextUtils.isEmpty(mRFIDScanTime)) {
            return "";
        }
        return TimeUtils.getStrTime(mRFIDScanTime);
    }

    public String getTruckNumber() {
        return mTruckNumber;
    }

    public boolean isOffline() {
        return TextUtils.isEmpty(mStatus) || mStatus.equals("未清理");
    }

    public String getAddress() {
        return mAddress;
    }
}
