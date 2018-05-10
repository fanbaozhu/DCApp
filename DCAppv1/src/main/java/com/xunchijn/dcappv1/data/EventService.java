package com.xunchijn.dcappv1.data;

import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.util.Result;
import com.xunchijn.dcappv1.util.RetrofitProvider;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class EventService {
    private EventApi mEventApi;

    public EventService() {
        mEventApi = RetrofitProvider.get().create(EventApi.class);
    }

    public Observable<Response<Result<EventResult>>> report(Map<String, String> map) {
        return mEventApi.Report(map).observeOn(Schedulers.io());
    }
}
