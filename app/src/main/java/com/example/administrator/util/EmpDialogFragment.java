package com.example.administrator.util;

import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.baidumap.R;
import com.example.administrator.models.Deptidname;
import com.example.administrator.models.Empidname;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class EmpDialogFragment extends DialogFragment {
    private RecyclerView mListView;
    private DialogListViewAdapter adapter;
    private DialogListViewAdapter.OnItemClickListener onItemClickListener;


    private static String UPGRADE_INFO="dept";

    public static EmpDialogFragment newInstance(String title,ArrayList<Deptidname> list) {
        EmpDialogFragment dialog = new EmpDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(UPGRADE_INFO, list);
        bundle.putString("Title",title);
        dialog.setArguments(bundle);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        if (window == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        Bundle bundle =getArguments();
        if (bundle==null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        ArrayList<Deptidname> list = (ArrayList<Deptidname>) bundle.getSerializable(UPGRADE_INFO);
        String string = bundle.getString("Title");
        View view = inflater.inflate(R.layout.activity_dialog_fragment, (ViewGroup) window.findViewById(android.R.id.content),false);
        mListView = view.findViewById(R.id.recycler_view);
        TextView title = view.findViewById(R.id.text_title);
        title.setText(string);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DialogListViewAdapter(list);
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
        return view;
    }

    public void setOnItemClickListener(DialogListViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
