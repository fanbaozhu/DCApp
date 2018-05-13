package com.xunchijn.dcappv1.event.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.SelectItem;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter {
    private List<? extends SelectItem> mList;
    private int mLayoutId;

    public SelectAdapter(List<? extends SelectItem> list, int layoutId) {
        mList = list;
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new SelectView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        SelectItem item = mList.get(position);
        if (item != null && holder instanceof SelectView) {
            ((SelectView) holder).bindSubDepartment(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SelectView extends RecyclerView.ViewHolder {
        private TextView mTextView;

        SelectView(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }

        void bindSubDepartment(final SelectItem item) {
            mTextView.setText(item.getName());
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
        void onItemClick(SelectItem item);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
