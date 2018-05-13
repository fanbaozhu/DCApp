package com.xunchijn.dcappv1.map.presenter;

import android.util.Log;

import com.xunchijn.dcappv1.data.EventService;
import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.map.contract.SelectContrast;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SelectPresenter implements SelectContrast.Presenter {
    private static final String TAG = "Select";
    private SelectContrast.View mView;
    private EventService mEventService;
    private Observer<Response<Result<EventResult>>> mObserver;

    public SelectPresenter(SelectContrast.View view) {
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
                Log.e(TAG, "onError: ", e);
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
                mView.showDepartment(result.getData().getCheckDepartment());
                return;
            }
            if (result.getData().getCheckSubDepartment() != null) {
                mView.showSubDepartment(result.getData().getCheckSubDepartment());
                return;
            }
            if (result.getData().getUserList() != null) {
                mView.showUsers(result.getData().getUserList());
                return;
            }
            if (result.getData().getCarList() != null) {
                mView.showCars(result.getData().getCarList());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }

    @Override
    public void getDepartment() {
        mEventService.getDepartments().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getSubDepartment(String departmentId) {
        mEventService.getSubDepartments(departmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getUsers(String subDepartmentId) {
        mEventService.getDepartmentUsers(subDepartmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCars(String subDepartmentId) {
        mEventService.getDepartmentCars(subDepartmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
