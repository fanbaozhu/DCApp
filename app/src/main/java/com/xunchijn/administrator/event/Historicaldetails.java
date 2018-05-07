package com.xunchijn.administrator.event;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xunchijn.administrator.baidumap.R;
import com.xunchijn.administrator.models.AssMainModel;
import com.xunchijn.administrator.models.DCApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/10/24.
 */

public class Historicaldetails extends AppCompatActivity {


    private EditText spnRoad;
    private EditText spnEvaluationType;
    private EditText spnAssessmentProject;
    //private EditText spnToScore;
    private EditText etAddress;
    private EditText etRemark;
    private ImageView ivPhoto;
    private ImageView ivPhoto2;
    private TextView tvRetnrn;

    //private ImageButton ibtnPicture;
    //private ImageButton ibtnCamera;

    private RequestQueue rQueue;


    /*
    使用相机拍照获取照片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;

    /*
    获取到的图片路径
     */
    private String picPath1="";
    private String picPath2="";
    DCApplication application;
    private AssMainModel assMainModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_details);
        //spnRegion = (EditText) findViewById(R.id.spnRegion);
        spnRoad = (EditText) findViewById(R.id.spnRoad);
        spnEvaluationType = (EditText) findViewById(R.id.spnEvaluationType);
        spnAssessmentProject = (EditText) findViewById(R.id.spnAssessmentProject);
        //spnToScore = (EditText) findViewById(R.id.spnToScore);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etRemark = (EditText) findViewById(R.id.etRemark);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        ivPhoto2 = (ImageView) findViewById(R.id.ivPhoto2);
        tvRetnrn = (TextView) findViewById(R.id.tvRetnrn);
        //ibtnSubmit = (ImageButton) findViewById(R.id.ibtnSubmit);
        //ibtnPicture = (ImageButton) findViewById(R.id.ibtnPicture);
        //ibtnCamera = (ImageButton) findViewById(R.id.ibtnCamera);
        rQueue = Volley.newRequestQueue(getApplicationContext());

        application=(DCApplication)getApplication();
        assMainModel =new AssMainModel();
        assMainModel.setCreateUserId(application.getUserName());

        BindModifyInfo();

        tvRetnrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Historicaldetails.this,ReportEvent.class);
                startActivity(i);
            }
        });

    }
    private void BindModifyInfo()
    {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle!=null) {
            String id = bundle.getString("ID");
            Log.e("huiyizongxiangku",id);
            if (id != null && (!id.equals(""))) {
                //ibtnSubmit.setEnabled(false);
                //ibtnPicture.setEnabled(false);
                //ibtnCamera.setEnabled(false);
                //ibtnSubmit.getDrawable().setAlpha(50);
//                ibtnPicture.getDrawable().setAlpha(50);
//                ibtnCamera.getDrawable().setAlpha(50);

                // 根据ID查询单笔资料
                // 新增数据
                String url = Historicaldetails.this.getString(R.string.requestUrl) + "GetEventInfoByKey?id=" + id;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("response", response);
                                if (response != null) {
                                    try {
                                        JSONArray array = new JSONArray(response);
                                        JSONObject jobj = array.optJSONObject(0);

                                        //spnRegion.setText(jobj.getString("PROJECT_NAME"));
                                        spnRoad.setText(jobj.getString("PROJECT_NAME"));
                                        spnEvaluationType.setText(jobj.getString("OPTION_NAME"));
                                        spnAssessmentProject.setText(jobj.getString("KAOHE_NAME"));
                                        //spnToScore.setText(jobj.getString("KOU_SCORE"));




                                        // 绑定是否同步事件
//                                        if (jobj.getString("IS_SENDEVENT").equals("0")) {
//                                            radioAll.check(R.id.radioShi);
//                                        } else {
//                                            radioAll.check(R.id.radioFou);
//                                        }

                                        // 绑定经纬度
                                        //tvLat.setText(jobj.getString("ASS_LAT"));
                                       // tvLon.setText(jobj.getString("ASS_LON"));
                                        etAddress.setText(jobj.getString("ASS_ADDRESS"));
                                        etRemark.setText(jobj.getString("REMARK"));

                                        Log.e("jobj.get(\"ASS_IMG1\")",jobj.getString("ASS_IMG1").toString());

                                        // 显示图片1
                                        if (jobj.getString("ASS_IMG1")!=null&&!jobj.getString("ASS_IMG1").equals(""))
                                        {
                                            String path1=Historicaldetails.this.getString(R.string.imgurl)+jobj.getString("ASS_IMG1");
                                            picPath1=path1;
                                            ImageRequest imageRequest = new ImageRequest(path1,
                                                    new Response.Listener<Bitmap>() {
                                                        @Override
                                                        public void onResponse(Bitmap response) {
                                                            Log.e("response",response.toString());
                                                            ivPhoto.setImageBitmap(response);
                                                        }
                                                    }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });

                                            rQueue.add(imageRequest);
                                        }

                                        // 显示图片2
                                        if (jobj.getString("ASS_IMG2")!=null&&!jobj.getString("ASS_IMG2").equals(""))
                                        {
                                            String path2=Historicaldetails.this.getString(R.string.imgurl)+jobj.getString("ASS_IMG2");
                                            Log.e("path2",path2);
                                            ImageRequest imageRequest2 = new ImageRequest(path2,
                                                    new Response.Listener<Bitmap>() {
                                                        @Override
                                                        public void onResponse(Bitmap response) {
                                                            Log.e("response",response.toString());
                                                            ivPhoto2.setImageBitmap(response);
                                                        }
                                                    }, 0,0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });

                                            rQueue.add(imageRequest2);
                                        }

//                                        // 显示图片3
//                                        if (jobj.getString("ASS_IMG3")!=null&&!jobj.getString("ASS_IMG3").equals(""))
//                                        {
//                                            String path3=NewEvent.this.getString(R.string.imgurl)+jobj.getString("ASS_IMG3");
//                                            Log.e("path3",path3);
//                                            ImageRequest imageRequest3 = new ImageRequest(path3,
//                                                    new Response.Listener<Bitmap>() {
//                                                        @Override
//                                                        public void onResponse(Bitmap response) {
//                                                            Log.e("response",response.toString());
//                                                            img3.setImageBitmap(response);
//                                                        }
//                                                    }, 50, 50, Bitmap.Config.RGB_565, new Response.ErrorListener() {
//                                                @Override
//                                                public void onErrorResponse(VolleyError error) {
//
//                                                }
//                                            });
//
//                                            mQueue.add(imageRequest3);
//                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Historicaldetails.this, "无法连接网络，请检查网络设置！", Toast.LENGTH_LONG).show();
                            }
                        });

                rQueue.add(stringRequest);
            }
        }
    }
}
