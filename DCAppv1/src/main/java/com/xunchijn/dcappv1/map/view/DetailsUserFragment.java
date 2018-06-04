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

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.CarInfo;
import com.xunchijn.dcappv1.base.UserInfo;
import com.xunchijn.dcappv1.map.presenter.DetailsContrast;

public class DetailsUserFragment extends Fragment implements DetailsContrast.View {
    public static String ID = "eventId";
    private DetailsContrast.Presenter mPresenter;
    private UserInfo mUserInfo;
    private TextView tvName;
    private TextView tvDept;
    private TextView tvZone;
    private TextView tvStatus;
    private TextView tvAddress;

    public static DetailsUserFragment newInstance(String eventId) {
        DetailsUserFragment detailsCarFragment = new DetailsUserFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID, eventId);
        detailsCarFragment.setArguments(bundle);
        return detailsCarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvName = view.findViewById(R.id.tvName);
        tvDept = view.findViewById(R.id.tvDept);
        tvZone = view.findViewById(R.id.tvZone);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvAddress = view.findViewById(R.id.tvAddress);
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        String eventId = bundle.getString(ID);
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        mPresenter.DetailsUser(eventId);
    }

    @Override
    public void showUser(UserInfo userInfo) {
        tvName.setText(String.format("人员姓名：%s", userInfo.getUserName()));
        tvDept.setText(String.format("所属部门：%s", userInfo.getUserDept()));
        tvZone.setText(String.format("所属区域：%s", userInfo.getUserZoon()));
        tvStatus.setText(String.format("当前状态：%s", userInfo.getUserStatus()));
        tvAddress.setText(String.format("当前位置：%s", userInfo.getUserAddress()));
    }

    @Override
    public void showCar(CarInfo carInfo) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailsContrast.Presenter presenter) {
        mPresenter = presenter;
    }
}
