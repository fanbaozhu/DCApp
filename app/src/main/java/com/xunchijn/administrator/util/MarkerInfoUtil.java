package com.xunchijn.administrator.util;

import java.io.Serializable;

/**
 * 地图标注信息实体类
 * Created by Administrator on 2018/4/12 0012.
 */

public class MarkerInfoUtil implements Serializable {
    private double lat;//纬度
    private double lon;//经度
    private String name;//名字
    private String dept;//部门
    private String address;//位置
    private String zone;//区域
    private String state;//状态
    //构造方法
    public MarkerInfoUtil() {}
    public MarkerInfoUtil(double lat, double lon, String name, String address, String zone, String state) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.address = address;
        this.zone = zone;
        this.state = state;
    }
    //toString方法
    @Override
    public String toString() {
        return "MarkerInfoUtil [lat=" + lat + ", lon=" + lon + ", name=" + name + ", address="
                + address + ", zone=" + zone + ", state=" + state +"]";
    }
}
