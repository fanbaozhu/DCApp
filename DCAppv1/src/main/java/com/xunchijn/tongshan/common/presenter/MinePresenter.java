package com.xunchijn.tongshan.common.presenter;

import android.content.Context;

import com.xunchijn.tongshan.common.module.UserAccount;
import com.xunchijn.tongshan.util.PreferHelper;

public class MinePresenter implements MineContrast.Presenter{
    private PreferHelper mPreferHelper;
    private MineContrast.View mView;


    public MinePresenter(MineContrast.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mPreferHelper = new PreferHelper(context);

    }
    public void getUserInfo() {
        UserAccount userAccount = mPreferHelper.getUserAccount();
        String account = userAccount.getUserAccount();
        mView.showUserInfo(account);

    }
}
