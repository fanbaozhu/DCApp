package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.adapter.SelectAdapter;
import com.xunchijn.dcappv1.adapter.SelectedAdapter;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.common.module.SettingItem;
import com.xunchijn.dcappv1.map.presenter.SelectContrast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectFragment extends Fragment implements SelectContrast.View {
    private static final String TYPE = "mSelectType";
    private SelectContrast.Presenter mPresenter;
    private SelectedAdapter mSelectedAdapter;
    private List<SettingItem> mSelectedItems;
    private RecyclerView mViewOptions;
    private TextView mViewTitle;
    private String mSelectType;
    private String mTitle;

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
        View view = inflater.inflate(R.layout.fragment_select_options, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewTitle = view.findViewById(R.id.text_title);

        mViewOptions = view.findViewById(R.id.recycler_view_options);
        mViewOptions.setLayoutManager(new GridLayoutManager(getContext(), 3));

        RecyclerView viewSelected = view.findViewById(R.id.recycler_view_selected);
        viewSelected.setLayoutManager(new LinearLayoutManager(getContext()));
        mSelectedItems = new ArrayList<>();
        mSelectedAdapter = new SelectedAdapter(mSelectedItems);
        viewSelected.setAdapter(mSelectedAdapter);
        mSelectedAdapter.setItemClickListener(new SelectedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(SettingItem item) {
                parseDelete(item);
                boolean delete = false;
                Iterator<SettingItem> itemIterator = mSelectedItems.iterator();
                while (itemIterator.hasNext()) {
                    SettingItem item1 = itemIterator.next();
                    if (item.equals(item1)) {
                        delete = true;
                    }
                    if (delete) {
                        itemIterator.remove();
                    }
                }
                mSelectedAdapter.notifyDataSetChanged();
            }
        });

        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        mSelectType = bundle.getString(TYPE);
        mPresenter.getDepartment();
    }

    private void parseOptionClick(SelectItem item) {
        SettingItem settingItem = new SettingItem(1, mTitle, item.getName());
        settingItem.setId(item.getId());
        mSelectedItems.add(settingItem);
        mSelectedAdapter.notifyDataSetChanged();
        if (mTitle.equals("部门")) {
            if (mSelectType.equals("人员")) {
                mPresenter.getSubDepartment(item.getId());
            } else {
                mPresenter.getCars(item.getId());
            }
            return;
        }
        if (mTitle.equals("子部门")) {
            mPresenter.getUsers(item.getId());
            return;
        }
        showOptions("", null);
    }

    private void parseDelete(SettingItem item) {
        if (item.getTitle().equals("部门")) {
            mPresenter.getDepartment();
            return;
        }
        int index = mSelectedItems.indexOf(item);
        SettingItem settingItem = mSelectedItems.get(index - 1);
        if (item.getTitle().equals("子部门")) {
            mPresenter.getSubDepartment(settingItem.getId());
            return;
        }
        if (item.getTitle().equals("人员")) {
            mPresenter.getUsers(settingItem.getId());
            return;
        }
        if (item.getTitle().equals("车辆")) {
            mPresenter.getCars(settingItem.getId());
        }
    }

    @Override
    public void showOptions(String title, List<? extends SelectItem> list) {
        mTitle = title;
        mViewTitle.setText(TextUtils.isEmpty(title) ? "" : String.format("请选择%s", mTitle));

        SelectAdapter mSelectAdapter = new SelectAdapter(list, R.layout.adapter_select_short);
        mViewOptions.setAdapter(mSelectAdapter);
        mSelectAdapter.setItemClickListener(new SelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {
                parseOptionClick(item);
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        mSelectedItems.clear();
        mSelectedAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SelectContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    public List<SettingItem> getSelectedItems() {
        return mSelectedItems;
    }
}
