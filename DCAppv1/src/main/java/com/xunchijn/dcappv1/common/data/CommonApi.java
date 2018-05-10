package com.xunchijn.dcappv1.common.data;

import com.xunchijn.dcappv1.common.module.CommonResult;

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
    @POST("API/tmd/CheckUserPass")
    Observable<Response<String>> Login(@Field("USER_NAME") String userAccount, @Field("USER_PWD") String userPassword);

    //报表获取
    @FormUrlEncoded
    @GET("API/tmd/GetStatistic")
    Observable<Response<CommonResult>> Statistic();

    //人员定位
    @FormUrlEncoded
    @GET("API/tmd/GetEmpinformation")
    Observable<Response<CommonResult>> GetEmp(@Field("GPS_SIMID") String empSimid);

    //车辆定位
    @FormUrlEncoded
    @GET("API/tmd/GetTruck")
    Observable<Response<CommonResult>> GetTruck(@Field("GPS_SIMID") String empSimid);
}
