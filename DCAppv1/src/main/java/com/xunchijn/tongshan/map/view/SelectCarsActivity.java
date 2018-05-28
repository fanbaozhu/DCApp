package com.xunchijn.tongshan.map.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.util.TitleFragment;
import com.xunchijn.tongshan.common.module.SettingItem;
import com.xunchijn.tongshan.map.presenter.SelectPresenter;

import java.util.List;

public class SelectCarsActivity extends AbsBaseActivity {
    private static final String TYPE = "type";
    private SelectFragment selectFragment;
    private String mType;

    public static void newInstance(Context context, String type) {
        Intent intent = new Intent(context, SelectCarsActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("选择车辆", true, true);
        titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                selectResult();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    private void selectResult() {
        List<SettingItem> items = selectFragment.getSelectedItems();
        if (items == null || items.size() < 2) {
            Toast.makeText(this, "请先选择一辆车", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mType.equals("定位")) {
            LocationActivity.newInstance(this, "车辆", items.get(1).getId());
        } else {
            TraceActivity.newInstance(this, "车辆", items.get(1).getId());
        }
        finish();
    }

    @Override
    public void initContent() {
        mType = getIntent().getStringExtra(TYPE);
        if (TextUtils.isEmpty(mType)) {
            return;
        }
        selectFragment = SelectFragment.newInstance("车辆");
        new SelectPresenter(selectFragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, selectFragment)
                .show(selectFragment)
                .commit();
    }
}
