package com.xunchijn.tongshan.statistic.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.DatePicker;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.statistic.presenter.DomainPresenter;
import com.xunchijn.tongshan.statistic.presenter.DomainsContrast;
import com.xunchijn.tongshan.util.TitleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
//报表详情页面
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
        new DomainPresenter(fragment,this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }
}
