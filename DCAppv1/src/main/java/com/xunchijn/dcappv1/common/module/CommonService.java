package com.xunchijn.dcappv1.common.module;

import com.xunchijn.dcappv1.base.Result;
import com.xunchijn.dcappv1.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CommonService {

    private CommonApi mCommonApi;


    public CommonService() {
        mCommonApi = RetrofitProvider.get().create(CommonApi.class);
    }

    //与接口方法一一对应
    public Observable<Response<Result<CommonResult>>> login(String userAccount, String userPassword) {
        return mCommonApi.Login(userAccount, userPassword).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<CommonResult>>> statistic() {
        return mCommonApi.Statistic().observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Result<CommonResult>>> getSearch(String name) {
        return mCommonApi.GetSearch(name).subscribeOn(Schedulers.io());
    }
}
