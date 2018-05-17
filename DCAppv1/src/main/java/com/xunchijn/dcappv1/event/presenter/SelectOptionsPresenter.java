package com.xunchijn.dcappv1.event.presenter;

import android.util.Log;

import com.xunchijn.dcappv1.event.model.EventService;
import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SelectOptionsPresenter implements SelectOptionsContrast.Presenter {
    private SelectOptionsContrast.View mView;
    private final String TAG = "SelectOptions";
    private EventService mEventService;
    private Observer<Response<Result<EventResult>>> mObserver;

    public SelectOptionsPresenter(SelectOptionsContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
        mObserver = new Observer<Response<Result<EventResult>>>() {
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
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
    }

    private void parseResult(Result<EventResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getCheckDepartment() != null) {
                mView.showShortOptions("部门", result.getData().getCheckDepartment());
                return;
            }
            if (result.getData().getCheckSubDepartment() != null) {
                mView.showShortOptions("子部门", result.getData().getCheckSubDepartment());
                return;
            }
            if (result.getData().getCheckType() != null) {
                mView.showShortOptions("考核类型", result.getData().getCheckType());
                return;
            }
            if (result.getData().getCheckContent() != null) {
                mView.showLongOptions("考核内容", result.getData().getCheckContent());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }

    @Override
    public void getDepartments() {
        mEventService.getDepartments().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getSubDepartments(String departmentId) {
        mEventService.getSubDepartments(departmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCheckType() {
        mEventService.getCheckType().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCheckContent(String checkType) {
        mEventService.getCheckContent(checkType).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
