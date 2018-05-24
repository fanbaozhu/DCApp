package com.xunchijn.sichuan.event.presenter;

import com.xunchijn.sichuan.base.BaseView;

import java.util.List;

public interface ReportContract {
    interface Presenter {

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

        void reportSuccess();
    }
}
