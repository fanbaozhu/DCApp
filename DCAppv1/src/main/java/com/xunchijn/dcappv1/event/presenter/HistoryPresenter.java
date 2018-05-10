package com.xunchijn.dcappv1.event.presenter;

import com.xunchijn.dcappv1.event.contract.HistoryContract;
import com.xunchijn.dcappv1.util.TestData;

public class HistoryPresenter implements HistoryContract.Presenter {
    private HistoryContract.View mView;

    public HistoryPresenter(HistoryContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getHistory() {
        mView.showHistory(TestData.getEventHistory(10));
    }
}
