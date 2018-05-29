package com.xunchijn.tongshan.event.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.DetailsAdapter;
import com.xunchijn.tongshan.event.model.DetailsItem;
import com.xunchijn.tongshan.event.presenter.DetailsContract;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment implements DetailsContract.View {
    private DetailsContract.Presenter mPresenter;
    private RecyclerView viewDetails;

    public static DetailsFragment newInstance(String eventId) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("eventId", eventId);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewDetails = view.findViewById(R.id.recycler_view_details);
        viewDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        String eventId = bundle.getString("eventId");
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        mPresenter.getCarDetails(eventId);
    }

    @Override
    public void showCarDetails(DetailsItem item) {
        List<DetailsItem> list = new ArrayList<>();
        list.add(item);
        DetailsAdapter mDetailsAdapter = new DetailsAdapter(list);
        viewDetails.setAdapter(mDetailsAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
