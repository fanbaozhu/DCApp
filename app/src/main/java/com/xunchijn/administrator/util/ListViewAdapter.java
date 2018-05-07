package com.xunchijn.administrator.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.models.Deptidname;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Deptidname> items;
    private TextView tvName;

    public ListViewAdapter(Context context, List<Deptidname> items) {
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.activity_list_adapter, null);
        }

        Deptidname deptidname = items.get(i);
        tvName = view.findViewById(R.id.tvName);
        tvName.setText(deptidname.getDept());

        return view;
    }
}
