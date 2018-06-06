package com.xunchijn.dcappv1.common.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.adapter.SettingAdapter;
import com.xunchijn.dcappv1.base.BaseConfig;
import com.xunchijn.dcappv1.common.module.SettingItem;
import com.xunchijn.dcappv1.common.presenter.MineContrast;

public class MineFragment extends Fragment implements MineContrast.View {
    private MineContrast.Presenter mPresenter;
    private ImageView mViewHead;
    private TextView mViewName;
    private TextView mViewSimId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_mine, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mViewHead = view.findViewById(R.id.image_user_head);
        mViewName = view.findViewById(R.id.text_user_name);
        mViewSimId = view.findViewById(R.id.text_user_id);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_mine_setting);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SettingAdapter settingAdapter = new SettingAdapter(BaseConfig.getMineSettings());
        recyclerView.setAdapter(settingAdapter);
        settingAdapter.setItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingItem item) {
                if (item.getIndex() == 0) {
                    startActivity(new Intent(getContext(), MessagesActivity.class));
                    return;
                }
                if (item.getIndex() == 1) {
                    startActivity(new Intent(getContext(), ResetPassActivity.class));
                    return;
                }
                if (item.getIndex() == 2) {
                    startActivity(new Intent(getContext(), FeedbackActivity.class));
                    return;
                }
                if (item.getIndex() == 3) {
                    startActivity(new Intent(getContext(), AboutUsActivity.class));
                    return;
                }
                if (item.getIndex() == 4) {
                    logout();
                }
            }
        });
        mPresenter.getUserInfo();
    }

    @Override
    public void showUserInfo(String userName) {
        mViewName.setText(userName);
//        mViewSimId.setText(userInfo.getUserId());
//        if (!TextUtils.isEmpty(userInfo.getUserPoint())) {
//            Glide.with(getContext()).load(userInfo.getUserPoint()).into(mViewHead);
//        }
    }

    @Override
    public void logoutSuccess() {
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MineContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    private void logout() {
        new AlertDialog.Builder(getContext()).setMessage("确定要退出当前账号吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.logout();
                    }
                }).setNegativeButton("取消", null).create().show();
    }
}
