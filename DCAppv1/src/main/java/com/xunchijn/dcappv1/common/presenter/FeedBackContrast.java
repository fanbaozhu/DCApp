package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;

public interface FeedBackContrast {
    interface Presenter {
        void feedback(String feedback);
    }

    interface View extends BaseView<Presenter> {
        void feedbackSuccess();

        void feedback();
    }
}
