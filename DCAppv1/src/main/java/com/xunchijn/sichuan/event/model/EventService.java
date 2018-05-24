package com.xunchijn.sichuan.event.model;

import com.xunchijn.sichuan.base.Result;
import com.xunchijn.sichuan.util.RetrofitProvider;

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

    public Observable<Response<Result<EventResult>>> uploadPic(Map<String, RequestBody> map) {
        return mEventApi.UploadPic(map).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> report(Map<String, String> map) {
        return mEventApi.Report(map).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getDepartments() {
        return mEventApi.GetDepartments().subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getSubDepartments(String departmentId) {
        return mEventApi.GetSubDepartments(departmentId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getCheckType() {
        return mEventApi.GetCheckType().subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getCheckContent(String typeId) {
        return mEventApi.GetCheckContent(typeId).subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getEventHistory() {
        return mEventApi.GetEventHistory().subscribeOn(Schedulers.io());
    }

    public Observable<Response<Result<EventResult>>> getEventInfo(String eventId) {
        return mEventApi.GetEventInfo(eventId).subscribeOn(Schedulers.io());
    }
}
