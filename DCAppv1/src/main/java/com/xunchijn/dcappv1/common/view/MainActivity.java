package com.xunchijn.dcappv1.common.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.TitleFragment;
import com.xunchijn.dcappv1.statistic.StatisticActivity;
import com.xunchijn.dcappv1.event.view.HistoryActivity;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private View mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("大厂APP", true, true);
        titleFragment.setLeftDrawableId(R.mipmap.ic_title_user);
        titleFragment.setRightDrawableId(R.mipmap.ic_title_search_64);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
        titleFragment.setConfirmListener(new TitleFragment.OnConfirmListener() {
            @Override
            public void onBack() {
                mDrawerLayout.openDrawer(mNavigationView);
            }

            @Override
            public void onConfirm() {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        initTitle();

        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mainFragment)
                .show(mainFragment)
                .commit();

        initSlideView();
    }

    private void initSlideView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.slide_view);

        ImageView close = findViewById(R.id.image_close);
        close.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mNavigationView);
            }
        });

        ImageView userHead = findViewById(R.id.image_user_head);
        userHead.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue));

        TextView eventHistory = findViewById(R.id.text_event_history);
        eventHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mNavigationView);
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        TextView statistic = findViewById(R.id.text_statistic);
        statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mNavigationView);
                startActivity(new Intent(MainActivity.this, StatisticActivity.class));
            }
        });
    }

}
