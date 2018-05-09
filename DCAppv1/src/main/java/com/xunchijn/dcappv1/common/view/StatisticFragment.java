package com.xunchijn.dcappv1.common.view;

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
import com.xunchijn.dcappv1.common.adapter.StatisticAdapter;
import com.xunchijn.dcappv1.common.contract.StatisticContrast;
import com.xunchijn.dcappv1.common.module.StatisticItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticFragment extends Fragment implements StatisticContrast.View {
    private StatisticContrast.Presenter mPresenter;
    private RecyclerView mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mView = view.findViewById(R.id.recycler_view_statistic);
    }

    //处理成功之后的逻辑
    public void showStatistics(List<StatisticItem> list) {
        //设置布局的排列方式
        mView.setLayoutManager(new LinearLayoutManager(getContext()));
        //填充数据
        StatisticAdapter adapter = new StatisticAdapter(list);
        mView.setAdapter(adapter);
        //设计点击事件
//        adapter.setItemClickListener(new StatisticAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(StatisticItem item) {
//
//            }
//        });
    }

    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(StatisticContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
