package com.xunchijn.tongshan.statistic.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.DomainsAdapter;
import com.xunchijn.tongshan.statistic.model.DomainItem;
import com.xunchijn.tongshan.statistic.presenter.DomainsContrast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomainRecordsFragment extends Fragment implements DomainsContrast.View {
	private DomainsContrast.Presenter mPresenter;
	private List<DomainItem> mList;
	private DomainsAdapter mDomainsAdapter;
	private String mTime;
	private String mType;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mList = new ArrayList<>();
		mDomainsAdapter = new DomainsAdapter(mList);
		recyclerView.setAdapter(mDomainsAdapter);
		mDomainsAdapter.setItemClickListener(new DomainsAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(DomainItem item) {
				Intent intent = new Intent(getContext(), DomainDetailsActivity.class);
				intent.putExtra("carName", item.getCarName());
				intent.putExtra("gps_simId", item.getCarId());
				intent.putExtra("userName",item.getUserName());
				intent.putExtra("user_simId",item.getUserId());
				intent.putExtra("type", mType);
				DomainRecordsActivity activity = (DomainRecordsActivity) getActivity();
				if (activity == null) {
					return;
				}
				String time = activity.getTimes();
				if (! TextUtils.isEmpty(time)) {
					mTime = time;
				}
				intent.putExtra("startTime", mTime);
				startActivity(intent);
			}
		});
		initData();
	}

	private void initData() {
		Log.d("Domain", "initView: " + mType);
		mTime = String.valueOf(new Date().getTime() - 24 * 60 * 60 * 1000);
		mTime = mTime.substring(0, 10);
		if (mPresenter == null) {
			return;
		}
		switch (mType) {
			case "车辆进出区域报表":
				mPresenter.getCarRecords(mTime);
				break;
			case "车辆加水报表":
				mPresenter.getRegionCar(mTime, "加水");
				break;
			case "车辆垃圾清运报表":
				mPresenter.getRegionCar(mTime, "垃圾");
				break;
			case "人员工作报表":
				mPresenter.getEmpWork(mTime);
				break;
		}
	}

	@Override
	//分页查询时使用 后期会加上判断 上拉加载 下拉刷新 如果是下拉 那么先.clear清除 然后走addAll重新填充 如果是上拉 那么直接走addAll
	public void showCarRecords(List<DomainItem> list) {
		mList.clear();
		//继续加载
		mList.addAll(list);
		//更新列表
		mDomainsAdapter.notifyDataSetChanged();
	}

	@Override
	public void showUserRecords(List<DomainItem> list) {
		mList.clear();
		//继续加载
		mList.addAll(list);
		//更新列表
		mDomainsAdapter.notifyDataSetChanged();
	}

	@Override
	public void showError(String error) {
		Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setPresenter(DomainsContrast.Presenter presenter) {
		mPresenter = presenter;
	}

	public void setType(String type) {
		mType = type;
	}
}