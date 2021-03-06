package com.xunchijn.dcappv1.map.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.UserInfo;
import com.xunchijn.dcappv1.event.model.SelectItem;
import com.xunchijn.dcappv1.base.CarInfo;
import com.xunchijn.dcappv1.map.presenter.LocationContrast;

import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:地图定位页面
 **/
public class LocationFragment extends Fragment implements LocationContrast.View {
    private LocationContrast.Presenter mPresenter;
    private BitmapDescriptor bitmap;
    private TextView mViewNames;
    private MapView mMapView;
    private BaiduMap mMap;

    public static LocationFragment newInstance(String type, String id) {
        LocationFragment fragment = new LocationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_location, container, false);
        mViewNames = view.findViewById(R.id.text_bottom);
        initMap(view);
        return view;
    }

    private void initMap(View view) {
        mMapView = view.findViewById(R.id.bmapView);
        //获取地图控件引用
        mMap = mMapView.getMap();

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
        mMap.setMapStatus(msu);

        initData();
    }

    private void initData() {
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_gps_point);
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        String type = bundle.getString("type");
        String id = bundle.getString("id");
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(type)) {
            return;
        }
        if (type.equals("人员")) {
            mPresenter.getUser(id);
        } else {
            mPresenter.getCar(id);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LocationContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUsers(List<SelectItem> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            showLocation(list.get(i).getName(), list.get(i).getPosition());
            builder.append(list.get(i).getName());
            builder.append(" ,");
        }
        int index = builder.lastIndexOf(",");
        builder.delete(index, index + 1);
        mViewNames.setText(builder.toString());
    }

    private void showLocation(String name, String position) {
        LatLng llDot = new LatLng(Double.valueOf(position.split(",")[1]), Double.valueOf(position.split(",")[0]));
        OverlayOptions option = new MarkerOptions().position(llDot).icon(bitmap);
        mMap.addOverlay(option);

        OverlayOptions optionText = new TextOptions().position(llDot).text(name).fontSize(24).fontColor(Color.BLUE);
        mMap.addOverlay(optionText);

        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(llDot);
        mMap.setMapStatus(u);
    }

    @Override
    public void showCars(List<SelectItem> list) {
        for (int i = 0; i < list.size(); i++) {
            showLocation(list.get(i).getName(), list.get(i).getPosition());
        }
    }

    @Override
    public void showUser(UserInfo userInfo) {
        showLocation(userInfo.getUserName(), userInfo.getUserPoint());
        mViewNames.setText(userInfo.getUserName());
    }

    @Override
    public void showCar(CarInfo carInfo) {
        showLocation(carInfo.getCarName(), carInfo.getCarPoint());
        mViewNames.setText(carInfo.getCarName());
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }
}
