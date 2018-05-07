package com.xunchijn.administrator.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import com.baidu.mapapi.model.LatLng;
import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.models.SpinnerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmpMapActivity extends Activity {
    private View mPopView;
    private PopupWindow mPopupWindow;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    ArrayList<String> EmpSimId;
    private RequestQueue mQueue;
    private Spinner spnDept;
    private Spinner spnName;
    private TextView tvName;
    private TextView tvDept;
    private TextView tvZone;
    private TextView tvAddress;
    private TextView tvState;
    private LinearLayout llAll;
    List<OverlayOptions> options = new ArrayList<OverlayOptions>();
    private ArrayAdapter<SpinnerData> adapter;
    String pointxy;
    String px;
    String py;
    Double pointx;
    Double pointy;
    BitmapDescriptor bitmap;
    String BuMen;
    String Name;
    private boolean first = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_emp_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        spnDept = (Spinner) findViewById(R.id.spnDept);
        spnName = (Spinner) findViewById(R.id.spnName);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDept = (TextView) findViewById(R.id.tvDept);
        tvZone = (TextView) findViewById(R.id.tvZone);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvState = (TextView) findViewById(R.id.tvState);
        llAll =(LinearLayout) findViewById(R.id.llAll);
        mQueue = Volley.newRequestQueue(getApplicationContext());
        mBaiduMap = mMapView.getMap();
        bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.gps_point);
        llAll.setVisibility(View.GONE);

        GetDept();

        //addCustomElementsDemo();
        spnDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerData p = (SpinnerData) ((Spinner) findViewById(R.id.spnDept)).getSelectedItem();
                BuMen = p.getValue();
                // 画点
                GetEmpByDept(BuMen);
                // 下拉框
                GetName(BuMen);
                llAll.setVisibility(View.INVISIBLE);
                first = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        spnName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(first){
                    first = false;
                    return;
                }
                SpinnerData p = (SpinnerData) ((Spinner) findViewById(R.id.spnName)).getSelectedItem();
                Name = p.getValue();
                //画点
                GetCustomByName(Name);
                //展示信息
                GetEmpinformation(Name);
                llAll.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // 获取全部人员定位
//    public void addCustomElementsDemo(){
//        mBaiduMap.clear();
//        String url = this.getString(R.string.requestUrl) + "GetEmpposition";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        if(s == null && s.equals("")){
//                            Toast.makeText(EmpMapActivity.this,"未找到数据",Toast.LENGTH_SHORT).show();
//                        }else if(s != null) {
//                            try {
//                                JSONArray array = new JSONArray(s);
//                                EmpSimId = new ArrayList<>();
//                                options = new ArrayList<>();
//                                for (int i = 0; i < array.length(); i++) {
//                                    JSONObject jobj = array.optJSONObject(i);
//                                    pointxy = jobj.getString("GPS_POTXY");
//                                    px = pointxy.split(",")[0];
//                                    pointx = Double.parseDouble(px);
//                                    py = pointxy.split(",")[1];
//                                    pointy = Double.parseDouble(py);
//                                    LatLng llDot = new LatLng(pointy,pointx);
//                                    OverlayOptions option = new MarkerOptions()
//                                            .position(llDot).icon(bitmap);
//                                    options.add(option);
//                                    EmpSimId.add(jobj.getString("GPS_SIMID"));
//                                }
//                                mBaiduMap.addOverlays(options);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                } ,    new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        mQueue.add(stringRequest);
//    }

    private void InitPopWindow() {
        // TODO Auto-generated method stub
        // 将布局文件转换成View对象，popupview 内容视图
        mPopView = getLayoutInflater().inflate(R.layout.activity_emp_map, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        mPopupWindow = new PopupWindow(mPopView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        mPopupWindow.setOutsideTouchable(true);
        // mpopupWindow.setBackgroundDrawable(background);
    }

    public void ShowPopWindow(View view) {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            // 设置PopupWindow 显示的形式 底部或者下拉等
            // 在某个位置显示
            mPopupWindow.showAtLocation(mPopView, Gravity.BOTTOM, 0, 30);
            // 作为下拉视图显示
            // mPopupWindow.showAsDropDown(mPopView, Gravity.CENTER, 200, 300);
        }

    }

    /// 根据部门ID展示部门
    private void GetDept() {
        final List<SpinnerData> listSpinner = new ArrayList<SpinnerData>();
        String url = this.getString(R.string.requestUrl) + "GetEmpDept";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null && s.equals("")) {
                            spnDept.setAdapter(null);
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    SpinnerData data = new SpinnerData(jsonObject.getString("PROJECT_CODE"), jsonObject.getString("PROJECT_NAME"));
                                    listSpinner.add(data);
                                }
                                ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<SpinnerData>(EmpMapActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                                spnDept.setAdapter(adapter);
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

    /// 根据部门展示地图
    private void GetEmpByDept(String dept) {
        mBaiduMap.clear();
        String url = this.getString(R.string.requestUrl) + "GetEmppositionByDept?dept=" + dept;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null && s.equals("")) {
                            Toast.makeText(EmpMapActivity.this, "未找到数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                EmpSimId = new ArrayList<>();
                                options = new ArrayList<>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jobj = array.optJSONObject(i);
                                    pointxy = jobj.getString("GPS_POTXY");
                                    px = pointxy.split(",")[0];
                                    pointx = Double.parseDouble(px);
                                    py = pointxy.split(",")[1];
                                    pointy = Double.parseDouble(py);
                                    LatLng llDot = new LatLng(pointy, pointx);
                                    OverlayOptions option = new MarkerOptions()
                                            .position(llDot).icon(bitmap);
                                    options.add(option);
                                    MapStatus mapStatus = new MapStatus.Builder()
                                            .target(llDot)
                                            .zoom(16)
                                            .build();
                                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                                    mBaiduMap.setMapStatus(mapStatusUpdate);
                                    EmpSimId.add(jobj.getString("GPS_SIMID"));
                                }
                                mBaiduMap.addOverlays(options);
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

    ///获取部门人员姓名
    private void GetName(String dept) {
        final List<SpinnerData> listSpinner = new ArrayList<SpinnerData>();
        String url = this.getString(R.string.requestUrl) + "GetEmpName?dept=" + dept;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                            if (s.equals("")) {
                                spnName.setAdapter(null);
                                Toast.makeText(EmpMapActivity.this,"未找到人员数据",Toast.LENGTH_SHORT).show();
                                return;
                            } else if (s != null) {
                                try {
                                    JSONArray array = new JSONArray(s);
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject jsonObject = array.getJSONObject(i);
                                        SpinnerData data = new SpinnerData(jsonObject.getString("GPS_SIMID"), jsonObject.getString("EMPLOYEE_NAME"));
                                        listSpinner.add(data);
                                    }
                                    ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<SpinnerData>(EmpMapActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                                    spnName.setAdapter(adapter);
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

    /// 展示每个人的点
    private void GetCustomByName(String Name){
        mBaiduMap.clear();
        String url = this.getString(R.string.requestUrl) + "GetEmpCustomByName?Name=" + Name;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null && s.equals("")) {
                            Toast.makeText(EmpMapActivity.this, "未找到数据", Toast.LENGTH_SHORT).show();
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
                                    OverlayOptions option = new MarkerOptions()
                                            .position(llDot).icon(bitmap);
                                    options.add(option);
                                    MapStatus mapStatus = new MapStatus.Builder()
                                            .target(llDot)
                                            .zoom(16)
                                            .build();
                                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                                    mBaiduMap.setMapStatus(mapStatusUpdate);
                                }
                                mBaiduMap.addOverlays(options);
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

    // 获取每个人的详细信息并展示到PopWindow中
    private void GetEmpinformation(String Name){
        String url = this.getString(R.string.requestUrl) + "GetEmpinformation?Name=" + Name;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s == null && s.equals("")) {
                            Toast.makeText(EmpMapActivity.this, "未找到数据", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (s != null) {
                            try {
                                JSONArray array = new JSONArray(s);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jobj = array.optJSONObject(i);
                                    tvName.setText("姓名：" + jobj.getString("EMPLOYEE_NAME"));
                                    tvDept.setText("部门：" + jobj.getString("PROJECT_NAME"));
                                    tvZone.setText("区域：" + jobj.getString("ZONE_NAME"));
                                    tvAddress.setText("地址：" + jobj.getString("MEMO"));
                                    tvState.setText("状态：" + jobj.getString("GPS_SOS"));
                                    InitPopWindow();
                                }
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
