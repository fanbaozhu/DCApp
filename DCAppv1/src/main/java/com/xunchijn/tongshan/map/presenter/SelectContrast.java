package com.xunchijn.tongshan.map.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.event.model.SelectItem;

import java.util.List;

public interface SelectContrast {
    interface Presenter {
        void getDepartment();

        void getSubDepartment(String departmentId);

        void getUsers(String subDepartmentId);

        void getCars(String subDepartmentId);
    }

    interface View extends BaseView<Presenter> {
        void showOptions(String string, List<? extends SelectItem> list);
    }
}
