package com.xunchijn.dcappv1.common.presenter;

import android.util.Log;

import com.xunchijn.dcappv1.common.contract.StatisticContrast;
import com.xunchijn.dcappv1.common.data.CommonService;
import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticPresenter implements StatisticContrast.Presenter {
    private final String TAG = "Statistic";
    private StatisticContrast.View mView;
    private CommonService mService;

    public StatisticPresenter(StatisticContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mService = new CommonService();
    }

    public void getStatistic() {
        mService.statistic()
                //在主线程执行
                .subscribeOn(AndroidSchedulers.mainThread())
                //返回的结果
                .subscribe(new Observer<Response<Result<CommonResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<CommonResult>> resultResponse) {
                        if (resultResponse.isSuccessful()) {
                            parseResult(resultResponse.body());
                        } else {
                            mView.showError(resultResponse.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void parseResult(Result<CommonResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getStatisticList() != null) {
                mView.showStatistics(result.getData().getStatisticList());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
