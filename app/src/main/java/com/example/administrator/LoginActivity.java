package com.example.administrator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.baidumap.R;
import com.example.administrator.models.DCApplication;
import com.example.administrator.models.UpdateApk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class LoginActivity extends AppCompatActivity {
    String userName="";
    String passWord="";
    private EditText etName;
    private EditText etPassword;
    private Button btnSubmit;
    private CheckBox cbRemberPassword;
    private SharedPreferences sp;
    private boolean ischecked=true;

    //启动第一个Activity
//    protected void onStart(){
//        System.out.println("FirstAcvity --->onStart");
//        super.onStart();
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName=(EditText)findViewById(R.id.etName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSubmit=(Button) findViewById(R.id.btnSubmit);
        cbRemberPassword=(CheckBox)findViewById(R.id.cbRemberPassword);
        sp=this.getSharedPreferences("userInfo", this.MODE_PRIVATE);

        userName=sp.getString("userName","");
        passWord=sp.getString("passWord","");
        ischecked=sp.getBoolean("isChecked", false);

        if(userName!=null&&passWord!=null)
        {
            Log.e("userName",userName);

            if (ischecked)
            {
                if (!userName.equals(""))
                {
                    etName.setText(userName);
                }

                if (!passWord.equals(""))
                {
                    etPassword.setText(passWord);
                }

                cbRemberPassword.setChecked(true);
            }
            else
            {
                cbRemberPassword.setChecked(false);
            }
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            RequestQueue rQueue= Volley.newRequestQueue(getApplicationContext());
            @Override
            public void onClick(View view) {
                userName=etName.getText().toString();
                passWord=etPassword.getText().toString();
                if(userName.trim().equals("")||passWord.trim().equals("")){
                    Toast.makeText(LoginActivity.this,"请输入用户名和密码!",Toast.LENGTH_LONG).show();
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginActivity.this.getString(R.string.requestUrl) + "CheckUserPass",
                            new Response.Listener<String>(){
                                //判断用户名密码
                                public void onResponse(String response){
                                    if(response.equals("1")){
                                        Toast.makeText(LoginActivity.this,"用户名密码不正确，请重新输入！",Toast.LENGTH_LONG).show();
                                    }else if(response.equals("0")){
                                        //记录帐号密码
                                        DCApplication application = (DCApplication)getApplication();
                                        application.setUserName(userName);
                                        application.setPassWord(passWord);
                                        //判断是否记住密码
                                        if(cbRemberPassword.isChecked()){
                                            Log.e("判断是否记住密码","记住密码");
                                            SharedPreferences.Editor editor = sp.edit();
                                            editor.putString("userName",userName);
                                            editor.putString("passWord",passWord);
                                            editor.putBoolean("isChecked",true);
                                            editor.commit();
                                        }else{
                                            Log.e("判断是否记住密码","不记住密码");
                                            SharedPreferences.Editor editor = sp.edit();
                                            editor.putString("userName",userName);
                                            editor.putString("passWord",passWord);
                                            editor.putBoolean("isChecked",false);
                                            editor.commit();
                                        }
                                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(LoginActivity.this,"未知错误，请联系管理员！",Toast.LENGTH_LONG).show();
                                    }
                                }
                            },new Response.ErrorListener(){
                        public void onErrorResponse(VolleyError error){
                            Toast.makeText(LoginActivity.this,"无法连接网络，请检查网络设置！",Toast.LENGTH_LONG).show();
                        }
                    }){
                        protected Map<String, String> getParams()
                        {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("userName",userName);
                            map.put("passWord",passWord);
                            Log.e("map",map.toString());
                            return map;
                        }
                    };
                    rQueue.add(stringRequest);
                }
            }
        });
    }



    //判断是否存在新版本
    private void GetNewVersion(){
        Log.e("进入GetNewVersion","进入GetNewVersion");
        UpdateApk updateapk = new UpdateApk();
        updateapk.GetContext(this);
        updateapk.CheckVersion();
    }
}
