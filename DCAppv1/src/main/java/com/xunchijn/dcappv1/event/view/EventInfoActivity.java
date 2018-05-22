package com.xunchijn.dcappv1.event.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.event.presenter.EventInfoPresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

public class EventInfoActivity extends AbsBaseActivity {

    public static void start(Context context, String eventId) {
        Intent intent = new Intent(context, EventInfoActivity.class);
        intent.putExtra("eventId", eventId);
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
        EventInfoFragment fragment = EventInfoFragment.newInstance(eventId);
        new EventInfoPresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }
}
