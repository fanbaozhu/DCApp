package com.xunchijn.dcappv1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.MessageItem;

import java.util.List;

public class MessagesAdapter extends Adapter {
    private List<MessageItem> mList;

    public MessagesAdapter(List<MessageItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_messages, parent, false);
        return new MessageView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        MessageItem item = mList.get(position);
        if (item != null && holder instanceof MessageView) {
            ((MessageView) holder).bindMessage(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class MessageView extends RecyclerView.ViewHolder {
        private TextView mViewTitle;
        private TextView mViewContent;

        MessageView(View itemView) {
            super(itemView);
            mViewTitle = itemView.findViewById(R.id.text_message_title);
            mViewContent = itemView.findViewById(R.id.text_message_content);
        }

        void bindMessage(MessageItem item) {
            if (!TextUtils.isEmpty(item.getTitle())) {
                mViewTitle.setText(item.getTitle());
            }
            if (!TextUtils.isEmpty(item.getContent())) {
                mViewContent.setText(item.getContent());
            }
        }
    }
}
