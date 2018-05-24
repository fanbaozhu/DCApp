package com.xunchijn.sichuan.base;

import com.google.gson.annotations.SerializedName;

public class CarInfo {
    private String carId;
    private String carName;
    @SerializedName("carZoon")
    private String carZone;
    private String carStatus;
    private String carAddress;
    private String carPoint;
    private String carGPSScanTime;

    public String getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarZone() {
        return carZone;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public String getCarPoint() {
        return carPoint;
    }

    public String getCarGPSScanTime() {
        return carGPSScanTime;
    }
}
