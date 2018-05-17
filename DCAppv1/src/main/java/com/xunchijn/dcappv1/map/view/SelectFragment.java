package com.xunchijn.dcappv1.map.view;

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
import com.xunchijn.dcappv1.event.adapter.NestingSelectAdapter;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.map.contract.SelectContrast;
import com.xunchijn.dcappv1.map.model.Car;
import com.xunchijn.dcappv1.map.model.User;

import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends Fragment implements SelectContrast.View {
    private List<SelectItem> mSelectItems = new ArrayList<>();
    private static final String TYPE = "mSelectType";
    private NestingSelectAdapter mSelectAdapter;
    private SelectContrast.Presenter mPresenter;
    private List<NestingItem> mList;
    private String mSubDepartment;
    private String mSelectType;
    private String mDepartment;
    private boolean mMultiSelection;

    public static SelectFragment newInstance(String selectType) {
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
        mList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_select);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSelectAdapter = new NestingSelectAdapter(mList);
        mSelectAdapter.setMultiSelection(mMultiSelection);
        recyclerView.setAdapter(mSelectAdapter);
        mSelectAdapter.setItemClickListener(new NestingSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {
                selectUsers(item);
            }

            @Override
            public void onSubTitleClick(NestingItem item) {

            }
        });
        initData();
    }

    private void selectUsers(SelectItem item) {
        if (mSelectType.equals("车辆")) {
            if (item.getId().length() == 6) {
                mDepartment = item.getName();
                //根据乡镇Id，直接获取车辆信息
                mPresenter.getCars(item.getId());
                return;
            }
            if (!mMultiSelection) {
                mSelectItems.clear();
                mSelectItems.add(item);
                return;
            }
            if (mSelectItems.contains(item)) {
                mSelectItems.remove(item);
            } else {
                mSelectItems.add(item);
            }
        } else {
            if (item.getId().length() == 6) {
                mDepartment = item.getName();
                //根据部门Id，获取子部门
                mPresenter.getSubDepartment(item.getId());
                return;
            }
            if (item.getId().length() == 9) {
                mSubDepartment = item.getName();
                //点击子部门选项，获取用户/车辆
                mPresenter.getUsers(item.getId());
                return;
            }
            if (!mMultiSelection) {
                mSelectItems.clear();
                mSelectItems.add(item);
                return;
            }
            if (mSelectItems.contains(item)) {
                mSelectItems.remove(item);
            } else {
                mSelectItems.add(item);
            }
        }
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        mSelectType = bundle.getString(TYPE);
        mPresenter.getDepartment();
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
        item.setItems(list);
        mList.add(item);
        mSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCars(ArrayList<Car> list) {
        mList.clear();
        NestingItem item = new NestingItem("2", String.format("%s-选择车辆", mDepartment), "重置");
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

    public List<SelectItem> getSelectItems() {
        return mSelectItems;
    }

    public void setMultiSelection(boolean multiSelection) {
        mMultiSelection = multiSelection;
    }
}
