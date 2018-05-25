package com.xunchijn.dcappv1.statistic.view;

import android.content.Intent;
import android.text.TextUtils;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.statistic.presenter.DomainPresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class DomainDetailsActivity extends AbsBaseActivity {

    @Override
    public void initTitle() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String title = intent.getStringExtra("carName");
        if (TextUtils.isEmpty(title)) {
            return;
        }
        TitleFragment titleFragment = TitleFragment.newInstance(title, true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        DomainDetailsFragment fragment = new DomainDetailsFragment();
        new DomainPresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }
}
