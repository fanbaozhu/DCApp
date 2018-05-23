package com.xunchijn.dcappv1.common.presenter;

import android.content.Context;
import android.util.Log;

import com.xunchijn.dcappv1.base.Result;
import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.common.module.CommonService;
import com.xunchijn.dcappv1.util.PreferHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class MessagesPresenter implements MessagesContrast.Presenter {
    private String TAG = "Messages";
    private MessagesContrast.View mView;
    private CommonService mCommonService;
    private PreferHelper mPreferHelper;

    public MessagesPresenter(MessagesContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mCommonService = new CommonService();
        mPreferHelper = new PreferHelper(context);
    }

    @Override
    public void getMessages(int pageIndex, int pageCount) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", mPreferHelper.getUserAccount().getUserAccount());
        map.put("pageIndex", String.valueOf(pageIndex));
        map.put("pageCount", String.valueOf(pageCount));
        mCommonService.getMessages(map).observeOn(AndroidSchedulers.mainThread())
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
            if (result.getData().getMessages() != null) {
                mView.showMessages(result.getData().getMessages());
            }
        } else {
            mView.showError(result.getMessage());
        }
    }
}
