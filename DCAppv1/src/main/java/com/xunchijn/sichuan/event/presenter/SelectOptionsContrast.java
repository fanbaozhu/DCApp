package com.xunchijn.sichuan.event.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.event.model.SelectItem;

import java.util.List;

public interface SelectOptionsContrast {

    interface Presenter {
        void getDepartments();

        void getSubDepartments(String departmentId);

        void getCheckType();

        void getCheckContent(String checkType);
    }

    interface View extends BaseView<Presenter> {

        void showShortOptions(String title, List<SelectItem> list);

        void showLongOptions(String title, List<SelectItem> list);
    }
}
