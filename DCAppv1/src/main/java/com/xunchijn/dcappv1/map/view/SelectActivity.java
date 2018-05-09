package com.xunchijn.dcappv1.map.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.event.adapter.NestingSelectAdapter;
import com.xunchijn.dcappv1.event.adapter.SelectAdapter;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.event.widget.SelectDialog;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.util.TestData;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    private List<NestingItem> mList;
    private static final String TYPE = "mType";
    private static final String TITLE = "mTitle";
    private String mType;
    private String mTitle;

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
                MapActivity.newInstance(SelectActivity.this, mTitle, new ArrayList<CarInfo>(), new ArrayList<UserInfo>());
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

        mList = TestData.getDepartments(mType);
        RecyclerView departmentView = findViewById(R.id.recycler_view_selects);
        departmentView.setLayoutManager(new LinearLayoutManager(this));
        NestingSelectAdapter adapter = new NestingSelectAdapter(mList);
        departmentView.setAdapter(adapter);
        adapter.setItemClickListener(new NestingSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {

            }

            @Override
            public void onSubTitleClick(NestingItem item) {
                showDialog(item);
            }
        });
    }

    private void showDialog(NestingItem item) {
        if (item.getSubtitle().equals("清空")) {
            new AlertDialog.Builder(this).setMessage("确定清空历史记录吗？")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
        } else if (item.getSubtitle().equals("筛选")) {
            final SelectDialog dialog = new SelectDialog();
            dialog.setTitle("部门");
            dialog.setList(TestData.getSelectItems(16));
            dialog.setItemClickListener(new SelectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(SelectItem item) {
                    dialog.dismiss();
                }
            });
            dialog.show(getSupportFragmentManager(), item.getName());
        }
    }
}
