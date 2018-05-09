package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午5:12
 * Description:通用搜索页面
 **/
public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_common_search);
        initTitle();

    }

    private void initTitle() {
        TitleFragment fragment = TitleFragment.newInstance("搜索页面", true, false);
        fragment.setShowEditSearch(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, fragment)
                .show(fragment).commit();

    }


}
