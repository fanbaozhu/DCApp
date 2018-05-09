package com.xunchijn.dcappv1.event.view;

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
import com.xunchijn.dcappv1.event.adapter.SelectAdapter;
import com.xunchijn.dcappv1.event.adapter.SelectAdapter.OnItemClickListener;
import com.xunchijn.dcappv1.event.model.SelectItem;

import java.util.List;

public class SelectDialog extends DialogFragment {
    private OnItemClickListener mItemClickListener;
    private List<SelectItem> mList;
    private String mTitle;
    private TextView mViewTitle;

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
        mViewTitle = view.findViewById(R.id.text_title);
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
        SelectAdapter adapter = new SelectAdapter(mList, R.layout.adapter_select_dialog);
        mViewSelects.setAdapter(adapter);
        adapter.setItemClickListener(mItemClickListener);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setList(List<SelectItem> list) {
        mList = list;
    }

    public void refreshTitle(String title) {
        mViewTitle.setText(title);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
