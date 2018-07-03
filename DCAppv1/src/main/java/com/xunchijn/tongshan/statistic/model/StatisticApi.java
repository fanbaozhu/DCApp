package com.xunchijn.tongshan.statistic.model;

import com.xunchijn.tongshan.base.Result;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatisticApi {

    //获取车辆出入表
    @GET("API/tmd/GetCarDiscrepancy")
    Observable<Response<Result<StatisticResult>>> GetCarDomains(@Query("startTime") String startTime, @Query("Name") String Name);

    //获取车辆出入/加水/垃圾详细表
    @GET("API/tmd/GetCarDiscrepancyDetails")
    Observable<Response<Result<StatisticResult>>> GetCarDomainsDetails(@Query("startTime") String startTime, @Query("gps_simId") String simId);

    //获取车辆加水/垃圾倾倒表
    @GET("API/tmd/GetCarRegionDiscrepancy")
    Observable<Response<Result<StatisticResult>>> GetCarOtherDomains(@Query("startTime") String startTime, @Query("Name") String Name, @Query("type") String type);

    //获取车辆加水/垃圾详细表
    @GET("API/tmd/GetCarRegionDiscrepancyDetails")
    Observable<Response<Result<StatisticResult>>> GetCarOtherDomainsDetails(@Query("startTime") String startTime, @Query("gps_simId") String simId);

}
