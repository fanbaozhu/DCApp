package com.xunchijn.tongshan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.event.model.DetailsItem;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter {
    private List<DetailsItem> mList;
    private Context mContext;

    public DetailsAdapter(List<DetailsItem> list) {
        mList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_details, parent, false);
        return new DetailsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        DetailsItem detailsItem = mList.get(position);
        if (detailsItem != null && holder instanceof DetailsView) {
            ((DetailsView) holder).bindDetails(detailsItem);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class DetailsView extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDept;
        private TextView tvZone;
        private TextView tvStatus;
        private TextView tvAddress;
        DetailsView(View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDept = itemView.findViewById(R.id.tvDept);
            tvZone = itemView.findViewById(R.id.tvZone);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
        void bindDetails(final DetailsItem detailsItem) {
            tvName.setText(detailsItem.getCarName());
            tvDept.setText(detailsItem.getCarDept());
            tvZone.setText(detailsItem.getCarZone());
            tvStatus.setText(detailsItem.getCarStatus());
            tvAddress.setText(detailsItem.getCarAddress());

        }
    }
}
