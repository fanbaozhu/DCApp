package com.xunchijn.dcappv1.event.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.DepartmentEntity;

import java.util.List;

public class SubDepartmentAdapter extends RecyclerView.Adapter {
    private List<DepartmentEntity> mList;
    private Context mContext;
    private DepartmentEntity mLastSelected;

    public SubDepartmentAdapter(List<DepartmentEntity> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_report_sub_department, parent, false);
        return new SubDepartmentView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        DepartmentEntity entity = mList.get(position);
        if (entity != null && holder instanceof SubDepartmentView) {
            ((SubDepartmentView) holder).bindSubDepartment(entity);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SubDepartmentView extends RecyclerView.ViewHolder {
        private TextView mTextView;

        SubDepartmentView(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);

        }

        void bindSubDepartment(final DepartmentEntity entity) {
            mTextView.setText(entity.getName());
            if (entity.isSelected()) {
                mTextView.setBackgroundResource(R.drawable.bg_blue_round_line_4dp);
                mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlue));
            } else {
                mTextView.setBackgroundResource(R.drawable.bg_gray_round_line_4dp);
                mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray));
            }
            if (mItemClickListener == null) {
                return;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    entity.setSelected(true);
                    if (mLastSelected != null && mLastSelected != entity) {
                        mLastSelected.setSelected(false);
                    }
                    mLastSelected = entity;
                    notifyDataSetChanged();
                    mItemClickListener.onItemClick(entity);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DepartmentEntity entity);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
