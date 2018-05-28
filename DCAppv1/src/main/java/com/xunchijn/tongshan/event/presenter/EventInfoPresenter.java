package com.xunchijn.tongshan.event.presenter;

import android.util.Log;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.event.model.EventResult;
import com.xunchijn.tongshan.event.model.EventService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class EventInfoPresenter implements EventInfoContract.Presenter {
    private final String TAG = "EventInfo";
    private EventInfoContract.View mView;
    private EventService mEventService;

    public EventInfoPresenter(EventInfoContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
    }

    @Override
    public void getEventInfo(String id) {
        mEventService.getEventInfo(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<EventResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<EventResult>> resultResponse) {
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

    private void parseResult(Result<EventResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getEventItem() != null) {
                mView.showEventInfo(result.getData().getEventItem());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
