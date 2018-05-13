package com.xunchijn.dcappv1.common.presenter;

import android.content.Context;
import android.util.Log;

import com.xunchijn.dcappv1.common.contract.LoginContrast;
import com.xunchijn.dcappv1.common.module.CommonResult;
import com.xunchijn.dcappv1.common.module.UserAccount;
import com.xunchijn.dcappv1.data.CommonService;
import com.xunchijn.dcappv1.util.PreferHelper;
import com.xunchijn.dcappv1.util.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class LoginPresenter implements LoginContrast.Presenter {
    private String TAG = "Login";
    private PreferHelper mPreferHelper;
    private LoginContrast.View mView;
    private CommonService mService;

    public LoginPresenter(LoginContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mPreferHelper = new PreferHelper(context);
        mService = new CommonService();
    }

    //检查本地是否存在用户数据
    @Override
    public void checkLocalData() {
        UserAccount userAccount = mPreferHelper.getUserAccount();
        if (userAccount == null) {
            return;
        }
        login(userAccount.getUserAccount(), userAccount.getUserPassword());
    }

    //通过网络，登陆
    @Override
    public void login(final String userAccount, final String password) {
        mService.login(userAccount, password).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Result<CommonResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Response<Result<CommonResult>> resultResponse) {
                        if (resultResponse.isSuccessful()) {
                            if (resultResponse.body().getCode() == 200) {
                                if (resultResponse.body().getData() == null) {
                                    return;
                                }
                                if (resultResponse.body().getData().getUserInfo() != null) {
                                    mPreferHelper.saveUserAccount(new UserAccount(userAccount, password));
                                    mView.loginSuccess(resultResponse.body().getData().getUserInfo().getUserName());
                                }
                            } else {
                                mView.showError(resultResponse.body().getMessage());
                            }
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
}
