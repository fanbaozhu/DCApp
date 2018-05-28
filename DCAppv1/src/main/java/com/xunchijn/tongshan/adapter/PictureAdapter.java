package com.xunchijn.tongshan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xunchijn.tongshan.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter {
    private List<String> mUrls;
    private int mMax;
    private boolean mShowAdd;
    private Context mContext;
    private RequestOptions options;

    public PictureAdapter(List<String> urls) {
        this(urls, urls == null ? 0 : urls.size());
    }

    public PictureAdapter(List<String> urls, int max) {
        this(urls, max, false);
    }

    public PictureAdapter(List<String> urls, int max, boolean showAdd) {
        mUrls = urls;
        mMax = max;
        mShowAdd = showAdd;
        options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_report_picture, parent, false);
        return new PictureView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PictureView) {
            if (position >= mUrls.size()) {
                ((PictureView) holder).bindAddPictureView();
                return;
            }
            String url = mUrls.get(position);
            if (!TextUtils.isEmpty(url)) {
                ((PictureView) holder).bindView(url);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mUrls == null) {
            return mShowAdd ? 1 : 0;
        }
        if (mMax <= 0) {
            return 0;
        }
        if (mUrls.size() >= mMax) {
            return mMax;
        }
        return mShowAdd ? mUrls.size() + 1 : mUrls.size();
    }

    private class PictureView extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        PictureView(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_report_picture);
        }

        void bindView(final String url) {
            if (url.startsWith("http")) {
                Glide.with(mContext).load(url).apply(options).into(mImageView);
            } else {
                Glide.with(mContext).load(String.format("file://%s", url)).apply(options).into(mImageView);
            }
            if (mItemClickListener == null) {
                return;
            }
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onPictureClick(url);
                }
            });
        }

        void bindAddPictureView() {
            Glide.with(mContext).load(R.mipmap.ic_picture_add).apply(new RequestOptions().centerInside()).into(mImageView);
            if (mItemClickListener == null) {
                return;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onAddPicture();
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onPictureClick(String url);

        void onAddPicture();
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
