package com.xunchijn.tongshan.event.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.event.model.EventItem;

import java.util.List;

public interface EventHistoryContract {
    interface Presenter {

        //获取历史
        void getHistory();

        //获取事件历史
        void getEventHistory();
    }

    interface View extends BaseView<Presenter> {

        //展示历史
        void showHistory(List<EventItem> list);
    }
}
