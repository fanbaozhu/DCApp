package com.xunchijn.dcappv1.event.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午6:16
 * Description:
 **/
public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_event_history);

        TitleFragment titleFragment = TitleFragment.newInstance("上报历史", true, false);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }
}
