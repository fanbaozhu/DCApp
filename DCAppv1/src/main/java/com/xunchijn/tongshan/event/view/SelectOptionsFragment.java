package com.xunchijn.tongshan.event.view;

import android.app.Activity;
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

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.SelectAdapter;
import com.xunchijn.tongshan.adapter.SelectedAdapter;
import com.xunchijn.tongshan.common.module.SettingItem;
import com.xunchijn.tongshan.event.model.SelectItem;
import com.xunchijn.tongshan.event.presenter.SelectOptionsContrast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectOptionsFragment extends Fragment implements SelectOptionsContrast.View {
    private SelectAdapter.OnItemClickListener mItemClickListener;
    private SelectOptionsContrast.Presenter mPresenter;
    private SelectedAdapter mSelectedAdapter;
    private ArrayList<SettingItem> mSelectItems;
    private RecyclerView mViewOptions;
    private TextView mViewTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_options, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewTitle = view.findViewById(R.id.text_title);

        RecyclerView mViewSelected = view.findViewById(R.id.recycler_view_selected);
        mViewSelected.setLayoutManager(new LinearLayoutManager(getContext()));

        mSelectItems = new ArrayList<>();
        mSelectedAdapter = new SelectedAdapter(mSelectItems);
        mViewSelected.setAdapter(mSelectedAdapter);
        mSelectedAdapter.setItemClickListener(new SelectedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(SettingItem item) {
                parseDelete(item);
                boolean delete = false;
                Iterator<SettingItem> itemIterator = mSelectItems.iterator();
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

        mViewOptions = view.findViewById(R.id.recycler_view_options);
        mItemClickListener = new SelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {
                parseSelected(item);
            }
        };

        initData();
    }

    private void parseDelete(SettingItem item) {
        if (item.getTitle().equals("部门")) {
            mPresenter.getDepartments();
            return;
        }
        if (item.getTitle().equals("子部门")) {
            int index = mSelectItems.indexOf(item);
            SettingItem settingItem = mSelectItems.get(index - 1);
            mPresenter.getSubDepartments(settingItem.getId());
            return;
        }
        if (item.getTitle().equals("考核类型")) {
            mPresenter.getCheckType();
            return;
        }
        if (item.getTitle().equals("考核内容")) {
            int index = mSelectItems.indexOf(item);
            SettingItem settingItem = mSelectItems.get(index - 1);
            mPresenter.getCheckContent(settingItem.getId());
        }
    }

    private void parseSelected(SelectItem item) {
        SettingItem item1 = new SettingItem(1, mTitle, item.getName());
        item1.setId(item.getId());
        mSelectItems.add(item1);
        mSelectedAdapter.notifyDataSetChanged();
        if (mTitle.equals("部门")) {
            mPresenter.getSubDepartments(item.getId());
            return;
        }
        if (mTitle.equals("子部门")) {
            showShortOptions("", null);
            return;
        }
        if (mTitle.equals("考核类型")) {
            mPresenter.getCheckContent(item.getId());
            return;
        }
        if (mTitle.equals("考核内容")) {
            showShortOptions("", null);
        }
    }

    private void initData() {
        if (mPresenter == null) {
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        boolean isCheckType = activity.getIntent().getBooleanExtra("isCheckType", false);
        if (isCheckType) {
            mPresenter.getCheckType();
        } else {
            mPresenter.getDepartments();
        }
    }

    private String mTitle;

    @Override
    public void showShortOptions(String title, List<SelectItem> list) {
        mTitle = title;
        mViewTitle.setText(TextUtils.isEmpty(title) ? "" : String.format("请选择%s", title));
        mViewOptions.setLayoutManager(new GridLayoutManager(getContext(), 3));

        SelectAdapter selectAdapter = new SelectAdapter(list, R.layout.adapter_select_short);
        mViewOptions.setAdapter(selectAdapter);
        selectAdapter.setItemClickListener(mItemClickListener);
    }

    @Override
    public void showLongOptions(String title, List<SelectItem> list) {
        mTitle = title;
        mViewTitle.setText(TextUtils.isEmpty(title) ? "" : String.format("请选择%s", title));
        mViewOptions.setLayoutManager(new LinearLayoutManager(getContext()));

        SelectAdapter selectAdapter = new SelectAdapter(list, R.layout.adapter_select_long);
        mViewOptions.setAdapter(selectAdapter);
        selectAdapter.setItemClickListener(mItemClickListener);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SelectOptionsContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    public ArrayList<SettingItem> getSelectItems() {
        return mSelectItems;
    }
}
