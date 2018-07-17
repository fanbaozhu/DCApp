package com.xunchijn.tongshan.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.map.model.DetailsItem;
import com.xunchijn.tongshan.map.presenter.DetailsContract;

public class DetailsFragment extends Fragment implements DetailsContract.View {
	private static String TYPE = "TYPE";
	private DetailsContract.Presenter mPresenter;
	private TextView tvName;
	private TextView tvDept;
	private TextView tvZone;
	private TextView tvStatus;
	private TextView tvAddress;

	public static DetailsFragment newInstance(String eventId, String type) {
		DetailsFragment detailsFragment = new DetailsFragment();
		Bundle bundle = new Bundle();
		bundle.putString("eventId", eventId);
		bundle.putString("TYPE", type);
		detailsFragment.setArguments(bundle);
		return detailsFragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_details, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		tvName = view.findViewById(R.id.tvName);
		tvDept = view.findViewById(R.id.tvDept);
		tvZone = view.findViewById(R.id.tvZone);
		tvStatus = view.findViewById(R.id.tvStatus);
		tvAddress = view.findViewById(R.id.tvAddress);
		Bundle bundle = getArguments();
		if (bundle == null || mPresenter == null) {
			return;
		}
		String eventId = bundle.getString("eventId");
		String type = bundle.getString(TYPE);
		if (TextUtils.isEmpty(eventId)) {
			return;
		}
		if (type.equals("车辆")) {
			mPresenter.getCarDetails(eventId);
		} else if (type.equals("人员")) {
			mPresenter.getUserDetails(eventId);
		}

	}

	@Override
	public void showCarDetails(DetailsItem item) {
		tvName.setText(String.format("车牌名称：%s", item.getCarName()));
		tvDept.setText(String.format("所属部门：%s", item.getCarDept()));
		tvZone.setText(String.format("所属区域：%s", item.getCarZone()));
		tvStatus.setText(String.format("当前状态：%s", item.getCarStatus()));
		tvAddress.setText(String.format("当前位置：%s", item.getCarAddress()));
	}

	@Override
	public void showUserDetails(DetailsItem item) {
		tvName.setText(String.format("人员姓名：%s", item.getUserName()));
		tvDept.setText(String.format("所属部门：%s", item.getUserDept()));
		tvZone.setText(String.format("所属区域：%s", item.getUserZone()));
		tvStatus.setText(String.format("当前状态：%s", item.getUserStatus()));
		tvAddress.setText(String.format("当前位置：%s", item.getUserAddress()));
	}

	@Override
	public void showError(String error) {
		Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setPresenter(DetailsContract.Presenter presenter) {
		mPresenter = presenter;
	}
}
