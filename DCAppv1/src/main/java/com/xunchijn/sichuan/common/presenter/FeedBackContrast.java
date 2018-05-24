package com.xunchijn.sichuan.common.presenter;

import com.xunchijn.sichuan.base.BaseView;

public interface FeedBackContrast {
    interface Presenter {
        void feedback(String title, String feedback, String userPhone);
    }

    interface View extends BaseView<Presenter> {
        void feedbackSuccess();

        void feedback();
    }
}
