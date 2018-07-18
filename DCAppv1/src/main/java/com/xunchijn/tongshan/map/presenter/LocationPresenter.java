package com.xunchijn.tongshan.map.presenter;

import android.content.Context;
import android.util.Log;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.common.module.UserAccount;
import com.xunchijn.tongshan.map.model.MapResult;
import com.xunchijn.tongshan.map.model.MapService;
import com.xunchijn.tongshan.util.PreferHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class LocationPresenter implements LocationContrast.Presenter {
	private static final String TAG = "Location";
	private LocationContrast.View mView;
	private MapService mMapService;
	private PreferHelper mPreferHelper;
	private Observer<Response<Result<MapResult>>> mObserver;

	public LocationPresenter(LocationContrast.View view, Context context) {
		mView = view;
		mView.setPresenter(this);
		mMapService = new MapService();
		mPreferHelper = new PreferHelper(context);
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
			if (result.getData().getUserInfo() != null) {
				mView.showUser(result.getData().getUserInfo());
				return;
			}
			if (result.getData().getUserList() != null) {
				mView.showUsers(result.getData().getUserList());
				return;
			}
			if (result.getData().getCarList() != null) {
				mView.showCars(result.getData().getCarList());
				return;
			}
			if (result.getData().getCarInformation() != null) {
				mView.showCar(result.getData().getCarInformation());
			}
		} else {
			mView.showError(result.getMessage());
		}
	}

	@Override
	public void getUsers(String subDepartmentId) {
		UserAccount userAccount = mPreferHelper.getUserAccount();
		String account = userAccount.getUserAccount();
		mMapService.getDepartmentUsers(subDepartmentId, account).observeOn(AndroidSchedulers.mainThread())
				.subscribe(mObserver);
	}

	@Override
	public void getUser(String userId) {
		mMapService.getUserInfo(userId).observeOn(AndroidSchedulers.mainThread())
				.subscribe(mObserver);
	}

	@Override
	public void getCars(String subDepartmentId) {
		UserAccount userAccount = mPreferHelper.getUserAccount();
		String account = userAccount.getUserAccount();
		mMapService.getDepartmentCars(subDepartmentId, account).observeOn(AndroidSchedulers.mainThread())
				.subscribe(mObserver);
	}

	@Override
	public void getCar(String carId) {
		mMapService.getCarInfo(carId).observeOn(AndroidSchedulers.mainThread())
				.subscribe(mObserver);
	}
}
