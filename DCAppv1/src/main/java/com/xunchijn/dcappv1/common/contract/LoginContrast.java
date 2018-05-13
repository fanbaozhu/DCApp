package com.xunchijn.dcappv1.common.contract;

import com.xunchijn.dcappv1.base.BaseView;

public interface LoginContrast {
    interface Presenter {
        void checkLocalData();

        void login(String userAccount, String password);
    }

    interface View extends BaseView<Presenter> {
        void loginSuccess(String userName);
    }
}
