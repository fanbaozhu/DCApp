package com.xunchijn.tongshan.event.model;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.map.model.DetailsResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface EventApi {

    //文件上传
    @Multipart
    @POST("API/tmd/ImgUpload")
    Observable<Response<Result<EventResult>>> UploadPic(@PartMap Map<String, RequestBody> map);

    //获取事件历史
    @GET("API/tmd/GetEventHistory")
    Observable<Response<Result<EventResult>>> GetEventHistory(@Query("account") String account);

    //事件上报
    @FormUrlEncoded
    @POST("API/tmd/InsertAssMain")
    Observable<Response<Result<EventResult>>> Report(@FieldMap Map<String, String> map);

    //获取历史
    @GET("API/tmd/GetMessageInfo")
    Observable<Response<Result<EventResult>>> getHistory(@Query("account") String account);

    //事件上报
    @FormUrlEncoded
    @POST("API/tmd/InsertMessages")
    Observable<Response<Result<EventResult>>> InsertMessages(@FieldMap Map<String, String> map);

    //获取部门
    @GET("API/tmd/GetDepartments")
    Observable<Response<Result<EventResult>>> GetDepartments();

    //获取子部门
    @GET("API/tmd/GetSubDepartments")
    Observable<Response<Result<EventResult>>> GetSubDepartments(@Query("departmentId") String departmentId);

    //获取考核类型
    @GET("API/tmd/GetCheckType")
    Observable<Response<Result<EventResult>>> GetCheckType();

    //获取考核内容
    @GET("API/tmd/GetCheckContent")
    Observable<Response<Result<EventResult>>> GetCheckContent(@Query("typeId") String typeId);

    //获取事件详情
    @GET("API/tmd/GetInfomation")
    Observable<Response<Result<EventResult>>> GetEventInfo(@Query("id") String typeId);

    //获取详情
    @GET("API/tmd/GetMessageInfomation")
    Observable<Response<Result<EventResult>>> GetInfo(@Query("id") String typeId);

    //获取车辆详情
    @GET("API/tmd/GetCarinFormation")
    Observable<Response<Result<DetailsResult>>> GetCarinFormation(@Query("id") String typeId);
}
