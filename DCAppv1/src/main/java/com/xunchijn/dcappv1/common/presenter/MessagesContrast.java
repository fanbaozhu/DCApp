package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.common.module.MessageItem;

import java.util.List;

public interface MessagesContrast {
    interface Presenter {
        void getMessages(int pageIndex, int pageCount);
    }

    interface View extends BaseView<Presenter> {
        void showMessages(List<MessageItem> list);
    }
}
