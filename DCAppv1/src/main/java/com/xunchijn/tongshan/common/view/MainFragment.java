package com.xunchijn.tongshan.common.view;

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
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.SubTitleAdapter;
import com.xunchijn.tongshan.adapter.TitleAdapter;
import com.xunchijn.tongshan.base.BaseConfig;
import com.xunchijn.tongshan.common.module.SubTitleItem;
import com.xunchijn.tongshan.event.view.EventHistoryActivity;
import com.xunchijn.tongshan.event.view.EventReportActivity;
import com.xunchijn.tongshan.map.view.SelectCarsActivity;
import com.xunchijn.tongshan.map.view.SelectUsersActivity;
import com.xunchijn.tongshan.statistic.view.TableActivity;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TitleAdapter adapter = new TitleAdapter(BaseConfig.getMainTitles());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new SubTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SubTitleItem item) {
                String title = item.getName().substring(2, 4);
                parseClick(item.getId(), title);
            }
        });
    }

    private void parseClick(int witch, String title) {
        switch (witch) {
            case 1:
                SelectCarsActivity.newInstance(getContext(), title);
                break;
            case 2:
            case 3:
                SelectCarsActivity.newInstance(getContext(), title);
                break;
            case 4:
                Toast.makeText(getContext(), "暂未开通该功能", Toast.LENGTH_SHORT).show();
                break;
            case 5:
            case 6:
                SelectUsersActivity.newInstance(getContext(), title);
                break;
            case 7:
                startActivity(new Intent(getContext(), TableActivity.class));
                break;
            case 8:
                EventHistoryActivity.start(getContext(), true);
                break;
            case 9:
                EventReportActivity.start(getContext(), true);
                break;
            case 10:
                EventHistoryActivity.start(getContext(), false);
                break;
            case 11:
                EventReportActivity.start(getContext(), false);
                break;

        }
    }
}
