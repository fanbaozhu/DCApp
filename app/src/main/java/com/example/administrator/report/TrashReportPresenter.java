package com.example.administrator.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class TrashReportPresenter implements TrashReportContrast.Presenter {
    private TrashReportContrast.View mView;

    public TrashReportPresenter(TrashReportContrast.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getDayList() {

    }

    @Override
    public void getWeekList() {

    }

    @Override
    public void getMonthList() {
        List<ReportItem> list = new ArrayList<>();

        mView.showList(list);
    }
}
