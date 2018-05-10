package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.adapter.StatisticAdapter;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.adapter.EmpPositionAdapter;
import com.xunchijn.dcappv1.map.contract.EmpPositionContrast;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.EmpItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:地图定位页面
 **/
public class LocationFragment extends Fragment implements EmpPositionContrast.View {
    private EmpPositionContrast.Presenter mPresenter;
    private ArrayList<UserInfo> mUsers;
    private ArrayList<CarInfo> mCars;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    BitmapDescriptor bitmap;
    private RecyclerView mView;
    String empPoint;
    List<OverlayOptions> options = new ArrayList<OverlayOptions>();

    public static LocationFragment newInstance(Bundle bundle) {
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mCars = (ArrayList<CarInfo>) bundle.getSerializable("cars");
        mUsers = (ArrayList<UserInfo>) bundle.getSerializable("users");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_location, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Double pointx;
        Double pointy;
        String px;
        String py;
        mView = view.findViewById(R.id.recycler_view_statistic);
        mMapView = view.findViewById(R.id.bmapView);
        bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_gps_point);
        Bundle bundle = new Bundle();
        if (bundle == null) {
            return;
        }
        empPoint = bundle.getString("point");
        options = new ArrayList<>();
        px = empPoint.split(",")[0];
        pointx = Double.parseDouble(px);
        py = empPoint.split(",")[1];
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
        mBaiduMap.addOverlays(options);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(EmpPositionContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmpPosition(List<EmpItem> list) {
        //设置布局的排列方式
        mView.setLayoutManager(new LinearLayoutManager(getContext()));
        //填充数据
        EmpPositionAdapter adapter = new EmpPositionAdapter(list);

        mView.setAdapter(adapter);
    }
}
