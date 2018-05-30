package com.xunchijn.tongshan.map.presenter;

import android.util.Log;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.map.model.DetailsResult;
import com.xunchijn.tongshan.event.model.EventService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class DetailsPresenter implements DetailsContract.Presenter {
    private final String TAG = "GetDetails";
    private DetailsContract.View mView;
    private EventService mEventService;

    public DetailsPresenter(DetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
    }

    @Override
    public void getCarDetails(String id) {
        mEventService.GetCarinFormation(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<DetailsResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<DetailsResult>> resultResponse) {
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
                });
    }

    private void parseResult(Result<DetailsResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getCarInfomation() != null) {
                mView.showCarDetails(result.getData().getCarInfomation());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
