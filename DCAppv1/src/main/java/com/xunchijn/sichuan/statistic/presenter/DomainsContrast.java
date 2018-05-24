package com.xunchijn.sichuan.statistic.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.statistic.model.DomainItem;

import java.util.List;

public interface DomainsContrast {
    interface Presenter {
        void getCarRecords(String time);

        void getCarDomainDetails(String time, String carId);
    }

    interface View extends BaseView<Presenter> {
        void showCarRecords(List<DomainItem> list);
    }
}
