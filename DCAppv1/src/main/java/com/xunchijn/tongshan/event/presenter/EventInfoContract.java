package com.xunchijn.tongshan.event.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.event.model.EventItem;

public interface EventInfoContract {
    interface Presenter {
        void getEventInfo(String id);
    }

    interface View extends BaseView<Presenter> {
        void showEventInfo(EventItem item);
    }
}
