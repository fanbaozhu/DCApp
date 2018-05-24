package com.xunchijn.dcappv1.statistic.view;

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
import com.xunchijn.dcappv1.adapter.DomainsAdapter;
import com.xunchijn.dcappv1.statistic.model.DomainItem;
import com.xunchijn.dcappv1.statistic.presenter.DomainsContrast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomainRecordsFragment extends Fragment implements DomainsContrast.View {
    private DomainsContrast.Presenter mPresenter;
    private List<DomainItem> mList;
    private DomainsAdapter mDomainsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mDomainsAdapter = new DomainsAdapter(mList);
        recyclerView.setAdapter(mDomainsAdapter);
        initData();
    }

    private void initData() {

        String time = String.valueOf(new Date().getTime());
        if (time.length() == 13) {
            time = time.substring(0, 10);
        }
        if (mPresenter == null) {
            return;
        }
        mPresenter.getCarRecords(time);
    }

    @Override
    public void showCarRecords(List<DomainItem> list) {
        mList.clear();
        mList.addAll(list);
        mDomainsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DomainsContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
