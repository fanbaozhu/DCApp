package com.xunchijn.dcappv1.event.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.event.TestData;
import com.xunchijn.dcappv1.event.adapter.DepartmentAdapter;

public class SetDepartmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_department);
        initTitle();
        initDepartment();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("选择部门", true, true, 0);
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
        RecyclerView departmentView = findViewById(R.id.recycler_view_department);
        departmentView.setLayoutManager(new LinearLayoutManager(this));
        DepartmentAdapter adapter = new DepartmentAdapter(TestData.getDepartments());
        departmentView.setAdapter(adapter);
    }
}
