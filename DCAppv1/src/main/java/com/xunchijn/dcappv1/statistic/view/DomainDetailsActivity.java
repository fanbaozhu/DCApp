package com.xunchijn.dcappv1.statistic.view;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.statistic.presenter.DomainRecordsPresenter;
import com.xunchijn.dcappv1.statistic.presenter.DomainsContrast;
import com.xunchijn.dcappv1.util.TitleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class DomainDetailsActivity extends AbsBaseActivity {

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("车辆出入区域表", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        DomainDetailsFragment fragment = new DomainDetailsFragment();
        new DomainRecordsPresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }
}
