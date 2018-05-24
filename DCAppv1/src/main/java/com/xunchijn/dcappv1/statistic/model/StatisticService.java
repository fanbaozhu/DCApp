package com.xunchijn.dcappv1.statistic.model;

import com.xunchijn.dcappv1.base.Result;
import com.xunchijn.dcappv1.util.RetrofitProvider;

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
