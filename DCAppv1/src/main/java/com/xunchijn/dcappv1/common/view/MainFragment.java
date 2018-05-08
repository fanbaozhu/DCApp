package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;

public class MainFragment extends Fragment implements View.OnClickListener{
    private TextView mViewTrace;
    private TextView mViewLocation;
    private TextView mViewEventReport;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewTrace = view.findViewById(R.id.text_trace);
        mViewLocation = view.findViewById(R.id.text_location);
        mViewEventReport = view.findViewById(R.id.text_event_report);
        mViewTrace.setText("轨迹\n回放");
        mViewLocation.setText("地图\n定位");
        mViewEventReport.setText("事件\n上传");
    }

    @Override
    public void onClick(View v) {

    }
}
