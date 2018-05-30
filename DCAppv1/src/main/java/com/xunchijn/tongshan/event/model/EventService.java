package com.xunchijn.tongshan.event.model;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.map.model.DetailsResult;
import com.xunchijn.tongshan.util.RetrofitProvider;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Response;


public class EventService {
    private EventApi mEventApi;

    public EventService() {
        mEventApi = RetrofitProvider.get().create(EventApi.class);
    }

    //上传图片
    public Observable<Response<Result<EventResult>>> uploadPic(Map<String, RequestBody> map) {
        return mEventApi.UploadPic(map).subscribeOn(Schedulers.io());
    }

    //上报
    public Observable<Response<Result<EventResult>>> report(Map<String, String> map) {
        return mEventApi.Report(map).subscribeOn(Schedulers.io());
    }

    //获取二级部门
    public Observable<Response<Result<EventResult>>> getDepartments() {
        return mEventApi.GetDepartments().subscribeOn(Schedulers.io());
    }

    //获取三级部门
    public Observable<Response<Result<EventResult>>> getSubDepartments(String departmentId) {
        return mEventApi.GetSubDepartments(departmentId).subscribeOn(Schedulers.io());
    }

    //获取考核类型
    public Observable<Response<Result<EventResult>>> getCheckType() {
        return mEventApi.GetCheckType().subscribeOn(Schedulers.io());
    }

    //获取考核子类型
    public Observable<Response<Result<EventResult>>> getCheckContent(String typeId) {
        return mEventApi.GetCheckContent(typeId).subscribeOn(Schedulers.io());
    }


    public Observable<Response<Result<EventResult>>> getEventHistory() {
        return mEventApi.GetEventHistory().subscribeOn(Schedulers.io());
    }

    //获取历史详情
    public Observable<Response<Result<EventResult>>> getEventInfo(String eventId) {
        return mEventApi.GetEventInfo(eventId).subscribeOn(Schedulers.io());
    }

    //获取车辆实时信息
    public Observable<Response<Result<DetailsResult>>> GetCarinFormation(String eventId) {
        return mEventApi.GetCarinFormation(eventId).subscribeOn(Schedulers.io());
    }
}
