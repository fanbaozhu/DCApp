package com.xunchijn.tongshan.common.presenter;

import com.xunchijn.tongshan.base.BaseView;

public interface LoginContrast {
    interface Presenter {
        //检查本地数据
        void checkLocalData();
        //登陆
        void login(String userAccount, String password);
    }

    interface View extends BaseView<Presenter> {
        //成功
        void loginSuccess(String userName);
    }
}