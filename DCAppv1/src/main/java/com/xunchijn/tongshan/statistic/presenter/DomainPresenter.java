package com.xunchijn.tongshan.statistic.presenter;

import android.content.Context;
import android.util.Log;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.common.module.UserAccount;
import com.xunchijn.tongshan.statistic.model.StatisticResult;
import com.xunchijn.tongshan.statistic.model.StatisticService;
import com.xunchijn.tongshan.util.PreferHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class DomainPresenter implements DomainsContrast.Presenter {
    private String TAG = "";
    private DomainsContrast.View mView;
    private StatisticService mStatisticService;
    private PreferHelper mPreferHelper;
    private Observer<Response<Result<StatisticResult>>> mResultObserver;

    public DomainPresenter(DomainsContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mStatisticService = new StatisticService();
        mPreferHelper = new PreferHelper(context);
        mResultObserver = new Observer<Response<Result<StatisticResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Response<Result<StatisticResult>> resultResponse) {
                if (resultResponse.isSuccessful()) {
                    parseResult(resultResponse.body());
                } else {
                    mView.showError(resultResponse.message());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
    }

    private void parseResult(Result<StatisticResult> resultResult) {
        if (resultResult.getCode() == 200) {
            if (resultResult.getData() == null) {
                return;
            }
            if (resultResult.getData().getCarList() != null) {
                mView.showCarRecords(resultResult.getData().getCarList());
                return;
            }
            if (resultResult.getData().getCarDetails() != null) {
                mView.showCarRecords(resultResult.getData().getCarDetails());
            }
        } else {
            mView.showError(resultResult.getMessage());
        }
    }

    @Override
    public void getCarRecords(String time) {
        UserAccount userAccount = mPreferHelper.getUserAccount();
        String Name = userAccount.getUserAccount();
        mStatisticService.GetCarDomains(time, Name).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mResultObserver);
    }

    @Override
    public void getCarDomainDetails(String time, String carId) {
        mStatisticService.GetCarDomainsDetails(time, carId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mResultObserver);
    }
}
