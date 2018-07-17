package com.xunchijn.tongshan.map.model;

import com.xunchijn.tongshan.base.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface MapApi {
    /*地图相关*/

    //获取人员列表
    @GET("API/tmd/GetEmpByDept")
    Observable<Response<Result<MapResult>>> GetDepartmentUsers(@Query("deptid") String subDepartmentId, @Query("Name") String acconut);

    //获取用户详情
    @GET("API/tmd/GetEmpinformation")
    Observable<Response<Result<MapResult>>> GetUserInfo(@Query("id") String subDepartmentId);

    //获取车辆列表
    @GET("API/tmd/GetTruckByDept")
    Observable<Response<Result<MapResult>>> GetDepartmentCars(@Query("dept") String subDepartmentId, @Query("Name") String acconut);

    //获取车辆详情
    @GET("API/tmd/GetCarInformation")
    Observable<Response<Result<MapResult>>> GetCarInfo(@Query("id") String subDepartmentId);

    //获取人员轨迹
    @GET("API/tmd/GetUserTrace")
    Observable<Response<Result<MapResult>>> GetUserTrace(@QueryMap Map<String, String> map);

    //获取车辆轨迹
    @GET("API/tmd/GetCarTrace")
    Observable<Response<Result<MapResult>>> GetCarTrace(@QueryMap Map<String, String> map);
}
