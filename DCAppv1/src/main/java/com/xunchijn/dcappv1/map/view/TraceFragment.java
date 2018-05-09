package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.common.module.UserInfo;
import com.xunchijn.dcappv1.map.model.CarInfo;

import java.util.ArrayList;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午1:55
 * Description:轨迹回放页面
 **/
public class TraceFragment extends Fragment {
    private ArrayList<UserInfo> mUsers;
    private ArrayList<CarInfo> mCars;

    public static TraceFragment newInstance(Bundle bundle) {
        TraceFragment fragment = new TraceFragment();
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
        View view = inflater.inflate(R.layout.fragment_map_trace, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
