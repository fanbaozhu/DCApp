package com.xunchijn.dcappv1.event.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.EventEntity;

import java.util.List;

public interface HistoryContract {
    interface Presenter {

        //获取历史
        void getHistory();
    }

    interface View extends BaseView<Presenter> {

        //展示历史
        void showHistory(List<EventEntity> list);
    }
}
