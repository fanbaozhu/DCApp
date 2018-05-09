package com.xunchijn.dcappv1.event.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.NestingItem;

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
        void getCheckContent();

        //上传图片
        void uploadPictures(List<String> urls);

        //事件上报：事件描述、图片地址、部门、子部门、考核类型、事件内容
        void report(String describe, String urls, String department, String subDepartment, String type, String content);
    }

    interface View extends BaseView<Presenter> {

        void showDepartment(List<NestingItem> list);

        void showSubDepartment(List<NestingItem> list);

        void showCheckType(List<NestingItem> list);

        void showCheckContent(List<NestingItem> list);
    }
}
