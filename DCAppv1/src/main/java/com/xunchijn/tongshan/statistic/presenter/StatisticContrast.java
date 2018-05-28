package com.xunchijn.tongshan.statistic.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.statistic.model.StatisticItem;

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
