package com.xunchijn.administrator.models;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xunchijn.administrator.baidumap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Administrator on 2017/10/16.
 */
public class UpdateApk {
    //版本号
    private String versionCode="";
    //版本名
    private String versionName="";
    //新版本号
    private String newversionCode="";
    //新版本名
    private String newversionName="";
    //下载地址
    private String downloadUrl="";
    //volley框架的请求队列方法
    private RequestQueue rQueue;
    //进度条对话框
    private ProgressDialog pBar;

    private Context context;

    //构造函数
    public void GetContext (Context contextp){
        context=contextp;
        rQueue= Volley.newRequestQueue(contextp.getApplicationContext());
        try{
            versionCode= String.valueOf(context.getPackageManager().getPackageInfo("xunchijn.cgapp",0).versionCode);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        try{
            versionName= String.valueOf(context.getPackageManager().getPackageInfo("xunchijn.cgapp",0).versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在新版本
     */
    public void CheckVersion(){
        Log.e("进入版本验证","进入版本验证");
        Log.e("versionCode",versionCode);
        Log.e("versionName",versionName);

        //获取服务器版本信息及apk现在地址
        String url = context.getString(R.string.requestUrl)+"GetVersionInfo";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response != null && !response.equals("")) {
                                Log.e("response", response);
                                JSONObject jsonObject = new JSONObject(response);

                                newversionCode = jsonObject.getString("verCode");
                                newversionName = jsonObject.getString("verName");
                                downloadUrl = jsonObject.getString("downurl");

                                if ((!versionCode.equals("")) && (!newversionCode.equals(""))) {
                                    if (Integer.parseInt(newversionCode) > Integer.parseInt(versionCode)) {
                                        Log.e("更新", "存在新版本");
                                        UpdateVersion();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(context, "无法连接网络，请检查网络设置！", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(),error);
                    }
                });
            rQueue.add(stringRequest);
        }
    /**
     * 更新APK
     */
    public void UpdateVersion(){
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(versionName);
        sb.append("Code:");
        sb.append(versionCode+"\n");
        sb.append("发现新版本:");
        sb.append(newversionName);
        sb.append("Code");
        sb.append(newversionCode);
        sb.append(",是否更新?");
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle("软件更新")
                //设置内容
                .setMessage(sb.toString())
                .setPositiveButton("更新",//设置确定按钮
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which){
                                //点击更新后下载
                                pBar = new ProgressDialog(context);
                                pBar.setTitle("正在下载");
                                pBar.setMessage("请稍候...");
                                //设置进度条风格
                                pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                pBar.show();
                                DownAPK();
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                //点击取消后退出程序
                                dialog.cancel();
                                //强制关闭
                                System.exit(0);
                            }
                        }).create();//创建
        //显示对话框
        dialog.show();
    }

    /**
     * 下载最新APK
     */

    public void DownAPK(){
        Log.e("downloadUrl",downloadUrl);
        new Thread(){
            public void run(){
                pBar.setCanceledOnTouchOutside(false);
                // 获取扩展存储设备的文件目录
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    try{
                        URL url = new URL(downloadUrl);
                        //打开链接
                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                        conn.setConnectTimeout(5000);
                        //获取到文件的大小
                        pBar.setMax(conn.getContentLength());
                        InputStream is = conn.getInputStream();
                        //获取外部存储
                        File file = new File(Environment.getExternalStorageDirectory(),"update.apk");
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;
                        int total=0;
                        while((len = bis.read(buffer))!=-1){
                            fos.write(buffer,0,len);
                            total+=len;

                            //获取当前下载量
                            pBar.setProgress(total);
                        }
                        fos.close();
                        bis.close();
                        is.close();
                        InstallApk(file);
                        //结束关闭进度条对话框
                      pBar.dismiss();
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }catch (FileNotFoundException e){
                        pBar.dismiss();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //安装APK
    protected void InstallApk(File file){
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
