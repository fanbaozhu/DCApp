package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;

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
