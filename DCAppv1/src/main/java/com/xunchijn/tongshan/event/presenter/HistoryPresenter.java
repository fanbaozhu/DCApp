package com.xunchijn.tongshan.event.presenter;

import android.util.Log;

import com.xunchijn.tongshan.event.model.EventService;
import com.xunchijn.tongshan.event.model.EventResult;
import com.xunchijn.tongshan.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class HistoryPresenter implements HistoryContract.Presenter {
    private String TAG = "History";
    private HistoryContract.View mView;
    private EventService mEventService;

    public HistoryPresenter(HistoryContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
    }

    @Override
    public void getHistory() {
        mEventService.getEventHistory().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<EventResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<EventResult>> resultResponse) {
                        if (resultResponse.isSuccessful()) {
                            if (resultResponse.body().getCode() == 200) {
                                if (resultResponse.body().getData() == null) {
                                    return;
                                }
                                if (resultResponse.body().getData().getEventHistory() != null) {
                                    mView.showHistory(resultResponse.body().getData().getEventHistory());
                                }
                            } else {
                                mView.showError(resultResponse.body().getMessage());
                            }
                        } else {
                            mView.showError(resultResponse.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
