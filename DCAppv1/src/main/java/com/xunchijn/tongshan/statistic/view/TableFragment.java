package com.xunchijn.tongshan.statistic.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.SettingAdapter;
import com.xunchijn.tongshan.base.BaseConfig;
import com.xunchijn.tongshan.common.module.SettingItem;

public class TableFragment extends Fragment {
	private static final String TYPE = "mTableType";
	private int mType;
	private SettingAdapter settingAdapter;

	public static TableFragment newInstance(int tableType) {
		TableFragment tableFragment = new TableFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(TYPE, tableType);
		tableFragment.setArguments(bundle);
		return tableFragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_table, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		RecyclerView recyclerView = view.findViewById(R.id.recycler_view_table);
		Bundle bundle = getArguments();
		if (bundle == null) {
			return;
		}
		mType = bundle.getInt(TYPE);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		if (mType==7) {
			settingAdapter = new SettingAdapter(BaseConfig.getCarTable());
		} else if (mType==12) {
			settingAdapter = new SettingAdapter(BaseConfig.getUserTable());
		}

		recyclerView.setAdapter(settingAdapter);
		settingAdapter.setItemClickListener(new SettingAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(SettingItem item) {
				DomainRecordsActivity.start(getContext(), item.getTitle());
			}
		});
	}
}
