package com.xunchijn.tongshan.common.module;

import com.xunchijn.tongshan.base.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public interface CommonApi {

    //账号登录
    @FormUrlEncoded
    @POST("API/tmd/Login")
    Observable<Response<Result<CommonResult>>> Login(@Field("userName") String userAccount, @Field("passWord") String userPassword);

    //报表获取
    @GET("API/tmd/GetStatistic")
    Observable<Response<Result<CommonResult>>> Statistic();

    //人员定位
    @GET("API/tmd/GetEmpinformation")
    Observable<Response<CommonResult>> GetEmp(@Query("GPS_SIMID") String empSimid);

    //车辆定位
    @GET("API/tmd/GetTruck")
    Observable<Response<CommonResult>> GetTruck(@Query("GPS_SIMID") String empSimid);

    //查询
    @GET("API/tmd/GetUserSearch")
    Observable<Response<Result<CommonResult>>> GetSearch(@Query("Name") String name);

    //修改密码
    @FormUrlEncoded
    @POST("API/tmd/ResetPass")
    Observable<Response<Result<CommonResult>>> ResetPass(@FieldMap Map<String, String> map);

    //获取消息通知
    @GET("API/tmd/GetMessages")
    Observable<Response<Result<CommonResult>>> GetMessages(@QueryMap Map<String, String> map);

    //意见反馈
    @FormUrlEncoded
    @POST("API/tmd/Feedback")
    Observable<Response<Result<CommonResult>>> Feedback(@FieldMap Map<String, String> map);
}
