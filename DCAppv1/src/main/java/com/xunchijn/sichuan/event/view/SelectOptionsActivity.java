package com.xunchijn.sichuan.event.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.util.TitleFragment;
import com.xunchijn.sichuan.common.module.SettingItem;
import com.xunchijn.sichuan.event.presenter.SelectOptionsPresenter;

import java.util.ArrayList;

public class SelectOptionsActivity extends AbsBaseActivity {
    private SelectOptionsFragment mOptionsFragment;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("上报设置", true, true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                ArrayList<SettingItem> list = mOptionsFragment.getSelectItems();
                if (list != null && list.size() == 4) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selected", list);
                    intent.putExtra("args", bundle);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                } else {
                    Toast.makeText(SelectOptionsActivity.this, "请先设置完成", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initContent() {
        mOptionsFragment = new SelectOptionsFragment();
        new SelectOptionsPresenter(mOptionsFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mOptionsFragment)
                .show(mOptionsFragment)
                .commit();
    }
}
