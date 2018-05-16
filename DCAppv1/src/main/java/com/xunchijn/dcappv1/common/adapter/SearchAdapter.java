package com.xunchijn.dcappv1.common.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.SearchItem;
import com.xunchijn.dcappv1.statistic.StatisticAdapter;
import com.xunchijn.dcappv1.statistic.StatisticItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class SearchAdapter extends RecyclerView.Adapter {
    private List<SearchItem> mList;

    public SearchAdapter(List<SearchItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search_list, parent, false);
        return new SearchItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position >= mList.size()) {
            return;
        }
        SearchItem item = mList.get(position);
        if (holder instanceof SearchAdapter.SearchItemView && item != null) {
            ((SearchAdapter.SearchItemView) holder).bindSettingView(item);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
    private class SearchItemView extends RecyclerView.ViewHolder {
        private TextView viewStatus;
        private TextView viewUserName;
        private TextView viewUserDept;
        private TextView viewAddress;
        private TextView viewUserZone;

        SearchItemView(View itemView) {
            super(itemView);
            viewStatus = itemView.findViewById(R.id.text_status);
            viewUserName = itemView.findViewById(R.id.user_name);
            viewUserDept = itemView.findViewById(R.id.user_dept);
            viewAddress = itemView.findViewById(R.id.user_address);
            viewUserZone = itemView.findViewById(R.id.user_zone);
        }

        void bindSettingView(SearchItem item) {
            viewUserName.setText(item.getUserName());
            viewUserDept.setText(item.getUserDept());
            viewUserZone.setText(item.getUserZone());
            viewStatus.setText(item.getUserStatus());
            viewAddress.setText(item.getUserAddress());
        }
    }
}
