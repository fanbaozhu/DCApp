package com.xunchijn.dcappv1.common.view;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.common.presenter.MessagesPresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

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
        new MessagesPresenter(messagesFragment, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, messagesFragment)
                .show(messagesFragment)
                .commit();
    }
}
