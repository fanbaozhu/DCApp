package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.adapter.NestingSelectAdapter;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.map.contract.SelectContrast;
import com.xunchijn.dcappv1.map.model.Car;
import com.xunchijn.dcappv1.map.model.User;

import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends Fragment implements SelectContrast.View {
    private SelectContrast.Presenter mPresenter;
    private static final String TYPE = "mSelectType";
    private NestingSelectAdapter mSelectAdapter;
    private List<NestingItem> mList;
    private String mSelectType;
    private String mSubDepartmentId;
    private String mDepartment;
    private String mSubDepartment;

    public static SelectFragment newInstance(Context context, String selectType) {
        SelectFragment selectFragment = new SelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, selectType);
        selectFragment.setArguments(bundle);
        return selectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_select);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mSelectAdapter = new NestingSelectAdapter(mList);
        mSelectAdapter.setItemClickListener(new NestingSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {

            }

            @Override
            public void onSubTitleClick(NestingItem item) {

            }
        });
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        mPresenter.getDepartment();
        mSelectType = bundle.getString(TYPE);
    }


    @Override
    public void showDepartment(List<SelectItem> list) {
        mList.clear();
        NestingItem item = new NestingItem("0", "选择部门", "重置");
        item.setItems(list);
        mList.add(item);
        mSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSubDepartment(List<SelectItem> list) {
        mList.clear();
        NestingItem item = new NestingItem("1", String.format("%s-选择子部门", mDepartment), "重置");
        item.setItems(list);
        mList.add(item);
        mSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUsers(ArrayList<User> list) {
        mList.clear();
        NestingItem item = new NestingItem("2", String.format("%s-%s-选择人员", mDepartment, mSubDepartment), "重置");
        list.add(0, new User("0", "全部人员"));
        item.setItems(list);
        mList.add(item);
        mSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCars(ArrayList<Car> list) {
        mList.clear();
        NestingItem item = new NestingItem("2", String.format("%s-%s-选择车辆", mDepartment, mSubDepartment), "重置");
        list.add(0, new Car("0", "全部车辆"));
        item.setItems(list);
        mList.add(item);
        mSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SelectContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
