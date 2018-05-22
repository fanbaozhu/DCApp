package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;

public interface ResetPassContrast {

    interface Presenter {
        void resetPassword(String oldPassword, String newPassword);
    }

    interface View extends BaseView<Presenter> {
        void resetSuccess();
    }
}
