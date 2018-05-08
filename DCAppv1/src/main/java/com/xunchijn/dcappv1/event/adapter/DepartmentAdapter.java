package com.xunchijn.dcappv1.event.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.DepartmentEntity;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter {
    private List<DepartmentEntity> mList;
    private Context mContext;

    public DepartmentAdapter(List<DepartmentEntity> list) {
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
        DepartmentEntity entity = mList.get(position);
        if (entity != null && holder instanceof DepartmentView) {
            ((DepartmentView) holder).bindDepartments(entity);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class DepartmentView extends ViewHolder {
        private TextView mViewTitle;
        private TextView mViewSubtitle;
        private RecyclerView mViewSubDepartment;

        DepartmentView(View itemView) {
            super(itemView);
            mViewTitle = itemView.findViewById(R.id.text_item_title);
            mViewSubtitle = itemView.findViewById(R.id.text_item_subtitle);
            mViewSubDepartment = itemView.findViewById(R.id.recycler_view_sub_department);
        }

        void bindDepartments(final DepartmentEntity departmentEntity) {
            mViewTitle.setText(departmentEntity.getName());
            mViewSubtitle.setText(departmentEntity.getSubtitle());
            if (departmentEntity.getSubDepartment() == null) {
                return;
            }
            mViewSubDepartment.setLayoutManager(new GridLayoutManager(mContext, 3));
            SubDepartmentAdapter adapter = new SubDepartmentAdapter(departmentEntity.getSubDepartment());
            mViewSubDepartment.setAdapter(adapter);
            adapter.setItemClickListener(new SubDepartmentAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(DepartmentEntity entity) {
                    departmentEntity.setSubtitle(entity.getName());
                    mViewSubtitle.setText(entity.getName());
                }
            });
        }
    }
}
