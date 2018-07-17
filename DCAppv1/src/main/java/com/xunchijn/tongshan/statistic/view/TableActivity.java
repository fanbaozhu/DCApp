package com.xunchijn.tongshan.statistic.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.map.view.SelectUsersActivity;
import com.xunchijn.tongshan.util.TitleFragment;

//所有报表页面
public class TableActivity extends AbsBaseActivity {
	private static final String TYPE = "type";
	private int mType;
	private TableFragment tableFragment;

	public static void newInstance(Context context, int value) {
		Intent intent = new Intent(context, TableActivity.class);
		intent.putExtra(TYPE, value);
		context.startActivity(intent);
	}

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
		mType = getIntent().getIntExtra(TYPE,0);
		if (mType==0) {
			return;
		}
		tableFragment =TableFragment.newInstance(mType);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.layout_container, tableFragment)
				.show(tableFragment)
				.commit();
	}
}
