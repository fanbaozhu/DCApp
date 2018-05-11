package com.xunchijn.dcappv1.event.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.event.adapter.ReportSettingAdapter;
import com.xunchijn.dcappv1.event.adapter.ReportSettingAdapter.OnItemClickListener;
import com.xunchijn.dcappv1.event.model.SettingItem;

import java.util.List;

public class SettingItemDialog extends DialogFragment {
    private OnItemClickListener mItemClickListener;
    private List<SettingItem> mList;
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.dialog_select, (ViewGroup) window.findViewById(android.R.id.content),
                false);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView mViewTitle = view.findViewById(R.id.text_title);
        RecyclerView mViewSelects = view.findViewById(R.id.recycler_view_selects);
        if (!TextUtils.isEmpty(mTitle)) {
            mViewTitle.setText(mTitle);
        }
        if (mList == null) {
            return;
        }
        if (mList.size() > 6) {
            WindowManager manager = getActivity().getWindowManager();
            int height = manager.getDefaultDisplay().getHeight();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height / 3);
            mViewSelects.setLayoutParams(params);
        }
        mViewSelects.setLayoutManager(new LinearLayoutManager(getContext()));
        ReportSettingAdapter adapter = new ReportSettingAdapter(mList,R.layout.dialog_setting);
        mViewSelects.setAdapter(adapter);
        adapter.setItemClickListener(mItemClickListener);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setList(List<SettingItem> list) {
        mList = list;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
