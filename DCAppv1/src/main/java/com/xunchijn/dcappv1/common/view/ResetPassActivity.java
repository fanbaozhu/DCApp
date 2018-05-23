package com.xunchijn.dcappv1.common.view;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.common.presenter.ResetPassPresenter;
import com.xunchijn.dcappv1.util.TitleFragment;

public class ResetPassActivity extends AbsBaseActivity {
    private ResetPassFragment mResetPassFragment;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("修改密码", true, true);
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                mResetPassFragment.resetPassword();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        mResetPassFragment = new ResetPassFragment();
        new ResetPassPresenter(mResetPassFragment, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mResetPassFragment)
                .show(mResetPassFragment)
                .commit();
    }
}
