package com.xunchijn.tongshan.statistic.view;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.util.TitleFragment;
//所有报表页面
public class TableActivity extends AbsBaseActivity {
    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("报表查询", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        TableFragment tableFragment = new TableFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, tableFragment)
                .show(tableFragment)
                .commit();
    }
}
