package com.xunchijn.tongshan.statistic.model;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.util.RetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StatisticService {
    private StatisticApi mStatisticApi;

    public StatisticService() {
        mStatisticApi = RetrofitProvider.get().create(StatisticApi.class);
    }

    public Observable<Response<Result<StatisticResult>>> GetCarDomains(String startTime, String Name) {
        return mStatisticApi.GetCarDomains(startTime, Name).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<StatisticResult>>> GetCarDomainsDetails(String startTime, String simId) {
        return mStatisticApi.GetCarDomainsDetails(startTime, simId).subscribeOn(Schedulers.io());
    }
}
