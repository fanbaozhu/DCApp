package com.xunchijn.dcappv1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.SettingItem;

import java.util.List;

public class SelectedAdapter extends Adapter {
    private List<SettingItem> mItems;

    public SelectedAdapter(List<SettingItem> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_selected, parent, false);
        return new SelectedView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mItems.size()) {
            return;
        }
        SettingItem item = mItems.get(position);
        if (item != null && holder instanceof SelectedView) {
            ((SelectedView) holder).bindSelectedView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    private class SelectedView extends RecyclerView.ViewHolder {
        private TextView mViewTitle;
        private ImageView mViewDelete;
        private TextView mViewSubtitle;

        SelectedView(View itemView) {
            super(itemView);
            mViewTitle = itemView.findViewById(R.id.text_title);
            mViewSubtitle = itemView.findViewById(R.id.text_subtitle);
            mViewDelete = itemView.findViewById(R.id.image_delete);
        }

        void bindSelectedView(final SettingItem item) {
            if (!TextUtils.isEmpty(item.getTitle())) {
                mViewTitle.setText(item.getTitle());
            }
            if (!TextUtils.isEmpty(item.getSubtitle())) {
                mViewSubtitle.setText(item.getSubtitle());
            }
            if (mItemClickListener == null) {
                return;
            }
            mViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemDelete(item);
                }
            });
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

        void onItemDelete(SettingItem item);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
