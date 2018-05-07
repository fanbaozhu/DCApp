package com.xunchijn.administrator.api;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public interface ReportApi {
    //垃圾清运报表
    @FormUrlEncoded
    @GET("API/clearingReport")
    Observable<Response<Result<AccountResult>>> ClearingReport();

}
