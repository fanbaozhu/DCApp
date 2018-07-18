package com.xunchijn.tongshan.event.view;

import android.content.Context;
import android.content.Intent;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.event.presenter.EventHistoryPresenter;
import com.xunchijn.tongshan.util.TitleFragment;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午6:16
 * Description:
 **/
public class EventHistoryActivity extends AbsBaseActivity {
    private static final String EVENT_FLAG = "isEvent";

    public static void start(Context context, boolean isEvent) {
        Intent intent = new Intent(context, EventHistoryActivity.class);
        intent.putExtra(EVENT_FLAG, isEvent);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("上报历史", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }

    @Override
    public void initContent() {
        boolean isEvent = getIntent().getBooleanExtra("isEvent", true);
        EventHistoryFragment historyFragment = new EventHistoryFragment();
        historyFragment.setEvent(isEvent);
        new EventHistoryPresenter(historyFragment,this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, historyFragment)
                .show(historyFragment).commit();
    }
}
