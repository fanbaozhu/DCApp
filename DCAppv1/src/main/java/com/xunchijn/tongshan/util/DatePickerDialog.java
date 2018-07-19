package com.xunchijn.tongshan.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunchijn.tongshan.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerDialog extends DialogFragment {
    private DatePicker datePicker;
    private Timestamp timestamp;
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.dialog_date_picker, (ViewGroup) window.findViewById(android.R.id.content), false);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView textView = view.findViewById(R.id.text_title);
        if (!TextUtils.isEmpty(mTitle)) {
            textView.setText(mTitle);
        }
        ImageView cancel = view.findViewById(R.id.image_dialog_cancel);
        cancel.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorBlue));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        btnConfirm.setText("确定");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime();
            }
        });
        datePicker = view.findViewById(R.id.date);
    }

    private void getTime() {
        String year = String.valueOf(datePicker.getYear());
        String month = String.valueOf(datePicker.getMonth() + 1);
        String day = String.valueOf(datePicker.getDayOfMonth());
        if (month.length() == 1) {
            month = "0" + month;
        }
        String time = String.format("%s年%s月%s日 00:00:00", year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long times = new Date().getTime() / 1000;
        try {
            timestamp = new Timestamp(format.parse(time).getTime());
            times = format.parse(time).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (timestamp == null) {
            timestamp = new Timestamp(new Date().getTime());
        }

        if (onConfirmClickListener != null) {
            onConfirmClickListener.OnConfirm(timestamp.toString(), times);
        }
        dismiss();
    }

    public interface OnConfirmClickListener {
        void OnConfirm(String timestamp, long time);
    }

    private OnConfirmClickListener onConfirmClickListener;

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
