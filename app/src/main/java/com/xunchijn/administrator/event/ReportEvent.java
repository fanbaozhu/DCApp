package com.xunchijn.administrator.event;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xunchijn.administrator.MainActivity;
import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.map.MapActivity;
import com.xunchijn.administrator.models.AssMainModel;
import com.xunchijn.administrator.models.DCApplication;
import com.xunchijn.administrator.models.SpinnerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class ReportEvent extends ListActivity implements AbsListView.OnScrollListener {

    private Spinner spnCompany;
    private Spinner spnEvent;
    private Spinner spnAssessment;
    private Spinner spnAssessmentProject;
    //private Spinner spnRoadType;
//private Spinner spnToScore;
    private EditText etAddress;
    private EditText etRemark;
    private ImageView ivPhoto;
    private ImageView ivPhoto2;
    private TextView tvHistory;
    private TextView tvSubmit;
    private TextView tvPicture;
    private TextView tvCamera;
    private ImageButton ibtnMap;
    private TextView tvOut;
    private RequestQueue rQueue;
    private TextView tvLon;
    private TextView tvLat;
    private int LOCRQUEST = 1;
    private File outputImage;
//private EditText etToScore;

    /*
    使用相机拍照获取照片
     */
    public static final int TAKE_PHOTO = 1;

    /*
    使用相册选择照片
     */
    public static final int CHOOSE_PHOTO = 2;

    /*
    获取到的图片路径
     */
    private String picPath1 = "";
    private String picPath2 = "";
    private String toScore = "";
    private String kouScore = "";
    String filename = "";
    DCApplication application;
    private AssMainModel assMainModel;
    private Uri photoUri;
    private String fileend = "";
    private String filePathstart, filePathstartPress;
    private File cameraFilestart;
    private DrawerLayout dlStart;
    private ListView listView;
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount = 9;       // 当前窗口可见项总数
    private ListViewAdapter adapter;
    private int totalCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_event);
        spnCompany = (Spinner) findViewById(R.id.spnCompany);
        spnEvent = (Spinner) findViewById(R.id.spnEvent);
        spnAssessment = (Spinner) findViewById(R.id.spnAssessment);
//        spnAssessmentProject = (Spinner) findViewById(R.id.spnAssessmentProject);
        //spnRoadType = (Spinner) findViewById(R.id.spnRoadType);
        //spnToScore = (Spinner) findViewById(R.id.spnToScore);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etRemark = (EditText) findViewById(R.id.etRemark);
        //etToScore = (EditText) findViewById(R.id.etToScore);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        ivPhoto2 = (ImageView) findViewById(R.id.ivPhoto2);
        tvHistory = (TextView) findViewById(R.id.tvHistory);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        tvPicture = (TextView) findViewById(R.id.tvPicture);
        tvCamera = (TextView) findViewById(R.id.tvCamera);
        ibtnMap = (ImageButton) findViewById(R.id.ibtnMap);
        tvOut = (TextView) findViewById(R.id.tvOut);
        tvLon = (TextView) findViewById(R.id.tvLon);
        tvLat = (TextView) findViewById(R.id.tvLat);
        rQueue = Volley.newRequestQueue(getApplicationContext());
        dlStart = (DrawerLayout) findViewById(R.id.dlStart);

        application = (DCApplication) getApplication();
        assMainModel = new AssMainModel();
        assMainModel.setCreateUserId(application.getUserName());
        listView = getListView();

        // 绑定公司
        BoundCompany();
        //绑定事件类型
        BoundEvent();

        // 初始化listview
        initAdapter();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("position", String.valueOf(position));
                Intent i = new Intent(ReportEvent.this, Historicaldetails.class);
                final TextView txtID = (TextView) view.findViewById(R.id.txtID);
                Log.e("aaaaaaaa", txtID.getText().toString());
                i.putExtra("ID", txtID.getText());
                startActivity(i);
            }
        });

        //绑定测评类型、项目下拉选单
        spnCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("position", String.valueOf(position));
                SpinnerData p = (SpinnerData) ((Spinner) findViewById(R.id.spnCompany)).getSelectedItem();
                String value = p.getValue();

                GetEvaluationType(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportEvent.this, MainActivity.class);
                startActivity(i);
            }
        });

        /*
        选择相册
         */
        tvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ivPhoto.getDrawable() == null || ivPhoto2.getDrawable() == null) {
                    if (ContextCompat.checkSelfPermission(ReportEvent.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ReportEvent.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        openAlbum();
                    }
                } else {
                    Toast.makeText(ReportEvent.this, "只能选择两张图片", Toast.LENGTH_LONG).show();
                }
            }
        });

        ibtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportEvent.this, MapActivity.class);
                i.putExtra("locStr", etAddress.getText());
                //i.putExtra("lon",tvLon.getText());
                startActivityForResult(i, 3);
            }
        });
        /*
        选择拍照
         */
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SDState = Environment.getExternalStorageState();
                if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    filename = String.valueOf(System.currentTimeMillis());
                    if (currentapiVersion < 24) {
                        filePathstart = Environment.getExternalStorageDirectory() + "/file_" + filename + ".jpg";
                        Log.e("aaaaaaaaaaaaaaa", filename);
                        cameraFilestart = new File(filePathstart);
                        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFilestart));
                        startActivityForResult(in, 1);
                    } else {
                        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // filePathstart = Environment.getExternalStorageDirectory()+"/"+"KHTTEMP.jpg";
                        filePathstart = Environment.getExternalStorageDirectory() + "/file_" + filename + ".jpg";
                        cameraFilestart = new File(filePathstart);
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put(MediaStore.Images.Media.DATA, cameraFilestart.getAbsolutePath());
                        Uri uri = getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        in.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(in, 1);
                    }
                } else {
                    //ToastUtil.show(this,"内存卡不存在");
                    Toast.makeText(ReportEvent.this, "内存卡不存在", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        上传事件
         */

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etAddress.getText() == null || etAddress.getText().equals("")) {
                    Toast.makeText(ReportEvent.this, "请选择位置！", Toast.LENGTH_LONG).show();
                    return;
                }
                if (picPath1 == null || picPath1.equals("")) {
                    Toast.makeText(ReportEvent.this, "请先进行拍照！", Toast.LENGTH_LONG).show();
                    return;
                }

                assMainModel.setAssCode("ASS" + DataHelper.GetDate("yyyyMMddHHmmssSSS"));
                assMainModel.setAssType(((SpinnerData) (spnAssessment).getSelectedItem()).getValue());
                String item = "";
                if (((spnCompany).getSelectedItem()) != null) {
                    item = ((SpinnerData) (spnCompany).getSelectedItem()).getValue();
                }
                assMainModel.setAssObj(item);
                String empcode = "";
                if (((spnEvent).getSelectedItem()) != null) {
                    empcode = ((SpinnerData) (spnEvent).getSelectedItem()).getValue();
                }

                assMainModel.setAssEmpcode(empcode);

                assMainModel.setAssLat(tvLat.getText().toString());
                assMainModel.setAssLon(tvLon.getText().toString());
                assMainModel.setAddr(etAddress.getText().toString());
                String isSendEvent = "0";
                assMainModel.setIsSendEvent(isSendEvent);
                assMainModel.setRemark(etRemark.getText().toString());
                assMainModel.setAssImg1("");
                assMainModel.setAssImg2("");
                assMainModel.setAssStutas("0");
                assMainModel.setDeleteFlag("0");
                assMainModel.setCreateDate(DataHelper.GetDate("yyyy-MM-dd HH:mm:ss"));


                // 上传图片
                UploadUtil upload = new UploadUtil();

                // 上传第一张图片
                //Log.e("picPath1",picPath1);

                if (picPath1 != null && (!picPath1.equals(""))) {
                    String uploadUrl = ReportEvent.this.getString(R.string.requestUrl) + "ImgUpload?fileName=" + assMainModel.getAssCode() + "1";
                    File file = new File(picPath1);
                    upload.uploadFile(file, "picPath1", uploadUrl, null);
                    Log.e("12369874", file.toString());
                    assMainModel.setAssImg1(assMainModel.getAssCode() + "1" + picPath1.substring(picPath1.lastIndexOf(".")));
                }
                // 上传第二张图片
                if (picPath2 != null && (!picPath2.equals(""))) {
                    String uploadUrl = ReportEvent.this.getString(R.string.requestUrl) + "ImgUpload?fileName=" + assMainModel.getAssCode() + "2";
                    File file = new File(picPath2);
                    upload.uploadFile(file, "picPath2", uploadUrl, null);

                    assMainModel.setAssImg2(assMainModel.getAssCode() + "2" + picPath2.substring(picPath2.lastIndexOf(".")));
                }
                // 新增数据
                String url = ReportEvent.this.getString(R.string.requestUrl) + "InsertAssMain";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {


                                    if (response.equals("1")) {
                                        Toast.makeText(ReportEvent.this, "事件上报成功！", Toast.LENGTH_LONG).show();
                                        onCreate(null);

                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ReportEvent.this, "无法连接网络，请仔细检查网络设置！", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("ASS_CODE", assMainModel.getAssCode());
                        //map.put("ASS_EVENT", assMainModel.getAssType());
                        map.put("ASS_TYPE", assMainModel.getAssType().equals("") ? "-1" : assMainModel.getAssType());
                        map.put("ASS_PROJECT", assMainModel.getAssObj().equals("") ? "-1" : assMainModel.getAssObj());
                        map.put("IS_SENDEVENT", assMainModel.getIsSendEvent());
                        map.put("ASS_LON", assMainModel.getAssLon());
                        map.put("ASS_LAT", assMainModel.getAssLat());
                        map.put("ASS_IMG1", assMainModel.getAssImg1().equals("") ? "-1" : assMainModel.getAssImg1());
                        map.put("ASS_IMG2", assMainModel.getAssImg2().equals("") ? "-1" : assMainModel.getAssImg2());
                        map.put("CREATE_USERID", assMainModel.getCreateUserId());
                        map.put("REMARK", assMainModel.getRemark().equals("") ? "-1" : assMainModel.getRemark());
                        map.put("ASS_ADDRESS", etAddress.getText().toString());
                        map.put("ASS_OBJ", assMainModel.getAssEmpcode().equals("") ? "-1" : assMainModel.getAssEmpcode());
                        //map.put("KOU_SCORE", assMainModel.getAssScore().equals("") ? "-1" : assMainModel.getAssScore());
                        //map.put("ROAD_TYPE", assMainModel.getRoadType().equals("") ? "-1" : assMainModel.getRoadType());
                        //map.put("ASS_ROAD", assMainModel.getAssRoad().equals("") ? "-1" : assMainModel.getAssRoad());
                        Log.e("map", map.toString());
                        return map;
                    }
                };

                rQueue.add(stringRequest);

            }
        });

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlStart.openDrawer(Gravity.LEFT);
                // 初始化listview
                initAdapter();
                //loadData(adapter.getCount());
                adapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                listView.setSelection(visibleLastIndex - visibleItemCount + 1); //设置选中项
            }
        });


    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        String userName = application.getUserName();
        // 获取资料总笔数
        String totalUrl = this.getString(R.string.requestUrl) + "GetEventInfoCount?userName=" + userName;

        StringRequest countRequest = new StringRequest(totalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("")) {
                        } else {
                            totalCount = Integer.parseInt(s);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        rQueue.add(countRequest);

        String url = this.getString(R.string.requestUrl) + "GetEventInfo?userName=" + userName + "&countStart=0&countEnd=999";
        // Log.e("GetEventInfo",url);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null || response.equals("")) {
                            Log.e("response", "response  null");
                        } else {
                            Log.e("response", response);
                            BindData(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        rQueue.add(stringRequest);
    }

    // 查询绑定数据
    private void BindData(String response) {
        JSONArray json = null;
        try {
            ArrayList<AssMainModel> items = new ArrayList<AssMainModel>();
            json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObj = json.optJSONObject(i);

                AssMainModel assMainModel = new AssMainModel();
                assMainModel.setID(jsonObj.getString("ID"));
                assMainModel.setUpdateDate(jsonObj.getString("UPDATE_DATE"));
                assMainModel.setAssTypeName(jsonObj.getString("PROJECT_NAME"));
                assMainModel.setAssObjName(jsonObj.getString("OPTION_NAME"));
                assMainModel.setAssEventName(jsonObj.getString("REMARK"));
                assMainModel.setAssStatusName(jsonObj.getString("ASS_STATUSNM"));
                assMainModel.setAssStutas(jsonObj.getString("ASS_STATUS"));
                items.add(assMainModel);
            }

            adapter = new ListViewAdapter(ReportEvent.this, items);

            setListAdapter(adapter);

        } catch (JSONException e) {
            Log.e("TAG", "[JSON]" + response + " " + e.getMessage(), e);
        }
    }

    // 查询绑定数据
    private void BindDataHave(String response) {
        JSONArray json = null;
        try {
            Log.e("response", response);
            json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObj = json.optJSONObject(i);
                AssMainModel assMainModel = new AssMainModel();
                assMainModel.setID(jsonObj.getString("ID"));
                assMainModel.setUpdateDate(jsonObj.getString("UPDATE_DATE"));
                assMainModel.setAssTypeName(jsonObj.getString("PROJECT_NAME"));
                assMainModel.setAssObjName(jsonObj.getString("OPTION_NAME"));
                assMainModel.setAssEventName(jsonObj.getString("KAOHE_NAME"));
                assMainModel.setAssStatusName(jsonObj.getString("ASS_STATUSNM"));
                assMainModel.setAssStutas(jsonObj.getString("ASS_STATUS"));
                adapter.addItem(assMainModel);
            }
            setListAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            Log.i("LOADMORE", "loading...");
        }
    }

    /**
     * 加载数据
     */
    private void loadData(int count) {
        String userName = application.getUserName();
        Log.e("count", String.valueOf(count));
        Log.e("totalCount", String.valueOf(totalCount));
        Log.e("visibleItemCount", String.valueOf(visibleItemCount));
        Log.e("count+visibleItemCount", String.valueOf((count + visibleItemCount)));
        if (count < totalCount) {
            String url = "http://124.133.27.150:8086/API/tmd/GetEventInfo?userName=" + userName + "&countStart="
                    + String.valueOf(count) + "&countEnd=" + String.valueOf((count + visibleItemCount));
            StringRequest stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            BindDataHave(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", error.getMessage(), error);

                }
            });

            rQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "已全部加载完成！", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 区域下拉列表绑定
     */
    private void BoundCompany() {
        final List<SpinnerData> listSpinner = new ArrayList<SpinnerData>();

        String url = this.getString(R.string.requestUrl) + "GetCompany";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("")) {
                            spnCompany.setAdapter(null);
                        } else {
                            try {
                                JSONArray array = new JSONArray(s);

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);

                                    SpinnerData data = new SpinnerData(jsonObject.getString("MANAGE_CODE"), jsonObject.getString("OPTION_NAME"));
                                    listSpinner.add(data);
                                }
                                ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<SpinnerData>(ReportEvent.this, android.R.layout.simple_spinner_item, listSpinner);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                                spnCompany.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        rQueue.add(stringRequest);
    }

    /**
     * 道路下拉列表绑定
     */
    private void BoundEvent() {
        final List<SpinnerData> listSpinner = new ArrayList<SpinnerData>();

        String url = this.getString(R.string.requestUrl) + "GetKAOHEItem";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("")) {
                            spnEvent.setAdapter(null);
                        } else {
                            try {
                                JSONArray array = new JSONArray(s);

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);

                                    SpinnerData data = new SpinnerData(jsonObject.getString("PROJECT_CODE"), jsonObject.getString("PROJECT_NAME"));
                                    listSpinner.add(data);
                                }

                                ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<SpinnerData>(ReportEvent.this, android.R.layout.simple_spinner_item, listSpinner);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                                spnEvent.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        rQueue.add(stringRequest);
    }

    /**
     * 测评类型绑定
     */
    private void GetEvaluationType(String kaohetype) {
        final List<SpinnerData> listSpinner = new ArrayList<SpinnerData>();

        String url = this.getString(R.string.requestUrl) + "GetKAOHEType?kaohetype=" + kaohetype;

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("")) {
                            spnAssessment.setAdapter(null);
                        } else {
                            try {
                                JSONArray array = new JSONArray(s);

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);

                                    SpinnerData data = new SpinnerData(jsonObject.getString("KAOHE_CODE"), jsonObject.getString("KAOHE_NAME"));
                                    listSpinner.add(data);
                                }

                                ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<SpinnerData>(ReportEvent.this, android.R.layout.simple_spinner_item, listSpinner);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                                spnAssessment.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        rQueue.add(stringRequest);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "请打开权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (cameraFilestart != null) {
                    Bitmap bitmap = null;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 7;//直接设置它的压缩率，表示1/2
                    filePathstartPress = Environment.getExternalStorageDirectory() + "/newfiles_" + System.currentTimeMillis() + ".jpg";
                    FileOutputStream fos = null;
                    try {
                        bitmap = BitmapFactory.decodeFile(filePathstart, options);
                        fos = new FileOutputStream(filePathstartPress);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
                        fos.flush();
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (bitmap != null) {
                        if (ivPhoto.getDrawable() == null) {
                            picPath1 = filePathstartPress;
                            ivPhoto.setImageBitmap(bitmap);
                        } else {
                            picPath2 = filePathstartPress;
                            ivPhoto2.setImageBitmap(bitmap);
                        }
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4及以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;

            case 3:
                if (resultCode == RESULT_OK) {
                    String lat = data.getStringExtra("locLat");
                    String lon = data.getStringExtra("locLon");
                    String add = data.getStringExtra("locStr");
                    // Log.e("add",add);
                    Log.e("locLat", lat);
                    Log.e("locLon", lon);

                    if (lat != "" && lat != null && lon != "" && lon != null) {
                        tvLat.setText(lat);
                        tvLon.setText(lon);
                        // tvAddr.setText(add);
                        ((EditText) findViewById(R.id.etAddress)).setText(add);

                    }

                } else {
                    //Toast.makeText(this, "您还未选择位置，请选择！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docID = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docID.split(":")[1];//解析出数字格式的ID
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docID));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri,则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            //读取图像文件内容
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (picPath1 == null || picPath1.equals("")) {
                picPath1 = imagePath;
                Log.e("222222", picPath1.toString());
            } else if (picPath2 == null || picPath2.equals("")) {
                picPath2 = imagePath;
            }

            if (ivPhoto.getDrawable() == null) {
                ivPhoto.setImageBitmap(bitmap);
            } else if (ivPhoto2.getDrawable() == null) {
                ivPhoto2.setImageBitmap(bitmap);
            }
        } else {
            Toast.makeText(this, "图片未找到", Toast.LENGTH_SHORT).show();
        }
    }


    public static String savaPhotoToLocal(Intent data, Bitmap btp) {
        Log.e("进入保存方法", "进入保存方法");
        // 如果文件夹不存在则创建文件夹，并将bitmap图像文件保存
        File rootdir = Environment.getExternalStorageDirectory();
        String imagerDir = rootdir.getPath() + "/KHT";
        Log.e("imagerDir", imagerDir);
        File dirpath = new File(imagerDir);

        if (!dirpath.exists()) {
            dirpath.mkdir();
        }

        String filename = System.currentTimeMillis() + ".jpg";
        File tempFile = new File(dirpath, filename);
        String filePath = tempFile.getAbsolutePath();
        try {
            // 将bitmap转为jpg文件保存
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            btp.compress(Bitmap.CompressFormat.JPEG, 100, fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public static int reckonThumbnail(int oldWidth, int oldHeight, int newWidth, int newHeight) {
        if ((oldHeight > newHeight && oldWidth > newWidth)
                || (oldHeight <= newHeight && oldWidth > newWidth)) {
            int be = (int) (oldWidth / (float) newWidth);
            if (be <= 1)
                be = 1;
            return be;
        } else if (oldHeight > newHeight && oldWidth <= newWidth) {
            int be = (int) (oldHeight / (float) newHeight);
            if (be <= 1)
                be = 1;
            return be;
        }
        return 1;
    }

    public static Bitmap PicZoom(Bitmap bmp, int width, int height) {
        int bmpWidth = bmp.getWidth();
        int bmpHeght = bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((float) width / bmpWidth, (float) height / bmpHeght);

        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeght, matrix, true);
    }
}
