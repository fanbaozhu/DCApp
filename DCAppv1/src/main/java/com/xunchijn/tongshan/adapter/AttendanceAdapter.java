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
import com.xunchijn.tongshan.util.TimeUtils;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter {
	private List<DomainItem> mList;

	public AttendanceAdapter(List<DomainItem> list) {
		mList = list;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_attendance_details, parent, false);
		return new AttendanceAdapter.AttendanceDetailsView(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		DomainItem item = mList.get(position);
		if (item != null && holder instanceof AttendanceAdapter.AttendanceDetailsView) {
			((AttendanceAdapter.AttendanceDetailsView) holder).bindDomains(item);
		}
	}

	@Override
	public int getItemCount() {
		return mList == null ? 0 : mList.size();
	}

	private class AttendanceDetailsView extends RecyclerView.ViewHolder {
		private TextView date;
		private TextView amQianDao;
		private TextView amQianTui;
		private TextView aEarlyRetreat;
		private TextView aLate;
		private TextView aOffLine;
		private TextView aAbsenteeism;
		private TextView aTransboundary;

		private TextView pmQianDao;
		private TextView pmQianTui;
		private TextView pEarlyRetreat;
		private TextView pLate;
		private TextView pOffLine;
		private TextView pAbsenteeism;
		private TextView pTransboundary;

		AttendanceDetailsView(View itemView) {
			super(itemView);
			date = itemView.findViewById(R.id.text_date);
			amQianDao = itemView.findViewById(R.id.text_amQianDao);
			amQianTui = itemView.findViewById(R.id.text_amQianTui);
			aEarlyRetreat = itemView.findViewById(R.id.text_sEarlyRetreat);
			aLate = itemView.findViewById(R.id.text_sLate);
			aOffLine = itemView.findViewById(R.id.text_sOffLine);
			aAbsenteeism = itemView.findViewById(R.id.text_sAbsenteeism);
			aTransboundary = itemView.findViewById(R.id.text_sTransboundary);

			pmQianDao = itemView.findViewById(R.id.text_pmQianDao);
			pmQianTui = itemView.findViewById(R.id.text_pmQianTui);
			pEarlyRetreat = itemView.findViewById(R.id.text_pEarlyRetreat);
			pLate = itemView.findViewById(R.id.text_pLate);
			pOffLine = itemView.findViewById(R.id.text_pOffLine);
			pAbsenteeism = itemView.findViewById(R.id.text_pAbsenteeism);
			pTransboundary = itemView.findViewById(R.id.text_pTransboundary);
		}

		void bindDomains(DomainItem item) {
			if (! TextUtils.isEmpty(item.getScantime())) {
				date.setText(String.format(TimeUtils.getStrTime(item.getScantime()).split(" ")[0]));
			}
			if (! TextUtils.isEmpty(item.getsStart())) {

				amQianDao.setText(String.format("上午：%s", item.getsStart()));
			}
			if (! TextUtils.isEmpty(item.getsEnd())) {
				amQianTui.setText(String.format("上午：%s", item.getsEnd()));
			}
			if (! TextUtils.isEmpty(item.getsZaoTui())) {
				aEarlyRetreat.setText(String.format("早退：%s分钟", item.getsZaoTui()));
			}
			if (! TextUtils.isEmpty(item.getsChiDao())) {
				aLate.setText(String.format("迟到：%s分钟", item.getsChiDao()));
			}
			if (! TextUtils.isEmpty(item.getsLiXian())) {
				aOffLine.setText(String.format("离线：%s分钟", item.getsLiXian()));
			}
			if (! TextUtils.isEmpty(item.getsKuangGong())) {
				aAbsenteeism.setText(String.format("旷工：%s分钟", item.getsKuangGong()));
			}
			if (! TextUtils.isEmpty(item.getsYueJie())) {
				aTransboundary.setText(String.format("越界：%s分钟", item.getsYueJie()));
			}
			if (! TextUtils.isEmpty(item.getxStart())) {

				pmQianDao.setText(String.format("下午：%s", item.getxStart()));
			}
			if (! TextUtils.isEmpty(item.getxEnd())) {
				pmQianTui.setText(String.format("下午：%s", item.getxEnd()));
			}
			if (! TextUtils.isEmpty(item.getxZaoTui())) {
				pEarlyRetreat.setText(String.format("早退：%s分钟", item.getxZaoTui()));
			}
			if (! TextUtils.isEmpty(item.getxChiDao())) {
				pLate.setText(String.format("迟到：%s分钟", item.getxChiDao()));
			}
			if (! TextUtils.isEmpty(item.getxLiXian())) {
				pOffLine.setText(String.format("离线：%s分钟", item.getxLiXian()));
			}
			if (! TextUtils.isEmpty(item.getxKuangGong())) {
				pAbsenteeism.setText(String.format("旷工：%s分钟", item.getxKuangGong()));
			}
			if (! TextUtils.isEmpty(item.getxYueJie())) {
				pTransboundary.setText(String.format("越界：%s分钟", item.getxYueJie()));
			}
		}
	}
}

