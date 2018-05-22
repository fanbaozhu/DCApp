package com.xunchijn.dcappv1.common.view;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.util.TitleFragment;

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
        //Todo 初始化意见反馈presenter
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mFeedbackFragment)
                .show(mFeedbackFragment)
                .commit();
    }
}
