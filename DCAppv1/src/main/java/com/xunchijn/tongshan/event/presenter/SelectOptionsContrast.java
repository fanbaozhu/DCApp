package com.xunchijn.tongshan.event.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.common.module.SettingItem;
import com.xunchijn.tongshan.event.model.SelectItem;

import java.util.ArrayList;
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

        ArrayList<SettingItem> getSelectItems();
    }
}
