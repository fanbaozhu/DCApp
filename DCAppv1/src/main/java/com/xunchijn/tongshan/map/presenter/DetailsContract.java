package com.xunchijn.tongshan.map.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.map.model.DetailsItem;

public interface DetailsContract {
    interface Presenter {
        void getCarDetails(String id);
        void getUserDetails(String id);
    }

    interface View extends BaseView<Presenter> {
        void showCarDetails(DetailsItem item);
        void showUserDetails(DetailsItem item);
    }
}
