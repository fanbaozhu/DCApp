package com.xunchijn.dcappv1.common.presenter;

import android.content.Context;

import com.xunchijn.dcappv1.common.contract.LoginContrast;
import com.xunchijn.dcappv1.common.data.CommonService;
import com.xunchijn.dcappv1.common.module.UserAccount;
import com.xunchijn.dcappv1.util.PreferHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class LoginPresenter implements LoginContrast.Presenter {
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
    public void login(String userAccount, String password) {
        mService.login(userAccount, password)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        if (stringResponse.isSuccessful()) {
                            String result = stringResponse.body();
                            if (result.equals("0")) {
                                mView.loginSuccess();
                            } else if (result.equals("1")) {
                                mView.showError("登陆失败");
                            }
                        }else {
                            mView.showError(stringResponse.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
