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

import java.util.List;

public class DomainsAdapter extends RecyclerView.Adapter {
    private List<DomainItem> mList;

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

    private class DomainView extends RecyclerView.ViewHolder {
        private TextView mViewName;
        private TextView mViewType;
        private TextView mViewDept;
        private TextView mViewCount;
        private TextView mViewTimes;

        DomainView(View itemView) {
            super(itemView);
            mViewName = itemView.findViewById(R.id.text_car_name);
            mViewType = itemView.findViewById(R.id.text_car_type);
            mViewDept = itemView.findViewById(R.id.text_car_dept);
            mViewCount = itemView.findViewById(R.id.text_car_count);
            mViewTimes = itemView.findViewById(R.id.text_car_times);
        }

        void bindDomains(DomainItem item) {
            if (!TextUtils.isEmpty(item.getCarName())) {
                mViewName.setText(String.format("车牌号：%s", item.getCarName()));
            }
            if (!TextUtils.isEmpty(item.getCarName())) {
                mViewType.setText(String.format("车辆类型：%s", item.getCarType()));
            }
            if (!TextUtils.isEmpty(item.getCarName())) {
                mViewDept.setText(String.format("所在部门：%s", item.getCarDept()));
            }
            if (!TextUtils.isEmpty(item.getCarName())) {
                mViewCount.setText(String.format("运行：%s次", item.getFrequency()));
            }
            if (!TextUtils.isEmpty(item.getCarName())) {
                mViewTimes.setText(String.format("累计时长：%s分钟", item.getTimeDifference()));
            }
        }
    }
}
