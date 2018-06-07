package com.xunchijn.tongshan.common.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.common.presenter.MinePresenter;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private FragmentManager mFragmentManager;
    private MainFragment mMainFragment;
    private MineFragment mMineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        initNavigationBar();

        mMainFragment = new MainFragment();
        mMineFragment = new MineFragment();
        new MinePresenter(mMineFragment,this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.layout_container, mMainFragment)
                .add(R.id.layout_container, mMineFragment)
                .hide(mMineFragment)
                .show(mMainFragment)
                .commit();
    }

    private void initNavigationBar() {
        BottomNavigationBar mNavigationBar = findViewById(R.id.bottom_navigation_bar);

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
        if (position == 1) {
            mFragmentManager.beginTransaction().hide(mMainFragment).show(mMineFragment).commit();
        } else {
            mFragmentManager.beginTransaction().hide(mMineFragment).show(mMainFragment).commit();
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
