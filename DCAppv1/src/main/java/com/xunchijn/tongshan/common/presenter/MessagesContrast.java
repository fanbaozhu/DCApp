package com.xunchijn.tongshan.common.presenter;

import com.xunchijn.tongshan.base.BaseView;
import com.xunchijn.tongshan.common.module.MessageItem;

import java.util.List;

public interface MessagesContrast {
    interface Presenter {
        void getMessages(int pageIndex, int pageCount);
    }

    interface View extends BaseView<Presenter> {
        void showMessages(List<MessageItem> list);
    }
}
