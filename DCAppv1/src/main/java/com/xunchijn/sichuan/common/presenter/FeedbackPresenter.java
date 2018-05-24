package com.xunchijn.sichuan.common.presenter;

import android.content.Context;
import android.util.Log;

import com.xunchijn.sichuan.base.Result;
import com.xunchijn.sichuan.common.module.CommonResult;
import com.xunchijn.sichuan.common.module.CommonService;
import com.xunchijn.sichuan.common.module.UserAccount;
import com.xunchijn.sichuan.util.PreferHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class FeedbackPresenter implements FeedBackContrast.Presenter {
    private String TAG = "feedback";
    private FeedBackContrast.View mView;
    private CommonService mCommonService;
    private PreferHelper mPreferHelper;

    public FeedbackPresenter(FeedBackContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mCommonService = new CommonService();
        mPreferHelper = new PreferHelper(context);
    }

    @Override
    public void feedback(String title, String content, String userPhone) {
        Map<String, String> map = new HashMap<>();
        UserAccount account = mPreferHelper.getUserAccount();
        map.put("userId", account.getUserAccount());
        map.put("userName", account.getUserAccount());
        map.put("title", title);
        map.put("content", content);
        map.put("userPhone", userPhone);
        mCommonService.feedback(map).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<CommonResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<CommonResult>> resultResponse) {
                        if (resultResponse.isSuccessful()) {
                            parseResult(resultResponse.body());
                        } else {
                            mView.showError(resultResponse.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void parseResult(Result<CommonResult> result) {
        if (result.getCode() == 200) {
            if (result.getData() == null) {
                return;
            }
            if (result.getData().getFeedbackStatus() != null && result.getData().getFeedbackStatus() == 1) {
                mView.feedbackSuccess();
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
