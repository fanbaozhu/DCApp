package com.xunchijn.dcappv1.event.contract;

import com.xunchijn.dcappv1.base.BaseView;
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

        /*
         *@Method 事件上报
         *  @Params describe:事件描述
         * @Params urls:事件描述
         * @Params address:事件描述
         * @Params point:事件描述
         * @Params subDepartment:事件描述
         * @Params type:事件描述
         * @Params content:事件描述
         * @Params accountId:事件描述
         * */
        void report(String describe, List<String> urls, String address, String point,
                    String subDepartment, String type, String content, String accountId);
    }

    interface View extends BaseView<Presenter> {

        void showDepartment(List<SelectItem> list);

        void showSubDepartment(List<SelectItem> list);

        void showCheckType(List<SelectItem> list);

        void showCheckContent(List<SelectItem> list);

        void reportSuccess();
    }
}
