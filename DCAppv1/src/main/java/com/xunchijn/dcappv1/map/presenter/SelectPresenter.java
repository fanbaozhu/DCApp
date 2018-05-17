package com.xunchijn.dcappv1.map.presenter;

import android.util.Log;

import com.xunchijn.dcappv1.event.model.EventService;
import com.xunchijn.dcappv1.map.model.MapService;
import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.map.model.MapResult;
import com.xunchijn.dcappv1.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SelectPresenter implements SelectContrast.Presenter {
    private static final String TAG = "Select";
    private SelectContrast.View mView;
    private EventService mEventService;
    private MapService mMapService;
    private Observer<Response<Result<EventResult>>> mObserverCommon;
    private Observer<Response<Result<MapResult>>> mObserverMap;

    public SelectPresenter(SelectContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
        mMapService = new MapService();
        mObserverCommon = new Observer<Response<Result<EventResult>>>() {
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
        mObserverMap = new Observer<Response<Result<MapResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Response<Result<MapResult>> resultResponse) {
                if (resultResponse.isSuccessful()) {
                    parseMapResult(resultResponse.body());
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
                mView.showOptions("部门", result.getData().getCheckDepartment());
                return;
            }
            if (result.getData().getCheckSubDepartment() != null) {
                mView.showOptions("子部门", result.getData().getCheckSubDepartment());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }

    private void parseMapResult(Result<MapResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getUserList() != null) {
                mView.showOptions("人员", result.getData().getUserList());
                return;
            }
            if (result.getData().getCarList() != null) {
                mView.showOptions("车辆", result.getData().getCarList());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }

    @Override
    public void getDepartment() {
        mEventService.getDepartments().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverCommon);
    }

    @Override
    public void getSubDepartment(String departmentId) {
        mEventService.getSubDepartments(departmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverCommon);
    }

    @Override
    public void getUsers(String subDepartmentId) {
        mMapService.getDepartmentUsers(subDepartmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverMap);
    }

    @Override
    public void getCars(String subDepartmentId) {
        mMapService.getDepartmentCars(subDepartmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverMap);
    }
}
