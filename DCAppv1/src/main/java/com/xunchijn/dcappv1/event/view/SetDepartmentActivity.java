package com.xunchijn.dcappv1.event.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.event.adapter.NestingSelectAdapter;
import com.xunchijn.dcappv1.event.model.NestingItem;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.util.TestData;

import java.util.List;

public class SetDepartmentActivity extends AppCompatActivity {
    private List<NestingItem> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_department);
        initTitle();
        initDepartment();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("选择部门", true, true, 0,0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, titleFragment)
                .show(titleFragment).commit();
        titleFragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onConfirm() {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        });
    }

    private void initDepartment() {
        mList = TestData.getDepartments("人员");
        RecyclerView departmentView = findViewById(R.id.recycler_view_department);
        departmentView.setLayoutManager(new LinearLayoutManager(this));
        NestingSelectAdapter adapter = new NestingSelectAdapter(mList);
        departmentView.setAdapter(adapter);
        adapter.setItemClickListener(new NestingSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelectItem item) {

            }

            @Override
            public void onSubTitleClick(NestingItem item) {

            }
        });
    }
}
