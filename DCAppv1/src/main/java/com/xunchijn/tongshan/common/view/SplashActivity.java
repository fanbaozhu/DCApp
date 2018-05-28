package com.xunchijn.tongshan.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.common.presenter.LoginContrast;
import com.xunchijn.tongshan.common.presenter.LoginPresenter;

public class SplashActivity extends AppCompatActivity implements LoginContrast.View {
    private LoginContrast.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        mPresenter = new LoginPresenter(this, this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mPresenter.checkLocalData();
            }
        }).start();
    }

    @Override
    public void loginSuccess(String userName) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showError(String error) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void setPresenter(LoginContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}