package com.xunchijn.sichuan.map.model;

import com.xunchijn.sichuan.base.Result;
import com.xunchijn.sichuan.util.RetrofitProvider;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class MapService {
    private MapApi mMapApi;

    public MapService() {
        mMapApi = RetrofitProvider.get().create(MapApi.class);
    }

    public Observable<Response<Result<MapResult>>> getDepartmentUsers(String subDepartmentId) {
        return mMapApi.GetDepartmentUsers(subDepartmentId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getUserInfo(String userId) {
        return mMapApi.GetUserInfo(userId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getDepartmentCars(String subDepartmentId) {
        return mMapApi.GetDepartmentCars(subDepartmentId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getCarInfo(String id) {
        return mMapApi.GetCarInfo(id).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getUserTrace(String targetId, String startTime, String endTime) {
        Map<String, String> map = new HashMap<>();
        map.put("targetId", targetId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return mMapApi.GetUserTrace(map).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getCarTrace(String targetId, String startTime, String endTime) {
        Map<String, String> map = new HashMap<>();
        map.put("targetId", targetId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return mMapApi.GetCarTrace(map).subscribeOn(Schedulers.io());
    }
}
