package com.xunchijn.dcappv1.common.data;

import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CommonService {

    private CommonApi mCommonApi;

    public CommonService() {
        mCommonApi = RetrofitProvider.get().create(CommonApi.class);
    }
    //与接口方法一一对应
    public Observable<Response<String>> login(String userAccount, String userPassword) {
        return mCommonApi.Login(userAccount, userPassword).observeOn(Schedulers.io());
    }

    public Observable<Response<CommonResult>>statistic(){
        return mCommonApi.Statistic();
    }

    public Observable<Response<CommonResult>>getEmp(String empSimid){
        return mCommonApi.GetEmp(empSimid).observeOn(Schedulers.io());
    }

    public Observable<Response<CommonResult>>getTruck(String truckSimid){
        return mCommonApi.GetTruck(truckSimid).observeOn(Schedulers.io());
    }
}
