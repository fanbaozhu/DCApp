package com.xunchijn.sichuan.map.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.map.model.TraceInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface TraceContrast {

    interface Presenter {
        //获取用户轨迹
        void getUserTrace(String userAccount, String startTime, String endTime);

        //获取车辆轨迹
        void getCarTrace(String carId, String startTime, String endTime);

    }

    interface View extends BaseView<Presenter> {

        //显示用户轨迹
        void showUserTrace(List<TraceInfo> list);

        //显示车辆轨迹
        void showCarTrace(List<TraceInfo> list);
    }
}
