package com.xunchijn.dcappv1.map.presenter;

import com.xunchijn.dcappv1.base.Result;
import com.xunchijn.dcappv1.map.model.MapResult;
import com.xunchijn.dcappv1.map.model.MapService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class DetailsPresenter implements DetailsContrast.Presenter {
    private static final String TAG = "Details";
    private DetailsContrast.View mView;
    private MapService mMapService;

    public DetailsPresenter(DetailsContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mMapService = new MapService();
    }

    @Override
    public void DetailsUser(String simId) {
        mMapService.getUserInfo(simId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<MapResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void DetailsCar(String simId) {
        mMapService.getCarInfo(simId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<MapResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void parseResult(Result<MapResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getCarInformation() != null) {
                mView.showCar(result.getData().getCarInformation());
            }
            if (result.getData().getUserInfo() != null) {
                mView.showUser(result.getData().getUserInfo());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
