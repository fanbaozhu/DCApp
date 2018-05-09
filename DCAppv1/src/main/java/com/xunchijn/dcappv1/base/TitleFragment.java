package com.xunchijn.dcappv1.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xunchijn.dcappv1.R;

public class TitleFragment extends Fragment implements View.OnClickListener {
    private OnConfirmListener mConfirmListener;
    private static final String TITLE = "title";
    private static final String SHOW_BACK = "showBack";
    private static final String SHOW_CONFIRM = "showConfirm";
    private static final String CONFIRM_PIC = "confirmPic";
    private static final String BACK_PIC = "backPic";

    public static TitleFragment newInstance(String title, boolean showBack, boolean showConfirm, int confirmPic, int backPic) {
        TitleFragment fragment = new TitleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putBoolean(SHOW_BACK, showBack);
        bundle.putBoolean(SHOW_CONFIRM, showConfirm);
        bundle.putInt(CONFIRM_PIC, confirmPic);
        bundle.putInt(BACK_PIC, backPic);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Bundle args = getArguments();
        if (args == null) {
            return;
        }
        TextView mViewTitle = view.findViewById(R.id.text_title);
        String title = args.getString(TITLE);
        if (!TextUtils.isEmpty(title)) {
            mViewTitle.setText(title);
        }
        ImageView mBackView = view.findViewById(R.id.image_title_back);
        ImageView mConfirmView = view.findViewById(R.id.image_title_confirm);
        boolean showBack = args.getBoolean(SHOW_BACK, false);
        mBackView.setVisibility(showBack ? View.VISIBLE : View.GONE);
        boolean showConfirm = args.getBoolean(SHOW_CONFIRM, false);
        mConfirmView.setVisibility(showConfirm ? View.VISIBLE : View.GONE);
        int drawableId = args.getInt(CONFIRM_PIC, 0);
        if (drawableId != 0) {
            Glide.with(getContext()).load(drawableId).apply(new RequestOptions().centerCrop()).into(mConfirmView);
        }
        int backPic = args.getInt(BACK_PIC, 0);
        if (backPic != 0) {
            Glide.with(getContext()).load(backPic).apply(new RequestOptions().centerCrop()).into(mBackView);
        }
        mBackView.setOnClickListener(this);
        mConfirmView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mConfirmListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.image_title_confirm:
                mConfirmListener.onConfirm();
                break;
            case R.id.image_title_back:
                mConfirmListener.onBack();
                break;
        }
    }

    public interface OnConfirmListener {

        void onBack();

        void onConfirm();

    }

    public void setConfirmListener(OnConfirmListener confirmListener) {
        mConfirmListener = confirmListener;
    }
}
