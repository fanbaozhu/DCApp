package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.map.model.CarInfo;
import com.xunchijn.dcappv1.map.model.Point;
import com.xunchijn.dcappv1.util.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:轨迹回放页面
 **/
public class TraceFragment extends Fragment {
    private ArrayList<Point> mPoints;
    private ArrayList<CarInfo> mCars;
    private static final int TIME_INTERVAL = 80;
    private static final double DISTANCE = 0.0001;
    private Marker mMoveMarker;
    private Handler mHandler;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    BitmapDescriptor bitmap;
    List<LatLng> options = new ArrayList<>();
    Polyline mPolyline;

    public static TraceFragment newInstance(Bundle bundle) {
        TraceFragment fragment = new TraceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_gps_point);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mPoints = TestData.getEmpPoint();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_trace, container, false);
        mMapView = view.findViewById(R.id.bmapView);
        mHandler = new Handler(Looper.getMainLooper());
        mBaiduMap = mMapView.getMap();
        initView();
        return view;
    }

    private void initView() {
        Point empPoint;
        int i = mPoints.size();
        options = new ArrayList<>();
        for (int a = 0; a < i; a++) {
            empPoint = mPoints.get(a);
            LatLng llDot = new LatLng(empPoint.getY(), empPoint.getX());
            options.add(llDot);
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
        OverlayOptions markerOptions;
        markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.arrow)).position(options.get(0))
                .rotate((float) getAngle(0));
        mMoveMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
        moveLooper();
    }

    /**
     * 根据点获取图标转的角度
     */
    private double getAngle(int startIndex) {
        if ((startIndex + 1) >= mPolyline.getPoints().size()) {
            throw new RuntimeException("index out of bonds");
        }
        LatLng startPoint = mPolyline.getPoints().get(startIndex);
        LatLng endPoint = mPolyline.getPoints().get(startIndex + 1);
        return getAngle(startPoint, endPoint);
    }

    /**
     * 根据两点算取图标转的角度
     */
    private double getAngle(LatLng fromPoint, LatLng toPoint) {
        double slope = getSlope(fromPoint, toPoint);
        if (slope == Double.MAX_VALUE) {
            if (toPoint.latitude > fromPoint.latitude) {
                return 0;
            } else {
                return 180;
            }
        }
        float deltAngle = 0;
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180;
        }
        double radio = Math.atan(slope);
        double angle = 180 * (radio / Math.PI) + deltAngle - 90;
        return angle;
    }

    /**
     * 根据点和斜率算取截距
     */
    private double getInterception(double slope, LatLng point) {

        double interception = point.latitude - slope * point.longitude;
        return interception;
    }

    /**
     * 算斜率
     */
    private double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        double slope = ((toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude));
        return slope;

    }

    /**
     * 循环进行移动逻辑
     */
    public void moveLooper() {
        new Thread() {

            public void run() {

                while (true) {

                    for (int i = 0; i < options.size() - 1; i++) {


                        final LatLng startPoint = options.get(i);
                        final LatLng endPoint = options.get(i + 1);
                        mMoveMarker
                                .setPosition(startPoint);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // refresh marker's rotate
                                if (mMapView == null) {
                                    return;
                                }
                                mMoveMarker.setRotate((float) getAngle(startPoint,
                                        endPoint));
                            }
                        });
                        double slope = getSlope(startPoint, endPoint);
                        // 是不是正向的标示
                        boolean isReverse = (startPoint.latitude > endPoint.latitude);

                        double intercept = getInterception(slope, startPoint);

                        double xMoveDistance = isReverse ? getXMoveDistance(slope) :
                                -1 * getXMoveDistance(slope);


                        for (double j = startPoint.latitude; !((j > endPoint.latitude) ^ isReverse);
                             j = j - xMoveDistance) {
                            LatLng latLng = null;
                            if (slope == Double.MAX_VALUE) {
                                latLng = new LatLng(j, startPoint.longitude);
                            } else {
                                latLng = new LatLng(j, (j - intercept) / slope);
                            }

                            final LatLng finalLatLng = latLng;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mMapView == null) {
                                        return;
                                    }
                                    mMoveMarker.setPosition(finalLatLng);
                                }
                            });
                            try {
                                Thread.sleep(TIME_INTERVAL);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    //break;
                }
            }

        }.start();
    }

    /**
     * 计算x方向每次移动的距离
     */
    private double getXMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE) {
            return DISTANCE;
        }
        return Math.abs((DISTANCE * slope) / Math.sqrt(1 + slope * slope));
    }

    public void showUserTrace(List<Point> list) {

    }
}
