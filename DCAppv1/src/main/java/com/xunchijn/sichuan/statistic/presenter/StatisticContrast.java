package com.xunchijn.sichuan.statistic.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.statistic.model.StatisticItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public interface StatisticContrast {
    interface Presenter{
        void getStatistic();
    }

    interface View extends BaseView<Presenter>{
        void showStatistics(List<StatisticItem> list);
    }
}
