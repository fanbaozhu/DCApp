package com.xunchijn.administrator.event;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.models.AssMainModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by Administrator on 2016/1/5.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<AssMainModel> items;
    private LayoutInflater inflater;
    private TextView text;
    private RequestQueue rQueue;
    private TextView txtID;
    private ListView listView;
//    private  AssMainModel assMainModel;
    private ImageButton ibtnDelete;

    public ListViewAdapter(Context context, List<AssMainModel> items) {
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rQueue= Volley.newRequestQueue(context);

        for (int i=0;i<items.size();i++)
        {
            AssMainModel assMainModel=(AssMainModel)items.get(i);

            if (!assMainModel.getAssStutas().equals("0"))
            {
                //ibtnDelete.setEnabled(false);
                //ibtnDelete.getDrawable().setAlpha(50);
            }
        }
    }

    // 获取当前行数
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_list_item, null);
        }
        ibtnDelete=(ImageButton)view.findViewById(R.id.ibtnDelete);
        AssMainModel assMainModel=items.get(position);
//        Log.e("getAssStutas()", assMainModel.getAssStutas().toString());
//        if (assMainModel.getAssStutas()!=null)
//        {
//            if (!assMainModel.getAssStutas().equals("0"))
//            {
//                Log.e("哈哈哈","进入判断！");
//                ibtnDelete.setEnabled(false);
//                ibtnDelete.getDrawable().setAlpha(50);
//                ListViewAdapter.this.notifyDataSetChanged();
//            }
//        }

        TextView txtMainDate=(TextView)view.findViewById(R.id.txtMainDate);
        txtMainDate.setText(assMainModel.getUpdateDate());

        txtID=(TextView)view.findViewById(R.id.txtID);
        txtID.setText(assMainModel.getID());

        ibtnDelete.setOnTouchListener(new MyOnTouchListener(ibtnDelete));

        text = (TextView) view.findViewById(R.id.txtItem);
        text.setText(assMainModel.getAssTypeName() + "-" + assMainModel.getAssObjName() + "-" + assMainModel.getAssEventName() + "-" + assMainModel.getAssStatusName());

        ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                new AlertDialog.Builder(v.getContext()).setTitle("删除提示框").setMessage("确认删除该数据？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                AssMainModel assMainModel = items.get(position);
                              //  if (!assMainModel.getAssStutas().equals("0")) {
                              //      Toast.makeText(v.getContext(), "已处理完成事项，不得删除！！", Toast.LENGTH_LONG).show();
                             //       return;
                            //    }
                                Log.e("assMainModel.getID()", assMainModel.getID());
                                // 删除资料
                                String url = v.getResources().getString(R.string.requestUrl).toString() + "AssmainDelete";
                                Log.e("url", url);
                                StringRequest deleteRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String s) {
                                                if (s.equals("")) {
                                                } else {
                                                    items.remove(position);

                                                    ListViewAdapter.this.notifyDataSetChanged();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("TAG", error.getMessage(), error);
                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        AssMainModel assMainModel = items.get(position);
                                        Map<String, String> map = new HashMap<String, String>();
                                        map.put("ID", assMainModel.getID());

                                        return map;
                                    }
                                };

                                rQueue.add(deleteRequest);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        return view;
    }

    /**
     * 添加列表项
     * @param item
     */
    public void addItem(AssMainModel item) {
        items.add(item);
    }
}
