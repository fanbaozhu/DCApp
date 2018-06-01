package com.xunchijn.dcappv1.map.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.base.CarInfo;
import com.xunchijn.dcappv1.base.UserInfo;

public interface DetailsContrast {
    interface Presenter {
        void DetailsUser(String simId);

        void DetailsCar(String simId);
    }

    interface View extends BaseView<Presenter> {
        void showUser(UserInfo userInfo);
        void showCar(CarInfo carInfo);
    }
}