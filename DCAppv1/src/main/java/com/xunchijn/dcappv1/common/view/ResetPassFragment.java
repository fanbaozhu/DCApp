package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.presenter.ResetPassContrast;

public class ResetPassFragment extends Fragment implements ResetPassContrast.View {
    private ResetPassContrast.Presenter mPresenter;
    private EditText mInputPassOld;
    private EditText mInputPassNew;
    private EditText mInputPassAgain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_pass, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mInputPassNew = view.findViewById(R.id.edit_pass_new);
        mInputPassOld = view.findViewById(R.id.edit_pass_old);
        mInputPassAgain = view.findViewById(R.id.edit_pass_again);
    }

    @Override
    public void resetSuccess() {
        showError("密码修改成功");
        getActivity().onBackPressed();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ResetPassContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    public void resetPassword() {
        String oldPass = mInputPassOld.getText().toString();
        if (TextUtils.isEmpty(oldPass)) {
            showError("原密码不能为空");
            return;
        }
        String newPass = mInputPassNew.getText().toString();
        if (TextUtils.isEmpty(newPass)) {
            showError("新密码不能为空");
            return;
        }
        String newPassAgain = mInputPassAgain.getText().toString();
        if (TextUtils.isEmpty(newPassAgain) || !newPass.equals(newPassAgain)) {
            showError("两次新密码不相同");
            return;
        }
        if (mPresenter != null) {
            mPresenter.resetPassword(oldPass, newPass);
        }
    }
}
