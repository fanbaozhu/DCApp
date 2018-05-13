package com.xunchijn.dcappv1.map.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.User;

import java.util.ArrayList;
import java.util.List;

public interface SelectContrast {
    interface Presenter {
        void getDepartment();

        void getSubDepartment(String departmentId);

        void getUsers(String subDepartmentId);

        void getCars(String subDepartmentId);
    }

    interface View extends BaseView<Presenter> {
        void showDepartment(List<SelectItem> list);

        void showSubDepartment(List<SelectItem> list);

        void showUsers(ArrayList<User> list);

        void showCars(ArrayList<CarInfo> list);
    }
}
