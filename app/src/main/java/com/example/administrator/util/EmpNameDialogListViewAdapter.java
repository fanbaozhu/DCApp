package com.example.administrator.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.baidumap.R;
import com.example.administrator.models.Empidname;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class EmpNameDialogListViewAdapter extends RecyclerView.Adapter {
    private List<Empidname> list;

    public EmpNameDialogListViewAdapter(List<Empidname> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog_item, parent, false);
        return new EmpNameView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Empidname empidname = list.get(position);
        if (holder instanceof EmpNameView) {
            ((EmpNameView) holder).setDept(empidname);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (emponItemClickListener==null){
                        return;
                    }
                    emponItemClickListener.onItemClick(empidname);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class EmpNameView extends RecyclerView.ViewHolder {
        private TextView textView;

        EmpNameView(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        void setDept(Empidname emp) {
            textView.setText(emp.getEmpname());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Empidname empidname);
    }

    private OnItemClickListener emponItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.emponItemClickListener = onItemClickListener;
    }
}
