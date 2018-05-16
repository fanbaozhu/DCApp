package com.xunchijn.dcappv1.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_select_time);

        NumberPicker numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMaxValue(2018);
        numberPicker.setMinValue(2000);


    }
}
