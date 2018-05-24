package com.xunchijn.sichuan.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.xunchijn.sichuan.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间选择器
 * Created by Administrator on 2017/10/12.
 */

public class TimePickerDialog extends DialogFragment {
    private static final String TAG = "TIME_PICKER";
    private boolean mFixedDate = true;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Timestamp timestamp;
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.dialog_time_picker, (ViewGroup) window.findViewById(android.R.id.content), false);
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
        cancel.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGreen));
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
        datePicker.setEnabled(mFixedDate);
        timePicker = view.findViewById(R.id.time);
        timePicker.setIs24HourView(true);

        resizePicker(datePicker);
        resizePicker(timePicker);
    }

    private void resizePicker(FrameLayout frameLayout) {
        List<NumberPicker> list = findNumberPicker(frameLayout);
        for (NumberPicker numberPicker : list) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            numberPicker.setLayoutParams(params);
        }
    }

    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<>();
        View child;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    private void getTime() {
        String year = String.valueOf(datePicker.getYear());
        String month = String.valueOf(datePicker.getMonth() + 1);
        String day = String.valueOf(datePicker.getDayOfMonth());
        String hour = timePicker.getCurrentHour().toString();
        String minute = timePicker.getCurrentMinute().toString();
        if (month.length() == 1) {
            month = "0" + month;
        }
        Log.d(TAG, String.format("year=%s month=%s day=%s hourOfDay=%s minute=%s", year, month, day, hour, minute));
        String time = String.format("%s年%s月%s日 %s:%s:00", year, month, day, hour, minute);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long times = new Date().getTime() / 1000;
        try {
            timestamp = new Timestamp(format.parse(time).getTime());
            times = format.parse(time).getTime() / 1000;
            Log.d(TAG, "getTime: " + times);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (timestamp == null) {
            timestamp = new Timestamp(new Date().getTime());
        }
        Log.d(TAG, "onTimeChanged: " + timestamp.toString());
        if (onConfirmClickListener != null) {
            onConfirmClickListener.OnConfirm(timestamp.toString(), times);
        }
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

    public void setFixedDate(boolean fixedDate) {
        mFixedDate = fixedDate;
    }
}
