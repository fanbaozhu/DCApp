package com.xunchijn.dcappv1.map.view;

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
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.contract.LocationContrast;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.User;
import com.xunchijn.dcappv1.map.presenter.LocationPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:地图定位页面
 **/
public class LocationFragment extends Fragment implements LocationContrast.View {
    private static final String TAG = "Location";
    private LocationContrast.Presenter mPresenter;
    private BitmapDescriptor bitmap;
    private TextView mViewNames;
    private BaiduMap mMap;

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
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_gps_point);
        mPresenter = new LocationPresenter(this);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        boolean isAll = bundle.getBoolean("isAll");
        String id = bundle.getString("id");
        if (TextUtils.isEmpty(id)) {
            return;
        }
        if (isAll) {
            mPresenter.getUsers(id);
        } else {
            mPresenter.getUser(id);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_location, container, false);
        mViewNames = view.findViewById(R.id.text_bottom);
        MapView mMapView = view.findViewById(R.id.bmapView);
        mMap = mMapView.getMap();
        return view;
    }

    private void showLocation(List<OverlayOptions> options, String position) {
        LatLng llDot = new LatLng(Double.valueOf(position.split(",")[0]), Double.valueOf(position.split(",")[1]));
        OverlayOptions option = new MarkerOptions().position(llDot).icon(bitmap);
        MapStatus mapStatus = new MapStatus.Builder().target(llDot).zoom(16).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mMap.setMapStatus(mapStatusUpdate);
        options.add(option);
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
    public void showUsers(ArrayList<User> list) {
        List<OverlayOptions> options = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            showLocation(options, list.get(i).getPosition());
            builder.append(list.get(i).getName());
            builder.append(" ,");
        }
        int index = builder.lastIndexOf(",");
        builder.delete(index, index + 1);
        mMap.addOverlays(options);
        mViewNames.setText(builder.toString());
    }

    @Override
    public void showCars(ArrayList<CarInfo> list) {
        List<OverlayOptions> options = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            showLocation(options, list.get(i).getPosition());
        }
        mMap.addOverlays(options);
    }

    @Override
    public void showUser(UserInfo userInfo) {
        List<OverlayOptions> options = new ArrayList<>();
        showLocation(options, userInfo.getUserPoint());
        mViewNames.setText(userInfo.getUserName());
        mMap.addOverlays(options);
    }
}
