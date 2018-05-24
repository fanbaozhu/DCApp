package com.xunchijn.sichuan.event.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.event.model.EventItem;

import java.util.List;

public interface HistoryContract {
    interface Presenter {

        //获取历史
        void getHistory();
    }

    interface View extends BaseView<Presenter> {

        //展示历史
        void showHistory(List<EventItem> list);
    }
}
