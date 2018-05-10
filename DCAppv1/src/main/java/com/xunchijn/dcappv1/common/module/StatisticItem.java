package com.xunchijn.dcappv1.common.module;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.xunchijn.dcappv1.util.TimeUtils;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 10:41
 * Description:
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
    @SerializedName("index")
    private int mIndex;

    public StatisticItem(int index, String truckNumber, String status, String RFIDScanTime, String address) {
        mIndex = index;
        mTruckNumber = truckNumber;
        mStatus = status;
        mRFIDScanTime = RFIDScanTime;
        mAddress = address;
    }

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
