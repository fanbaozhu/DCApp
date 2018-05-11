package com.xunchijn.dcappv1.event.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.SettingItem;

import java.util.List;

public class ReportSettingAdapter extends RecyclerView.Adapter {
    private List<SettingItem> mList;
    private int mResourceId;

    public ReportSettingAdapter(List<SettingItem> list) {
        mList = list;
        mResourceId = R.layout.adapter_report_setting;
    }

    public ReportSettingAdapter(List<SettingItem> list, int resourceId) {
        mList = list;
        mResourceId = resourceId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mResourceId, parent, false);
        return new SettingView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        SettingItem item = mList.get(position);
        if (holder instanceof SettingView && item != null) {
            ((SettingView) holder).bindSettingView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SettingView extends RecyclerView.ViewHolder {
        private TextView viewTitle;
        private TextView viewSubtitle;

        SettingView(View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.text_item_title);
            viewSubtitle = itemView.findViewById(R.id.text_item_subtitle);
        }

        void bindSettingView(final SettingItem item) {
            viewTitle.setText(item.getTitle());
            viewSubtitle.setText(item.getSubtitle());
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
        void onItemClick(SettingItem item);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
