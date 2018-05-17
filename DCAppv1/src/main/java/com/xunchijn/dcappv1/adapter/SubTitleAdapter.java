package com.xunchijn.dcappv1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.SubTitleItem;

import java.util.List;

public class SubTitleAdapter extends RecyclerView.Adapter {
    private List<SubTitleItem> mList;
    private Context mContext;

    public SubTitleAdapter(List<SubTitleItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_subtitle_v, parent, false);
        return new SubTitleView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        SubTitleItem item = mList.get(position);
        if (item != null && holder instanceof SubTitleView) {
            ((SubTitleView) holder).bindSubTitle(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class SubTitleView extends RecyclerView.ViewHolder {
        private ImageView mViewPicture;
        private TextView mViewTitle;

        SubTitleView(View itemView) {
            super(itemView);
            mViewPicture = itemView.findViewById(R.id.image_view);
            mViewTitle = itemView.findViewById(R.id.text_view);
        }

        void bindSubTitle(final SubTitleItem item) {
            if (!TextUtils.isEmpty(item.getName())) {
                mViewTitle.setText(item.getName());
            }
            if (item.getResourceId() != 0) {
                Glide.with(mContext).load(item.getResourceId()).into(mViewPicture);
            }
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
        void onItemClick(SubTitleItem item);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
