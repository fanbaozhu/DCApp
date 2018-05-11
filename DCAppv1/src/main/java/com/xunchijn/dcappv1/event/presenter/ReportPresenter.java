package com.xunchijn.dcappv1.event.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.xunchijn.dcappv1.data.EventService;
import com.xunchijn.dcappv1.event.contract.ReportContract;
import com.xunchijn.dcappv1.event.model.EventResult;
import com.xunchijn.dcappv1.util.Result;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ReportPresenter implements ReportContract.Presenter {
    private final String TAG = "Report";
    private ReportContract.View mView;
    private EventService mEventService;
    private Observer<Response<Result<EventResult>>> mObserver;
    private Map<String, String> mMap;

    public ReportPresenter(ReportContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
        mObserver = new Observer<Response<Result<EventResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Response<Result<EventResult>> resultResponse) {
                if (resultResponse.isSuccessful()) {
                    parseResult(resultResponse.body());
                } else {
                    mView.showError(resultResponse.message());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
    }

    private void parseResult(Result<EventResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getCheckDepartment() != null) {
                mView.showDepartment(result.getData().getCheckDepartment());
                return;
            }
            if (result.getData().getCheckSubDepartment() != null) {
                mView.showSubDepartment(result.getData().getCheckSubDepartment());
                return;
            }
            if (result.getData().getCheckType() != null) {
                mView.showCheckType(result.getData().getCheckType());
                return;
            }
            if (result.getData().getCheckContent() != null) {
                mView.showCheckContent(result.getData().getCheckContent());
                return;
            }
            String fileName = result.getData().getFileName();
            if (!TextUtils.isEmpty(fileName)) {
                mMap.put("pictureName", fileName);
                report();
                return;
            }
            if (result.getData().getReportStatus() != null && result.getData().getReportStatus() == 1) {
                mView.reportSuccess();
            }

        } else {
            mView.showError(result.getMessage());
        }
    }

    @Override
    public void getDepartment() {
        mEventService.getDepartments().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getSubDepartment(String departmentId) {
        mEventService.getSubDepartments(departmentId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCheckType() {
        mEventService.getCheckType().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void getCheckContent(String typeId) {
        mEventService.getCheckContent(typeId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    @Override
    public void report(String describe, List<String> urls, String address, String point, String subDepartment, String type, String content, String accountId) {
        uploadPic(urls);

        mMap = new HashMap<>();
        mMap.put("describe", describe);
        mMap.put("subDepartment", subDepartment);
        mMap.put("type", type);
        mMap.put("content", content);
        mMap.put("accountId", accountId);
        mMap.put("point", point);
        mMap.put("address", address);
    }

    private void report() {
        if (mMap == null) {
            mView.showError("参数不能为null");
            return;
        }
        mEventService.report(mMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    private void uploadPic(List<String> urls) {
        if (urls == null || urls.size() == 0) {
            mView.showError("图片不能为空");
            return;
        }
        Map<String, RequestBody> reqMap = new HashMap<>();
        for (String url : urls) {
            File file = new File(url);
            if (!file.exists()) {
                continue;
            }
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            reqMap.put(String.format("picPath\";filename=\"%s", file.getName()), requestFile);
        }
        mEventService.uploadPic(reqMap).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
