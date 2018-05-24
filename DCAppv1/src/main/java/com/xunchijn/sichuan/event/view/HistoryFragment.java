package com.xunchijn.sichuan.event.view;

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

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.adapter.HistoryAdapter;
import com.xunchijn.sichuan.event.model.EventItem;
import com.xunchijn.sichuan.event.presenter.HistoryContract;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContract.View {
    private HistoryContract.Presenter mPresenter;
    private RecyclerView viewHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_history, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewHistory = view.findViewById(R.id.recycler_view_history);
        viewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mPresenter != null) {
            mPresenter.getHistory();
        }
    }

    @Override
    public void showHistory(List<EventItem> list) {
        HistoryAdapter historyAdapter = new HistoryAdapter(list);
        viewHistory.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EventItem item) {
                EventInfoActivity.start(getContext(), item.getEventId());
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
