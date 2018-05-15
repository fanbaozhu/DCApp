package com.xunchijn.dcappv1.map.presenter;

import com.xunchijn.dcappv1.map.contract.TraceContrast;
import com.xunchijn.dcappv1.util.TestData;

public class TracePresenter implements TraceContrast.Presenter {
    private TraceContrast.View mView;

    public TracePresenter(TraceContrast.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getUserTrace(String userAccount, String startTime, String endTime) {
        mView.showUserTrace(TestData.getEmpPoint());
    }

    @Override
    public void getCarTrace(String carId, String startTime, String endTime) {

    }
}
