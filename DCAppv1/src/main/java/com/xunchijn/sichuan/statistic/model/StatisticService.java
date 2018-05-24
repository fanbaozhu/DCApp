package com.xunchijn.sichuan.statistic.model;

import com.xunchijn.sichuan.base.Result;
import com.xunchijn.sichuan.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StatisticService {
    private StatisticApi mStatisticApi;

    public StatisticService() {
        mStatisticApi = RetrofitProvider.get().create(StatisticApi.class);
    }

    public Observable<Response<Result<StatisticResult>>> GetCarDomains(String startTime) {
        return mStatisticApi.GetCarDomains(startTime).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<StatisticResult>>> GetCarDomainsDetails(String startTime, String simId) {
        return mStatisticApi.GetCarDomainsDetails(startTime, simId).subscribeOn(Schedulers.io());
    }
}
