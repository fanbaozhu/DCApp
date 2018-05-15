package com.xunchijn.dcappv1.event.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;

import java.util.List;

/*
 * 嵌套选择列表
 * */

public class NestingSelectAdapter extends RecyclerView.Adapter {
    private List<NestingItem> mList;
    private Context mContext;

    public NestingSelectAdapter(List<NestingItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_report_dapartment, parent, false);
        return new DepartmentView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        NestingItem item = mList.get(position);
        if (item != null && holder instanceof DepartmentView) {
            ((DepartmentView) holder).bindDepartments(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class DepartmentView extends ViewHolder {
        private TextView mViewTitle;
        private Button mButton;
        private RecyclerView mViewSubDepartment;

        DepartmentView(View itemView) {
            super(itemView);
            mViewTitle = itemView.findViewById(R.id.text_item_title);
            mButton = itemView.findViewById(R.id.btn_reset);
            mViewSubDepartment = itemView.findViewById(R.id.recycler_view_sub_department);
        }

        void bindDepartments(final NestingItem nestingItem) {
            mViewTitle.setText(nestingItem.getName());
            mButton.setText(nestingItem.getSubtitle());
            if (mItemClickListener != null) {
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onSubTitleClick(nestingItem);
                    }
                });
            }

            if (nestingItem.getItems() == null) {
                return;
            }
            mViewSubDepartment.setLayoutManager(new GridLayoutManager(mContext, 3));
            SelectAdapter adapter = new SelectAdapter(nestingItem.getItems(), R.layout.adapter_select_item);
            mViewSubDepartment.setAdapter(adapter);
            adapter.setItemClickListener(new SelectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(SelectItem item) {
                    nestingItem.setName(item.getName());
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(item);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SelectItem item);

        void onSubTitleClick(NestingItem item);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
