package com.xunchijn.tongshan.map.presenter;

import android.util.Log;

import com.xunchijn.tongshan.map.model.MapService;
import com.xunchijn.tongshan.map.model.MapResult;
import com.xunchijn.tongshan.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class TracePresenter implements TraceContrast.Presenter {
    private static final String TAG = "Trace";
    private TraceContrast.View mView;
    private MapService mMapService;
    private Observer<Response<Result<MapResult>>> mObserver;

    public TracePresenter(TraceContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mMapService = new MapService();
        mObserver = new Observer<Response<Result<MapResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Response<Result<MapResult>> resultResponse) {
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

    private void parseResult(Result<MapResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getUserTraceList() != null) {
                mView.showUserTrace(result.getData().getUserTraceList());
                return;
            }
            if (result.getData().getCarTraceList() != null) {
                mView.showCarTrace(result.getData().getCarTraceList());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }

    @Override
    public void getUserTrace(String userAccount, String startTime, String endTime) {
        mMapService.getUserTrace(userAccount, startTime, endTime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCarTrace(String carId, String startTime, String endTime) {
        mMapService.getCarTrace(carId, startTime, endTime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
