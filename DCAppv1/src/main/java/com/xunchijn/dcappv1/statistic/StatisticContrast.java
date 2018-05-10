package com.xunchijn.dcappv1.statistic;

import com.xunchijn.dcappv1.base.BaseView;

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
