package com.xunchijn.dcappv1.event.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;

import java.util.List;

public interface ReportContract {
    interface Presenter {

        //获取部门列表
        void getDepartment();

        //获取子部门列表
        void getSubDepartment(String departmentId);

        //获取考核类型列表
        void getCheckType();

        //获取考核内容列表
        void getCheckContent(String typeId);

        //事件上报：事件描述、图片地址、部门、子部门、考核类型、事件内容
        void report(String describe, List<String> urls, String position, String subDepartment,
                    String type, String content, String accountId, String point, String address);
    }

    interface View extends BaseView<Presenter> {

        void showDepartment(List<SelectItem> list);

        void showSubDepartment(List<SelectItem> list);

        void showCheckType(List<SelectItem> list);

        void showCheckContent(List<SelectItem> list);
    }
}
