package com.xunchijn.dcappv1.data;

import android.graphics.Point;

import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public interface CommonApi {
//    //账号登录
//    @FormUrlEncoded
//    @POST("API/Login")
//    Observable<Response<Result<CommonResult>>> Login1(@FieldMap Map<String, String> map);


    //账号登录
    @FormUrlEncoded
    @POST("API/tmd/Login")
    Observable<Response<Result<CommonResult>>> Login(@Field("userName") String userAccount, @Field("passWord") String userPassword);

    //报表获取
    @GET("API/tmd/GetStatistic")
    Observable<Response<Result<CommonResult>>> Statistic();

    //人员定位
    @FormUrlEncoded
    @GET("API/tmd/GetEmpinformation")
    Observable<Response<CommonResult>> GetEmp(@Field("GPS_SIMID") String empSimid);

    //车辆定位
    @FormUrlEncoded
    @GET("API/tmd/GetTruck")
    Observable<Response<CommonResult>> GetTruck(@Field("GPS_SIMID") String empSimid);


}
