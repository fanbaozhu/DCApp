package com.xunchijn.sichuan.common.presenter;

import com.xunchijn.sichuan.base.BaseView;

public interface ResetPassContrast {

    interface Presenter {
        void resetPassword(String oldPassword, String newPassword);
    }

    interface View extends BaseView<Presenter> {
        void resetSuccess();
    }
}
