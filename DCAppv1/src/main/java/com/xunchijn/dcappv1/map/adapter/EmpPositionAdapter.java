package com.xunchijn.dcappv1.map.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.map.model.EmpItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class EmpPositionAdapter extends RecyclerView.Adapter {
    private List<EmpItem> mList;
    private static String EMP_POINT="point";

    public EmpPositionAdapter(List<EmpItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_emp_position, parent, false);
        return new EmpPositionAdapter.SettingView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        EmpItem item = mList.get(position);
        if (holder instanceof EmpPositionAdapter.SettingView && item != null) {
            ((EmpPositionAdapter.SettingView) holder).bindSettingView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SettingView extends RecyclerView.ViewHolder {
        private TextView viewStatus;
        private TextView viewEmpName;
        private TextView viewEmpDept;
        private TextView viewGPSScanTime;
        private TextView viewEmpZoon;
        private TextView viewAddress;
        String empPoint;


        SettingView(View itemView) {
            super(itemView);
            viewStatus = itemView.findViewById(R.id.tv_status);
            viewEmpName = itemView.findViewById(R.id.emp_name);
            viewEmpDept = itemView.findViewById(R.id.emp_dept);
            viewAddress = itemView.findViewById(R.id.address);
            viewGPSScanTime = itemView.findViewById(R.id.gps_scantime);
            viewEmpZoon = itemView.findViewById(R.id.emp_zoon);
        }

        void bindSettingView(final EmpItem item) {
            viewStatus.setText(item.getEmpStatus());
            viewEmpName.setText(item.getEmpName());
            viewEmpDept.setText(item.getEmpDept());
            viewAddress.setText(item.getEmpAddress());
            viewGPSScanTime.setText(item.getEmpScanTime());
            viewEmpZoon.setText(item.getEmpZoon());
            empPoint = item.getEmpPoint();
            if (mItemClickListener == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("EMP_POINT",empPoint);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(EmpItem item);
    }

    private EmpPositionAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(EmpPositionAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
