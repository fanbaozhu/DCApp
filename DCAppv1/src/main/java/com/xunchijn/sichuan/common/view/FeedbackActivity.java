package com.xunchijn.sichuan.common.view;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.common.presenter.FeedbackPresenter;
import com.xunchijn.sichuan.util.TitleFragment;

public class FeedbackActivity extends AbsBaseActivity {
    private FeedbackFragment mFeedbackFragment;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("反馈意见", true, true);
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                mFeedbackFragment.feedback();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        mFeedbackFragment = new FeedbackFragment();
        new FeedbackPresenter(mFeedbackFragment, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mFeedbackFragment)
                .show(mFeedbackFragment)
                .commit();
    }
}
