package com.xunchijn.dcappv1.event.adapter;

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
import com.bumptech.glide.request.RequestOptions;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.model.EventEntity;

import java.util.List;

import static com.xunchijn.dcappv1.util.RetrofitProvider.BASE_URL;

public class HistoryAdapter extends RecyclerView.Adapter {
    private List<EventEntity> mList;
    private Context mContext;

    public HistoryAdapter(List<EventEntity> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_event_history, parent, false);
        return new EventView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        EventEntity eventEntity = mList.get(position);
        if (eventEntity != null && holder instanceof EventView) {
            ((EventView) holder).bindEvent(eventEntity);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class EventView extends RecyclerView.ViewHolder {
        private ImageView mViewPicture;
        private TextView mViewReportTime;
        private TextView mViewReportLocation;
        private TextView mViewReportDescribe;
        private TextView mViewStatus;

        EventView(View itemView) {
            super(itemView);
            mViewPicture = itemView.findViewById(R.id.image_event_picture);
            mViewReportTime = itemView.findViewById(R.id.text_report_time);
            mViewReportLocation = itemView.findViewById(R.id.text_report_location);
            mViewReportDescribe = itemView.findViewById(R.id.text_report_describe);
            mViewStatus = itemView.findViewById(R.id.text_status);
        }

        void bindEvent(EventEntity eventEntity) {
            if (eventEntity.getEventStatus().equals("新上报")) {
                mViewStatus.setText("新上报");
                mViewStatus.setBackgroundResource(R.drawable.bg_gray_round_rect_8dp);
            } else {
                mViewStatus.setText("已处理");
                mViewStatus.setBackgroundResource(R.drawable.bg_green_round_rect_8dp);
            }
            String pictureName = eventEntity.getEventPictureName();
            if (!TextUtils.isEmpty(pictureName)) {
                String[] urls = pictureName.split(",");
                Glide.with(mContext).load(String.format("%s/UploadImg/%s", BASE_URL, urls[0]))
                        .apply(new RequestOptions().centerCrop()).into(mViewPicture);
            }
            mViewReportTime.setText(eventEntity.getReportTime());
            mViewReportLocation.setText(eventEntity.getEventPosition());
            mViewReportDescribe.setText(eventEntity.getEventDescribe());
        }
    }
}
