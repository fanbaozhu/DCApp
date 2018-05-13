package com.xunchijn.dcappv1.data;

import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.util.Result;

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

    //事件上报
    @FormUrlEncoded
    @POST("API/tmd/InsertAssMain")
    Observable<Response<Result<EventResult>>> Report(@FieldMap Map<String, String> map);

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

    //获取事件历史
    @GET("API/tmd/GetEventInfo")
    Observable<Response<Result<EventResult>>> GetEventHistory();

    //获取事件历史
    @GET("API/tmd/GetEmpName")
    Observable<Response<Result<EventResult>>> GetDepartmentUsers(@Query("deptid") String subDepartmentId);

    //获取用户详情
    @GET("API/tmd/GetEmpinformation")
    Observable<Response<Result<EventResult>>> GetUserInfo(@Query("Name") String subDepartmentId);

    //获取事件历史
    @GET("API/tmd/GetEmpName")
    Observable<Response<Result<EventResult>>> GetDepartmentCars(@Query("deptid") String subDepartmentId);
}
