package com.xunchijn.dcappv1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.TitleItem;

import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter {
    private List<TitleItem> mList;
    private Context mContext;

    public TitleAdapter(List<TitleItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_title, parent, false);
        return new TitleView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        TitleItem item = mList.get(position);
        if (item != null && holder instanceof TitleView) {
            ((TitleView) holder).bindTitleView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class TitleView extends RecyclerView.ViewHolder {
        private ImageView mViewPicture;
        private TextView mViewTitle;
        private RecyclerView mViewSubTitles;

        TitleView(View itemView) {
            super(itemView);
            mViewPicture = itemView.findViewById(R.id.image_view_title);
            mViewTitle = itemView.findViewById(R.id.text_view_title);
            mViewSubTitles = itemView.findViewById(R.id.recycler_view_title);
            mViewSubTitles.setLayoutManager(new GridLayoutManager(mContext, 3));
        }

        void bindTitleView(TitleItem item) {
            if (!TextUtils.isEmpty(item.getName())) {
                mViewTitle.setText(item.getName());
            }
            if (item.getResourceId() != 0) {
                Glide.with(mContext).load(item.getResourceId()).into(mViewPicture);
            }
            if (item.getSubTitleItems() != null) {
                SubTitleAdapter adapter = new SubTitleAdapter(item.getSubTitleItems());
                mViewSubTitles.setAdapter(adapter);
                adapter.setItemClickListener(mItemClickListener);
            }
        }
    }

    private SubTitleAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(SubTitleAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
