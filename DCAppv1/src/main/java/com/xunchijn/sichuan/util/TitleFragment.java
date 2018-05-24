package com.xunchijn.sichuan.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xunchijn.sichuan.R;

public class TitleFragment extends Fragment implements View.OnClickListener {
    private OnItemClickListener mConfirmListener;
    private static final String TITLE = "title";
    private static final String SHOW_BACK = "showBack";
    private static final String SHOW_CONFIRM = "showConfirm";
    private int mLeftDrawableId;
    private int mRightDrawableId;
    private boolean mShowEditSearch;
    private Activity mActivity;
    private TextWatcher mTextWatcher;

    public static TitleFragment newInstance(String title, boolean showBack, boolean showConfirm) {
        TitleFragment fragment = new TitleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putBoolean(SHOW_BACK, showBack);
        bundle.putBoolean(SHOW_CONFIRM, showConfirm);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setLeftDrawableId(int leftDrawableId) {
        mLeftDrawableId = leftDrawableId;
    }

    public void setRightDrawableId(int rightDrawableId) {
        mRightDrawableId = rightDrawableId;
    }

    public void setShowEditSearch(boolean showEditSearch) {
        mShowEditSearch = showEditSearch;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
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

        if (mRightDrawableId != 0) {
            Glide.with(mActivity).load(mRightDrawableId).apply(new RequestOptions().centerCrop()).into(mConfirmView);
        }
        if (mLeftDrawableId != 0) {
            Glide.with(mActivity).load(mLeftDrawableId).apply(new RequestOptions().centerCrop()).into(mBackView);
        }
        mBackView.setOnClickListener(this);
        mConfirmView.setOnClickListener(this);

        EditText editContent = view.findViewById(R.id.edit_title_content);
        editContent.setVisibility(mShowEditSearch ? View.VISIBLE : View.GONE);
        editContent.addTextChangedListener(mTextWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_title_confirm:
                if (mConfirmListener != null) {
                    mConfirmListener.onConfirm();
                }
                break;
            case R.id.image_title_back:
                if (mConfirmListener != null) {
                    mConfirmListener.onBack();
                } else {
                    mActivity.onBackPressed();
                }
                break;
        }
    }

    public interface OnItemClickListener {

        void onBack();

        void onConfirm();

    }

    public void setItemClickListener(OnItemClickListener confirmListener) {
        mConfirmListener = confirmListener;
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        mTextWatcher = textWatcher;
    }
}
