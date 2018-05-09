package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.common.contract.StatisticContrast;
import com.xunchijn.dcappv1.common.data.CommonService;
import com.xunchijn.dcappv1.common.module.CommonResult;

import org.greenrobot.eventbus.MainThreadSupport;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticPresenter implements StatisticContrast.Presenter {
    private StatisticContrast.View mView;
    private CommonService mService;


    public StatisticPresenter(StatisticContrast.View view){
        mView = view;
        mView.setPresenter(this);
        mService = new CommonService();
    }

    public void getStatistic() {
        mService.statistic()
                //在主线程执行
                .subscribeOn(AndroidSchedulers.mainThread())
                //返回的结果
                .subscribe(new Observer<Response<CommonResult>>() {
                    @Override
                    //订阅 暂时无卵用
                    public void onSubscribe(Disposable d) {

                    }

                    //过程
                    @Override
                    public void onNext(Response<CommonResult> commonResultResponse) {

                    }

                    //失败
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    //完成
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
