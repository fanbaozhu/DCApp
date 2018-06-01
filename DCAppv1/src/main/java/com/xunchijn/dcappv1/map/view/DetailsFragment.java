package com.xunchijn.dcappv1.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunchijn.dcappv1.base.CarInfo;
import com.xunchijn.dcappv1.base.UserInfo;
import com.xunchijn.dcappv1.event.view.EventInfoFragment;
import com.xunchijn.dcappv1.map.presenter.DetailsContrast;

public class DetailsFragment extends Fragment implements DetailsContrast.View {
    public static String ID = "eventId";
    private DetailsContrast.Presenter mPresenter;
    private CarInfo mCarInfo;

    public static DetailsFragment newInstance(String eventId) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID, eventId);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.xunchijn.dcappv1.R.layout.fragment_details, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        String eventId = bundle.getString(ID);
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        mPresenter.DetailsCar(eventId);
    }

    @Override
    public void showUser(UserInfo userInfo) {

    }

    @Override
    public void showCar(CarInfo carInfo) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setPresenter(DetailsContrast.Presenter presenter) {

    }
}
