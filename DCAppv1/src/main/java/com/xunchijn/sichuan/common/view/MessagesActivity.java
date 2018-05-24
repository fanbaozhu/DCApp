package com.xunchijn.sichuan.common.view;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.common.presenter.MessagesPresenter;
import com.xunchijn.sichuan.util.TitleFragment;

public class MessagesActivity extends AbsBaseActivity {

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("消息通知", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        MessagesFragment messagesFragment = new MessagesFragment();
        messagesFragment.setPageCount(20);
        new MessagesPresenter(messagesFragment, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, messagesFragment)
                .show(messagesFragment)
                .commit();
    }
}
