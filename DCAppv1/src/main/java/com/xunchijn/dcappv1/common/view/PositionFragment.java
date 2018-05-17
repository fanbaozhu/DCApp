package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.xunchijn.dcappv1.R;

public class PositionFragment extends Fragment {
    private boolean isFirstLoc = true;
    private MapView mMapView;
    private BaiduMap mMap;
    private EditText mEditPosition;
    private String mPosition = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_position, container, false);
        mEditPosition = view.findViewById(R.id.edit_position);
        initMap(view);
        return view;
    }

    private void initMap(View view) {

        mMapView = view.findViewById(R.id.bmapView);
        //获取地图控件引用
        mMap = mMapView.getMap();
        //设置地图模式：普通地图
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启定位图层
        mMap.setMyLocationEnabled(true);

        //定位相关的类
        LocationClient mLocationClient = new LocationClient(getContext());     //声明LocationClient类、

        //配置定位SDK参数
        LocationClientOption option = new LocationClientOption();
        //设置定位模式：高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置经纬度返回类型
        option.setCoorType("bd09ll");
        //设置发送定位请求的时间间隔，0代表单次定位，多次必须大于1000
        option.setScanSpan(1000);//
        //设置是否使用GPS
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        // 打开gps
        option.setOpenGps(true);

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);

        LocationListener myListener = new LocationListener();

        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        //开启定位
        mLocationClient.start();
        //图片点击事件，回到定位点
        mLocationClient.requestLocation();
    }

    private class LocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            // 设置定位数据
            mMap.setMyLocationData(locData);

            // 当不需要定位图层时关闭定位图层
            //mMap.setMyLocationEnabled(false);
            if (isFirstLoc) {
                LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                isFirstLoc = false;
            }
            mPosition = String.format("%s,%s", bdLocation.getLatitude(), bdLocation.getLongitude());
            mEditPosition.setText(bdLocation.getAddrStr());

            //获取定位类型/、定位错误返回码
            parseErrorCode(bdLocation.getLocType());
        }
    }


    private void parseErrorCode(int errorCode) {
        if (errorCode == BDLocation.TypeNetWorkLocation || errorCode == BDLocation.TypeGpsLocation) {
            return;
        }
        if (errorCode == BDLocation.TypeOffLineLocationFail) {
            showError("Offline location failed, please check the net (wifi/cell)!");
            return;
        }
        if (errorCode == BDLocation.TypeCriteriaException) {
            showError("无法获取有效定位依据，定位失败");
            return;
        }
        if (errorCode == BDLocation.TypeServerCheckKeyError) {
            showError("AK不存在或者非法");
        }
    }

    private void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    public String getPosition() {
        return mPosition;
    }

    public String getEditPosition() {
        return mEditPosition.getText().toString();
    }
}
