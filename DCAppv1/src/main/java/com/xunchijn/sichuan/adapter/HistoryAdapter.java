package com.xunchijn.sichuan.adapter;

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
import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.event.model.EventItem;

import java.util.List;

import static com.xunchijn.sichuan.util.RetrofitProvider.BASE_URL;

public class HistoryAdapter extends RecyclerView.Adapter {
    private List<EventItem> mList;
    private Context mContext;

    public HistoryAdapter(List<EventItem> list) {
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
        EventItem eventItem = mList.get(position);
        if (eventItem != null && holder instanceof EventView) {
            ((EventView) holder).bindEvent(eventItem);
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

        void bindEvent(final EventItem eventItem) {
            if (eventItem.getEventStatus().equals("新上报")) {
                mViewStatus.setText("新上报");
                mViewStatus.setBackgroundResource(R.drawable.bg_gray_round_rect_8dp);
            } else {
                mViewStatus.setText("已处理");
                mViewStatus.setBackgroundResource(R.drawable.bg_green_round_rect_8dp);
            }
            String pictureName = eventItem.getEventPictureName();
            if (!TextUtils.isEmpty(pictureName)) {
                String[] urls = pictureName.split(",");
                Glide.with(mContext).load(String.format("%s/UploadImg/%s", BASE_URL, urls[0]))
                        .apply(new RequestOptions().centerCrop()).into(mViewPicture);
            }
            mViewReportTime.setText(eventItem.getReportTime());
            mViewReportLocation.setText(eventItem.getEventPosition());
            mViewReportDescribe.setText(eventItem.getEventDescribe());
            if (mOnItemClickListener == null) {
                return;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(eventItem);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(EventItem item);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
