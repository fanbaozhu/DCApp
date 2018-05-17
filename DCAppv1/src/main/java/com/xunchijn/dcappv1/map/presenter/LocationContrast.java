package com.xunchijn.dcappv1.map.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.base.UserInfo;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.base.CarInfo;

import java.util.List;

/**
 * 契约类 P层和V层绑定 BaseView是基类
 * Created by Administrator on 2018/5/9 0009.
 */

public interface LocationContrast {
    interface Presenter {

        void getUsers(String subDepartmentId);

        void getUser(String userId);

        void getCar(String carId);

        void getCars(String subDepartmentId);
    }

    interface View extends BaseView<Presenter> {

        void showUsers(List<SelectItem> list);

        void showCars(List<SelectItem> list);

        void showUser(UserInfo userInfo);

        void showCar(CarInfo carInfo);
    }
}
