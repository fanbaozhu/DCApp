package com.xunchijn.dcappv1.common.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.StatisticItem;
import com.xunchijn.dcappv1.event.adapter.ReportSettingAdapter;
import com.xunchijn.dcappv1.event.model.SettingItem;

import java.util.List;

/**
 * Author：ZHOUJIAWEI
 * Time:2018/5/9 0009   上午 10:53
 * Description:
 **/


public class StatisticAdapter extends RecyclerView.Adapter {
    private List<StatisticItem> mList;

    public StatisticAdapter(List<StatisticItem> list){
        mList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_statistic,parent,false);
        return new SettingView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position>=mList.size()){
            return;
        }
        StatisticItem item = mList.get(position);
        if (holder instanceof StatisticAdapter.SettingView && item != null) {
            ((StatisticAdapter.SettingView) holder).bindSettingView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SettingView extends RecyclerView.ViewHolder{
        private TextView viewStatus;
        private TextView viewTruckNumber;
        private TextView viewRFIDScanTime;
        private TextView viewAddress;

        SettingView(View itemView) {
            super(itemView);
            viewStatus = itemView.findViewById(R.id.tv_status);
            viewTruckNumber = itemView.findViewById(R.id.truck_number);
            viewRFIDScanTime = itemView.findViewById(R.id.rfid_scantime);
            viewAddress = itemView.findViewById(R.id.address);
        }
        void bindSettingView(final StatisticItem item){
            viewStatus.setText(item.getStatus());
            viewTruckNumber.setText(item.getTruckNumber());
            viewRFIDScanTime.setText(item.getRfidScanTime());
            viewAddress.setText(item.getAddress());
            if (mItemClickListener == null) {
                return;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(StatisticItem item);
    }

    private StatisticAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(StatisticAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
