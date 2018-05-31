package com.xunchijn.tongshan.event.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.event.presenter.EventInfoPresenter;
import com.xunchijn.tongshan.util.TitleFragment;

public class EventInfoActivity extends AbsBaseActivity {

    public static void start(Context context, String eventId, boolean isEvent) {
        Intent intent = new Intent(context, EventInfoActivity.class);
        intent.putExtra("eventId", eventId);
        intent.putExtra("isEvent", isEvent);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("事件详情", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        String eventId = getIntent().getStringExtra("eventId");
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        boolean isEvent = getIntent().getBooleanExtra("isEvent", true);
        EventInfoFragment fragment = EventInfoFragment.newInstance(eventId);
        fragment.setEvent(isEvent);
        new EventInfoPresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }
}
