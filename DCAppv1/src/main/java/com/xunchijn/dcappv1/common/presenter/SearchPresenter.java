package com.xunchijn.dcappv1.common.presenter;

import android.util.Log;

import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.common.module.CommonService;
import com.xunchijn.dcappv1.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class SearchPresenter implements SearchContrast.Presenter {
    private String TAG = "Search";
    private SearchContrast.View mView;
    private CommonService mService;


    public SearchPresenter(SearchContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mService = new CommonService();
    }

    public void search(String name) {
        mService.getSearch(name).observeOn(AndroidSchedulers.mainThread())
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
                        Log.e(TAG, "onError: ", e);
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
            if (result.getData().getSearchItemList() != null) {
                mView.searchSuccess(result.getData().getSearchItemList());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
