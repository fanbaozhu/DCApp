package com.xunchijn.dcappv1.event.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.EventItem;

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
