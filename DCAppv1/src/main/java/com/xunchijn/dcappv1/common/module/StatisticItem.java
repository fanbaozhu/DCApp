package com.xunchijn.dcappv1.common.module;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 10:41
 * Description:
 **/


public class StatisticItem {
    private String rfidScanTime;
    private String truckNumber;
    private String status;
    private String address;
    private int index;

    public StatisticItem(int index, String truckNumber, String status, String rfidScanTime, String address) {
        this.index = index;
        this.truckNumber = truckNumber;
        this.status = status;
        this.rfidScanTime = rfidScanTime;
        this.address = address;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRfidScanTime(String rfidScanTime) {
        this.rfidScanTime = rfidScanTime;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRfidScanTime() {
        return rfidScanTime;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }
}
