package com.xunchijn.dcappv1.common.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.StatisticItem;

import java.util.List;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 10:53
 * Description:
 **/


public class StatisticAdapter extends RecyclerView.Adapter {
    private List<StatisticItem> mList;

    public StatisticAdapter(List<StatisticItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_statistic, parent, false);
        return new StatisticItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        StatisticItem item = mList.get(position);
        if (holder instanceof StatisticItemView && item != null) {
            ((StatisticItemView) holder).bindSettingView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class StatisticItemView extends RecyclerView.ViewHolder {
        private TextView viewStatus;
        private TextView viewTruckNumber;
        private TextView viewRFIDScanTime;
        private TextView viewAddress;

        StatisticItemView(View itemView) {
            super(itemView);
            viewStatus = itemView.findViewById(R.id.text_status);
            viewTruckNumber = itemView.findViewById(R.id.truck_number);
            viewRFIDScanTime = itemView.findViewById(R.id.text_scan_time);
            viewAddress = itemView.findViewById(R.id.text_address);
        }

        void bindSettingView(StatisticItem item) {
            viewTruckNumber.setText(item.getTruckNumber());
            viewStatus.setBackgroundResource(item.isOffline() ?
                    R.drawable.bg_gray_round_rect_8dp : R.drawable.bg_green_round_rect_8dp);
            viewRFIDScanTime.setText(item.getRFIDScanTime());
            viewAddress.setText(item.getAddress());
        }
    }
}
