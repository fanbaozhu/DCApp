package com.xunchijn.dcappv1.statistic.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.statistic.model.DomainItem;

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
