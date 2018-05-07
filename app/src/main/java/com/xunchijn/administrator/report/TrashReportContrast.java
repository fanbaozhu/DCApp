package com.xunchijn.administrator.report;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public interface TrashReportContrast {
    interface Presenter {
        void getDayList();

        void getWeekList();

        void getMonthList();
    }
    interface  View<T extends Presenter> {
        void showList(List<ReportItem> list);

        void showError(String error);

        void setPresenter(T presenter);
    }
}
