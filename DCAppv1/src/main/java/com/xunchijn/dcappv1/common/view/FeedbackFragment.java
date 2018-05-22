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
import com.xunchijn.dcappv1.common.presenter.FeedBackContrast;

public class FeedbackFragment extends Fragment implements FeedBackContrast.View {
    private FeedBackContrast.Presenter mPresenter;
    private EditText mInputFeedback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        mInputFeedback = view.findViewById(R.id.edit_feedback);
        return view;
    }

    @Override
    public void feedback() {
        String feedback = mInputFeedback.getText().toString();
        if (TextUtils.isEmpty(feedback)) {
            showError("请留下您的意见或建议");
            return;
        }
        if (mPresenter != null) {
            mPresenter.feedback(feedback);
        }
    }

    @Override
    public void feedbackSuccess() {
        showError("谢谢您的意见或建议，我们争取做的更好！");
        getActivity().onBackPressed();
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
