package com.xunchijn.tongshan.event.view;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.util.TitleFragment;

public class PositionActivity extends AbsBaseActivity {
    private PositionFragment mPositionFragment;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("获取定位", true, true);
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
                String position = mPositionFragment.getEditPosition();
                String point = mPositionFragment.getPosition();
                if (TextUtils.isEmpty(position) || TextUtils.isEmpty(point)) {
                    Toast.makeText(PositionActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("position", position);
                    intent.putExtra("point", point);
                    setResult(RESULT_OK, intent);
                }
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        mPositionFragment = new PositionFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mPositionFragment)
                .show(mPositionFragment)
                .commit();
    }
}
