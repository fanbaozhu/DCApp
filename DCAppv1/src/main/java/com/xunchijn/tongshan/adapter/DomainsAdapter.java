package com.xunchijn.tongshan.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.statistic.model.DomainItem;

import java.util.List;

public class DomainsAdapter extends RecyclerView.Adapter {
	private List<DomainItem> mList;
	private String mType;
	private OnItemClickListener mItemClickListener;

	public DomainsAdapter(List<DomainItem> list) {
		mList = list;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_statistic_domain, parent, false);
		return new DomainView(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		DomainItem item = mList.get(position);
		if (item != null && holder instanceof DomainView) {
			((DomainView) holder).bindDomains(item);
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	public void setItemClickListener(OnItemClickListener itemClickListener) {
		mItemClickListener = itemClickListener;
	}

	public interface OnItemClickListener {
		void onItemClick(DomainItem item);
	}

	private class DomainView extends RecyclerView.ViewHolder {
		private TextView mViewName;
		private TextView mViewType;
		private TextView mViewDept;
		private TextView mViewCount;
		private TextView mViewTimes;
		private TextView mViewStatus;
		private TextView mViewEarlyRetreat;
		private String flag;

		DomainView(View itemView) {
			super(itemView);
			mViewName = itemView.findViewById(R.id.text_car_name);
			mViewType = itemView.findViewById(R.id.text_car_type);
			mViewDept = itemView.findViewById(R.id.text_car_dept);
			mViewCount = itemView.findViewById(R.id.text_car_count);
			mViewTimes = itemView.findViewById(R.id.text_car_times);
			mViewStatus = itemView.findViewById(R.id.text_car_status);
			mViewEarlyRetreat = itemView.findViewById(R.id.text_car_earlyRetreat);
		}

		void bindDomains(final DomainItem item) {
			flag = item.getFlag();
			if (! TextUtils.isEmpty(item.getCarName())) {
				mViewName.setText(String.format("车牌号：%s", item.getCarName()));
			}
			if (! TextUtils.isEmpty(item.getUserName())) {
				mViewName.setText(String.format("姓名：%s", item.getUserName()));
			}
			if (! TextUtils.isEmpty(item.getCarType())) {
				mViewType.setText(String.format("车辆类型：%s", item.getCarType()));
			}
			if (! TextUtils.isEmpty(item.getUserDept())) {
				mViewType.setText(String.format("所在部门：%s", item.getUserDept()));
			}
			if (! TextUtils.isEmpty(item.getCarDept())) {
				mViewDept.setVisibility(View.VISIBLE);
				mViewDept.setText(String.format("所在部门：%s", item.getCarDept()));
			} else if (! TextUtils.isEmpty(item.getLate())) {
				mViewDept.setVisibility(View.VISIBLE);
				mViewDept.setText(String.format("迟到：%s次", item.getLate()));
			} else if (! TextUtils.isEmpty(item.getUserStatus())) {
				mViewDept.setVisibility(View.VISIBLE);
				if (item.getUserStatus().equals("1")) {
					mViewDept.setText("状态: 工作");
				} else if (item.getUserStatus().equals("2")) {
					mViewDept.setText("状态: 越界");
				} else if (item.getUserStatus().equals("3")) {
					mViewDept.setText("状态: 滞留");
				} else if (item.getUserStatus().equals("4")) {
					mViewDept.setText("状态: 离线");
				}
			}
			if (! TextUtils.isEmpty(item.getFrequency())) {
				mViewCount.setText(String.format("运行：%s次", item.getFrequency()));
			} else if (! TextUtils.isEmpty(item.getAbsenteeism())) {
				mViewCount.setText(String.format("旷工：%s次", item.getAbsenteeism()));
			}
			if (! TextUtils.isEmpty(item.getOffLine())) {
				mViewStatus.setVisibility(View.VISIBLE);
				mViewStatus.setText(String.format("离线：%s次", item.getOffLine()));
			}
			if (! TextUtils.isEmpty(item.getTransboundary())) {
				mViewTimes.setText(String.format("越界：%s次", item.getTransboundary()));
			} else if (TextUtils.isEmpty(flag)) {
				mViewTimes.setText(String.format("累计时长：%s分钟", item.getTimeDifference()));
			} else if (flag.equals("加水")) {
				mViewTimes.setText(String.format("累计加水：%s方", item.getNumber()));
			} else if (flag.equals("垃圾")) {
				mViewTimes.setText(String.format("累计清理：%s吨", item.getNumber()));
			}
			if (! TextUtils.isEmpty(item.getEarlyRetreat())) {
				mViewEarlyRetreat.setVisibility(View.VISIBLE);
				mViewEarlyRetreat.setText(String.format("早退：%s次", item.getEarlyRetreat()));
			}
			if (mItemClickListener == null) {
				return;
			}
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mItemClickListener.onItemClick(item);
				}
			});
		}
	}
}
