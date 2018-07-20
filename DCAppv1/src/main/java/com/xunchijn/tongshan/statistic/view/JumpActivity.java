package com.xunchijn.tongshan.statistic.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.util.TitleFragment;

public class JumpActivity extends AbsBaseActivity {
	private JumpFragment jumpFragment;

	public static void newInstance(Context context,String startTime,String endTime,String simId) {
		Intent intent = new Intent(context, JumpActivity.class);
		intent.putExtra("startTime",startTime);
		intent.putExtra("endTime",endTime);
		intent.putExtra("simId",simId);
		context.startActivity(intent);
	}
	@Override
	public void initTitle() {
		TitleFragment titleFragment = TitleFragment.newInstance("详情",true,false);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.layout_title, titleFragment)
				.show(titleFragment)
				.commit();
	}

	@Override
	public void initContent() {
		Bundle bundle = getIntent().getExtras();
		if(bundle==null){
			return;
		}
		String startTime = bundle.getString("startTime");
		String endTime = bundle.getString("endTime");
		String simId = bundle.getString("simId");
		jumpFragment =JumpFragment.newInstance(startTime,endTime,simId);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.layout_container, jumpFragment)
				.show(jumpFragment)
				.commit();
	}
}
