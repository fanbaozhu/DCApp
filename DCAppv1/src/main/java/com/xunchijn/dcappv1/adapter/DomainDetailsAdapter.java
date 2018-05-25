package com.xunchijn.dcappv1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.statistic.model.DomainItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class DomainDetailsAdapter extends RecyclerView.Adapter {
    private List<DomainItem> mList;
    public DomainDetailsAdapter(List<DomainItem> list) {
        mList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_statistic_domain_details, parent, false);
        return new DomainDetailsAdapter.DomainDetailView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DomainItem item = mList.get(position);
        if (item != null && holder instanceof DomainDetailsAdapter.DomainDetailView) {
            ((DomainDetailsAdapter.DomainDetailView) holder).bindDomains(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond*1000);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    private class DomainDetailView extends RecyclerView.ViewHolder {
        private TextView mViewName;
        private TextView mViewDept;
        private TextView mViewTimes;

        DomainDetailView(View itemView) {
            super(itemView);
            mViewName = itemView.findViewById(R.id.text_car_name);
            mViewDept = itemView.findViewById(R.id.text_car_dept);
            mViewTimes = itemView.findViewById(R.id.text_car_times);
        }

        void bindDomains(DomainItem item) {
            if (!TextUtils.isEmpty(item.getStartTime())) {
                String start = item.getStartTime();
                String startTime="";
                long time = Long.valueOf(start);
                startTime = DomainDetailsAdapter.getDateToString(time,"yyyy-MM-dd HH:mm:ss");
                mViewName.setText(String.format("进区域：%s", startTime));
            }
            if (!TextUtils.isEmpty(item.getEndTime())) {
                String end = item.getEndTime();
                String endTime="";
                long time = Long.valueOf(end);
                endTime = DomainDetailsAdapter.getDateToString(time,"yyyy-MM-dd HH:mm:ss");
                mViewDept.setText(String.format("出区域：%s", endTime));
            }
            if (!TextUtils.isEmpty(item.getTimeDifference())) {
                mViewTimes.setText(String.format("时长：%s分钟", item.getTimeDifference()));
            }
        }
    }
}
