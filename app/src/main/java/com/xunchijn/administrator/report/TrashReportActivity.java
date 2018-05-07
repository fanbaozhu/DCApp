package com.xunchijn.administrator.report;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xunchijn.administrator.baidumap.R;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class TrashReportActivity extends AppCompatActivity implements View.OnClickListener{
    TrashReportListFragment dayListFragment;
    TrashReportListFragment weekListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_report);
        dayListFragment = TrashReportListFragment.newInstance("day");
        weekListFragment = TrashReportListFragment.newInstance("week");
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container, dayListFragment).show(dayListFragment).commit();

        //String type = getIntent().getStringExtra(TYPE);
    }

    @Override
    public void onClick(View view) {

    }
//
//    private static final String TYPE = "type";
//
//    public static void startTrashReport(Context context,String type){
//        Intent intent = new Intent(context, TruckMapActivity.class);
//        intent.putExtra(TYPE,type);
//        intent.putExtra(TYPE,type);
//        intent.putExtra(TYPE,type);
//        intent.putExtra(TYPE,type);
//        context.startActivity(intent);
//    }
}
