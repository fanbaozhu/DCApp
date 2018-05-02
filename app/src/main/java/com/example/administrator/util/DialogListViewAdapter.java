package com.example.administrator.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.baidumap.R;
import com.example.administrator.models.Deptidname;
import com.example.administrator.models.Empidname;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class DialogListViewAdapter extends RecyclerView.Adapter {
    private List<Deptidname> list;


    public DialogListViewAdapter(List<Deptidname> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_item, parent, false);
        return new DeptView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Deptidname deptidname = list.get(position);
        if (holder instanceof DeptView) {
            ((DeptView) holder).setDept(deptidname);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener==null){
                        return;
                    }
                    onItemClickListener.onItemClick(deptidname);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class DeptView extends RecyclerView.ViewHolder {
        private TextView textView;

        DeptView(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        void setDept(Deptidname dept) {
            textView.setText(dept.getDept());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Deptidname deptidname);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
