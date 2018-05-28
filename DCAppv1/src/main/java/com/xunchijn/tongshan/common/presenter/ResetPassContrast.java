package com.xunchijn.tongshan.common.presenter;

import com.xunchijn.tongshan.base.BaseView;

public interface ResetPassContrast {

    interface Presenter {
        void resetPassword(String oldPassword, String newPassword);
    }

    interface View extends BaseView<Presenter> {
        void resetSuccess();
    }
}
