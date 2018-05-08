package com.xunchijn.dcappv1.common.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.xunchijn.dcappv1.R;


/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText userName;
    private EditText userPassword;
    private Button loginConfirm;
    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.login_userName);
        userPassword = findViewById(R.id.login_userPassword);
        loginConfirm = findViewById(R.id.login_confirm);
    }
}
