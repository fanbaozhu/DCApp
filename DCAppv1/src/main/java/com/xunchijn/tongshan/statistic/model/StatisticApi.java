package com.xunchijn.tongshan.statistic.model;

import com.xunchijn.tongshan.base.Result;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatisticApi {

    //获取车辆出入表
    @GET("API/tmd/GetCarDiscrepancy")
    Observable<Response<Result<StatisticResult>>> GetCarDomains(@Query("startTime") String startTime);

    //获取车辆出入详细表
    @GET("API/tmd/GetCarDiscrepancyDetails")
    Observable<Response<Result<StatisticResult>>> GetCarDomainsDetails(@Query("startTime") String startTime, @Query("gps_simId") String simId);
}
