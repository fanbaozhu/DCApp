package com.xunchijn.dcappv1.common.module;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("address")
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

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public void setRFIDScanTime(String RFIDScanTime) {
        this.mRFIDScanTime = RFIDScanTime;
    }

    public void setTruckNumber(String truckNumber) {
        this.mTruckNumber = truckNumber;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getRFIDScanTime() {
        return mRFIDScanTime;
    }

    public String getTruckNumber() {
        return mTruckNumber;
    }

    public String getStatus() {
        return mStatus;
    }

    public boolean isOffline() {
        return TextUtils.isEmpty(mStatus) || mStatus.equals("离线");
    }

    public String getAddress() {
        return mAddress;
    }
}
