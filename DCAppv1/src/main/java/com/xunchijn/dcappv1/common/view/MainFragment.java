package com.xunchijn.dcappv1.common.view;

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

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.BaseConfig;
import com.xunchijn.dcappv1.adapter.SubTitleAdapter;
import com.xunchijn.dcappv1.adapter.TitleAdapter;
import com.xunchijn.dcappv1.common.module.SubTitleItem;
import com.xunchijn.dcappv1.event.view.HistoryActivity;
import com.xunchijn.dcappv1.event.view.ReportActivity;
import com.xunchijn.dcappv1.map.view.SelectCarsActivity;
import com.xunchijn.dcappv1.map.view.SelectUsersActivity;
import com.xunchijn.dcappv1.statistic.view.StatisticActivity;

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
                Toast.makeText(getContext(), "暂未开通该功能", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getContext(), StatisticActivity.class));
                break;
            case 8:
                startActivity(new Intent(getContext(), HistoryActivity.class));
                break;
            case 9:
                startActivity(new Intent(getContext(), ReportActivity.class));
                break;
        }
    }
}
