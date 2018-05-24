package com.xunchijn.sichuan.event.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.event.model.EventItem;

public interface EventInfoContract {
    interface Presenter {
        void getEventInfo(String id);
    }

    interface View extends BaseView<Presenter> {
        void showEventInfo(EventItem item);
    }
}
