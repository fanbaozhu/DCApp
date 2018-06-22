package com.xunchijn.tongshan.statistic.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.statistic.model.DomainItem;

import java.util.List;

public interface DomainsContrast {
    interface Presenter {
        void getCarRecords(String time);

        void getCarDomainDetails(String time, String carId);

        void getRegionCar(String time, String type);

        void getRegionCarDetails(String time, String carId);
    }

    interface View extends BaseView<Presenter> {
        void showCarRecords(List<DomainItem> list);
    }
}
