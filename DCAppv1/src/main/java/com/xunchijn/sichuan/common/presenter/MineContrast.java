package com.xunchijn.sichuan.common.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.base.UserInfo;

public interface MineContrast {
    interface Presenter {
        void getUserInfo();
    }

    interface View extends BaseView<Presenter> {
        void showUserInfo(UserInfo userInfo);
    }
}
