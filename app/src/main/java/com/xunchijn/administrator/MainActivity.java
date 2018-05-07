package com.xunchijn.administrator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xunchijn.administrator.event.ReportEvent;
import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.map.EmpMapActivity;
import com.xunchijn.administrator.map.EmpTrajectoryMapActivity;
import com.xunchijn.administrator.map.TruckMapActivity;

public class MainActivity extends AppCompatActivity{
    private Button btnEvent;
    private Button btnEmpMap;
    private Button btnTruckMap;
    private Button btnEmptrajectory;
    private Button btnbtnTrucktrajectory;
    private Button btnLoginOut;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEvent =(Button) findViewById(R.id.btnEvent);
        btnEmpMap =(Button) findViewById(R.id.btnEmpMap);
        btnTruckMap =(Button) findViewById(R.id.btnTruckMap);
        btnEmptrajectory =(Button) findViewById(R.id.btnEmptrajectory);
        btnbtnTrucktrajectory =(Button) findViewById(R.id.btnbtnTrucktrajectory);
        btnLoginOut =(Button) findViewById(R.id.btnLoginOut);

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ReportEvent.class);
                startActivity(i);
            }
        });

        btnEmpMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmpMapActivity.class);
                startActivity(i);
            }
        });

        btnTruckMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TruckMapActivity.class);
                startActivity(i);
            }
        });

        btnEmptrajectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmpTrajectoryMapActivity.class);
                startActivity(i);
            }
        });
    }
}