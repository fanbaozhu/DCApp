package com.xunchijn.dcappv1.event.view;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.AbsBaseActivity;
import com.xunchijn.dcappv1.util.TitleFragment;
import com.xunchijn.dcappv1.event.presenter.HistoryPresenter;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午6:16
 * Description:
 **/
public class HistoryActivity extends AbsBaseActivity {

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("上报历史", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }

    @Override
    public void initContent() {
        HistoryFragment historyFragment = new HistoryFragment();
        new HistoryPresenter(historyFragment,this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, historyFragment)
                .show(historyFragment).commit();
    }
}
