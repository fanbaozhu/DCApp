package com.xunchijn.tongshan.event.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.adapter.PictureAdapter;
import com.xunchijn.tongshan.adapter.PictureAdapter.OnItemClickListener;
import com.xunchijn.tongshan.adapter.SettingAdapter;
import com.xunchijn.tongshan.base.BaseConfig;
import com.xunchijn.tongshan.common.module.SettingItem;
import com.xunchijn.tongshan.event.model.EventItem;
import com.xunchijn.tongshan.event.presenter.EventInfoContract;
import com.xunchijn.tongshan.util.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

public class EventInfoFragment extends Fragment implements EventInfoContract.View {
    private EventInfoContract.Presenter mPresenter;
    private List<String> mPictures;
    private List<SettingItem> mSettingItems;
    private PictureAdapter mPictureAdapter;
    private SettingAdapter mSettingAdapter;
    private TextView mViewDescribe;
    private TextView mViewReportTime;
    private boolean mIsEvent = true;

    public static EventInfoFragment newInstance(String eventId) {
        EventInfoFragment eventInfoFragment = new EventInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("eventId", eventId);
        eventInfoFragment.setArguments(bundle);
        return eventInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_info, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewDescribe = view.findViewById(R.id.text_report_describe);
        mViewReportTime = view.findViewById(R.id.text_report_time);

        RecyclerView viewPictures = view.findViewById(R.id.recycler_view_picture);
        viewPictures.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPictures = new ArrayList<>();
        mPictureAdapter = new PictureAdapter(mPictures, 2);
        viewPictures.setAdapter(mPictureAdapter);
        mPictureAdapter.setItemClickListener(mOnPictureClick);

        RecyclerView viewSettings = view.findViewById(R.id.recycler_view_setting);
        viewSettings.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mIsEvent) {
            mSettingItems = BaseConfig.getEventInFoSettingItems();
        } else {
            mSettingItems = BaseConfig.getInfo();
        }
        mSettingAdapter = new SettingAdapter(mSettingItems);
        viewSettings.setAdapter(mSettingAdapter);

        initData();
    }

    private OnItemClickListener mOnPictureClick = new PictureAdapter.OnItemClickListener() {
        @Override
        public void onPictureClick(String url) {
        }

        @Override
        public void onAddPicture() {

        }
    };

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle == null || mPresenter == null) {
            return;
        }
        String eventId = bundle.getString("eventId");
        if (TextUtils.isEmpty(eventId)) {
            return;
        }
        if (mIsEvent) {
            mPresenter.getEventInfo(eventId);
        } else {
            mPresenter.getInfo(eventId);
        }
    }

    @Override
    public void showEventInfo(EventItem item) {
        mViewDescribe.setText(item.getEventDescribe());
        mViewReportTime.setText(item.getReportTime());
        if (!TextUtils.isEmpty(item.getEventPictureName())) {
            String[] urls = item.getEventPictureName().split(",");
            mPictures.clear();
            for (String url : urls) {
                mPictures.add(String.format("%s/UploadImg/%s", RetrofitProvider.BASE_URL, url));
            }
        }
        if (mPictures.size() != 0) {
            mPictureAdapter.notifyDataSetChanged();
        }

        if (!TextUtils.isEmpty(item.getEventPosition())) {
            mSettingItems.get(0).setSubtitle(item.getEventPosition());
        }
        if (!TextUtils.isEmpty(item.getEventDepartment())) {
            mSettingItems.get(1).setSubtitle(item.getEventDepartment());
        }
//        if (!TextUtils.isEmpty(item.getEventSubDepartment())) {
//            mSettingItems.get(1).setSubtitle(item.getEventSubDepartment());
//        }
        if (mIsEvent) {
            if (!TextUtils.isEmpty(item.getCheckType())) {
                mSettingItems.get(2).setSubtitle(item.getCheckType());
            }
            if (!TextUtils.isEmpty(item.getCheckContent())) {
                mSettingItems.get(3).setSubtitle(item.getCheckContent());
            }
        }
        mSettingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(EventInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setEvent(boolean event) {
        mIsEvent = event;
    }
}
