package com.xunchijn.tongshan.map.model;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.util.RetrofitProvider;

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

    public Observable<Response<Result<MapResult>>> getDepartmentUsers(String subDepartmentId, String account) {
        return mMapApi.GetDepartmentUsers(subDepartmentId, account).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getUserInfo(String userId) {
        return mMapApi.GetUserInfo(userId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<MapResult>>> getDepartmentCars(String subDepartmentId, String account) {
        return mMapApi.GetDepartmentCars(subDepartmentId, account).subscribeOn(Schedulers.io());
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
