package com.xunchijn.dcappv1.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.contract.LoginContrast;
import com.xunchijn.dcappv1.common.presenter.LoginPresenter;


/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class LoginActivity extends AppCompatActivity implements LoginContrast.View, View.OnClickListener {
    private LoginContrast.Presenter mPresenter;
    private EditText mEditAccount;
    private EditText mEditPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mPresenter = new LoginPresenter(this, this);

        mEditAccount = findViewById(R.id.login_userName);
        mEditPassword = findViewById(R.id.login_userPassword);
        Button loginConfirm = findViewById(R.id.login_confirm);
        loginConfirm.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.checkLocalData();
        }
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        String userAccount = mEditAccount.getText().toString();
        if (TextUtils.isEmpty(userAccount)) {
            showError("账号不能为空");
            return;
        }
        String password = mEditPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            showError("密码不能为空");
            return;
        }
        if (mPresenter != null) {
            mPresenter.login(userAccount, password);
        }
    }
}
