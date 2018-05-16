package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.map.contract.TraceContrast;
import com.xunchijn.dcappv1.map.model.TraceInfo;
import com.xunchijn.dcappv1.map.presenter.TracePresenter;
import com.xunchijn.dcappv1.test.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:轨迹回放页面
 **/
public class TraceFragment extends Fragment implements TraceContrast.View {
    private String TAG = "Trace";
    private TraceContrast.Presenter mPresenter;
    private Polyline mPolyline;
    private Marker mMoveMarker;
    private MapView mMapView;
    private Handler mHandler;
    private BaiduMap mMap;
    private ImageView mViewPlay;
    private TextView mViewSelectTime;
    private TimePickerDialog timePickerDialog;
    private long mStartTime;
    private long mEndTime;

    //通过设置间隔时间和距离可以控制速度和图标移动的距离
    private int mTimeInterval = 80;
    private static final double mDistance = 0.00002;
    private BitmapDescriptor bitmap;
    private List<LatLng> options;

    public static TraceFragment newInstance(Bundle bundle) {
        TraceFragment fragment = new TraceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_trace, container, false);
        mMapView = view.findViewById(R.id.bmapView);
        mMapView.onCreate(getContext(), savedInstanceState);
        mMap = mMapView.getMap();
        ImageView viewQuick = view.findViewById(R.id.image_trace_quick);
        viewQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + mTimeInterval);
                if (mTimeInterval > 60) {
                    mTimeInterval = mTimeInterval - 10;
                    Toast.makeText(getContext(), "速度+1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "最大速度", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mViewPlay = view.findViewById(R.id.image_trace_play);
        mViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLooper();
            }
        });
        ImageView viewSlow = view.findViewById(R.id.image_trace_slow);
        viewSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + mTimeInterval);
                if (mTimeInterval < 100) {
                    mTimeInterval = mTimeInterval + 10;
                    Toast.makeText(getContext(), "速度—1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "最小速度", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mViewSelectTime = view.findViewById(R.id.text_select_time);
        mViewSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        String type = bundle.getString("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if (type.equals("人员")) {
            mPresenter.getUserTrace("17360782514", "1524600000", "1524622000");
        } else {
            mPresenter.getCarTrace("13819020864", "1524600000", "1524622000");
        }
    }

    private void selectStartTime() {
        timePickerDialog = new TimePickerDialog();
        timePickerDialog.setTitle("开始时间");
        timePickerDialog.setOnConfirmClickListener(new TimePickerDialog.OnConfirmClickListener() {
            @Override
            public void OnConfirm(String timestamp, long time) {
                timePickerDialog.dismiss();
                Log.d("test", "OnConfirm: timestamp=" + timestamp + " time=" + time);
                if (mStartTime == 0) {
                    mStartTime = time;
                    mViewSelectTime.setText(String.format("开始时间：%s", timestamp));
                    selectEndTime();
                } else if (mStartTime >= time) {
                    selectEndTime();
                    Toast.makeText(getContext(), "结束时间不能早于或者等于开始时间", Toast.LENGTH_SHORT).show();
                } else {
                    mEndTime = time;
                    mViewSelectTime.append(String.format("\n结束时间：%s", timestamp));
                }
            }
        });
        timePickerDialog.show(getFragmentManager(), "");
    }

    private void selectEndTime() {
        if (timePickerDialog == null) {
            timePickerDialog = new TimePickerDialog();
        }
        timePickerDialog.setTitle("结束时间");
        timePickerDialog.setFixedDate(false);
        timePickerDialog.show(getFragmentManager(), "");
    }

    private void initMap(List<TraceInfo> list) {
        MapStatus.Builder builder = new MapStatus.Builder();
        LatLng latLng = new LatLng(list.get(0).getPositionX(), list.get(0).getPositionY());
        builder.target(latLng);
        builder.zoom(16.0f);
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_trace_car);
        mHandler = new Handler(Looper.getMainLooper());
        drawPolyLine(list);
        moveLooper();
        mMapView.showZoomControls(false);
    }

    private void drawPolyLine(List<TraceInfo> list) {
        options = new ArrayList<>();
        for (int index = 0; index < list.size(); index++) {
            LatLng latLng = new LatLng(list.get(index).getPositionX(), list.get(index).getPositionY());
            options.add(latLng);
        }
        PolylineOptions polylineOptions = new PolylineOptions().points(options)
                .width(10).color(ContextCompat.getColor(getContext(), R.color.colorGreen));

        mPolyline = (Polyline) mMap.addOverlay(polylineOptions);

        //轨迹图标
        OverlayOptions marketOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f)
                .icon(bitmap).position(options.get(0)).rotate((float) getAngle(0));
        mMoveMarker = (Marker) mMap.addOverlay(marketOptions);
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
        return (180 * (radio / Math.PI) + deltAngle - 90);
    }

    /**
     * 根据点和斜率算取截距
     */
    private double getInterception(double slope, LatLng point) {
        return (point.latitude - slope * point.longitude);
    }

    /**
     * 算斜率
     */
    private double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        return ((toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mMap.clear();
    }

    /**
     * 计算x方向每次移动的距离
     */
    private double getXMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE) {
            return mDistance;
        }
        return Math.abs((mDistance * slope) / Math.sqrt(1 + slope * slope));
    }

    /**
     * 循环进行移动逻辑
     */
    public void moveLooper() {
        if (TracePlayThread.isAlive()) {
            isPause = !isPause;
        } else {
            TracePlayThread.start();
        }
        Glide.with(this).load(isPause ? R.mipmap.ic_trace_play : R.mipmap.ic_trace_pause).into(mViewPlay);
    }

    @Override
    public void showUserTrace(List<TraceInfo> list) {
        initMap(list);
    }

    @Override
    public void showCarTrace(List<TraceInfo> list) {
        initMap(list);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TraceContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    private boolean isPause = true;

    private Thread TracePlayThread = new Thread() {
        public void run() {
            for (int i = 0; i < options.size() - 1; i++) {
                final LatLng startPoint = options.get(i);
                final LatLng endPoint = options.get(i + 1);
                mMoveMarker.setPosition(startPoint);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // refresh marker's rotate
                        if (mMapView == null) {
                            return;
                        }
                        mMoveMarker.setRotate((float) getAngle(startPoint, endPoint));
                    }
                });
                double slope = getSlope(startPoint, endPoint);
                // 是不是正向的标示
                boolean isReverse = (startPoint.latitude > endPoint.latitude);

                double intercept = getInterception(slope, startPoint);

                double xMoveDistance = isReverse ? getXMoveDistance(slope) : -1 * getXMoveDistance(slope);


                for (double j = startPoint.latitude; !((j > endPoint.latitude) ^ isReverse); j = j - xMoveDistance) {
                    LatLng latLng;
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
                            mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(finalLatLng).build()));
                        }
                    });
                    try {
                        Thread.sleep(mTimeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (isPause) {
                        try {
                            Thread.sleep(mTimeInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getContext()).load(R.mipmap.ic_trace_play).into(mViewPlay);
                }
            });
        }
    };
}
