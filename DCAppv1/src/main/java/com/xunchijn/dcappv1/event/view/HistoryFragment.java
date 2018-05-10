package com.xunchijn.dcappv1.event.view;

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
import com.xunchijn.dcappv1.event.adapter.HistoryAdapter;
import com.xunchijn.dcappv1.event.contract.HistoryContract;
import com.xunchijn.dcappv1.event.model.EventEntity;
import com.xunchijn.dcappv1.event.presenter.HistoryPresenter;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContract.View {
    private HistoryContract.Presenter mPresenter;
    private RecyclerView viewHistory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HistoryPresenter(this);
    }

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
    public void showHistory(List<EventEntity> list) {
        HistoryAdapter historyAdapter = new HistoryAdapter(list);
        viewHistory.setAdapter(historyAdapter);
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
