package com.xunchijn.tongshan.common.view;

import android.app.Activity;
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

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.common.presenter.FeedBackContrast;

public class FeedbackFragment extends Fragment implements FeedBackContrast.View {
    private FeedBackContrast.Presenter mPresenter;
    private EditText mInputUserPhone;
    private EditText mInputFeedback;
    private EditText mInputTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        mInputUserPhone = view.findViewById(R.id.edit_user_phone);
        mInputFeedback = view.findViewById(R.id.edit_feedback);
        mInputTitle = view.findViewById(R.id.edit_title);
        return view;
    }

    @Override
    public void feedback() {
        String title = mInputTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            showError("标题不能为空");
            return;
        }
        String feedback = mInputFeedback.getText().toString();
        if (TextUtils.isEmpty(feedback)) {
            showError("请留下您的意见或建议");
            return;
        }
        String userPhone = mInputUserPhone.getText().toString();
        if (TextUtils.isEmpty(userPhone)) {
            showError("请留下您的联系方式");
            return;
        }
        if (mPresenter != null) {
            mPresenter.feedback(title, feedback, userPhone);
        }
    }

    @Override
    public void feedbackSuccess() {
        showError("谢谢您的意见或建议，我们争取做的更好！");
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(FeedBackContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
