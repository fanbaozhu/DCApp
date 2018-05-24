package com.xunchijn.sichuan.common.presenter;

import com.xunchijn.sichuan.base.BaseView;
import com.xunchijn.sichuan.common.module.MessageItem;

import java.util.List;

public interface MessagesContrast {
    interface Presenter {
        void getMessages(int pageIndex, int pageCount);
    }

    interface View extends BaseView<Presenter> {
        void showMessages(List<MessageItem> list);
    }
}
