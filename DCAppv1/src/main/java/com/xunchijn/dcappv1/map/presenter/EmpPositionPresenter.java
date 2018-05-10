package com.xunchijn.dcappv1.map.presenter;

import com.xunchijn.dcappv1.common.data.CommonService;
import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.map.contract.EmpPositionContrast;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class EmpPositionPresenter implements EmpPositionContrast.Presenter {
    private EmpPositionContrast.View mView;
    private CommonService mService;


    public EmpPositionPresenter(EmpPositionContrast.View view) {
        mView = view;
        mView.setPresenter(this);
        mService = new CommonService();
    }

    public void getEmpPosition(String empSimid) {
        mService.getEmp(empSimid)
                //在主线程执行
                .subscribeOn(AndroidSchedulers.mainThread())
                //返回的结果
                .subscribe(new Observer<Response<CommonResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<CommonResult> commonResultResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
