package com.xunchijn.dcappv1.common;

import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.util.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public interface CommonApi{
    //账号登录
    @FormUrlEncoded
    @POST("API/Login")
    Observable<Response<Result<CommonResult>>> Login(@FieldMap Map<String, String> map);

}
