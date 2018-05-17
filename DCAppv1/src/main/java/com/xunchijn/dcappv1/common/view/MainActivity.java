package com.xunchijn.dcappv1.common.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.util.PreferHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mNavigationBar;
    private PreferHelper mPreferHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferHelper = new PreferHelper(this);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        initNavigationBar();

        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mainFragment)
                .show(mainFragment)
                .commit();
    }

    private void initNavigationBar() {
        mNavigationBar = findViewById(R.id.bottom_navigation_bar);

        mNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorBlue)
                .setInActiveColor(R.color.colorGray)
                .setBarBackgroundColor(R.color.colorWhite);

        mNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_mine, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void logout() {
        new AlertDialog.Builder(this).setMessage("确定要退出当前账号吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPreferHelper.saveUserAccount(null);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                }).setNegativeButton("取消", null).create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
