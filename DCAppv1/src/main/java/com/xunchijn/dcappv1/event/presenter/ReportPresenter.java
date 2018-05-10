package com.xunchijn.dcappv1.event.presenter;

import com.xunchijn.dcappv1.data.EventService;
import com.xunchijn.dcappv1.event.contract.ReportContract;
import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.util.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class ReportPresenter implements ReportContract.Presenter {
    private ReportContract.View mView;
    private EventService mEventService;

    public ReportPresenter(ReportContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
    }

    @Override
    public void getDepartment() {

    }

    @Override
    public void getSubDepartment(String departmentId) {

    }

    @Override
    public void getCheckType() {

    }

    @Override
    public void getCheckContent() {

    }

    @Override
    public void report(String describe, List<String> urls, String position, String subDepartment, String type, String content, String accountId, String point, String address) {
        Map<String, String> map = new HashMap<>();
        map.put("describe", describe);
//        map.put("urls", urls);
        map.put("position", position);
        map.put("subDepartment", subDepartment);
        map.put("type", type);
        map.put("content", content);
        map.put("accountId", accountId);
        map.put("point", point);
        map.put("address", address);
        mEventService.report(map).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<Result<EventResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<Result<EventResult>> resultResponse) {
                if (resultResponse.isSuccessful()) {

                } else {
                    mView.showError(resultResponse.message());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
