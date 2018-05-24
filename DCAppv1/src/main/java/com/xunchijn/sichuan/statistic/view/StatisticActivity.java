package com.xunchijn.sichuan.statistic.view;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.statistic.presenter.DomainRecordsPresenter;
import com.xunchijn.sichuan.statistic.presenter.DomainsContrast;
import com.xunchijn.sichuan.util.TitleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticActivity extends AbsBaseActivity {
    private DomainsContrast.Presenter mPresenter;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("车辆出入区域表", true, true);
        titleFragment.setRightDrawableId(R.mipmap.ic_select_date);
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                showDialog();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        DomainRecordsFragment fragment = new DomainRecordsFragment();
        mPresenter = new DomainRecordsPresenter(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, fragment)
                .show(fragment)
                .commit();
    }

    private void showDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        int month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String time = String.format("%s年%s月%s日 00:00:00", year, month + 1, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                long times = 0;
                try {
                    times = format.parse(time).getTime() / 1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mPresenter.getCarRecords(String.valueOf(times));
            }
        }, year, month, day);
        dialog.show();
    }
}
