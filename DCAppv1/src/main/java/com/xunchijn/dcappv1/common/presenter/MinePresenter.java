package com.xunchijn.dcappv1.common.presenter;

import android.content.Context;

import com.xunchijn.dcappv1.common.module.UserAccount;
import com.xunchijn.dcappv1.util.PreferHelper;

public class MinePresenter implements MineContrast.Presenter {
    private MineContrast.View mView;
    private PreferHelper mPreferHelper;

    public MinePresenter(MineContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mPreferHelper = new PreferHelper(context);
    }

    @Override
    public void getUserInfo() {
        UserAccount account = mPreferHelper.getUserAccount();
        if (account == null) {
            return;
        }
        mView.showUserInfo(account.getUserAccount());
    }

    @Override
    public void logout() {
        mPreferHelper.saveUserAccount(null);
        mView.logoutSuccess();
    }
}
