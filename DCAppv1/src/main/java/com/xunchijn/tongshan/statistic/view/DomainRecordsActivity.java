package com.xunchijn.tongshan.statistic.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.base.AbsBaseActivity;
import com.xunchijn.tongshan.statistic.presenter.DomainPresenter;
import com.xunchijn.tongshan.statistic.presenter.DomainsContrast;
import com.xunchijn.tongshan.util.TimePickerDialog;
import com.xunchijn.tongshan.util.TitleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
//报表展示页面
public class DomainRecordsActivity extends AbsBaseActivity {
	private static final String TYPE = "type";
	private DomainsContrast.Presenter mPresenter;
	private String mTimes;
	private String mType;
	private TimePickerDialog timePickerDialog;
	private long mStartTime;
	private long mEndTime;

	public static void start(Context context, String type) {
		Intent intent = new Intent(context, DomainRecordsActivity.class);
		intent.putExtra(TYPE, type);
		context.startActivity(intent);
	}

	@Override
	public void initTitle() {
		mType = getIntent().getStringExtra(TYPE);
		TitleFragment titleFragment = TitleFragment.newInstance(mType, true, true);
		titleFragment.setRightDrawableId(R.mipmap.ic_select_date);
		titleFragment.setItemClickListener(new TitleFragment.OnItemClickListener() {
			@Override
			public void onBack() {
				onBackPressed();
			}

			@Override
			public void onConfirm() {
				if(mType.equals("人员考勤报表")){
					selectStartTime();
				}else{
					showDialog();
				}

			}
		});
		getSupportFragmentManager().beginTransaction()
				.add(R.id.layout_title, titleFragment)
				.show(titleFragment)
				.commit();
	}

	@Override
	public void initContent() {
		DomainRecordsFragment fragment = new DomainRecordsFragment();
		fragment.setType(mType);
		mPresenter = new DomainPresenter(fragment, this);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.layout_container, fragment)
				.show(fragment)
				.commit();
	}

	private void showDialog() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);       //获取年月日时分秒
		int month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
		int day = cal.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				String time = String.format("%s年%s月%s日 00:00:00", year, month + 1, dayOfMonth);
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				long times = 0;
				try {
					times = format.parse(time).getTime() / 1000;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				mTimes = String.valueOf(times);
//                mPresenter.getCarRecords(mTimes);
				switch (mType) {
					case "车辆进出区域报表":
						mPresenter.getCarRecords(mTimes);
						break;
					case "车辆加水报表":
						mPresenter.getRegionCar(mTimes, "加水");
						break;
					case "车辆垃圾清运报表":
						mPresenter.getRegionCar(mTimes, "垃圾");
						break;
					case "人员工作报表":
						mPresenter.getEmpWork(mTimes);
						break;
				}
			}
		}, year, month, day);
		dialog.show();
	}

	private void selectStartTime() {
		mStartTime = 0;
		mEndTime = 0;
		timePickerDialog = new TimePickerDialog();
		timePickerDialog.setTitle("开始时间");
		timePickerDialog.setOnConfirmClickListener(new TimePickerDialog.OnConfirmClickListener() {
			@Override
			public void OnConfirm(String timestamp, long time) {
				timePickerDialog.dismiss();
				Log.d("test", "OnConfirm: timestamp=" + timestamp + " time=" + time);
				if (mStartTime == 0) {
					mStartTime = time;
					selectEndTime();
				} else if (mStartTime >= time) {
					selectEndTime();
					Toast.makeText(getContext(), "结束时间不能早于或者等于开始时间", Toast.LENGTH_SHORT).show();
				} else {
					mEndTime = time;
					mPresenter.getEmpAttendance(String.valueOf(mStartTime), String.valueOf(mEndTime));
				}
			}
		});
		timePickerDialog.show(getSupportFragmentManager(), "timePickerDialog");
	}

	private void selectEndTime() {
		if (timePickerDialog == null) {
			timePickerDialog = new TimePickerDialog();
		}
		timePickerDialog.setTitle("结束时间");
		timePickerDialog.setFixedDate(false);
		timePickerDialog.show(getSupportFragmentManager(), "timePickerDialog");
	}
	public String getTimes() {
		return mTimes;
	}
}
