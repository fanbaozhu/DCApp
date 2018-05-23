package com.xunchijn.dcappv1.common.module;

import com.xunchijn.dcappv1.base.Result;
import com.xunchijn.dcappv1.util.RetrofitProvider;

import java.util.Map;

import io.reactivex.Observable;
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
        return mCommonApi.Statistic().subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<CommonResult>>> getSearch(String name) {
        return mCommonApi.GetSearch(name).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<CommonResult>>> resetPass(Map<String, String> map) {
        return mCommonApi.ResetPass(map).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<CommonResult>>> getMessages(Map<String, String> map) {
        return mCommonApi.GetMessages(map).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<CommonResult>>> feedback(Map<String, String> map) {
        return mCommonApi.Feedback(map).subscribeOn(Schedulers.io());
    }
}
