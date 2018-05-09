package com.xunchijn.dcappv1.data;

import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.util.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EventApi {

    //事件上报
    @FormUrlEncoded
    @POST("API/event/report")
    Observable<Response<Result<EventResult>>> Report(@FieldMap Map<String, String> map);
}
