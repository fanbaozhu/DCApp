package com.xunchijn.tongshan.event.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.event.model.DetailsItem;

import java.util.List;

public interface DetailsContract {
    interface Presenter {
        void getCarDetails(String id);
    }

    interface View extends BaseView<Presenter> {
        void showCarDetails(DetailsItem item);
    }
}
