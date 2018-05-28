package com.xunchijn.tongshan.common.presenter;

import com.xunchijn.tongshan.base.BaseView;

public interface FeedBackContrast {
    interface Presenter {
        void feedback(String title, String feedback, String userPhone);
    }

    interface View extends BaseView<Presenter> {
        void feedbackSuccess();

        void feedback();
    }
}
