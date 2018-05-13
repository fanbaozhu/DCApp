package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.event.adapter.NestingSelectAdapter;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.map.contract.SelectContrast;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.User;
import com.xunchijn.dcappv1.map.presenter.SelectPresenter;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity implements SelectContrast.View {
    private SelectContrast.Presenter mPresenter;
    private List<NestingItem> mList;
    private NestingSelectAdapter adapter;
    private static final String TYPE = "mType";
    private static final String TITLE = "mTitle";
    private String mType;
    private String mTitle;
    private SelectItem mItem;
    private String mSubDepartmentId;

    public static void newInstance(Context context, String type, String title) {
        Intent intent = new Intent(context, SelectActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        mPresenter = new SelectPresenter(this);
        mPresenter.getDepartment();
    }

    private void initTitle() {
        mType = getIntent().getStringExtra(TYPE);
        mTitle = getIntent().getStringExtra(TITLE);
        TitleFragment titleFragment = TitleFragment.newInstance(String.format("选择%s", mType), true, true);
        titleFragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                if (mItem.getId().equals("0")) {
                    //选择全部
                    MapActivity.newInstance(SelectActivity.this, mTitle, mSubDepartmentId, true);
                } else {
                    //选择单个人
                    MapActivity.newInstance(SelectActivity.this, mTitle, mItem.getId(), false);
                }
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    private void initView() {
        setContentView(R.layout.activity_select);
        initTitle();

        mList = new ArrayList<>();
        RecyclerView departmentView = findViewById(R.id.recycler_view_selects);
        departmentView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NestingSelectAdapter(mList);
        departmentView.setAdapter(adapter);
        adapter.setItemClickListener(new NestingSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {
                if (item.getId().length() == 6) {
                    //点击部门选项，获取子部门
                    mPresenter.getSubDepartment(item.getId());
                    return;
                }
                if (item.getId().length() == 9) {
                    //点击子部门选项，获取用户/车辆
                    mSubDepartmentId = item.getId();
                    mPresenter.getUsers(item.getId());
                    return;
                }
                mItem = item;
            }

            @Override
            public void onSubTitleClick(NestingItem item) {
            }
        });
    }

    @Override
    public void showDepartment(List<SelectItem> list) {
        NestingItem item = new NestingItem("0", "选择部门", "");
        item.setItems(list);
        mList.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSubDepartment(List<SelectItem> list) {
        mList.clear();
        NestingItem item = new NestingItem("1", "选择子部门", "");
        item.setItems(list);
        mList.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUsers(ArrayList<User> list) {
        mList.clear();
        NestingItem item = new NestingItem("2", "选择人员", "");
        list.add(0, new User("0", "全部人员"));
        item.setItems(list);
        mList.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCars(ArrayList<CarInfo> list) {
        mList.clear();
        NestingItem item = new NestingItem("2", "选择车辆", "");
        list.add(0, new CarInfo("0", "全部车辆"));
        item.setItems(list);
        mList.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SelectContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
