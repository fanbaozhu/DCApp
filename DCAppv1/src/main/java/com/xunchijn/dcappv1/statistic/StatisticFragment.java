package com.xunchijn.dcappv1.statistic;

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
import com.xunchijn.dcappv1.base.TitleFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class StatisticFragment extends Fragment implements StatisticContrast.View {
    private StatisticContrast.Presenter mPresenter;
    private RecyclerView mViewStatistics;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new StatisticPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.getStatistic();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewStatistics = view.findViewById(R.id.recycler_view_statistic);

        TitleFragment titleFragment = TitleFragment.newInstance("统计报表", true, false);
        getFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment).commit();
    }

    //处理成功之后的逻辑
    public void showStatistics(List<StatisticItem> list) {
        //设置布局的排列方式
        mViewStatistics.setLayoutManager(new LinearLayoutManager(getContext()));
        //填充数据
        StatisticAdapter adapter = new StatisticAdapter(list);
        mViewStatistics.setAdapter(adapter);
    }

    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(StatisticContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
