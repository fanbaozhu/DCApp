package com.xunchijn.tongshan.common.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.base.UserInfo;

public interface MineContrast {
    interface Presenter {
        void getUserInfo();
    }

    interface View extends BaseView<Presenter> {
        void showUserInfo(UserInfo userInfo);
    }
}
