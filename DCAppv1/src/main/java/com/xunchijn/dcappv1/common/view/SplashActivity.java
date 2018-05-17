package com.xunchijn.dcappv1.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.presenter.LoginContrast;
import com.xunchijn.dcappv1.common.presenter.LoginPresenter;

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
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void setPresenter(LoginContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
