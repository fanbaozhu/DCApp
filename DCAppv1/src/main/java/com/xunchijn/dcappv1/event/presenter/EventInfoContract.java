package com.xunchijn.dcappv1.event.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.event.model.EventItem;

public interface EventInfoContract {
    interface Presenter {
        void getEventInfo(String id);
    }

    interface View extends BaseView<Presenter> {
        void showEventInfo(EventItem item);
    }
}
