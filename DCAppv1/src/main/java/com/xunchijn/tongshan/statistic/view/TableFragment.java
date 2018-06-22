package com.xunchijn.tongshan.statistic.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.SettingAdapter;
import com.xunchijn.tongshan.base.BaseConfig;
import com.xunchijn.tongshan.common.module.SettingItem;
import com.xunchijn.tongshan.common.view.AboutUsActivity;
import com.xunchijn.tongshan.common.view.FeedbackActivity;
import com.xunchijn.tongshan.common.view.MessagesActivity;
import com.xunchijn.tongshan.common.view.ResetPassActivity;

public class TableFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SettingAdapter settingAdapter = new SettingAdapter(BaseConfig.getTable());
        recyclerView.setAdapter(settingAdapter);
        settingAdapter.setItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                DomainRecordsActivity.start(getContext(),item.getTitle());
            }
        });
    }
}
