package com.xunchijn.dcappv1.data;

import android.graphics.Point;

import com.xunchijn.dcappv1.map.model.MapResult;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface MapApi {
    //报表获取
    @GET("API/tmd/GetStatistic")
    Observable<Response<Result<MapResult>>> GetEmpPoint(@Field("GPS_SIMID") String empSimid);
}
