package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.base.UserInfo;

public interface MineContrast {
    interface Presenter {
        void getUserInfo();
    }

    interface View extends BaseView<Presenter> {
        void showUserInfo(UserInfo userInfo);
    }
}
