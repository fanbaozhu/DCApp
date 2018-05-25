package com.xunchijn.dcappv1.statistic.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.adapter.DomainDetailsAdapter;
import com.xunchijn.dcappv1.statistic.model.DomainItem;
import com.xunchijn.dcappv1.statistic.presenter.DomainsContrast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class DomainDetailsFragment extends Fragment implements DomainsContrast.View {
    private DomainsContrast.Presenter mPresenter;
    private List<DomainItem> mList;
    private DomainDetailsAdapter mDomainsAdapter;

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
        mDomainsAdapter = new DomainDetailsAdapter(mList);
        recyclerView.setAdapter(mDomainsAdapter);
        initData();
    }

    private void initData() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        String gps_simId = intent.getStringExtra("gps_simId");
        String startTime = intent.getStringExtra("startTime");
        if (TextUtils.isEmpty(gps_simId) || TextUtils.isEmpty(startTime)) {
            return;
        }
        if (mPresenter == null) {
            return;
        }
        mPresenter.getCarDomainDetails(startTime, gps_simId);
    }

    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DomainsContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    //分页查询时使用 后期会加上判断 上拉加载 下拉刷新 如果是下拉 那么先.clear清除 然后走addAll重新填充 如果是上拉 那么直接走addAll
    public void showCarRecords(List<DomainItem> list) {
        mList.clear();
        //继续加载
        mList.addAll(list);
        //更新列表
        mDomainsAdapter.notifyDataSetChanged();
    }

}
