package com.xunchijn.sichuan.map.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.util.TitleFragment;
import com.xunchijn.sichuan.common.module.SettingItem;
import com.xunchijn.sichuan.map.presenter.SelectPresenter;

import java.util.List;

public class SelectUsersActivity extends AbsBaseActivity {
    private static final String TYPE = "type";
    private SelectFragment selectFragment;
    private String mType;

    public static void newInstance(Context context, String type) {
        Intent intent = new Intent(context, SelectUsersActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("选择人员", true, true);
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
        if (items == null || items.size() < 3) {
            Toast.makeText(this, "请先选择一位人员", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mType.equals("定位")) {
            LocationActivity.newInstance(this, "人员", items.get(2).getId());
        } else {
            TraceActivity.newInstance(this, "人员", items.get(2).getId());
        }
        finish();
    }

    @Override
    public void initContent() {
        mType = getIntent().getStringExtra(TYPE);
        if (TextUtils.isEmpty(mType)) {
            return;
        }
        selectFragment = SelectFragment.newInstance("人员");
        new SelectPresenter(selectFragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, selectFragment)
                .show(selectFragment)
                .commit();
    }
}
