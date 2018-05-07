package com.xunchijn.administrator.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.xunchijn.administrator.baidumap.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class TrashReportListFragment extends Fragment implements TrashReportContrast.View {
    private TrashReportContrast.Presenter mPresenter;
    private ListView listView;

    public static TrashReportListFragment newInstance(String type) {
        TrashReportListFragment fragment = new TrashReportListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trash_reportlist_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.lvList);

        initData();
    }

    private void initData() {
        mPresenter = new TrashReportPresenter(TrashReportListFragment.this);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        String type = bundle.getString("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        switch (type) {
            case "day":
                mPresenter.getDayList();
                break;
            case "week":
                mPresenter.getWeekList();
                break;
            case "month":
                mPresenter.getMonthList();
                break;
        }
    }

    @Override
    public void showList(List list) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TrashReportContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
