package com.xunchijn.sichuan.common.view;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.common.presenter.ResetPassPresenter;
import com.xunchijn.sichuan.util.TitleFragment;

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
