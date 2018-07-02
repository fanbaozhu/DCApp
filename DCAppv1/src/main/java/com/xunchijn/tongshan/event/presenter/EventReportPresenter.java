package com.xunchijn.tongshan.event.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.xunchijn.tongshan.base.Result;
import com.xunchijn.tongshan.common.module.UserAccount;
import com.xunchijn.tongshan.event.model.EventResult;
import com.xunchijn.tongshan.event.model.EventService;
import com.xunchijn.tongshan.util.PhotoUtils;
import com.xunchijn.tongshan.util.PreferHelper;

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

public class EventReportPresenter implements EventReportContract.Presenter {
    private final String TAG = "Report";
    private EventReportContract.View mView;
    private EventService mEventService;
    private Observer<Response<Result<EventResult>>> mObserver;
    private Map<String, String> mMap;
    private PhotoUtils mPhotoUtils;
    private boolean mReport = true;
    private PreferHelper mPreferHelper;

    public EventReportPresenter(EventReportContract.View view, Activity context) {
        mView = view;
        mView.setPresenter(this);
        mEventService = new EventService();
        mPhotoUtils = new PhotoUtils(context);
        mPreferHelper = new PreferHelper(context);
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

    private void report() {
        if (mMap == null) {
            mView.showError("参数不能为null");
            return;
        }
        if (mReport) {
            mEventService.report(mMap).observeOn(AndroidSchedulers.mainThread()).subscribe(mObserver);
        } else {
            mEventService.InsertMessages(mMap).observeOn(AndroidSchedulers.mainThread()).subscribe(mObserver);
        }
    }

    @Override
    public void report(String describe, List<String> urls, String address, String point,
                       String subDepartment, String type, String content) {
        mMap = new HashMap<>();
        UserAccount userAccount = mPreferHelper.getUserAccount();
        String account = userAccount.getUserAccount();
        mMap.put("describe", describe);
        mMap.put("subDepartment", subDepartment);
        mMap.put("type", type);
        mMap.put("content", content);
        mMap.put("account", account);
        mMap.put("point", point);
        mMap.put("address", address);
        mReport = true;
        uploadPic(urls);
    }

    @Override
    public void report(String describe, List<String> urls, String address, String point) {
        mMap = new HashMap<>();
        UserAccount userAccount = mPreferHelper.getUserAccount();
        String account = userAccount.getUserAccount();
        mMap.put("describe", describe);
        mMap.put("point", point);
        mMap.put("address", address);
        mMap.put("account", account);
        mReport = false;
        uploadPic(urls);
    }

    private void uploadPic(List<String> urls) {
        if (urls == null || urls.size() == 0) {
            mView.showError("图片不能为空");
            return;
        }
        List<String> compressUrls = mPhotoUtils.CompressPictures(urls);
        Map<String, RequestBody> reqMap = new HashMap<>();
        for (String url : compressUrls) {
            File file = new File(url);
            if (!file.exists()) {
                continue;
            }
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
            reqMap.put(String.format("picPath\";filename=\"%s", file.getName()), requestFile);
        }
        mEventService.uploadPic(reqMap).observeOn(AndroidSchedulers.mainThread()).subscribe(mObserver);
    }
}