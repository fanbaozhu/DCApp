package com.xunchijn.dcappv1.map.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.User;

import java.util.ArrayList;

/**
 * 契约类 P层和V层绑定 BaseView是基类
 * Created by Administrator on 2018/5/9 0009.
 */

public interface LocationContrast {
    interface Presenter {

        void getUsers(String subDepartmentId);

        void getUser(String userId);

        void getCars(String subDepartmentId);
    }

    interface View extends BaseView<Presenter> {

        void showUsers(ArrayList<User> list);

        void showCars(ArrayList<CarInfo> list);

        void showUser(UserInfo userInfo);
    }
}
