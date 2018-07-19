package com.xunchijn.tongshan.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xunchijn.tongshan.R;

public class TestActivity extends AppCompatActivity {
    private NoticeDialog mNoticeDialog;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout_test);

        showDatePicker();
//        showNotice();
    }

    private void showDatePicker(){
        mDatePickerDialog = new DatePickerDialog();
        mDatePickerDialog.setOnConfirmClickListener(new DatePickerDialog.OnConfirmClickListener() {
            @Override
            public void OnConfirm(String timestamp, long time) {
                Log.d("main", "OnConfirm: " + timestamp + " " + time);
            }
        });
        mDatePickerDialog.show(getSupportFragmentManager(), "datePicker");
    }

    private void showNotice(){
        mNoticeDialog = new NoticeDialog();
        mNoticeDialog.setTitle("系统通知1");
        mNoticeDialog.setContent("通知内容1");
        mNoticeDialog.show(getSupportFragmentManager(), "noticeDialog");
    }
}
