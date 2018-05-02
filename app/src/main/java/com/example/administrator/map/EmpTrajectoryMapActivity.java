package com.example.administrator.map;

import android.app.Activity;


import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;


import android.widget.Button;
import android.widget.LinearLayout;



import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.baidumap.R;
import com.example.administrator.models.Deptidname;
import com.example.administrator.models.Empidname;

import com.example.administrator.util.ChooseDateDialog;
import com.example.administrator.util.DialogListViewAdapter;
import com.example.administrator.util.EmpDialogFragment;
import com.example.administrator.util.EmpNameDialogFragment;
import com.example.administrator.util.EmpNameDialogListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EmpTrajectoryMapActivity extends Activity {
    private View mPopView;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private RequestQueue mQueue;
    private LinearLayout llStart;
    private LinearLayout llEnd;
    BitmapDescriptor bitmap;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private Button btnQuery;
    private TextView tvDept;
    private TextView tvEmp;
    String starttime;
    String endtime;
    String dept;
    String deptid;
    String code;
    String simid;
    String empid;
    String empname;
    Date st;
    Date et;
    String pointxy;
    Double pointx;
    Double pointy;
    String px;
    String py;
    static long stamp;
    int difference;
    Polyline mPolyline;
    private int checkedItem = 0; //默认选中的item
    private ArrayList<Deptidname> listdept = new ArrayList<Deptidname>();
    private ArrayList<Empidname> listemp = new ArrayList<Empidname>();
    List<LatLng> options = new ArrayList<LatLng>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_emp_trajectory_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvEndTime = (TextView) findViewById(R.id.tvEndTime);
        llStart = (LinearLayout) findViewById(R.id.llStart);
        llEnd = (LinearLayout) findViewById(R.id.llEnd);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tvDept = (TextView) findViewById(R.id.tvDept);
        tvEmp = (TextView) findViewById(R.id.tvEmp);
        mQueue = Volley.newRequestQueue(getApplicationContext());
        mBaiduMap = mMapView.getMap();
        GetDept();
        bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.gps_point);

        llStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDateDialog cdd = new ChooseDateDialog();
                cdd.showDialog(EmpTrajectoryMapActivity.this, tvStartTime);
            }
        });
        llEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDateDialog cdd = new ChooseDateDialog();
                cdd.showDialog(EmpTrajectoryMapActivity.this, tvEndTime);
            }
        });

        tvDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EmpDialogFragment empDialogFragment = EmpDialogFragment.newInstance("请选择部门",listdept);
                empDialogFragment.setOnItemClickListener(new DialogListViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Deptidname deptidname) {
                        tvDept.setText(deptidname.getDept());
                        deptid = deptidname.getCode();
                        GetEmpName(deptid);
                        empDialogFragment.dismiss();
                    }
                });
                empDialogFragment.show(getFragmentManager(),"EmpDialog");
            }
        });

        tvEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( tvDept.getText().toString()==null||tvDept.getText().toString().equals("")){
                    Toast.makeText(EmpTrajectoryMapActivity.this,"请先选择部门",Toast.LENGTH_SHORT).show();
                    return;
                }
                final EmpNameDialogFragment empDialogFragment = EmpNameDialogFragment.newInstance("请选择人员",listemp);
                empDialogFragment.setOnItemClickListener(new EmpNameDialogListViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(Empidname empidname) {
                        tvEmp.setText(empidname.getEmpname());
                        simid = empidname.getEmpid();
                        //empid = simid;
                        empDialogFragment.dismiss();

                    }
                });
                empDialogFragment.show(getFragmentManager(),"EmpDialog");
            }
        });
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String empdept;
                String emp;
                String startt;
                String endt;
                empdept = tvDept.getText().toString();
                emp = tvEmp.getText().toString();
                startt = tvStartTime.getText().toString();
                endt = tvEndTime.getText().toString();
                if (TextUtils.isEmpty(empdept)||TextUtils.isEmpty(emp)) {
                    Toast.makeText(EmpTrajectoryMapActivity.this, "请选择部门和人员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(startt)||TextUtils.isEmpty(endt)) {
                    Toast.makeText(EmpTrajectoryMapActivity.this, "请选择时间段", Toast.LENGTH_SHORT).show();
                    return;
                }
                starttime = tvStartTime.getText().toString();
                endtime = tvEndTime.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    st = sdf.parse(starttime);
                    et = sdf.parse(endtime);
                    difference = et.compareTo(st);
                    if (difference >= 0) {
                        getStringToDate(starttime,"yyyy-MM-dd HH:mm");
                        String stt = stamp+"";
                        getStringToDate(endtime,"yyyy-MM-dd HH:mm");
                        String edt = stamp+"";
                        Gettrajectory(simid,stt,edt);
                    } else {
                        Toast.makeText(EmpTrajectoryMapActivity.this, "请选择正确的结束时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stamp = date.getTime()/1000;
        return stamp;
    }


    public void GetDept() {
        String url = this.getString(R.string.requestUrl) + "GetEmpDept";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null || s.equals("")) {
                            //tvDept.setText(null);
                            Toast.makeText(EmpTrajectoryMapActivity.this, "未找到部门数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                listdept = new ArrayList<Deptidname>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    dept = jsonObject.getString("PROJECT_NAME");
                                    code = jsonObject.getString("PROJECT_CODE");
                                    Deptidname dpi = new Deptidname();
                                    dpi.setCode(code);
                                    dpi.setDept(dept);
                                    listdept.add(dpi);

                                }
                                //tvDept.setText(dept);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mQueue.add(stringRequest);
    }

    public void GetEmpName(String deptid) {
        String url = this.getString(R.string.requestUrl) + "GetEmpName?deptid=" + deptid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null || s.equals("")) {
                            Toast.makeText(EmpTrajectoryMapActivity.this, "未找到人员数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                listemp = new ArrayList<Empidname>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    simid = jsonObject.getString("GPS_SIMID");
                                    empname = jsonObject.getString("EMPLOYEE_NAME");
                                    Empidname epi = new Empidname();
                                    epi.setEmpid(simid);
                                    epi.setEmpname(empname);
                                    listemp.add(epi);
                                }
                                //tvDept.setText(dept);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mQueue.add(stringRequest);
    }
    public void Gettrajectory(String empid, String starttime, String endtime){
        mBaiduMap.clear();
        String url = this.getString(R.string.requestUrl) + "GetEmptrajectory?empid=" + empid + "&&starttime=" + starttime + "&&endtime=" + endtime;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null && s.equals("")) {
                            Toast.makeText(EmpTrajectoryMapActivity.this, "未找到数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                options = new ArrayList<>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jobj = array.optJSONObject(i);
                                    pointxy = jobj.getString("GPS_POTXY");
                                    px = pointxy.split(",")[0];
                                    pointx = Double.parseDouble(px);
                                    py = pointxy.split(",")[1];
                                    pointy = Double.parseDouble(py);
                                    LatLng llDot = new LatLng(pointy, pointx);
                                    options.add(llDot);

                                    //options.add(option);
                                    MapStatus mapStatus = new MapStatus.Builder()
                                            .target(llDot)
                                            .zoom(16)
                                            .build();
                                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                                    mBaiduMap.setMapStatus(mapStatusUpdate);

                                }
                                OverlayOptions option = new PolylineOptions().width(10)
                                        .color(0xAAFF0000).points(options);
                                mPolyline = (Polyline) mBaiduMap.addOverlay(option);
                               // mBaiduMap.addOverlays(options);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(stringRequest);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
